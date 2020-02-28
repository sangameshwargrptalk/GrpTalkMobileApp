package Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {
	static String path = System.getProperty("user.dir") + "/Resources/";
	
	public static String setFileName(String fileName) {
		System.out.println(path);
		String filePath=path+fileName;
		System.out.println(filePath);
		return filePath;
		
	}
	public  String getJsonValue(String key) {
		String value = null;
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		//String cabailitiesFile=setFileName("capabilities.json");
		try {
			Object object = parser.parse(new FileReader("C:\\Users\\smsc\\eclipse-workspace\\GrpTalkAndrodi\\Resources\\capabilities.json"));
			jsonObject = (JSONObject) object;
			value = (String) jsonObject.get(key).toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
