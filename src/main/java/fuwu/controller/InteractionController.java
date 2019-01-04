package fuwu.controller;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import fuwu.aop.ForLog;
import fuwu.bo.RealInteraction;
import fuwu.commen.JsonResult;
import fuwu.em.GlobalErrorEnum;
import fuwu.em.InteractionTypeEnum;
import fuwu.mapper.InteractionMapper;
import fuwu.mapper.MediaInteractionRelationMapper;
import fuwu.mapper.MediaMapper;
import fuwu.mapper.ViewMapper;
import fuwu.po.Interaction;
import fuwu.po.Media;
import fuwu.service.ViewRelationService;
import fuwu.util.JsonResultUtil;
import fuwu.util.ParamCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LJW on 2018/4/20 - 11:59
 */

@Controller
@RequestMapping("/vrBk/interaction")
public class InteractionController {


    private static final Logger LOGGER = LoggerFactory.getLogger(InteractionController.class);

    private static final String SEPERATOR = ",";

    @Autowired
    private InteractionMapper interactionMapper;
    @Autowired
    private MediaInteractionRelationMapper mediaInteractionRelationMapper;
    @Autowired
    private ViewRelationService viewRelationService;
    @Autowired
    private MediaMapper mediaMapper;

    @ResponseBody
    @ForLog
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createInteraction(Interaction interaction,String mediaIds) {
        if (!ParamCheckUtil.checkParamsNotNull(interaction.getInteractionPosition(),interaction.getViewId(), mediaIds)) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }


        List<Integer> mediaIntList =
        Lists.transform(Splitter.on(SEPERATOR).trimResults().omitEmptyStrings().splitToList(mediaIds), new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        });

        if (ParamCheckUtil.checkIntListEmpty(mediaIntList)) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }

        mediaIntList = deleteDuplicateMedia(mediaIntList);
        interaction.setInteractionType(mediaIntList.size()>1 ? InteractionTypeEnum.COMPLEXY_INTERACTION.getInteractiontype() : InteractionTypeEnum.BASE_INTERACTION.getInteractiontype());

        if (interactionMapper.createInteraction(interaction)>0) {
            if (mediaInteractionRelationMapper.batchInsertMediaInteractionRelation(interaction.getId(),mediaIntList)>0) {
                RealInteraction realInteraction = new RealInteraction(interactionMapper.getInterationById(interaction.getId()));
                realInteraction.setMediaList(
                       mediaMapper.getMediaListByInteractionId(interaction.getId())
                );

                return JsonResultUtil.createSucess(realInteraction);
            } else {
                interactionMapper.deleteInteraction(interaction);
            }
        }

        return JsonResultUtil.createError(GlobalErrorEnum.ERROR);


    }

    @ResponseBody
    @ForLog
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult deleteInteraction(Interaction interaction) {
        if (!ParamCheckUtil.checkParamsNotNull(interaction.getId())) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }


        int result = interactionMapper.deleteInteraction(interaction);


        result += mediaInteractionRelationMapper.deleteByInteractionId(interaction.getId());


        return result>0 ? JsonResultUtil.createSucess(null) : JsonResultUtil.createError(GlobalErrorEnum.ERROR);
    }


    @ResponseBody
    @ForLog
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult updateInteraction(Interaction interaction,String mediaIdList) {
        if (!ParamCheckUtil.checkParamsNotNull(interaction.getId())) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }

        List<Integer> mediaIntList =
                Lists.transform(Splitter.on(SEPERATOR).trimResults().omitEmptyStrings().splitToList(mediaIdList), new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                });

        if (ParamCheckUtil.checkIntListEmpty(mediaIntList)) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }


        mediaIntList = deleteDuplicateMedia(mediaIntList);

        interaction.setInteractionType(mediaIntList.size()>1 ? InteractionTypeEnum.BASE_INTERACTION.getInteractiontype() : InteractionTypeEnum.COMPLEXY_INTERACTION.getInteractiontype());
        interactionMapper.updateInteraction(interaction);

        mediaInteractionRelationMapper.deleteByInteractionId(interaction.getId());


        mediaInteractionRelationMapper.batchInsertMediaInteractionRelation(interaction.getId(),mediaIntList);


        return JsonResultUtil.createSucess(viewRelationService.getRealViewRelationListByViewId(interaction.getViewId()));
    }

    private List<Integer> deleteDuplicateMedia(List<Integer> mediaIntList) {

        List<Media> mediaList = mediaMapper.getMediaListByIdList(mediaIntList);

        Map<Integer,Media> mediaMap = new HashMap<>();

        for(Media media : mediaList) {
            Integer curMediaType = media.getMediaType();
            if(!mediaMap.containsKey(curMediaType) || (mediaMap.containsKey(curMediaType)) && media.getId()>mediaMap.get(curMediaType).getId()) {
                mediaMap.put(media.getMediaType(),media);
            }
        }

        List<Integer> resultList = new ArrayList<>();

        for(Media media : mediaMap.values()) {
            resultList.add(media.getId());
        }

        return resultList;

    }


    @ResponseBody
    @ForLog
    @RequestMapping(value = "/getRealInteractionById", method = RequestMethod.GET)
    public JsonResult getInteractionWithMedia(Integer interactionId) {
        if (!ParamCheckUtil.checkParamsNotNull(interactionId)) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }

        RealInteraction realInteraction = new RealInteraction(interactionMapper.getInterationById(interactionId));
        realInteraction.setMediaList(
                mediaMapper.getMediaListByInteractionId(interactionId)
        );


        return JsonResultUtil.createSucess(realInteraction);
    }


}

