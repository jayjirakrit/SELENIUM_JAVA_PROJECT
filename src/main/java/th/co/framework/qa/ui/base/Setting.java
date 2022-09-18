package th.co.framework.qa.ui.base;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumMap;

public class Setting {

    public static final String DATE_TIME = new SimpleDateFormat("yyyy-MM-dd hh mm:ss").format(new Date());
    public static String SERVER = "local";
    public static AUTOMATE_TYPE AUTOMATE = AUTOMATE_TYPE.WEB;
    public static String PROJECT_NAME = "Framework Project";
    public static String TEAM_NAME = "Framework QA Team";
    public static final String KEY_REPORT_EXPECTED_RESULT = "Expected Result: ";
    public static final String KEY_REPORT_INPUT_DATA = "Input data: ";
    public static final String KEY_REPORT_SCREEN_CAPTURE = "Screen capture: ";


    public enum RESULT_TYPE {
        PASS,
        FAILED,
        SKIP
    }

    public enum AUTOMATE_TYPE {
        WEB,
        APP,
        API
    }

    public enum VERIFIER_USER_ROLE {
        ALL,
        BANK_ADMIN,
        KEY_USER,
        STANDARD_USER,
        DORMANT_USER
    }

    public class WebBrowser {
        public  static final String IE = "ie";
        public  static final String CHROME = "chrome";
        public  static final String FIREFOX = "firefox";
        public static final String OPERA = "opera";
        public  static final String SAFARI = "safari";
        public  static final String MICROSOFT_EDGE = "edge";
    }

    public class AddLogsParameter {
        public static final String DESCRIPTION = "description";
        public static final String EXPECTED_RESULT = "expected_result";
        public static final String TEST_CASE_ID = "test_case_id";

    }

    public static class Config {
        public static Boolean IS_EXPORT_EXCEL = false;
        public static Boolean IS_EXPORT_PDF = false;
        public static Boolean IS_SEND_EMAIL = false;
        public static Boolean IS_SAVE_LOGS = false;

    }

    public static class EmailConfig {
        public static String SMTP_SERVER = "";
        public static String USER_NAME = "";
        public static String PASSWORD = "";
        public static String EMAIL_FROM = "";
    }


}


