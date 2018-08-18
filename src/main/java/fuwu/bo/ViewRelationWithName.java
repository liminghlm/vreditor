package fuwu.bo;

import fuwu.po.ViewRelation;

/**
 * @Author: ypc
 * @Date: 2018/7/22 14:53
 * @Description:
 */
public class ViewRelationWithName extends ViewRelation {
    private String viewName;
    private String relationViewName;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getRelationViewName() {
        return relationViewName;
    }

    public void setRelationViewName(String relationViewName) {
        this.relationViewName = relationViewName;
    }
}
