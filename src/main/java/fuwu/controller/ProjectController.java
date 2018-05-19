package fuwu.controller;

import fuwu.bo.ViewDetail;
import fuwu.commen.JsonResult;
import fuwu.em.GlobalErrorEnum;
import fuwu.po.Project;
import fuwu.po.View;
import fuwu.service.ProjectService;
import fuwu.service.ViewService;
import fuwu.util.JsonPUtils;
import fuwu.util.JsonResultUtil;
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
    @RequestMapping(value = "/getMainView", method = RequestMethod.GET)
    public JsonResult getMainViewByProId(Integer projectId) {
        View view = viewService.getMainViewByProjectId(projectId);
        if(view == null || view.equals("")){
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
        return JsonResultUtil.createSucess(view);
        /*return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(viewService.getMainViewByProjectId(projectId)),
                callback
        );*/
    }

    /**
     * 本接口实现 根据场景id返回场景详细信息
     * @param viewId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getViewDetailByViewId", method = RequestMethod.GET)
    public JsonResult getViewDetailByViewId(Integer viewId) {
        ViewDetail viewdetail = viewService.getViewDetailByViewId(viewId);
        if(viewdetail == null || viewdetail.equals("")){
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
        return JsonResultUtil.createSucess(viewdetail);
        /*return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(viewService.getViewDetailByViewId(viewId)),
                callback
        );*/
    }

    /**
     * 本接口实现 project的创建
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createProject(HttpServletRequest request) {

        Project project = new Project();
        project.setProjectName(request.getParameter("name"));
        project.setProjectInfo(request.getParameter("description"));

        if(projectService.addProject(project)) {
            return JsonResultUtil.createSucess(project);
        }

        return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        /*return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(projectService.addProject(project)),
                callback
        );*/
    }

    /**
     * 本接口实现 根据projectName获得Project列表，没有ProjectName则返回全部Project。
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
    public JsonResult getProjectList(HttpServletRequest request) {
        Project project = new Project();
        project.setProjectName(request.getParameter("projectName"));
        List<Project> projectlist = projectService.getProjectList(project);
        if(projectlist == null || projectlist.equals("")){
            return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
        return JsonResultUtil.createSucess(projectlist);
    }

    /**
     * 本接口实现 project的编辑功能
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult updateProject(HttpServletRequest request) {
        Project project = new Project();
        project.setProjectName(request.getParameter("projectName"));
        project.setProjectInfo(request.getParameter("projectInfo"));
        if(projectService.updateProject(project)) {
            return JsonResultUtil.createSucess(project);
        }
        return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
    }

    /**
     * 本接口实现 project的删除功能。
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult deleteProject(Integer projectId) {

        if(projectService.deleteProject(projectId)) {
            return JsonResultUtil.createSucess(null);
        }
        return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
    }


}