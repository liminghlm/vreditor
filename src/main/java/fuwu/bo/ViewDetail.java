package fuwu.bo;

import fuwu.po.View;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/22 18:53
 * @Description:
 */
public class ViewDetail {
    private View view;
    private List<RealInteraction> intetractionList;
    private List<RealViewRelation> realViewRelationList;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<RealInteraction> getIntetractionList() {
        return intetractionList;
    }

    public void setIntetractionList(List<RealInteraction> intetractionList) {
        this.intetractionList = intetractionList;
    }

    public List<RealViewRelation> getRealViewRelationList() {
        return realViewRelationList;
    }

    public void setRealViewRelationList(List<RealViewRelation> realViewRelationList) {
        this.realViewRelationList = realViewRelationList;
    }
}
