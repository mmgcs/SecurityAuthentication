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
 * Servlet implementation class textcheck
 */
@WebServlet("/textcheck")
public class textcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public textcheck() {
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
		String phone=request.getParameter("phone");
		String text =request.getParameter("text");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql ="select _check from users where phone="+phone;
		ResultSet rs = mysql.selectSQL(sql);
		HashMap<String , String> data=new HashMap<String,String>();
		try {
			if (rs.next()) {
				String _check = rs.getString("_check");
				if (text.equals(_check)) {
					sql ="select question from qa where phone="+phone;
					rs =mysql.selectSQL(sql);
					if (rs.next()) {
						String question = rs.getString("question");
						data.put("question", question);
					}else {
						data.put("question", "0");
					}
				}else{
					data.put("question", "0");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseJsonUtils rsjson = new ResponseJsonUtils();
		rsjson.json(response, data);
	}

}
