package fuwu.service;

import fuwu.bo.RealViewRelation;
import fuwu.po.ViewRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/28 1:15
 * @Description:
 */
public interface ViewRelationService {
    public List<RealViewRelation> getRealViewRelationListByViewId(Integer viewId);
}
