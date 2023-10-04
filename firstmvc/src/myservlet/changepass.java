package myservlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Json.ResponseJsonUtils;
import data.Mysql;

/**
 * Servlet implementation class changepass
 */
@WebServlet("/changepass")
public class changepass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changepass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String phone=request.getParameter("phone");
		String passold = request.getParameter("passold");
		String passnew = request.getParameter("passnew");
		String flag =request.getParameter("flag");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		HashMap<String, String>data = new HashMap<String,String>();
		
		if (flag.equals("0")){
			if(mysql.updateSQL("update users set password='"+passnew+"' where phone='"+phone+"' and password='"+passold+"'"))
			{
				data.put("status", "1");
			}else{
				data.put("status", "0");
			}
				
		}else if (flag.equals("1")) {
			if(mysql.updateSQL("update users set sign_password='"+passnew+"' where phone='"+phone+"' and sign_password='"+passold+"'"))
			{
				data.put("status", "1");
			} else{
				data.put("status", "0");
			}
			
		}	
		ResponseJsonUtils rejson = new ResponseJsonUtils();
		rejson.json(response, data);
	}
	

}
