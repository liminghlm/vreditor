package fuwu.service.impl;

import fuwu.bo.RealViewRelation;
import fuwu.mapper.ViewRelationMapper;
import fuwu.po.ViewRelation;
import fuwu.service.ViewRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/28 1:15
 * @Description:
 */
@Service
public class ViewRelationServiceImpl implements ViewRelationService {
    @Autowired
    private ViewRelationMapper viewRelationMapper;
    @Override
    public List<RealViewRelation> getRealViewRelationListByViewId(Integer viewId) {
        //todo ViewRelation to RealViewRelation
        List<ViewRelation> viewRelationList=viewRelationMapper.getViewRelationListByViewId(viewId);
        List<RealViewRelation> realViewRelationList=new ArrayList<RealViewRelation>(viewRelationList.size());
        for (int i=0;i<viewRelationList.size();i++){
            RealViewRelation realViewRelation=new RealViewRelation(viewRelationList.get(i));
            realViewRelation.setRealViewRelation(RealViewRelation.makeRealViewRelation(realViewRelation,viewId));
            realViewRelation.setRealRelationViewId(RealViewRelation.makeRealRelationViewId(realViewRelation,viewId));
            realViewRelationList.add(realViewRelation);
        }
        return realViewRelationList;
    }
}
