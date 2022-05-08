package com.model2.mvc.web.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/cart/*")
public class CartController {
	
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public CartController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="addCart/{confirm}")
	public String addCart(@PathVariable String confirm, HttpSession session) throws Exception {
		User user = (User)session.getAttribute("user");		
		Product product = (Product)session.getAttribute("product");
		Cart cart = new Cart();
		
		System.out.println("getProduct cart 전달값 :: "+confirm);
		
		if (user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getUserId());
			map.put("prodNo", product.getProdNo());
			Cart resultCart = cartService.getCart(map);

			if (resultCart == null) {
				cart.setCartUser(user);
				cart.setCartProduct(product);
				
				if (cartService.addCart(cart) == 1) {
					//==> console 확인
					System.out.println(cart);
				}
			} else {
				return "forward:../updateCart?userId="+user.getUserId()+"&prodNo="+product.getProdNo()+"&amount=1";
			}
		}
		
		if ("ok".equals(confirm)) {
			System.out.println("1111111");
			return "redirect:../getCartList?userId="+user.getUserId();
		}
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping("updateCart")
	public String updateCart(@RequestParam("userId") String userId, @RequestParam("prodNo") int prodNo, 
							@RequestParam("amount") int amount) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("prodNo", prodNo);
		Cart cart = cartService.getCart(map);
		
		int resultAmount = cart.getAmount() + amount;
		cart.setAmount(resultAmount);
		
		if (cartService.updateCart(cart) == 1) {
			System.out.println("업데이트 완료");
		} 
		
		return "redirect:../getCartList?userId="+userId;
	}
	
	@RequestMapping("getCartList")
	public String getCartList(@RequestParam("userId") String userId, Model model) throws Exception {
		System.out.println(userId);
		Map<String,Object> map = cartService.getCartList(userId);
		List<Cart> list = (List<Cart>)map.get("list");
		
		for (Object cart : list) {
	 		System.out.println(cart);			
		}
		
		Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
		
		model.addAttribute("cart", map.get("list"));
		model.addAttribute("totalCount", map.get("totalCount"));
		
		return "forward:getCartList.jsp";
	}
	
	@RequestMapping("deleteCart")
	public String deleteCart(@RequestParam("userId") String userId, @RequestParam("prodNo") int prodNo) throws Exception {
		
		Cart cart = new Cart();
		User user = new User();
		Product product = new Product();
		user.setUserId(userId);
		product.setProdNo(prodNo);
		cart.setCartUser(user);
		cart.setCartProduct(product);
		
		if (cartService.deleteCart(cart) == 1) {
			System.out.println("삭제 성공");
		}
		
		return "redirect:/cart/getCartList?userId="+userId;
	}
}
