package th.co.framework.qa.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseModelUi {
    @SerializedName("scenario_name")
    public String scenarioName;
    @SerializedName("description")
    public String description;
    @SerializedName("expected_result")
    public ArrayList<String> expectedResult;
    @SerializedName("expected_result_key")
    public ArrayList<String> expectedResultKey;
    @SerializedName("logs")
    public ArrayList<String> logs;
    @SerializedName("is_skip")
    public boolean isSkip;
}


