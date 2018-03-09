package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.service.CartService;

@Controller
@RequestMapping(value = "/main")
public class CartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping(value="/displayCart", method=RequestMethod.GET)
	public String displayCart(Model model)
	{
		model.addAttribute("orders", cartService.getCart());
		return "cartForm";
	}
	
	@RequestMapping(value="/displayCart",params = "order", method=RequestMethod.POST)
	public String makeOrder(RedirectAttributes redirectAttributes,Model model)
	{
		model.addAttribute("orders", cartService.getCart());
		cartService.makeOrder();
		redirectAttributes.addFlashAttribute("success", "true");
		return "redirect:/main";
	}
	
    @RequestMapping(value="/displayCart",params = "back", method = RequestMethod.POST)
    public String backToOffer() {
        return "redirect:/main";
    }
	
    @RequestMapping(value="/delete/{uniqueProductCode}",method = RequestMethod.GET)  
    public String deleteChosenItem(@PathVariable String uniqueProductCode){  
    	cartService.removeItem(uniqueProductCode);
        return "redirect:/main/displayCart";  
    }  
    
}
