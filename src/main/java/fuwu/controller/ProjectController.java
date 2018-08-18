package fuwu.controller;

import com.alibaba.fastjson.JSON;
import fuwu.aop.ForLog;
import fuwu.bo.ViewDetail;
import fuwu.commen.JsonResult;
import fuwu.em.GlobalErrorEnum;
import fuwu.po.Project;
import fuwu.po.View;
import fuwu.service.ProjectService;
import fuwu.service.ViewService;
import fuwu.util.JsonResultUtil;
import fuwu.util.ParamCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LJW on 2018/4/20 - 11:59
 */

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ViewService viewService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    /**
     * 本接口实现 根据项目id返回主场景基本信息
     * @param projectId
     * @return
     */
    @ResponseBody
    @ForLog
    @RequestMapping(value = "/getMainView", method = RequestMethod.GET)
    public JsonResult getMainViewByProId(Integer projectId) {
        View view = viewService.getMainViewByProjectId(projectId);
        if(view == null){
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
        return JsonResultUtil.createSucess(view);
    }

    /**
     * 本接口实现 根据场景id返回场景详细信息
     * @param viewId
     * @return
     */
    @ResponseBody
    @ForLog
    @RequestMapping(value = "/getViewDetailByViewId", method = RequestMethod.GET)
    public JsonResult getViewDetailByViewId(Integer viewId) {
        ViewDetail viewdetail = viewService.getViewDetailByViewId(viewId);
        if(!ParamCheckUtil.checkParamsNotNull(viewId)){
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
        return JsonResultUtil.createSucess(viewdetail);
    }

    /**
     * 本接口实现 project的创建
     * @param
     * @return
     */
    @ResponseBody
    @ForLog
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createProject(String name,String description) {

        if ( !ParamCheckUtil.checkParamsNotNull(name) ) {
            LOGGER.error("/project/create error with null project name");
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }

        Project project = new Project(name,description);

        if(projectService.addProject(project)) {
            return JsonResultUtil.createSucess(project);
        } else {
            LOGGER.error("create project error ");
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }

    }

    /**
     * 本接口实现 根据projectName获得Project列表，没有ProjectName则返回全部Project。
     * @param
     * @return
     */
    @ResponseBody
    @ForLog
    @RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
    public JsonResult getProjectList( String projectName) {
        Project project = new Project();
        project.setProjectName(projectName);

        List<Project> projectlist = projectService.getProjectList(project);

        return JsonResultUtil.createSucess(projectlist);
    }

    /**
     * 本接口实现 project的编辑功能
     * @param
     * @return
     */
    @ResponseBody
    @ForLog
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult updateProject(String name,String description, Integer projectId) {
        if ( !ParamCheckUtil.checkParamsNotNull(name,projectId) ) {
            LOGGER.error("/project/update error with null project name or projectId");
            return JsonResultUtil.createError(GlobalErrorEnum.PARAM_NULL_ERROR);
        }

        Project project = new Project(name,description,projectId);

        if(projectService.updateProject(project)) {
            return JsonResultUtil.createSucess(project);
        } else {
            LOGGER.error("/project/update error with param {}",JSON.toJSON(project));
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
    }

    /**
     * 本接口实现 project的删除功能。
     * @param projectId
     * @return
     */
    @ResponseBody
    @ForLog
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult deleteProject(Integer projectId) {

        if(projectService.deleteProject(projectId)) {
            return JsonResultUtil.createSucess(null);
        } else {
            LOGGER.error("/project/delete error with param {}", projectId);
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
    }


    @ResponseBody
    @ForLog
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public JsonResult publishProject(Integer projectId) {

        if(projectService.publishProject(projectId)) {
            return JsonResultUtil.createSucess(null);
        } else {
            LOGGER.error("/project/publish error with param {}", projectId);
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
    }



}