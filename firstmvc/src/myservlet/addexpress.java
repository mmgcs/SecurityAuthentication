package myservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Mysql;

/**
 * Servlet implementation class addexpress
 */
@WebServlet("/addexpress")
public class addexpress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addexpress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String recive = request.getParameter("recive");
		String company = request.getParameter("company");
		String id =request.getParameter("id");
		String flag =request.getParameter("flag");
		Mysql mysql = new Mysql();
		mysql.connSQL();
		if(flag.equals("0")){
		    String sql ="insert into express(id,recive,company,flag,status) values("
	                +"'"+id+"',"
	                +"'"+recive+"',"
	                +"'"+company+"',"
	                +"'"+"0"+"',"
	                +"'"+"0"+"'"
	                +")";
		    mysql.insertSQL(sql);
		}else{
		    String sql ="insert into express(id,recive,company,flag,status) values("
                    +"'"+id+"',"
                    +"'"+recive+"',"
                    +"'"+company+"',"
                    +"'"+"1"+"',"
                    +"'"+"1"+"'"
                    +")";
		    mysql.insertSQL(sql);
		}
		
		mysql.deconnSQL();
		System.out.println("addexpress");
	}

}
