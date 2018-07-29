package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.service.CartService;
import com.shop.service.CategoryService;

@Controller
@RequestMapping(value = "/main")
public class CartController {

	private CartService cartService;
	private CategoryService categoryService;
	
	@Autowired
	public CartController(CartService cartService, CategoryService catService) {
		this.cartService = cartService;
		this.categoryService = catService;
	}

	@RequestMapping(value="/displayCart", method=RequestMethod.GET)
	public String displayCart(Model model) {
		model.addAttribute("orders", cartService.getCart());
		model.addAttribute("totalPrice",cartService.getTotalPriceOfCart());
		return "cartForm";
	}
	
	@RequestMapping(value="/displayCart",params = "order", method=RequestMethod.POST)
	public String makeOrder(RedirectAttributes redirectAttributes,Model model){
		boolean message = cartService.makeOrder();
		setReturnAttributes(redirectAttributes);
    	redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/main/displayOffer";
	}
	
	@RequestMapping(value="/displayCart",params = "clear", method = RequestMethod.POST)
    public String clearCart() {
		cartService.clearCart();
        return "cartForm";
    }
	
    @RequestMapping(value="/displayCart",params = "back", method = RequestMethod.POST)
    public String backToOffer(RedirectAttributes redirectAttributes) {
    	setReturnAttributes(redirectAttributes);
        return "redirect:/main/displayOffer";
    }

    @RequestMapping(value="/displayCart", method = RequestMethod.DELETE)
    public String deleteLineItem(@RequestParam("uniqueProductCode") String uniqueProductCode) {
    	cartService.removeItem(uniqueProductCode);
        return "redirect:/main/displayCart";
    }
    
	private void setReturnAttributes(RedirectAttributes redirectAttributes) {
    	redirectAttributes.addAttribute("categoryName", categoryService.getFirstCategory().getCategoryName());
    	redirectAttributes.addAttribute("page", 1);
	}
}
