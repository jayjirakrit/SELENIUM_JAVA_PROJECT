package th.co.framework.qa.ui.model.framework;

import com.google.gson.annotations.SerializedName;
import th.co.framework.qa.ui.model.BaseModelUi;

public class LoginModels extends BaseModelUi {

    @SerializedName("test_data")
    public
    TestData testData;

    public class TestData {
        public Body body;
    }

    public class Body {
        public String username;
        public String password;
    }

}
