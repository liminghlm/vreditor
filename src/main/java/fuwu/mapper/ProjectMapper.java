package fuwu.mapper;

import fuwu.po.Project;
import fuwu.po.View;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LJW on 2018/4/19 - 22:59
 */
public interface ProjectMapper {

    public boolean addProject(Project project);

    public boolean updateProject(Project project);

    public boolean deleteProject(@Param("id") Integer projectId);

    public List<Project> getProjectList(Project project);
}
