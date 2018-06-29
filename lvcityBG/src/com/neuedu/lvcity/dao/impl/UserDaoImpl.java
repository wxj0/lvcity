package com.neuedu.lvcity.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UserDao;
import com.neuedu.lvcity.model.Users;

public class UserDaoImpl implements UserDao {
	
	//���ݿ�����
		private Connection conn ;
		
		/**
		 * ���췽��
		 */
		
		public UserDaoImpl(Connection conn ) {
			// TODO Auto-generated constructor stub
			this.conn = conn;
		}

		@Override
		public Users login(String username, String password) {
			// TODO Auto-generated method stub
			//��������ֵ����
			Users users = null;
			//����Ԥ�����SQL������
			PreparedStatement pStatement = null;
			//�������������
			ResultSet rSet = null;
			try {
				//����Ԥ�����SQL���
				pStatement = conn.prepareStatement("select * from users where name=? and passwd=?");
				pStatement.setString(1, username);
				pStatement.setString(2, password);
				//ִ��SQL���
				rSet = pStatement.executeQuery();
				
				//������������ѽ����������ȡ��������Users��������
				if(rSet.next()){
					//����������װ��ѯ�������
					users = new Users();
					users.setId(rSet.getInt("id"));
					users.setName(rSet.getString("name"));
					users.setPasswd(rSet.getString("passwd"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtils.closeStatement(rSet, pStatement);
			}
			
			return users;
		}

}
