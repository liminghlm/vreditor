package fuwu.controller;

import com.alibaba.fastjson.JSON;
import fuwu.bo.RealInteraction;
import fuwu.bo.RealViewRelation;
import fuwu.bo.ViewDetail;
import fuwu.commen.JsonResult;
import fuwu.em.GlobalErrorEnum;
import fuwu.em.ViewTypeEnum;
import fuwu.po.Media;
import fuwu.po.View;
import fuwu.service.ViewService;
import fuwu.util.JsonPUtils;
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
import java.util.Date;
import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/27 23:52
 * @Description:
 */

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private ViewService viewService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);


    /**
     * 本接口实现 根据场景id 返回场景 交互点 及关系场景等相信信息
     * @param viewId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getViewDetailByViewId", method = RequestMethod.GET)
    public String getViewDetailByViewId(Integer viewId,String callback) {

        return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(viewService.getViewDetailByViewId(viewId)),
                callback
        );

    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public JsonResult createView(View view) {

        if (ParamCheckUtil.checkParamsNotNull(view.getProjectId(),view.getViewName(),view.getMediaId(),view.getViewType())) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }

        if (view.getViewType().equals(ViewTypeEnum.MAIN_VIEW.getViewType())) {
            return JsonResultUtil.createError(GlobalErrorEnum.MAIN_VEW_CONFLICT);
        }


        if(viewService.createView(view)) {
            return JsonResultUtil.createSucess(view);
        } else {
            LOGGER.error("create view error {}",JSON.toJSON(view));
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }

    }



}
