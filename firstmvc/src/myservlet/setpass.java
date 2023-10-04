package myservlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Mysql;

/**
 * Servlet implementation class setpass
 */
@WebServlet("/setpass")
public class setpass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setpass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String pass = request.getParameter("pass");
		String flag = request.getParameter("flag");
		String phone =request.getParameter("phone");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		if (flag.equals("1")) {
			mysql.updateSQL("update users set password='"+pass+"' where phone='"+phone+"'");
		}else if(flag.equals("2")) {
			mysql.updateSQL("update users set sign_password='"+pass+"' where phone='"+phone+"'");
		}
	}

}
