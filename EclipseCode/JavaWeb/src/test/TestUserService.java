package test;

import java.util.List;

import ding.io.dao.User;
import ding.io.service.UserService;

public class TestUserService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserService userdao=new UserService();
		List<User> user=userdao.select();
		System.out.println("List:"+user);
	}

}
