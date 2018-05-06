package fuwu.mapper;

import fuwu.po.ViewRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/28 1:13
 * @Description:
 */
public interface ViewRelationMapper {
    public List<ViewRelation> getViewRelationListByViewId(@Param("viewId") Integer viewId);
}
