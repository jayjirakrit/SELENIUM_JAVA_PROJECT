package th.co.framework.qa.ui.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public  class DateTime {
    public static boolean isLogsTime(Long startTime,Long endTime,int min){
        return TimeUnit.MILLISECONDS.toMinutes((startTime-endTime)) <=min;
    }

    public static Long getStartTime(){
        return new Long(System.currentTimeMillis());
    }

    public static Long getEndTime(String activityDate){
        return new Long(Long.parseLong(activityDate));
    }

    public static String getMongoCollectionName(String mongoCollectionName){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("yyyyMM");
        return mongoCollectionName + myDateObj.format(formatObj);
    }
}
