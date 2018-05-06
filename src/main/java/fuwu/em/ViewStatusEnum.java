package fuwu.em;

/**
 * @Author: ypc
 * @Date: 2018/4/22 17:50
 * @Description:
 */
public enum ViewStatusEnum {
    SUCESS(0,"sucess"),
    ERROR(1,"view error");


    private Integer viewStatusCode;
    private String viewMsg;

    public Integer getViewStatusCode() {
        return viewStatusCode;
    }

    public String getViewMsg() {
        return viewMsg;
    }

    ViewStatusEnum(Integer statusCode, String msg) {
        this.viewStatusCode =statusCode;
        this.viewMsg =msg;

    }
    public static  ViewStatusEnum getViewStatusEnumByCode(Integer code){
        for(ViewStatusEnum viewStatusEnum: ViewStatusEnum.values() ){
            if(viewStatusEnum.getViewStatusCode()==code){
                return viewStatusEnum;
            }
        }
        return null;
    }
}
