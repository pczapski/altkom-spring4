package pl.altkom.shop.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.altkom.shop.repo.ProductRepo;

@Controller
@RequestMapping("/")
public class CartController {
	@Inject
	ProductRepo repo;

	@RequestMapping("/cart")
	public String list(Model model) throws Exception {
		model.addAttribute("products", repo.getAll());
		return "cart";
	}

}
