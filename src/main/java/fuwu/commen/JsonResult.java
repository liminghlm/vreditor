package fuwu.commen;


import fuwu.em.GlobalErrorEnum;

/**
 * Created by LJW on 2018/4/13.16:22
 */
public class JsonResult {



    private Boolean isSuccess;
    private Object data;

    private Integer statusCode;
    private String msg;
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        isSuccess = success;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonResult() {
    }

    public JsonResult(Boolean isSuccess, Object data, Integer statusCode, String msg) {
        this.isSuccess = isSuccess;
        this.data=data;
        this.statusCode=statusCode;
        this.msg = msg;
    }

       public void makeStatusAndMsg(GlobalErrorEnum statusEnum){
       setStatusCode(statusEnum.getErrorCode());
       setMsg(statusEnum.getErrorMsg());
    }
}
