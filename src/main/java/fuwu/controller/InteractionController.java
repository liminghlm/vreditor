package fuwu.controller;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import fuwu.commen.JsonResult;
import fuwu.em.GlobalErrorEnum;
import fuwu.em.InteractionTypeEnum;
import fuwu.mapper.InteractionMapper;
import fuwu.mapper.MediaInteractionRelationMapper;
import fuwu.po.Interaction;
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

import java.util.List;

/**
 * Created by LJW on 2018/4/20 - 11:59
 */

@Controller
@RequestMapping("/interaction")
public class InteractionController {


    private static final Logger LOGGER = LoggerFactory.getLogger(InteractionController.class);

    private static final String SEPERATOR = ",";

    @Autowired
    private InteractionMapper interactionMapper;
    @Autowired
    private MediaInteractionRelationMapper mediaInteractionRelationMapper;
    @Autowired
    private ViewRelationService viewRelationService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public JsonResult createInteraction(Interaction interaction,String mediaIdList) {
        if (!ParamCheckUtil.checkParamsNotNull(interaction.getInteractionPosition(),interaction.getViewId(),mediaIdList)) {
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

        interaction.setInteractionType(mediaIntList.size()>1 ? InteractionTypeEnum.BASE_INTERACTION.getInteractiontype() : InteractionTypeEnum.COMPLEXY_INTERACTION.getInteractiontype());

        if (interactionMapper.createInteraction(interaction)>0) {
            if (mediaInteractionRelationMapper.batchInsertMediaInteractionRelation(interaction.getId(),mediaIntList)>0) {
                return JsonResultUtil.createSucess(viewRelationService.getRealViewRelationListByViewId(interaction.getViewId()));
            } else {
                interactionMapper.deleteInteraction(interaction);
            }
        }

        return JsonResultUtil.createError(GlobalErrorEnum.ERROR);


    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public JsonResult deleteInteraction(Interaction interaction) {
        if (!ParamCheckUtil.checkParamsNotNull(interaction.getId())) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }


        int result = interactionMapper.deleteInteraction(interaction);


        result += mediaInteractionRelationMapper.deleteByInteractionId(interaction.getId());


        return result>0 ? JsonResultUtil.createSucess(null) : JsonResultUtil.createError(GlobalErrorEnum.ERROR);
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.GET)
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


        interaction.setInteractionType(mediaIntList.size()>1 ? InteractionTypeEnum.BASE_INTERACTION.getInteractiontype() : InteractionTypeEnum.COMPLEXY_INTERACTION.getInteractiontype());
        interactionMapper.updateInteraction(interaction);

        mediaInteractionRelationMapper.deleteByInteractionId(interaction.getId());


        mediaInteractionRelationMapper.batchInsertMediaInteractionRelation(interaction.getId(),mediaIntList);


        return JsonResultUtil.createSucess(viewRelationService.getRealViewRelationListByViewId(interaction.getViewId()));
    }



}

