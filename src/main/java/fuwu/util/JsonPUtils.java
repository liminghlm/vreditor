package fuwu.util;

import com.alibaba.fastjson.JSON;

/**
 * @Author: ypc
 * @Date: 2018/5/5 13:41
 * @Description:
 */
public class JsonPUtils {
    public static String makeJsonP(Object object,String jsonP) {
        return jsonP+"("+JSON.toJSON(object)+")";
    }
}
