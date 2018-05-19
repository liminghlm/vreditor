package fuwu.em;

/**
 * @Author: ypc
 * @Date: 2018/4/22 17:50
 * @Description:
 */
public enum GlobalErrorEnum {
    SUCESS(0,"操作成功"),
    ERROR(1,"操作失败"),
    PARAM_NULL_ERROR(3,"参数不合法"),
    MAIN_VEW_CONFLICT(4,"主场景已存在");


    private Integer errorCode;
    private String errorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    GlobalErrorEnum(Integer statusCode, String msg) {
        this.errorCode =statusCode;
        this.errorMsg =msg;

    }
    public static GlobalErrorEnum getViewStatusEnumByCode(Integer code){
        for(GlobalErrorEnum viewStatusEnum: GlobalErrorEnum.values() ){
            if(viewStatusEnum.getErrorCode()==code){
                return viewStatusEnum;
            }
        }
        return null;
    }
}
