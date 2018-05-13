package fuwu.service.impl;


import fuwu.mapper.ProjectMapper;
import fuwu.po.Project;
import fuwu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by LJW on 2018/4/15 - 0:27
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public boolean addProject(Project project) {
        project.setProjectCreateTime(new Date(System.currentTimeMillis()));
        project.setProjectUpdateTime(new Date(System.currentTimeMillis()));
        project.setDeleted(0);
        project.setProjectState(1);
        if(projectMapper.addProject(project)){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean updateProject(Project project) {
        if(projectMapper.updateProject(project)){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProject(Integer projectId) {
        if(projectMapper.deleteProject(projectId)){
            return true;
        }
        return false;
    }

    @Override
    public List<Project> getProjectList(Project project) {

        return projectMapper.getProjectList(project);
    }
}
