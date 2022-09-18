package th.co.framework.qa.ui.util;

import com.google.gson.Gson;
import th.co.framework.qa.ui.base.Setting;
import th.co.framework.qa.ui.model.EnvironmentModel;


public class ConfigurationWeb {
    public static String setup(String webBrowserType, String serviceName) {
        String baseUrlUi = "";
        EnvironmentModel dataModel = new Gson().fromJson(String.valueOf(GetJsonData.getEnvironment()), EnvironmentModel.class);

        final String AUTOMATE_TYPE = System.getProperty("automateType").toLowerCase();
        final String SERVER = System.getProperty("environment").toLowerCase();

        if (AUTOMATE_TYPE.toLowerCase().equals("web")) {
            Setting.AUTOMATE = Setting.AUTOMATE_TYPE.WEB;
        }
        Setting.Config.IS_SEND_EMAIL = Boolean.parseBoolean(System.getProperty("sendExportEmail"));
        Setting.Config.IS_EXPORT_EXCEL = Boolean.parseBoolean(System.getProperty("generateExportExcelFile"));
        Setting.Config.IS_EXPORT_PDF = Boolean.parseBoolean(System.getProperty("generateExportPdfFile"));
        Setting.Config.IS_SAVE_LOGS = Boolean.parseBoolean(System.getProperty("saveLogs"));
        return baseUrlUi;
    }
}
