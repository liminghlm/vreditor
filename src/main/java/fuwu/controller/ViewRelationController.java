package fuwu.controller;

import fuwu.aop.ForLog;
import fuwu.commen.JsonResult;
import fuwu.em.GlobalErrorEnum;
import fuwu.em.ViewRelationEnum;
import fuwu.mapper.ViewMapper;
import fuwu.po.View;
import fuwu.po.ViewRelation;
import fuwu.service.ViewRelationService;
import fuwu.util.JsonResultUtil;
import fuwu.util.ParamCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: ypc
 * @Date: 2018/4/27 23:52
 * @Description:
 */

@Controller
@RequestMapping("/viewRelation")
public class ViewRelationController {

    @Autowired
    private ViewRelationService viewRelationService;

    @Autowired
    private ViewMapper viewMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewRelationController.class);


    @ResponseBody
    @ForLog
    @RequestMapping(value = "/geViewRelationListByProjectId", method = RequestMethod.POST)
    public JsonResult geViewRelationListByProjectId(Integer projectId) {

        if (!ParamCheckUtil.checkParamsNotNull(projectId)) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }

        return JsonResultUtil.createSucess(
                viewRelationService.getViewRelationListByProjectId(projectId)
        );



    }

    @ResponseBody
    @ForLog
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult deleteViewRelation(Integer viewRelationId) {

        if (!ParamCheckUtil.checkParamsNotNull(viewRelationId)) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }

        if (viewRelationService.deleteViewRelation(viewRelationId)>0) {
            return JsonResultUtil.createSucess(null);
        } else {
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }


    }


    @ResponseBody
    @ForLog
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createView(ViewRelation viewRelation) {

        if (!ParamCheckUtil.checkParamsNotNull(viewRelation.getViewId(),viewRelation.getRelationViewId(),viewRelation.getViewRelation())
                ||
            !isInteractionPositionValid(viewRelation)
                ) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }


        View view = viewMapper.getViewByViewId(viewRelation.getViewId()) ;
        View relationView = viewMapper.getViewByViewId(viewRelation.getRelationViewId());
        if ( !ParamCheckUtil.checkParamsNotNull(view,relationView) && view.getProjectId().equals(relationView.getProjectId())) {
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }


        if (viewRelationService.createViewRelation(viewRelation)) {
            return JsonResultUtil.createSucess(viewRelation);
        } else {
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }


    }



    /**
     * 如果场景关系为父子关系 那么场景关系的坐标不能为空
     * @param viewRelation
     * @return
     */
    private boolean isInteractionPositionValid(ViewRelation viewRelation) {
        if (viewRelation.getViewRelation().equals(ViewRelationEnum.PARENT_SON.getViewRelationCode())) {
            return !StringUtils.isEmpty(viewRelation.getViewInteractionPosition());
        }

        return true;
    }

}
