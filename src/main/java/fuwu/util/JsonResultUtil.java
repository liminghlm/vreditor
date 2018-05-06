package fuwu.util;

import fuwu.commen.JsonResult;
import fuwu.em.ViewStatusEnum;

/**
 * @Author: ypc
 * @Date: 2018/4/22 18:02
 * @Description:
 */
public class JsonResultUtil {
    public static JsonResult createSucess(Object object){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setIsSuccess(true);
        jsonResult.setData(object);
        jsonResult.makeStatusAndMsg(ViewStatusEnum.SUCESS);
        return  jsonResult;
    }
        public static JsonResult createError(ViewStatusEnum statusEnum){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setIsSuccess(false);
        jsonResult.makeStatusAndMsg(statusEnum);
        return jsonResult;
    }

}
