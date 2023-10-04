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
 * Servlet implementation class checksignpass
 */
@WebServlet("/checksignpass")
public class checksignpass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checksignpass() {
        super();
        // TODO Auto-generated constructor stub
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
		String phone = request.getParameter("phone");
		String sign_pass =request.getParameter("signpass");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql ="select sign_password from users where phone="+phone;
		ResultSet rs = mysql.selectSQL(sql);
		HashMap<String , String> data=new HashMap<String,String>();
		try {
			if (rs.next()) {
				String _sign_pass = rs.getString("sign_password");
				if (sign_pass.equals(_sign_pass)) {
					
					data.put("status", "1");
				}else{
					data.put("status", "0");
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
