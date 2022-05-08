package com.model2.mvc.web.product;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	static String history="";
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	public ProductRestController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value = "json/addProduct", method = RequestMethod.POST)
	public Product addProduct(@RequestBody Product product) throws Exception {
		
//		System.out.println(":: 파일 이름 => " + uploadfile.getOriginalFilename());
//		System.out.println(":: 파일 크기 => " + uploadfile.getSize());
//			
//		String saveName = uploadfile.getOriginalFilename();
//			
//		String temDir = request.getServletContext().getRealPath("/images/uploadFiles/");
//		Product product = new Product();
//		
//		System.out.println(":: 파일 이름 => " + saveName);
//		
//		Path copy = Paths.get(temDir, File.separator + StringUtils.cleanPath(saveName));
//		System.out.println(":: 파일 경로1 => " + request.getServletContext());
//		System.out.println(":: 파일 경로2 => " + request.getSession().getServletContext());
//		System.out.println(":: 파일 경로3 => " + temDir);
//		System.out.println(":: 파일 경로4 => " + File.separator);
//		System.out.println(":: 파일 경로5 => " + StringUtils.cleanPath(saveName));
//		System.out.println(":: 파일 경로6 => " + File.separator + StringUtils.cleanPath(saveName));
//		
//		try {
//			
//			Files.copy(uploadfile.getInputStream(), copy, StandardCopyOption.REPLACE_EXISTING);
//			//File saveFile = new File(temDir, saveName);
//			//uploadfile.transferTo(saveFile); //업로드 파일에 saveFile이라는 껍데기를 입힘
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			saveName = "empty.GIF";
//			
//		}
//		
//		product.setFileName(saveName);
//		product.setProdName(request.getParameter("prodName"));
//		product.setProdDetail(request.getParameter("prodDetail"));
//		product.setManuDate(request.getParameter("manuDate").replace("-", ""));
//		product.setPrice(Integer.parseInt(request.getParameter("price")));
//	
//		System.out.println("Product 확인 :: " + product);
//		System.out.println("insert 결과 :: " + productService.addProduct(product));
//		
//		model.addAttribute("product", product);
		
		System.out.println("/prodcut/addProduct : POST");
		
		int result = productService.addProduct(product);
		
		Product returnProduct = null;
		if (result == 1) {
			returnProduct = productService.getProduct(10022);
		}
		
		return returnProduct;
	}

	@RequestMapping(value = "json/getProduct/{menu}/{prodNo}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("menu") String menu, @PathVariable("prodNo") String prodNo,
							@CookieValue(value = "history", required = false) Cookie cookies, HttpServletResponse response) throws Exception {
		
		System.out.println("/prodcut/getProduct : GET");
		
		if ("manage".equals(menu)) {
			System.out.println("이곳은 manage 부분");
			//return "redirect:/product/updateProduct?prodNo="+prodNo+"&menu="+menu;
		}
		
		String pn = prodNo;
		if (cookies!=null && !cookies.equals("")) {
			boolean check = false;
			String[] strArray = cookies.getValue().split(",");
			if (strArray.length != 0) {
				for (String str:strArray) {
					if (str.equals(prodNo)) {
						pn = "";
						check = true;
						break;
					}
				}
					
				history = cookies.getValue();
				if (!check) {
					history = cookies.getValue()+",";
					pn = prodNo;
				}
			}	
		}
		
		Cookie cookie = new Cookie("history",  history+pn);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		Product product = productService.getProduct(Integer.parseInt(prodNo));
	 
		return product;
	}
	
	@RequestMapping(value = "json/updateProduct/{prodNo}", method = RequestMethod.GET)
	public Product updateProduct(@PathVariable int prodNo) throws Exception {
		
		System.out.println("/prodcut/updateProduct : GET");
		System.out.println(":: updateProductViewAction_prodNo => "+prodNo);
		
		return productService.getProduct(prodNo);
	}
	
	@RequestMapping(value = "json/updateProduct", method = RequestMethod.POST)
	public Product updateProduct(@RequestBody Product product) throws Exception {
		
		System.out.println("/prodcut/updateProduct : POST");
		System.out.println(":: updateProductAction_prodNo => "+product.getProdNo());
		System.out.println(":: update Product 확인 => "+product);

		productService.updateProduct(product);
		Product resultProduct = productService.getProduct(product.getProdNo());
		
		return resultProduct;
	}
	
	
	@RequestMapping("json/listProduct")
	public Map<String, Object> listProduct(@RequestBody(required = false) Search search, @RequestParam(required = false, value="searchOrder") String order) throws Exception {
		
		System.out.println("/prodcut/listProduct : GET/POST");
		//System.out.println("list currentPage " + request.getParameter("currentPage"));
		
		if (search == null) {
			search = new Search();
		}
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("order", order);			
		
		System.out.println("listProduct search :: " + search);
		System.out.println("listProduct order :: " + order);
		
		map = productService.getProductList(map);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		map.put("resultPage", resultPage);
		map.put("search", search);
		
		return map;
	}
	
	@RequestMapping(value = "json/getProductKeyword", method = RequestMethod.GET)
	public List<String> getKeyword(@RequestParam("keyWord") String keyword) throws Exception {
		
		System.out.println("/prodcut/getProductKeyword : GET");
		//System.out.println("encoding 전 " + keyword);
		keyword = URLDecoder.decode(keyword, "UTF-8");
		System.out.println("encoding 후 " + keyword);
		
		return productService.getProductKeyword(keyword);
	}
}
