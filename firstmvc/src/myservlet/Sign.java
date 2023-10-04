package myservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.sun.javafx.collections.MappingChange.Map;

import Json.ResponseJsonUtils;
import data.CA;
import data.Mysql;
import data.Sendmsg;
import data.Ecdsa;

/**
 * Servlet implementation class Sign
 */
@WebServlet("/sign")
public class Sign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sign() {
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
		
		 

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String phone,password,imei,pubkey,encry_prikeyprikey,encry_prikey;
		phone = request.getParameter("phone");
		password = request.getParameter("password");
		imei = request.getParameter("imei");
		pubkey = request.getParameter("pubkey");
		encry_prikey = request.getParameter("encry_prikey");
		String deskey= request.getParameter("des_key");
		Ecdsa ecdsa = new Ecdsa();
		ecdsa.jdkECDSA();
		System.out.println("1");
		System.out.println(encry_prikey);
		System.out.println("2");
		System.out.println(pubkey);
		System.out.println("3");
		System.out.println(ecdsa.Sign("123", encry_prikey));
		System.out.println("4");
		System.out.println(ecdsa.SignCheck("123",ecdsa.Sign("123", encry_prikey), pubkey));
		
		String sql="select * from users where phone="+phone;
		Mysql h = new Mysql();
		h.connSQL();
		ResultSet rs =  h.selectSQL(sql);		
		try {
			if(!rs.next()){//账户不存在
				String text =(int)((Math.random()*9+1)*100000)+"";	
				String token = getRandomString(128);
				Sendmsg msg = new Sendmsg();
				msg.sendMsg(phone, text);
				String insert_sql = "insert into users(phone,password,flag,_check,name,email,token,addr_city,first_flag) values("
				+"'"+ phone+"',"
				+"'"+ password+"',"
				+"'"+ "0"+"',"
				+"'"+ text+"',"
				+"'"+"新用户"+"',"
				+"'"+""+"',"
			    +"'"+token+"',"
			    +"'"+""+"',"
			    +"'"+"1"+"'"
						+")";
				System.out.println(insert_sql);
				h.insertSQL(insert_sql);//插入用户表
				CA ca = new CA();//给用户公钥签名
				String pub_sign = ca.caSign(phone+pubkey);				
				String insert_key_sql = "insert into u_keys(phone,pub,des,imei,pri,pub_sign) values("
						+"'"+ phone+"',"
						+"'"+ pubkey+"',"
						+"'"+ deskey+"',"
						+"'"+ imei+"',"
						+"'"+ encry_prikey+"',"
						+"'"+pub_sign+"'"
						+")";
				System.out.println(insert_key_sql);
				h.insertSQL(insert_key_sql);//插入用户公钥表				
				System.out.println("df");
			}else {//账户已存在
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		h.deconnSQL();//断开数据库连接
		HashMap<String,String> data=new HashMap<String,String>();
		data.put("status", "success");
		ResponseJsonUtils rejson =new ResponseJsonUtils();
		rejson.json(response, data);
		
		
	}
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }  

	
	
	
}
