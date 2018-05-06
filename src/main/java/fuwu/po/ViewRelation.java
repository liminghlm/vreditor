package fuwu.po;

import com.alibaba.fastjson.annotation.JSONType;


import java.io.Serializable;

/**
 * @Author: ypc
 * @Date: 2018/4/22 18:55
 * @Description:
 */
@JSONType(ignores ={"viewId", "relationViewId"})
public class ViewRelation implements Serializable{
    private Integer id;

    private Integer viewId;

    private Integer relationViewId;
    private Integer viewRelation;
    private String viewInteractionPosition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelationViewId() {
        return relationViewId;
    }

    public void setRelationViewId(Integer relationViewId) {
        this.relationViewId = relationViewId;
    }

    public Integer getViewRelation() {
        return viewRelation;
    }

    public void setViewRelation(Integer viewRelation) {
        this.viewRelation = viewRelation;
    }

    public String getViewInteractionPosition() {
        return viewInteractionPosition;
    }

    public void setViewInteractionPosition(String viewInteractionPosition) {
        this.viewInteractionPosition = viewInteractionPosition;
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }
}
