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
 * Servlet implementation class questioncheck
 */
@WebServlet("/questioncheck")
public class questioncheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public questioncheck() {
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
		String phone = request.getParameter("phone");
		String imei =request.getParameter("imei");
		String answer = request.getParameter("answer");
		Mysql mysql = new Mysql();
		HashMap<String , String> data=new HashMap<String,String>();
		mysql.connSQL();
		String sql ="select answer from qa where phone="+phone;
		ResultSet rs = mysql.selectSQL(sql);
		try {
			if (rs.next()) {
				String _answer = rs.getString("answer");
				if (answer.equals(_answer)) {
					sql = "update u_keys set imei='"+imei+"' where phone='"+phone+"'";
					mysql.updateSQL(sql);
					data.put("status", "1");
				}else {
					data.put("status", "0");
				}
			
				
			}else {
				data.put("status", "0");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseJsonUtils rsjson = new ResponseJsonUtils();
		rsjson.json(response, data);
		
	}

}
