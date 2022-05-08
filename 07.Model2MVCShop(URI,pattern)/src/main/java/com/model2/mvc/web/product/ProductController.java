package com.model2.mvc.web.product;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	static String history="";
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	public ProductController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, @RequestParam("fileName") MultipartFile uploadfile, Model model) throws Exception {
		
		System.out.println(":: 파일 이름 => " + uploadfile.getOriginalFilename());
		System.out.println(":: 파일 크기 => " + uploadfile.getSize());
			
		String saveName = uploadfile.getOriginalFilename();
			
		String temDir = request.getSession().getServletContext().getRealPath("/images/uploadFiles/");
		Product product = new Product();
		
		System.out.println(":: 파일 이름 => " + saveName);
		
		Path copy = Paths.get(temDir, File.separator + StringUtils.cleanPath(saveName));
		System.out.println(":: 파일 경로 => " + copy);
		
		try {
			
			Files.copy(uploadfile.getInputStream(), copy, StandardCopyOption.REPLACE_EXISTING);
			//File saveFile = new File(temDir, saveName);
			//uploadfile.transferTo(saveFile); //업로드 파일에 saveFile이라는 껍데기를 입힘
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			saveName = "empty.GIF";
			
		}
		
		product.setFileName(saveName);
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate").replace("-", ""));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
	
		System.out.println("Product 확인 :: " + product);
		System.out.println("insert 결과 :: " + productService.addProduct(product));
		
		model.addAttribute("product", product);
		
		return "forward:addProduct.jsp";
	}

	@RequestMapping(value = "getProduct", method = RequestMethod.GET)
	public String getProduct(@RequestParam("menu") String menu, @ModelAttribute Product product, @CookieValue(value = "history", required = false) Cookie cookies,
										HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("tranCode 확인 :: "+request.getParameter("tranCode"));
		
		if ("manage".equals(menu)) {
			return "redirect:/product/updateProduct?prodNo="+product.getProdNo()+"&menu="+menu;
		}
		
		String pn = request.getParameter("prodNo");
		if (cookies!=null && !cookies.equals("")) {
			boolean check = false;
			String[] strArray = cookies.getValue().split(",");
			if (strArray.length != 0) {
				for (String str:strArray) {
					if (str.equals(request.getParameter("prodNo"))) {
						pn = "";
						check = true;
						break;
					}
				}
					
				history = cookies.getValue();
				if (!check) {
					history = cookies.getValue()+",";
					pn = request.getParameter("prodNo");
				}
			}	
		}
		
		Cookie cookie = new Cookie("history",  history+pn);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		product = productService.getProduct(product.getProdNo());
		session.setAttribute("product", product);
		request.setAttribute("tranCode", request.getParameter("tranCode"));
		
		return "forward:getProduct.jsp?prodNo="+product.getProdNo()+"&menu="+menu;
	}
	
	@RequestMapping(value = "updateProduct", method = RequestMethod.GET)
	public String updateProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception {
		
		System.out.println(":: updateProductViewAction_prodNo => "+prodNo);
		
		model.addAttribute("product", productService.getProduct(prodNo));
		
		return "forward:updateProductView.jsp";
	}
	
	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute Product product) throws Exception {
		
		System.out.println(":: updateProductAction_prodNo => "+product.getProdNo());
		System.out.println(":: update Product 확인 => "+product);

		productService.updateProduct(product);
		
		return "redirect:./getProduct?prodNo="+product.getProdNo()+"&menu=ok";
	}
	
	//enter 입력 시 error 해결 필요
	@RequestMapping("listProduct")
	public String listProduct(HttpServletRequest request ,@ModelAttribute Search search, @RequestParam(required = false, value="searchOrder") String order, Model model) throws Exception {
		
		System.out.println("list currentPage " + request.getParameter("currentPage"));
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("order", order);			
		
		System.out.println("listProduct search :: " + search);
		System.out.println("listProduct order :: " + order);
		
		productService.getProductList(map);

		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("searchOrder", map.get("order"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:listProduct.jsp";
	}
}
