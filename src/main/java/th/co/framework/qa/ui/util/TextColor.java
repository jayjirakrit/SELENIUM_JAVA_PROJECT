package th.co.framework.qa.ui.util;

public class TextColor {
    public static String redColor(String text) {
        return "<font color=\"LightCoral\">" + text + "</font>";
    }

    public static String greenColor(String text) {
        return "<font color=\"SeaGreen\">" + text + "</font>";
    }

    public static String resultColor(boolean isTrue, String text) {
        if (isTrue) {
            return "<font color=\"SeaGreen\">" + text + "</font>";
        } else {
            return "<font color=\"LightCoral\">" + text + "</font>";
        }
    }

    public static String blueColor(String text) {
        return "<font color=\"CornflowerBlue\">" + text + "</font>";
    }

}
