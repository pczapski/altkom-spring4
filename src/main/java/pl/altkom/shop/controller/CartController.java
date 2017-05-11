package pl.altkom.shop.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.altkom.shop.service.DocumentRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

	@RequestMapping
	public String list(Model model) throws Exception {
		return "cart";
	}

	@RequestMapping("/process")
	@ResponseBody
	public Long createSaleDocument(@RequestBody DocumentRequest request) throws Exception {
		return null;
	}
	

}
 