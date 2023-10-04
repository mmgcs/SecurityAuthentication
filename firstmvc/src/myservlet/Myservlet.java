package myservlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import data.Ecdsa;
import data.Mysql;
import Json.ResponseJsonUtils;
import data.CA;

/**
 * Servlet implementation class Myservlet
 */
@WebServlet("/")
public class Myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Myservlet() {
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
		HashMap<String, String>data = new HashMap<String,String>();
//		HashMap<String,ArrayList>data = new HashMap<String, ArrayList>();
//		ArrayList list = new ArrayList();
//		for (int i = 0; i < 3; i++) {
//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put("name", "gmm");
//			map.put("pass", "123");
//			list.add(map);				
//		}
		Mysql mysql = new Mysql();
		mysql.connSQL();
		data.put("sign",caSign("123"));
		data.put("result","1");
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
	public  String caSign(String pub_string) {
		String sign="error";
		Mysql mysql = new Mysql();
		mysql.connSQL();
		Ecdsa ecdsa = new Ecdsa();
		ecdsa.jdkECDSA();
		String sql ="select pri_key,pub_key from ca where id=1";
		ResultSet rs =  mysql.selectSQL(sql);
		try {
			if (rs.next()) {
				String pri_key = rs.getString("pri_key");
				sign=ecdsa.Sign(pub_string, pri_key);
				System.out.print(ecdsa.SignCheck(pub_string, sign, rs.getString("pub_key"))+"\n");
				return sign;				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		mysql.deconnSQL();
		return sign;
	}
}
