package myservlet;

import java.io.IOException;
import java.sql.ResultSet;
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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		doGet(request, response);
		String phone = request.getParameter("phone");
		String pass = request.getParameter("pass");
		String imei =request.getParameter("imei");
		System.out.println(imei);
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql ="select password,flag from users where phone="+phone;
		ResultSet rs = mysql.selectSQL(sql);
		
		HashMap<String, String>data = new HashMap<String,String>();
		try {
			if(rs.next()){
				String pass_sql = rs.getString("password");
				String flag_sql = rs.getString("flag");
				String sql1 ="select imei from u_keys where phone="+phone;
				ResultSet rs1 = mysql.selectSQL(sql1);
				rs1.next();
				String imei_sql =rs1.getString("imei");
				System.out.println(imei_sql);
                System.out.println(pass);
                System.out.println(imei);
                System.out.println(pass_sql);
				if(flag_sql.equals("1")){
					if (pass_sql.equals(pass) && imei_sql.equals(imei)){
						data.put("status", "2");//登录成功
						data.put("phone", phone);
						addData(data);
						System.out.println("2");
						
						
					}else if (pass_sql.equals(pass)) {
						data.put("status", "3");
						
						System.out.println("3");
					}else
					{
						data.put("status", "1");//密码错误						
					}
					
				}else {
					data.put("status", "0");//尚未注册
					
				}
				
			}else {
				data.put("status", "0");//尚未注册
			}
			ResponseJsonUtils rsjson = new ResponseJsonUtils();
			rsjson.json(response, data);
				
		} catch (Exception e) {
			System.out.println("error");
		}
		System.out.println("end");
		
	}


	private void addData(HashMap<String, String> data) {
		Mysql mysql = new Mysql();
		mysql.connSQL();
		String sql = "select name,email,token,addr_city,first_flag from users where phone="+data.get("phone");
		ResultSet rs = mysql.selectSQL(sql);
		try {
			if (rs.next()) {
				data.put("name", rs.getString("name"));
				data.put("email", rs.getString("email"));
				data.put("token", rs.getString("token"));
				data.put("address", rs.getString("addr_city"));
				data.put("first_flag", rs.getString("first_flag"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("users 表出错");
		}
		sql ="select pri,pub,pub_sign from u_keys where phone="+data.get("phone");
		rs = mysql.selectSQL(sql);
		try {
			if (rs.next()) {
				data.put("pubkey", rs.getString("pub"));
				data.put("pubkey_sign", rs.getString("pub_sign"));
				data.put("encry_prikey",rs.getString("pri"));
			}
			
		} catch (Exception e) {
			System.out.println(" U_keys 表出错");
		}
		sql ="select pub_key from ca where id=1";
		rs = mysql.selectSQL(sql);
		try {
			if (rs.next()) {
				data.put("ca_pubkey", rs.getString("pub_key"));				
			}
		} catch (Exception e) {
			System.out.println("ca 表出错");
		}
	
		
		
		
	}

	

}
