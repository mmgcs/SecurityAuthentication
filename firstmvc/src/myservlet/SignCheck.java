package myservlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.swing.internal.plaf.metal.resources.metal;

import Json.ResponseJsonUtils;
import data.Mysql;

/**
 * Servlet implementation class SignCheck
 */
@WebServlet("/signcheck")
public class SignCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignCheck() {
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
		String phone = request.getParameter("phone");
		String text = request.getParameter("text");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql = "select _check from users where phone="+phone;
		ResultSet rs =mysql.selectSQL(sql);
		try {
			if(rs.next()){
				String check_text = rs.getString("_check");
				if(check_text.equals(text)){
					sql ="update users set flag=1 where phone="+phone;
					System.out.println(mysql.updateSQL(sql));
					HashMap<String, String>data = new HashMap<String,String>();
					data.put("status", "1");
					ResponseJsonUtils rejson = new ResponseJsonUtils();
					rejson.json(response, data);
				}else{
					System.out.println("check error");
				}				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
