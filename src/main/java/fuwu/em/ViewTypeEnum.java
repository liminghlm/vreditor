package fuwu.em;

import java.util.Objects;

public enum ViewTypeEnum {
    NORMAL_VIEW(0,"普通场景"),
    MAIN_VIEW(1,"主场景"),
    ;

    private Integer viewType;
    private String viewTypeDesc;

    ViewTypeEnum(Integer viewType, String viewTypeDesc) {
        this.viewType = viewType;
        this.viewTypeDesc = viewTypeDesc;
    }

    public Integer getViewType() {
        return viewType;
    }

    public String getViewTypeDesc() {
        return viewTypeDesc;
    }
}
