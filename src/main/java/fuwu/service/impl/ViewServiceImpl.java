package fuwu.service.impl;

import fuwu.bo.RealInteraction;
import fuwu.bo.RealViewRelation;
import fuwu.bo.ViewDetail;
import fuwu.mapper.ViewMapper;
import fuwu.po.View;
import fuwu.service.InteractionService;
import fuwu.service.ViewRelationService;
import fuwu.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LJW on 2018/4/19 - 23:13
 */
@Service
public class ViewServiceImpl implements ViewService {




    @Autowired
    private ViewMapper viewMapper;
    @Autowired
    private InteractionService interactionService;
    @Autowired
    private ViewRelationService viewRelationService;

    @Autowired
    private PicutureCompressService picutureCompressService;


    @Override
    public View getMainViewByProjectId(Integer projectId) {
        return viewMapper.getMainViewByProjectId(projectId);
    }

    @Override
    public ViewDetail getViewDetailByViewId(Integer viewId) {
        //todo View to ViewDetail
        ViewDetail viewDetail =new ViewDetail();
        View view = viewMapper.getViewByViewId(viewId);
        List<RealInteraction> realInteractionList=interactionService.getInterationlistByViewId(viewId);
        List<RealViewRelation> realViewRelationList=viewRelationService.getRealViewRelationListByViewId(viewId);
        viewDetail.setView(view);
        viewDetail.setIntetractionList(realInteractionList);
        viewDetail.setRealViewRelationList(realViewRelationList);
        return viewDetail;
    }

    @Override
    public List<View> getViewListByProjectId(Integer projectId) {
        return null;
    }

    @Override
    public boolean createView(View view) {
//        if (viewMapper.createView(view)) {
//            if (picutureCompressService.compressAndUpdate(view)) {
//                return true;
//            } else {
//                viewMapper.deletePhysically(view);
//            }
//        }

        return false;
    }


}
