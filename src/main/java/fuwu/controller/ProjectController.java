package fuwu.controller;

import fuwu.commen.JsonResult;
import fuwu.service.FtpService;
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

import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    private FtpService ftpService;


    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    /**
     * 本接口实现 根据项目id返回主场景基本信息
     * @param response
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMainView", method = RequestMethod.GET)
    public String getMainViewByProId(HttpServletResponse response, Integer projectId,String callback) {

        return JsonPUtils.makeJsonP(
                JsonResultUtil.createSucess(viewService.getMainViewByProjectId(projectId)),
                callback
        );
    }





    public static void main(String[] args) {
        System.out.println();
    }
}