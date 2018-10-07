package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.service.CartService;

@Controller
@RequestMapping(value = "/main/displayCart")
public class CartController {

	private CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String displayCart(Model model) {
		model.addAttribute("orders", cartService.getSortedCart());
		model.addAttribute("totalPrice",cartService.computeTotalPriceOfCart());
		return "cartForm";
	}
	
	@RequestMapping(params = "order", method=RequestMethod.GET)
	public String makeOrder(RedirectAttributes redirectAttributes,Model model){
		boolean message = cartService.makeOrder();
    	redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/main/displayOffer";
	}
	
	@RequestMapping(params = "clear", method = RequestMethod.GET)
    public String clearCart() {
		cartService.clearCart();
        return "cartForm";
    }
	
    @RequestMapping(params = "back", method = RequestMethod.GET)
    public String backToOffer(RedirectAttributes redirectAttributes) {
        return "redirect:/main/displayOffer";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteLineItem(@RequestParam("uniqueProductCode") String uniqueProductCode) {
    	cartService.removeItem(uniqueProductCode);
        return "redirect:/main/displayCart";
    }
}
