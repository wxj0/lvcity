package com.neuedu.lvcity.service.impl;

import java.sql.Connection;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UserDao;
import com.neuedu.lvcity.dao.impl.UserDaoImpl;
import com.neuedu.lvcity.model.Users;
import com.neuedu.lvcity.service.UsersService;

public class UsersServiceImpl implements UsersService{
	//����ģʽ
	 /**
	  * ���췽����˽�л�
	  */
	private  UsersServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//�ڲ�����Ψһ��ʵ��
	private static final UsersService Instance = new UsersServiceImpl();
	
	/**
	 * �ṩһ���ⲿ���ʵĹ����ӿ�
	 */
	public static  UsersService getInstance(){
		return Instance;
	}
	
	@Override
	public Users login(String name, String passwd) {
		// TODO Auto-generated method stub
		//��������ֵ�ı���
		Users user = null;
		//�������Ӷ���
		Connection conn = null;
		try{
			//��ȡ����
			conn = DBUtils.getConnection();
			//����dao���ʵ����Ķ���
			UserDao userDao = new UserDaoImpl(conn);
			//����dao��ĵ�¼���������ص�¼��user����			
			user = userDao.login(name, passwd);			
		}catch(Exception e){
			DBUtils.rollback(conn);
			System.out.println("��¼��ѯ�û��쳣��"+e.getMessage());
		}finally {
			DBUtils.closeConnection(conn);
		}	
		
		return user;
	}

}
