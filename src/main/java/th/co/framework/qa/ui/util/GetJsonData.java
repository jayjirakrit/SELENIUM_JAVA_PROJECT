package th.co.framework.qa.ui.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetJsonData {

    public static JSONObject verifierGetLoginTestCase() {
        return ReadJson.readJson("json/verifier/login_testcase_" + System.getProperty("server") + ".json");
    }

    public static JSONObject holderGetLoginTestCase() {
        return ReadJson.readJson("json/holder/login_testcase_" + System.getProperty("server") + ".json");
    }


    public static JSONObject holderCredentialsStatusTestCase() {
        return ReadJson.readJson("json/holder/verify_credentials_testcase_" + System.getProperty("server") + ".json");
    }

    public static JSONObject holderPresentationRequestStatusTestCase() {
        return ReadJson.readJson("json/holder/verify_presentation_request_testcase_" + System.getProperty("server") + ".json");
    }

    public static JSONObject holderViewUpdateProfileTestCase() {
        return ReadJson.readJson("json/holder/view_update_profile_testcase_" + System.getProperty("server") + ".json");
    }

    public static JSONObject holderLogoutTestCase() {
        return ReadJson.readJson("json/holder/logout_testcase_" + System.getProperty("server") + ".json");
    }


    public static JSONObject getDevices() {
        return ReadJson.readJson("json/device.json");
    }

    public static JsonObject verifierGetViewOrganizationTestCase() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src", "main", "resources", "json", "verifier", "view_organization_testcase.json").toAbsolutePath());
        return new Gson().fromJson(reader, JsonObject.class);
    }

    public static JSONObject verifierGetRequestPresentationTestCase() {
        return ReadJson.readJson("json/verifier/request_presentation_testcase_"+ System.getProperty("server") +".json");
    }

    public static JSONObject verifierGetUpdateOrganizationTestCase() {
        return ReadJson.readJson("json/verifier/edit_organization_testcase_" + System.getProperty("server") + ".json");
    }

    public static JSONObject verifierGetRequestConnectionTestCase() {
        return ReadJson.readJson("json/verifier/request_connection_testcase_"+ System.getProperty("server")+".json");
    }

    public static JSONObject verifierGetAcceptConnectionTestCase() {
        return ReadJson.readJson("json/verifier/update_connection_testcase_"+ System.getProperty("server")+".json");
    }

    public static JSONObject verifierCreateNewUserTestCase() {
        return ReadJson.readJson("json/verifier/create_user_testcase_" + System.getProperty("server") + ".json");
    }

    public static JSONObject verifierViewNewUserTestCase() {
        return ReadJson.readJson("json/verifier/view_user_testcase_" + System.getProperty("server") + ".json");
    }

    public static JSONObject getEnvironment() {
        return ReadJson.readJson("json/environment.json");
    }

    public static JSONObject getConfig() {
        return ReadJson.readJson("json/config.json");
    }

}
