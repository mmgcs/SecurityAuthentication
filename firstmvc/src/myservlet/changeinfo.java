package myservlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Mysql;

/**
 * Servlet implementation class changeinfo
 */
@WebServlet("/changeinfo")
public class changeinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeinfo() {
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
		String phone = request.getParameter("phone");
		String flag = request.getParameter("flag");
		System.out.print(flag);
		String info = request.getParameter("info");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		switch (flag) {
		case "0":
			mysql.updateSQL("update users set name='"+info+"' where phone= '"+phone+"'");
			
			break;
		case "1":
			mysql.updateSQL("update users set email='"+info+"' where phone= '"+phone+"'");
			break;
		case "2":
			mysql.updateSQL("update users set addr_city='"+info+"' where phone= '"+phone+"'");
			break;
		
		default:
			break;
		}
		mysql.deconnSQL();
	}

}
