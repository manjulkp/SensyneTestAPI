package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;

import org.json.JSONObject;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Common {

	public String responseToString(Response res) {

		ResponseBody body = res.getBody();
		String jsonString = body.asString();

		return jsonString;

	}

//	public Map<String, String> getChildObject(Response response, String root, String getValueOf) {
//	JSONObject obj = new JSONObject(response.getBody().asString());
//	Map<String, String> map = new HashMap<String, String>();
//
//	JSONObject pages = obj.getJSONObject(root);
//	Iterator iterator = pages.keys();
//	while (iterator.hasNext()) {
//		String key = (String) iterator.next();
//		if (!key.equals("curies")) {
//			JSONObject page = pages.getJSONObject(key);
//			String Value = page.getString(getValueOf);
//			map.put(key, Value);
//
//		}
//
//	}
//	return map;
//}

	public static Map<String, String> stringToMap(String s) {
		Map<String, String> map = new HashMap<String, String>();
		String[] pairs = s.split(",");
		for (int i = 0; i < pairs.length; i++) {
			String pair = pairs[i];
			String[] keyValue = pair.split(":");
			map.put(keyValue[0], keyValue[1]);
		}
		return map;
	}

	public static List<Header> makeHeaderCollection(Map<String, String> map) {

		List<Header> list = new ArrayList<Header>();

		Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();

			list.add(new Header((String) thisEntry.getKey(), (String) thisEntry.getValue()));

		}
		return list;

	}
	
	public static Map<String, String>  publishAllXpathInResponse(String s)
	{
		Map<String, String> keyValueStore = new HashMap<String, String>();
		Stack<String> keyPath = new Stack();
		JSONObject json = new JSONObject(s);
		keyValueStore = ParseJson.getAllXpathAndValueFromJsonObject(json, keyValueStore, keyPath);
		for(Map.Entry<String, String> map : keyValueStore.entrySet()) {
	       
	      System.out.println(map.getKey() + "-------------" + map.getValue());
	    }
		return keyValueStore; 
	}

}
