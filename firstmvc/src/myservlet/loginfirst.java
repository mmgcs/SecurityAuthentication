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
 * Servlet implementation class loginfirst
 */
@WebServlet("/loginfirst")
public class loginfirst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginfirst() {
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
		String email = request.getParameter("email");
		
		String question = request.getParameter("question");
		System.out.println(question);
		String answer = request.getParameter("answer");
		String phone = request.getParameter("phone");
		String pass2 = request.getParameter("pass2");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		mysql.updateSQL("update users set email="+"'"+email+"',sign_password="+"'"+pass2+"',first_flag='"+"0"+"' where phone='"+phone+"'");
		System.out.println("更新成功");
		mysql.insertSQL("insert into qa values("
		+"'"+phone+"',"
		+"'"+question+"',"+
		"'"+answer+"'"+
		")");
		System.out.println("插入成功");
		mysql.deconnSQL();
		System.out.print("loginfirst");
		HashMap<String, String>data = new HashMap<String,String>();
		data.put("result",question);
		ResponseJsonUtils rejson = new ResponseJsonUtils();
		rejson.json(response, data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
