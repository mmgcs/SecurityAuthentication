package myservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Mysql;

/**
 * Servlet implementation class shenji
 */
@WebServlet("/shenji")
public class shenji extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shenji() {
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
		String phone =request.getParameter("phone");
		String sign = request.getParameter("sign");
		String id = request.getParameter("id");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql ="insert into shenji(id,sign,phone) values("
					+"'"+id+"',"
					+"'"+sign+"',"
					+"'"+phone+"'"
					+")";
		mysql.insertSQL(sql);
		mysql.deconnSQL();
	}

}
