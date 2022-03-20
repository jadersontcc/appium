package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {
    public static Object[][] getJSONData(String JSON_path, String JSON_Data, int JSON_attributes) throws FileNotFoundException, IOException, ParseException{
        Object obj = new JSONParser().parse(new FileReader(JSON_path));
        JSONObject jo = (JSONObject) obj;
        JSONArray js = (JSONArray) jo.get(JSON_Data);

        Object[][] arr = new String[js.size()][JSON_attributes];
        for (int i = 0 ; i < js.size(); i++){
            JSONObject obj1 = (JSONObject) js.get(i);
            arr[i][0] = String.valueOf(obj1.get("TaskName"));
            arr[i][1] = String.valueOf(obj1.get("TaskDescription"));
        }
        return arr;
    }
}
