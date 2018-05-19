package fuwu.po;

import java.util.Date;
import java.util.List;

/**
 * Created by LJW on 2018/4/19 - 18:54
 */
public class View {

    private Integer id;
    private Integer mediaId;//场景资源地址
    private Integer viewMiniId;
    private Integer viewType;       //view是否为主场景，1是，0不是
    private String viewInfo;    //场景描述
    private String viewName;    //场景名称
    private Date viewCreateTime;//创建时间
    private Date viewUpdateTime;//最后修改时间
    private Integer projectId;      //场景所属项目id
    private Integer deleted;        //逻辑删除

    public View(Integer projectId) {
        this.setProjectId(projectId);
        this.setDeleted(0);
    }

    public View() {
        this.setDeleted(0);
    }

    public Integer getViewMiniId() {
        return viewMiniId;
    }

    public void setViewMiniId(Integer viewMiniId) {
        this.viewMiniId = viewMiniId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewType() {
        return viewType;
    }

    public void setViewType(Integer viewType) {
        this.viewType = viewType;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getViewInfo() {
        return viewInfo;
    }

    public String getViewName() {
        return viewName;
    }

    public Date getViewCreateTime() {
        return viewCreateTime;
    }

    public Date getViewUpdateTime() {
        return viewUpdateTime;
    }



    public void setViewInfo(String viewInfo) {
        this.viewInfo = viewInfo;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public void setViewCreateTime(Date viewCreateTime) {
        this.viewCreateTime = viewCreateTime;
    }

    public void setViewUpdateTime(Date viewUpdateTime) {
        this.viewUpdateTime = viewUpdateTime;
    }



    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
}
