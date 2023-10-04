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
 * Servlet implementation class getrecive
 */
@WebServlet("/getrecive")
public class getrecive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getrecive() {
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
		String id = request.getParameter("id");
		HashMap<String, String>data = new HashMap<String,String>();
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql ="select recive,flag from express where id="+id;
		ResultSet rs = mysql.selectSQL(sql);
		try {
			if (rs.next()) {
				data.put("recive", rs.getString("recive"));	
				if (rs.getString("flag").equals("1")) {
					data.put("type", "新型运单");
				}else {
					data.put("type", "传统运单");
				}
				ResponseJsonUtils rsjson = new ResponseJsonUtils();
				rsjson.json(response, data);
			}else {
				data.put("recive", "");
				data.put("type", "传统运单");
				ResponseJsonUtils rsjson = new ResponseJsonUtils();
				rsjson.json(response, data);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
