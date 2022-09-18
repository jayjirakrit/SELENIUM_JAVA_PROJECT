package th.co.framework.qa.ui.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;

public class ReadJson {
    public static JSONObject readJson(String patch) {

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(patch);
            Object obj = parser.parse(new InputStreamReader(is));
            jsonObject = (JSONObject) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static String getFilePathFromResource(String path) {
        String filePath = null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL imagePath = classloader.getResource(path);
            File file = Paths.get(imagePath.toURI()).toFile();
            filePath = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
