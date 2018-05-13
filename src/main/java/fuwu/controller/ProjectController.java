package fuwu.controller;

import fuwu.commen.JsonResult;
import fuwu.em.ViewStatusEnum;
import fuwu.po.Project;
import fuwu.service.FtpService;
import fuwu.service.ProjectService;
import fuwu.service.ViewService;
import fuwu.util.JsonPUtils;
import fuwu.util.JsonResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    public String getMainViewByProId(Integer projectId,String callback) {

        return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(viewService.getMainViewByProjectId(projectId)),
                callback
        );
    }

    /**
     * 本接口实现 根据场景id返回场景详细信息
     * @param viewId
     * @param callback
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

    /**
     * 本接口实现 project的创建
     * @param request
     * @param callback
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createProject(HttpServletRequest request,HttpServletResponse response, String callback) {

        Project project = new Project();
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        project.setProjectName(request.getParameter("name"));
        project.setProjectInfo(request.getParameter("description"));

        if(projectService.addProject(project)) {
            return JsonResultUtil.createSucess(project);
        }
        return JsonResultUtil.createError(ViewStatusEnum.ERROR);
        /*return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(projectService.addProject(project)),
                callback
        );*/
    }

    /**
     * 本接口实现 根据projectName获得Project列表，没有ProjectName则返回全部Project。
     * @param request
     * @param callback
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
    public JsonResult getProjectList(HttpServletRequest request,HttpServletResponse response, String callback) {
        Project project = new Project();

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");

        project.setProjectName(request.getParameter("projectName"));
        return JsonResultUtil.createSucess(projectService.getProjectList(project));
        /*return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(projectService.getProjectList(project.getProjectName())),
                callback
        );*/
    }

    /**
     * 本接口实现 project的编辑功能
     * @param request
     * @param callback
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProject(HttpServletRequest request, String callback) {
        Project project = new Project();
        project.setProjectName(request.getParameter("projectName"));
        project.setProjectInfo(request.getParameter("projectInfo"));
        return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(projectService.updateProject(project)),
                callback
        );
    }

    /**
     * 本接口实现 project的删除功能。
     * @param projectId
     * @param callback
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteProject(Integer projectId, String callback) {
        return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(projectService.deleteProject(projectId)),
                callback
        );
    }


}