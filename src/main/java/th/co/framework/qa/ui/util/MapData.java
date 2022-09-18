package th.co.framework.qa.ui.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public  class MapData {
    public static Map pojoToMap(Object fromValue){
        return (Map) new ObjectMapper().convertValue(fromValue, new TypeReference<Map<String, Object>>() {});
    }
}
