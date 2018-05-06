package fuwu.em;

import java.util.Objects;

/**
 * @Author: ypc
 * @Date: 2018/4/24 0:33
 * @Description:
 */
public enum  ViewRelationEnum {
    PARENT_SON(0,"父子"),
    LEFT_RIGHT(1,"左右"),
    UP_DOWN(2,"上下")
    ;

    private Integer viewRelationCode;
    private String viewRelationDesc;

    ViewRelationEnum(Integer viewRelationCode, String viewRelationDesc) {
        this.viewRelationCode = viewRelationCode;
        this.viewRelationDesc = viewRelationDesc;
    }

    public Integer getViewRelationCode() {
        return viewRelationCode;
    }

    public String getViewRelationDesc() {
        return viewRelationDesc;
    }

    public static ViewRelationEnum getViewRelationEnumByCode(Integer code) {
        for(ViewRelationEnum viewRelationEnum: ViewRelationEnum.values() ){
            if(Objects.equals(viewRelationEnum.getViewRelationCode(), code)){
                return viewRelationEnum;
            }
        }
        return null;
    }
}
