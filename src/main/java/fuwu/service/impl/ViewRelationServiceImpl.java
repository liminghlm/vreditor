package fuwu.service.impl;

import fuwu.bo.RealViewRelation;
import fuwu.bo.ViewRelationWithName;
import fuwu.controller.ViewRelationController;
import fuwu.mapper.ViewRelationMapper;
import fuwu.po.View;
import fuwu.po.ViewRelation;
import fuwu.service.ViewRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewRelationServiceImpl.class);

    @Autowired
    private ViewRelationMapper viewRelationMapper;
    @Override
    public List<RealViewRelation> getRealViewRelationListByViewId(Integer viewId) {
        //todo ViewRelation to RealViewRelation
        List<ViewRelation> viewRelationList=viewRelationMapper.getViewRelationListByViewId(viewId);
        List<RealViewRelation> realViewRelationList=new ArrayList<RealViewRelation>(viewRelationList.size());
        for (ViewRelation viewRelation: viewRelationList){
            RealViewRelation realViewRelation=new RealViewRelation(viewRelation);
            realViewRelation.setRealViewRelation(RealViewRelation.makeRealViewRelation(realViewRelation,viewId));
            realViewRelation.setRealRelationViewId(RealViewRelation.makeRealRelationViewId(realViewRelation,viewId));
            realViewRelationList.add(realViewRelation);
        }
        return realViewRelationList;
    }

    @Override
    public List<ViewRelationWithName> getViewRelationListByProjectId(Integer projectId) {
        return viewRelationMapper.getViewRelationListByProjectId(projectId);
    }

    @Override
    public Boolean createViewRelation(ViewRelation viewRelation) {
        boolean result = false;
        try {
            result = viewRelationMapper.createViewRelation(viewRelation);
        }catch (Exception e) {
            LOGGER.error("operate mysql error",e);
        }
        return result;
    }

    @Override
    public Integer deleteViewRelation(Integer viewRelationId) {
        return viewRelationMapper.deleteViewRelation(viewRelationId);
    }
}
