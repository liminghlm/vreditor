package fuwu.bo;

import fuwu.em.ViewRelationEnum;
import fuwu.po.ViewRelation;

/**
 * @Author: ypc
 * @Date: 2018/4/24 0:31
 * @Description:
 */
public class RealViewRelation extends ViewRelation {
    private String realViewRelation;
    private Integer realRelationViewId;

    private String realRelationViewName;

    public RealViewRelation() {
    }

    public RealViewRelation(ViewRelation viewRelation) {
        super.setId(viewRelation.getId());
        super.setViewId(viewRelation.getViewId());
        super.setRelationViewId(viewRelation.getRelationViewId());
        super.setViewRelation(viewRelation.getViewRelation());
        super.setViewInteractionPosition(viewRelation.getViewInteractionPosition());
    }

    public Integer getRealRelationViewId() {
        return realRelationViewId;
    }

    public void setRealRelationViewId(Integer realRelationViewId) {
        this.realRelationViewId = realRelationViewId;
    }

    public String getRealViewRelation() {
        return realViewRelation;
    }

    public void setRealViewRelation(String realViewRelation) {
        this.realViewRelation = realViewRelation;
    }

    public static String makeRealViewRelation (RealViewRelation realViewRelation,Integer viewId) {

        return viewId.equals(realViewRelation.getViewId())
                ? ViewRelationEnum.getViewRelationEnumByCode(realViewRelation.getViewRelation())
                .getViewRelationDesc().substring(1)
                : ViewRelationEnum.getViewRelationEnumByCode(realViewRelation.getViewRelation())
                .getViewRelationDesc().substring(0,1);
    }
    public  static Integer makeRealRelationViewId( RealViewRelation realViewRelation,Integer viewId){
        return viewId.equals(realViewRelation.getViewId())
                ? realViewRelation.getRelationViewId()
                : realViewRelation.getViewId();
    }

    public String getRealRelationViewName() {
        return realRelationViewName;
    }

    public void setRealRelationViewName(String realRelationViewName) {
        this.realRelationViewName = realRelationViewName;
    }
}
