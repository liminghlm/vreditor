package fuwu.po;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by LJW on 2018/4/15 - 0:26
 */

public class Project {

    private Integer id;
    private String projectName;     //项目名称
    private String projectInfo;     //项目描述
    private String projectState;    //项目类型，0未完成编辑，1已发布
    private Date projectCreateTime; //项目创建时间
    private Date projectUpdateTime; //项目更新时间
    private Integer deleted;            //逻辑删除

    public Integer getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectInfo() {
        return projectInfo;
    }

    public String getProjectState() {
        return projectState;
    }

    public Date getProjectCreateTime() {
        return projectCreateTime;
    }

    public Date getProjectUpdateTime() {
        return projectUpdateTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public void setProjectCreateTime(Date projectCreateTime) {
        this.projectCreateTime = projectCreateTime;
    }

    public void setProjectUpdateTime(Date projectUpdateTime) {
        this.projectUpdateTime = projectUpdateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
