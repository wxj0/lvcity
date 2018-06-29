package com.neuedu.lvcity.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UserDao;
import com.neuedu.lvcity.model.Users;

public class UserDaoImpl implements UserDao {
	
	//数据库连接
		private Connection conn ;
		
		/**
		 * 构造方法
		 */
		
		public UserDaoImpl(Connection conn ) {
			// TODO Auto-generated constructor stub
			this.conn = conn;
		}

		@Override
		public Users login(String username, String password) {
			// TODO Auto-generated method stub
			//声明返回值变量
			Users users = null;
			//声明预编译的SQL语句对象
			PreparedStatement pStatement = null;
			//声明结果集变量
			ResultSet rSet = null;
			try {
				//创建预编译的SQL语句
				pStatement = conn.prepareStatement("select * from users where name=? and passwd=?");
				pStatement.setString(1, username);
				pStatement.setString(2, password);
				//执行SQL语句
				rSet = pStatement.executeQuery();
				
				//遍历结果集，把结果集的数据取出来放在Users对象里面
				if(rSet.next()){
					//解析集，封装查询结果对象
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
