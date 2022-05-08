package com.model2.mvc.service.user.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;

//비즈니스 로직 캡슐화
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public UserServiceImpl() {
		userDAO = new UserDAO();
	}

	//User 회원가입
	public void addUser(UserVO userVO) throws Exception {
		userDAO.insertUser(userVO);
	}

	//User Login
	public UserVO loginUser(UserVO userVO) throws Exception {
		UserVO dbUser = userDAO.findUser(userVO.getUserId());

		if (!dbUser.getPassword().equals(userVO.getPassword()))
			throw new Exception("로그인에 실패했습니다.");

		return dbUser;
	}

	//User 개인정보
	public UserVO getUser(String userId) throws Exception {
		return userDAO.findUser(userId);
	}

	//User List 검색(관리자)
	public HashMap<String, Object> getUserList(SearchVO searchVO) throws Exception {
		return userDAO.getUserList(searchVO);
	}
	
	//User 정보 수정
	public void updateUser(UserVO userVO) throws Exception {
		userDAO.updateUser(userVO);
	}

	//User ID 중복체크
	public boolean checkDuplication(String userId) throws Exception {
		boolean result = true;
		UserVO userVO = userDAO.findUser(userId);
		if (userVO != null) {
			result = false;
		}
		return result;
	}
}