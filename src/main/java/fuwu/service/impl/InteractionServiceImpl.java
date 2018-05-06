package fuwu.service.impl;

import fuwu.bo.RealInteraction;
import fuwu.mapper.InteractionMapper;
import fuwu.po.Interaction;
import fuwu.po.Media;
import fuwu.service.InteractionService;
import fuwu.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/28 1:07
 * @Description:
 */
@Service
public class InteractionServiceImpl implements InteractionService{
    @Autowired
     private InteractionMapper interactionMapper;
    @Autowired
    private MediaService mediaService;

    @Override
    public List<RealInteraction> getInterationlistByViewId(Integer viewId) {
        List<Interaction> interactionList=interactionMapper.getInterationlistByViewId(viewId);
        List<RealInteraction> realInteractionList=new ArrayList<RealInteraction>(interactionList.size());
        for (int i = 0; i < interactionList.size(); i++) {
            List<Media> mediaList=mediaService.getMediaListByInteractionId(interactionList.get(i).getId());
            RealInteraction realInteraction=new RealInteraction(interactionList.get(i));
            realInteraction.setMediaList(mediaList);
            realInteractionList.add(realInteraction);
        }
        return realInteractionList;
    }
}
