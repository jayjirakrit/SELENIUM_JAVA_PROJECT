package th.co.framework.qa.ui.util;

import org.json.simple.JSONObject;

public class GetJsonDataUi {

    public static JSONObject userLoginSuccessTestCase() {
        return ReadJson.readJson("json/framework/login_success_testcase.json");
    }

    public static JSONObject userLoginFailedTestCase() {
        return ReadJson.readJson("json/framework/login_failed_testcase.json");
    }

    public static JSONObject getEnvironment() {
        return ReadJson.readJson("json/environment.json");
    }

    public static JSONObject getConfig() {
        return ReadJson.readJson("json/config.json");
    }

}
