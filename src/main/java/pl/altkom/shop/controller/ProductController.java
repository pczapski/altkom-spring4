package pl.altkom.shop.controller;

import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {

	@RequestMapping("/list")
	public void list(Writer writer, @RequestParam(required = false, value = "page") Integer page,
			@RequestParam(required = false, value = "orderBy") String orderBy) throws Exception {
		writer.write("Page: " + page);
		writer.write(" OrderBy: " + orderBy);
	}

}
