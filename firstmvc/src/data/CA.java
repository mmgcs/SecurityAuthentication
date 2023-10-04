package data;
import data.Ecdsa;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CA {
	public static void main(String[] args) throws SQLException {
//*********初始化ca公钥		
		Mysql mysql = new Mysql();
		mysql.connSQL();
		Ecdsa ecdsa = new Ecdsa();
		ecdsa.jdkECDSA();
		String pub_key = ecdsa.getPubkey();
		String pri_key = ecdsa.getPrikey();
		String sql = "insert into ca(id,pri_key,pub_key) values("+
				"'"+"1"+"',"+
				"'"+pri_key+"',"+
				"'"+pub_key+"'"+
				")";
		mysql.insertSQL(sql);
		mysql.deconnSQL();
		
			
		}
			


//**********************CA对用户公钥签名
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
