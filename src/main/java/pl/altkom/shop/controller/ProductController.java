package pl.altkom.shop.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.altkom.shop.model.Product;
import pl.altkom.shop.repo.ProductRepo;
import pl.altkom.shop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	static File imagesDir = new File("C:\\SzkolenieSpring\\phones");

	@Inject
	ProductRepo repo;
	@Inject
	ProductService service;

	@RequestMapping("/list")
	public String list(Model model, @RequestParam(required = false, value = "page") Integer page,
			@RequestParam(required = false, value = "orderBy") String orderBy) throws Exception {

		productsTable(model, page, orderBy);
		return "product/product-list";
	}

	@RequestMapping("/list/productsTable")
	public String productsTable(Model model, @RequestParam(required = false, value = "page") Integer page,
			@RequestParam(required = false, value = "orderBy") String orderBy) throws Exception {

		model.addAttribute("page", page);
		model.addAttribute("orderBy ", orderBy);

		List<Product> products = repo.getAll();
		model.addAttribute("products", products);

		return "product/product-list-prod-table";
	}

	@RequestMapping("/list.pdf")
	public String listAsPdf(@RequestParam(required = false, value = "page") Integer page, Model model)
			throws Exception {
		List<Product> products = repo.getAll();
		model.addAttribute("products", products);
		return "productPDFView";
	}

	@RequestMapping("/{id}/delete")
	public String delte(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) throws Exception {
		repo.delete(id);
		return "redirect:/product/list";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String prepareForm(Model model) throws Exception {
		Product product = new Product();
		product.setQuantity(1);
		product.setPrice(BigDecimal.TEN);
		model.addAttribute("product", product);
		return "product/product-form";
	}

	@RequestMapping(value = "/img/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] img(@PathVariable("id") Long id) throws Exception {
		String imgLocation = repo.find(id).getImgLocation();
		return imgLocation != null ? FileUtils.readFileToByteArray(new File(imgLocation)) : null;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute @Valid Product product, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) throws Exception {
		if (bindingResult.hasErrors()) {
			return "product/product-form";
		}
		File savedFile = saveFile(product.getFile());
		product.setImgLocation(savedFile.getAbsolutePath());
		if (product.getId() != null) {
			repo.update(product);
		} else {
			repo.insert(product);
		}
		redirectAttributes.addFlashAttribute("saved", true);
		return "redirect:/product/list";
	}

	private File saveFile(MultipartFile file) throws IOException {
		File savedFile;

		if (file.isEmpty()) {
			File[] listFiles = imagesDir.listFiles();
			savedFile = listFiles[new Random().nextInt(listFiles.length)];
		} else {
			savedFile = new File(imagesDir.getAbsolutePath() + File.separator + file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(), savedFile);
		}
		return savedFile;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) throws Exception {
		Product product = repo.find(id);
		model.addAttribute("product", product);
		return "product/product-form";
	}
}
