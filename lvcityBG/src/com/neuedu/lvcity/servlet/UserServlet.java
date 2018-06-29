package com.neuedu.lvcity.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.lvcity.model.Users;
import com.neuedu.lvcity.service.UsersService;
import com.neuedu.lvcity.service.impl.UsersServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/Admin/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action =  request.getParameter("action");
		 if("login".equals(action)){
			 doLogin(request,response);
		 }
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取session
		HttpSession session = request.getSession();
		//获取客户端的参数
		String username = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		
		//调用service的login方法
		UsersService usersService = UsersServiceImpl.getInstance();
		Users users = usersService.login(username, passwd);
		
		if(users == null ){
			//登录失败，重新登录
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			//登录成功.进入后台首页
			session.setAttribute("user", users);			
			response.sendRedirect(request.getContextPath()+"/Admin/index.jsp");
		}
	}

}