package com.neuedu.lvcity.service.impl;

import java.sql.Connection;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UserDao;
import com.neuedu.lvcity.dao.impl.UserDaoImpl;
import com.neuedu.lvcity.model.Users;
import com.neuedu.lvcity.service.UsersService;

public class UsersServiceImpl implements UsersService{
	//单例模式
	 /**
	  * 构造方法，私有化
	  */
	private  UsersServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//内部创建唯一的实例
	private static final UsersService Instance = new UsersServiceImpl();
	
	/**
	 * 提供一个外部访问的公共接口
	 */
	public static  UsersService getInstance(){
		return Instance;
	}
	
	@Override
	public Users login(String name, String passwd) {
		// TODO Auto-generated method stub
		//声明返回值的变量
		Users user = null;
		//声明连接对象
		Connection conn = null;
		try{
			//获取连接
			conn = DBUtils.getConnection();
			//创建dao层的实现类的对象
			UserDao userDao = new UserDaoImpl(conn);
			//调用dao层的登录方法，返回登录的user对象			
			user = userDao.login(name, passwd);			
		}catch(Exception e){
			DBUtils.rollback(conn);
			System.out.println("登录查询用户异常："+e.getMessage());
		}finally {
			DBUtils.closeConnection(conn);
		}	
		
		return user;
	}

}
