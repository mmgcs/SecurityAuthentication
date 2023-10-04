package myservlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class express_status
 */
@WebServlet("/express_status")
public class express_status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public express_status() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		String status =request.getParameter("status");
		String phone = request.getParameter("phone");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql ="select id,company from express where status='"+status+"' and recive='"+phone+"'";
		ResultSet rs = mysql.selectSQL(sql);
		HashMap<String,ArrayList>data = new HashMap<String, ArrayList>();
		ArrayList list = new ArrayList();
		try {
			
			while (rs.next()) {				
				 	System.out.println("hello");
					HashMap<String,String> map = new HashMap<String,String>();
					map.put("id", rs.getString("id"));
					map.put("company", rs.getString("company"));
					list.add(map);				
					
			}
			data.put("result", list);
		} catch (SQLException e) {
			
		}
		System.out.println("bads");
		ResponseJsonUtils rsjson = new ResponseJsonUtils();
		rsjson.json(response, data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
