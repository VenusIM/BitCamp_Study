package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

//로그인 로직 수행 Method
public class LoginAction extends Action {
 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserVO userVO = new UserVO();
		userVO.setUserId(request.getParameter("userId"));
		userVO.setPassword(request.getParameter("password"));

		//UserService를 implements한 UserServiceImpl()
		UserService service = new UserServiceImpl();
		//로그인에 성공했다면 User정보가 반환되어 저장
		UserVO dbVO = service.loginUser(userVO);

		//로그인된 User의 정보를 session에 저장
		HttpSession session = request.getSession();
		session.setAttribute("user", dbVO);

		return "redirect:/index.jsp";
	}
}