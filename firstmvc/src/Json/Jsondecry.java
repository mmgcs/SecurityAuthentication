package Json;

import org.json.JSONArray;
import org.json.JSONObject;

public class Jsondecry {
	
	
    
		
				
	public static void main(String[] args) {
		System.out.println("d");
		String jsonstr ="{\"result\":[{\"name\":\"gmm\",\"pass\":\"123\"},{\"name\":\"gmm\",\"pass\":\"123\"},{\"name\":\"gmm\",\"pass\":\"123\"}]}";
		JSONObject json = new JSONObject(jsonstr);
		JSONArray ja = json.getJSONArray("result");
		for (int i = 0; i < ja.length(); i++) {
			System.out.println(ja.getJSONObject(i).getString("name"));
			System.out.println(ja.getJSONObject(i).getString("pass"));
		}
		
	}

}
