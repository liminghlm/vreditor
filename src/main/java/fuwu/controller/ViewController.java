package fuwu.controller;

import com.alibaba.fastjson.JSON;
import fuwu.aop.ForLog;
import fuwu.bo.RealInteraction;
import fuwu.bo.RealViewRelation;
import fuwu.bo.ViewDetail;
import fuwu.commen.JsonResult;
import fuwu.em.GlobalErrorEnum;
import fuwu.em.ViewTypeEnum;
import fuwu.mapper.ViewMapper;
import fuwu.po.Media;
import fuwu.po.View;
import fuwu.service.ViewService;
import fuwu.util.JsonPUtils;
import fuwu.util.JsonResultUtil;
import fuwu.util.ParamCheckUtil;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
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
    private ViewMapper viewMapper;

    @Autowired
    private ViewService viewService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);


    /**
     * 本接口实现 根据场景id 返回场景 交互点 及关系场景等相信信息
     * @param viewId
     * @return
     */
    @ResponseBody
    @ForLog
    @RequestMapping(value = "/getViewDetailByViewId", method = RequestMethod.GET)
    public JsonResult getViewDetailByViewId(Integer viewId,String callback) {

        return JsonResultUtil.createSucess(viewService.getViewDetailByViewId(viewId));



    }

    @ResponseBody
    @ForLog
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createView(View view) {

        if (!ParamCheckUtil.checkParamsNotNull(view.getProjectId(),view.getViewName(),view.getMediaId(),view.getViewType())) {
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }

        View mainView = viewService.getMainViewByProjectId(view.getProjectId());
        if (view.getViewType().equals(ViewTypeEnum.MAIN_VIEW.getViewType()) && mainView!=null) {
            return JsonResultUtil.createError(GlobalErrorEnum.MAIN_VEW_CONFLICT);
        }


        try {
            if(viewService.createView(view)) {
                return JsonResultUtil.createSucess(view);
            } else {
                LOGGER.error("create view error {}",JSON.toJSON(view));
                return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return JsonResultUtil.createError(GlobalErrorEnum.ERROR);


    }
    @ResponseBody
    @RequestMapping(value = "/setMainView", method = RequestMethod.GET)
    @Transactional
    @ForLog
    public JsonResult setMainView(Integer viewId){
        View view= viewMapper.getViewByViewId(viewId);
        viewMapper.cancelMainView(view.getProjectId());
        if(viewMapper.setMainView(viewId)==1){
            view.setViewType(1);
            return JsonResultUtil.createSucess(view);
       }else {
           LOGGER.error("设置主场景失败{}",JSON.toJSON(view));
           return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
       }
    }

    @ResponseBody
    @ForLog
    @RequestMapping(value = "/getViewListByProjectId", method = RequestMethod.GET)
    public JsonResult getViewListByProjectId(Integer projectId){
        List<View> viewList=viewService.getViewListByProjectId(projectId);
        return JsonResultUtil.createSucess(viewList);
    }

    @ResponseBody
    @ForLog
    @RequestMapping(value = "/delete",method =RequestMethod.GET)
    public JsonResult deleteView(Integer viewId){
        if(viewService.deleteView(viewId)==1) {
            return JsonResultUtil.createSucess(null);
        }else {
            LOGGER.error("删除场景失败，viewId=,{}",viewId);
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
    }



}
