package fuwu.util;

public class ParamCheckUtil {
    public static boolean checkParamsNotNull(Object ... objects){
        for (Object s : objects) {
            if (null == s) {
                return false;
            }
        }

        return true;
    }

}
