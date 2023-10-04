package myservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Mysql;

/**
 * Servlet implementation class addorder
 */
@WebServlet("/addorder")
public class addorder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addorder() {
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
		String shou_name,shou_phone,shou_addr,shou_addr_info,
		ji_name,ji_phone,ji_addr,ji_addr_info,info,personid;
		shou_name =request.getParameter("shou_name");
		shou_phone = request.getParameter("shou_phone");
		shou_addr = request.getParameter("shou_addr");
		shou_addr_info = request.getParameter("shou_addr_info");
		ji_name =request.getParameter("ji_name");
		ji_phone = request.getParameter("ji_phone");
		ji_addr = request.getParameter("ji_addr");
		ji_addr_info = request.getParameter("ji_addr_info");
		info = request.getParameter("info");
		personid = request.getParameter("personid");
		String expresser = request.getParameter("expresser");
		String company = request.getParameter("company");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql = "insert into orders(shou_name,shou_phone,shou_addr,shou_addr_info,ji_name,ji_phone,ji_addr,ji_addr_info"
				+ ",info,flag,expresser,person_id,company) values ("
				+"'"+shou_name+"',"
				+"'"+shou_phone+"',"
				+"'"+shou_addr+"',"
				+"'"+shou_addr_info+"',"
				+"'"+ji_name+"',"
				+"'"+ji_phone+"',"
				+"'"+ji_addr+"',"
				+"'"+ji_addr_info+"',"
				+"'"+info+"',"
				+"'"+"待确认"+"',"
				+"'"+expresser+"',"
				+"'"+personid+"',"
				+"'"+company+"'"
				+")";
		mysql.insertSQL(sql);
		System.out.println("插入成功");
	}

}
