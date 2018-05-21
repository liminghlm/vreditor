package fuwu.mapper;

import fuwu.po.View;
import fuwu.po.ViewRelation;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/28 1:13
 * @Description:
 */
public interface ViewRelationMapper {
    public List<ViewRelation> getViewRelationListByViewId(@Param("viewId") Integer viewId);

    List<ViewRelation> getViewRelationListByProjectId(@Param("projectId")Integer projectId);

    Boolean createViewRelation(ViewRelation viewRelation);

    Integer deleteViewRelation(@Param("id")Integer viewRelationId);
}
