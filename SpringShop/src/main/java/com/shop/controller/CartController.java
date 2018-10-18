package com.shop.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.service.CartService;

@Controller
@RequestMapping(value = "/main")
public class CartController {

	private CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/displayOffer")
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
	

	@RequestMapping(method=RequestMethod.GET, value = "/displayCart")
	public String displayCart(Model model) {
		model.addAttribute("orders", cartService.getSortedCart());
		model.addAttribute("totalPrice",cartService.computeTotalPriceOfCart());
		return "cartForm";
	}
	
	@RequestMapping(params = "order", method=RequestMethod.GET, value = "/displayCart")
	public String makeOrder(RedirectAttributes redirectAttributes,Model model){
		boolean message = cartService.makeOrder();
    	redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/main/displayOffer";
	}
	
	@RequestMapping(params = "clear", method = RequestMethod.GET, value = "/displayCart")
    public String clearCart() {
		cartService.clearCart();
        return "cartForm";
    }
	
    @RequestMapping(params = "back", method = RequestMethod.GET, value = "/displayCart")
    public String backToOffer(RedirectAttributes redirectAttributes) {
        return "redirect:/main/displayOffer";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/displayCart")
    public String deleteLineItem(@RequestParam("uniqueProductCode") String uniqueProductCode) {
    	cartService.removeItem(uniqueProductCode);
        return "redirect:/main/displayCart";
    }
}
