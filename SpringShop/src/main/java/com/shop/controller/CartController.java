package com.shop.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.service.CartService;

@Controller
@RequestMapping(path = "/main")
public class CartController {

	private CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping(path = "/displayOffer")
	public String addProductToCart(@Valid LineItemDTO lineItem,BindingResult errors,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "categoryName") Optional<String> categoryName,
			RedirectAttributes redirectAttributes,
			Model model){	
		
		if(errors.hasErrors())
			redirectAttributes.addFlashAttribute("error", "Amount must be a natural number");
		else {
			cartService.addItem(lineItem);
			redirectAttributes.addFlashAttribute("currentChosenName", lineItem.getName());
			redirectAttributes.addFlashAttribute("currentChosenAmount", lineItem.getAmount());
		}
		
		redirectAttributes.addAttribute("categoryName", categoryName.map(x -> x).orElse(""));
		redirectAttributes.addAttribute("page", page);
		return "redirect:/main/displayOffer";
	}
	

	@GetMapping(path = "/displayCart")
	public String displayCart(Model model) {
		model.addAttribute("orders", cartService.getSortedCart());
		model.addAttribute("totalPrice",cartService.computeTotalPriceOfCart());
		return "cartForm";
	}
	
	@GetMapping(params = "order", path = "/displayCart")
	public String makeOrder(RedirectAttributes redirectAttributes,Model model){
		boolean message = cartService.makeOrder();
    	redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/main/displayOffer";
	}
	
	@GetMapping(params = "clear", path = "/displayCart")
    public String clearCart() {
		cartService.clearCart();
        return "cartForm";
    }
	
	@GetMapping(params = "back", path = "/displayCart")
    public String backToOffer(RedirectAttributes redirectAttributes) {
        return "redirect:/main/displayOffer";
    }

	@DeleteMapping(path = "/displayCart")
    public String deleteLineItem(@RequestParam("uniqueProductCode") String uniqueProductCode) {
    	cartService.removeItem(uniqueProductCode);
        return "redirect:/main/displayCart";
    }
}
