package myservlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Json.ResponseJsonUtils;
import data.Mysql;

/**
 * Servlet implementation class myxiaoge
 */
@WebServlet("/myxiaoge")
public class myxiaoge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myxiaoge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String phone =request.getParameter("phone");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql ="select phone2,name,company from friends";
		ResultSet rs = mysql.selectSQL(sql);
		HashMap<String,ArrayList>data = new HashMap<String, ArrayList>();
		ArrayList list = new ArrayList();
		try {
			
			while (rs.next()) {				
				 	System.out.println("hello");
					HashMap<String,String> map = new HashMap<String,String>();
					map.put("phone", rs.getString("phone2"));
					map.put("company", rs.getString("company"));
					map.put("name", rs.getString("name"));
					list.add(map);				
					
			}
			data.put("result", list);
		} catch (SQLException e) {
			
		}
		System.out.println("bads");
		ResponseJsonUtils rsjson = new ResponseJsonUtils();
		rsjson.json(response, data);
	}
}


