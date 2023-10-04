package myservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Mysql;

/**
 * Servlet implementation class jijian
 */
@WebServlet("/jijian")
public class jijian extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jijian() {
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
		String company = request.getParameter("company");
		String id = request.getParameter("id");
		String send = request.getParameter("send");
		String recive = request.getParameter("recive");
		String flag =request.getParameter("flag");
		String person_id =request.getParameter("personid");
		String info = request.getParameter("info");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String insert_sql ="insert into express values("
				+"'"+id+"',"
				+"'"+send+"',"
				+"'"+recive+"',"
				+"'"+flag+"',"
				+"'"+"0"+"',"
				+"'"+""+"',"
				+"'"+company+"',"
				+"'"+person_id+"',"
				+"'"+info+"'"
				+")";
		mysql.insertSQL(insert_sql);
		System.out.println("jijiandsd");
		response.getWriter().append("jijian: ").append(request.getContextPath());
	}

}
