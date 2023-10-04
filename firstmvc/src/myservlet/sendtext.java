package myservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Mysql;
import data.Sendmsg;

/**
 * Servlet implementation class sendtext
 */
@WebServlet("/sendtext")
public class sendtext extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendtext() {
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
		String phone= request.getParameter("phone");
		
		Sendmsg msg = new Sendmsg();
		String text =(int)((Math.random()*9+1)*100000)+"";	
		msg.sendMsg(phone, text);
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql = "update users set _check='"+text+"' where phone='"+phone+"'";
		mysql.updateSQL(sql);
		System.out.println("sdf");
		mysql.deconnSQL();
		
	}

}
