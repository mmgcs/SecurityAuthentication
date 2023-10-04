package myservlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Json.ResponseJsonUtils;
import data.Mysql;

/**
 * Servlet implementation class orderinfo
 */
@WebServlet("/orderinfo")
public class orderinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderinfo() {
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
		String order_id = request.getParameter("order_id");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql = "select * from orders where id="+order_id;
		ResultSet rs = mysql.selectSQL(sql);
		HashMap<String,String>data = new HashMap<String,String>();
		try {
			if(rs.next()){
				data.put("shou_name", rs.getString("shou_name"));
				data.put("shou_phone", rs.getString("shou_phone"));
				data.put("shou_addr", rs.getString("shou_addr"));
				data.put("shou_addr_info", rs.getString("shou_addr_info"));
				data.put("ji_name", rs.getString("ji_name"));
				data.put("ji_phone", rs.getString("ji_phone"));
				data.put("ji_addr", rs.getString("ji_addr"));
				data.put("ji_addr_info", rs.getString("ji_addr_info"));
				data.put("info", rs.getString("info"));
				data.put("personid", rs.getString("person_id"));
				ResponseJsonUtils rsjson = new ResponseJsonUtils();
				rsjson.json(response, data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("orderinfo");
	}

}
