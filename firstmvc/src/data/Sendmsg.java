package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sendmsg {
	public int  sendMsg(String phone, String text){
		
			String httpurl = "http://v1.avatardata.cn/Sms/Send?key=98318f08839e4dda84f30ec68734a5d1&mobile="+phone+"&templateId=e989be17628141bc8cc7fdfc7f35ea4d&param="
					+text;
			HttpURLConnection conn = null;
			try {
				URL url = new URL(httpurl);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null){
					response.append(line);
				}
				System.out.println(response.toString());
				return 1;
		}catch(Exception e){
			return 0;
			
		}
		
	}


}
