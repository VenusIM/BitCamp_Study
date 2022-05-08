package com.model2.mvc.web.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;
	
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	public PurchaseRestController() {
		// TODO Auto-generated constructor stub
		System.out.println(getClass());
	}
	
	@RequestMapping(value = "json/addPurchase/{prodNo}", method = RequestMethod.GET)
	public Product addPurchase(@PathVariable("prodNo") int prodNo) throws Exception {
		
		System.out.println("/prodcut/addPurchase : GET");
		System.out.println("addPurchaseView_prodNo :: " + prodNo);
		
		return productService.getProduct(prodNo);
	}
	
	@RequestMapping(value = "json/addPurchase", method = RequestMethod.POST)
	public Purchase addPurchase(@RequestBody Purchase purchase, HttpSession session) throws Exception {
		
		System.out.println("/prodcut/addPurchase : POST");
		
		User user = (User)session.getAttribute("user");
		Product product = (Product)session.getAttribute("product");
		
		System.out.println("addPurchase_user :: " + user);
		System.out.println("addPurchase_product :: " + product);
		
		if (user == null) {
			user = new User();
		}
		
		//purchase.setBuyer(user);
		//purchase.setPurchaseProd(product);
		purchase.setTranCode("1");
		
		System.out.println("addPurchase_purchase :: " + purchase);
		
		if (purchaseService.addPurchase(purchase) == 1) {
			System.out.println("addPurchase :: 구매 완료");
		}
		
		return purchaseService.getPurchase(10000);
	}
	
	@RequestMapping(value = "json/getPurchase/{tranNo}", method = RequestMethod.GET)
	public Purchase getPurchase(@PathVariable int tranNo) throws Exception {
		
		System.out.println("/prodcut/getPurchase : GET");
		
		return purchaseService.getPurchase(tranNo);
	}
	
	@RequestMapping(value = "json/updatePurchase/{tranNo}", method = RequestMethod.GET)
	public Purchase updatePurchase(@PathVariable int tranNo) throws Exception {
		
		System.out.println("/prodcut/updatePurchase : GET");
		//System.out.println("updatePurchaseView_tranNo :: " + tranNo);
		
		return purchaseService.getPurchase(tranNo);
	}
	
	@RequestMapping(value = "json/updatePurchase", method = RequestMethod.POST)
	public Purchase updatePurchase(@RequestBody Purchase purchase) throws Exception {
		
		System.out.println("updatePurchase_purchase :: " + purchase);
		
		if (purchaseService.updatePurcahse(purchase) == 1) {
			System.out.println("구매 업데이트 성공");
		}
		
		purchase = purchaseService.getPurchase(purchase.getTranNo()); 
		System.out.println("updatePurchase_getPurchase :: " + purchase);
		
		return purchase;
	}
	
	@RequestMapping(value = "json/updateTranCodeByProd/{prodNo}/{tranCode}", method = RequestMethod.GET)
	public Purchase updateTranCodeByProd(@PathVariable int prodNo, @PathVariable String tranCode, HttpServletRequest request) throws Exception {
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		
		product.setProdNo(prodNo);
		purchase.setTranCode(tranCode);
		purchase.setPurchaseProd(product);
		
		if (purchaseService.updateTranCode(purchase) == 1) {
			System.out.println("구매 코드 업데이트 성공");
		}
		
//		System.out.println("guestName :: " + request.getParameter("guestName"));
//		System.out.println("guestPhone :: " + request.getParameter("guestPhone"));
		 
		return purchaseService.getPurchase2(prodNo);
	}

	@RequestMapping(value = "json/listPurchase")
	public Map<String, Object> listPurchase(@RequestBody(required = false) Search search, HttpServletRequest request) throws Exception {
		
		User user = new User();
		user.setUserId("user12");
		
		if (search == null) {
			search = new Search();
		}
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("user", user);
		map.put("guestName", request.getParameter("guestName"));
		map.put("guestPhone", request.getParameter("guestPhone"));
		
		purchaseService.getPurchaseList(map);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		map.put("resultpage", resultPage);
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
		
		return map;
	}
	
}
