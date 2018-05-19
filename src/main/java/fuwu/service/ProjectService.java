package fuwu.service;



import fuwu.po.Project;
import fuwu.po.View;

import java.util.List;

/**
 * Created by LJW on 2018/4/15 - 0:27
 */
public interface ProjectService {

    public boolean addProject(Project project);

    public boolean updateProject(Project project);

    public boolean deleteProject(Integer projectId);

    public List<Project> getProjectList(Project project);

    boolean publishProject(Integer projectId);
}
