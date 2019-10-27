package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJson {
	
	/*usage 
	 * Map<String, String> keyValueStore = new HashMap<>();
	    Stack<String> keyPath = new Stack();
	    JSONObject json = new JSONObject(jsonstring);
	    keyValueStore = Parse.getAllXpathAndValueFromJsonObject(json, keyValueStore, keyPath);
	    for(Map.Entry<String, String> map : keyValueStore.entrySet()) {
	        System.out.println(map.getKey() + ":" + map.getValue());
	    } 
	    
	      JSONObject obj = new JSONObject(s);
        @SuppressWarnings("unchecked")
        List<String> parameterKeys = new ArrayList<String>(obj.keySet());
        List<String>  result = null;
        List<String> keys = new ArrayList<>();
        for (String str : parameterKeys) {
            keys.add(str);
            result = ParserTest.addNestedKeys(obj, keys, str);
        }
        System.out.println(result.toString()); 
	 */
	
	public static Map<String, String> getAllXpathAndValueFromJsonObject(JSONObject json, Map<String, String> keyValueStore, Stack<String> keyPath) {
	    Set<String> jsonKeys = json.keySet();
	   
	    for (Object keyO : jsonKeys) {
	        String key = (String) keyO;
	        
	        
	      
	        keyPath.push(key);
	        Object object = json.get(key);

	        if (object instanceof JSONObject) {
	            getAllXpathAndValueFromJsonObject((JSONObject) object, keyValueStore, keyPath);
	        }

	        if (object instanceof JSONArray) {
	            doJsonArray((JSONArray) object, keyPath, keyValueStore, json, key);
	        }

	        if (object instanceof String || object instanceof Boolean || object.equals(null) || object instanceof Integer) {
	            String keyStr = "";

	            for (String keySub : keyPath) {
	                keyStr += keySub + ".";
	            }

	            keyStr = keyStr.substring(0, keyStr.length() - 1);

	            keyPath.pop();

	            keyValueStore.put(keyStr, json.get(key).toString());
	            
	            
	        }
	    }

	    if (keyPath.size() > 0) {
	        keyPath.pop();
	    }

	    return keyValueStore;
	}

	public static void doJsonArray(JSONArray object, Stack<String> keyPath, Map<String, String> keyValueStore, JSONObject json,
	        String key) {
	    JSONArray arr = (JSONArray) object;
	    for (int i = 0; i < arr.length(); i++) {
	        keyPath.push(Integer.toString(i));
	        Object obj = arr.get(i);
	        if (obj instanceof JSONObject) {
	            getAllXpathAndValueFromJsonObject((JSONObject) obj, keyValueStore, keyPath);
	        }

	        if (obj instanceof JSONArray) {
	            doJsonArray((JSONArray) obj, keyPath, keyValueStore, json, key);
	        }

	        if (obj instanceof String || obj instanceof Boolean || obj.equals(null) || obj instanceof Integer) {
	            String keyStr = "";

	            for (String keySub : keyPath) {
	                keyStr += keySub + ".";
	            }

	            keyStr = keyStr.substring(0, keyStr.length() - 1);

	            keyPath.pop();

	            keyValueStore.put(keyStr , json.get(key).toString());
	        }
	    }
	    if (keyPath.size() > 0) {
	        keyPath.pop();
	    }
	}
	public static List<String> addNestedKeys(JSONObject obj, List<String> keys, String key) {
	    if (isNestedJsonAnArray(obj.get(key))) {
	        JSONArray array = (JSONArray) obj.get(key);
	        for (int i = 0; i < array.length(); i++) {
	            try {
	                JSONObject arrayObj = (JSONObject) array.get(i);
	                List<String> list = new ArrayList<>(arrayObj.keySet());
	                for (String s : list) {
	                    putNestedKeysToList(keys, key, s);
	                    addNestedKeys(arrayObj, keys, s);
	                }
	            } catch (JSONException e) {
	               // LOG.error("", e);
	            }
	        }
	    } else if (isNestedJsonAnObject(obj.get(key))) {
	        JSONObject arrayObj = (JSONObject) obj.get(key);
	        List<String> nestedKeys = new ArrayList<>(arrayObj.keySet());
	        for (String s : nestedKeys) {
	            putNestedKeysToList(keys, key, s);
	            addNestedKeys(arrayObj, keys, s);
	        }
	    }
	    return keys;
	}

	private static void putNestedKeysToList(List<String> keys, String key, String s) {
	    if (!keys.contains(key + ":" + s)) {
	       // keys.add(key + Constants.JSON_KEY_SPLITTER + s);
	    	keys.add(key + ":" + s);
	    }
	}



	private static boolean isNestedJsonAnObject(Object object) {
	    boolean bool = false;
	    if (object instanceof JSONObject) {
	        bool = true;
	    }
	    return bool;
	}

	private static boolean isNestedJsonAnArray(Object object) {
	    boolean bool = false;
	    if (object instanceof JSONArray) {
	        bool = true;
	    }
	    return bool;
	}
	
	
	


}
