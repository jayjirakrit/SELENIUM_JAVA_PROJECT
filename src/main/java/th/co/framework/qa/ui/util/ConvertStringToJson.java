package th.co.framework.qa.ui.util;

import com.google.gson.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConvertStringToJson {

    public static JSONObject convert(String responseBody) throws ParseException {
        JSONParser parser = new JSONParser();

        return (JSONObject) parser.parse(responseBody);
    }

    public static String jsonFormat(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }

    public static String jsonFormatArray(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonArray json = parser.parse(jsonString).getAsJsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }

    public static String jsonFormatObject(JSONObject obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(obj);

        return prettyJson;
    }

}
