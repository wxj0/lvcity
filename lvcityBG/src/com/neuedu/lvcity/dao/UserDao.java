package com.neuedu.lvcity.dao;

import com.neuedu.lvcity.model.Users;

public interface UserDao {
	
	public Users login(String username,String password);

}
