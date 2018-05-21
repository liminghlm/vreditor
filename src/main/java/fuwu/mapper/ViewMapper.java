package fuwu.mapper;

import fuwu.po.View;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * Created by LJW on 2018/4/19 - 23:17
 */
public interface ViewMapper {
    View getMainViewByProjectId(@Param("projectId") Integer projectId);

    View getViewByViewId(@Param("viewId") Integer viewId);
    Integer updateViewMiniId(@Param("viewMiniId") Integer viewMiniId,@Param("viewId") Integer viewId);

    boolean createView(View view);
    Integer deletePhysically(View view);
    Integer cancelMainView(@Param("projectId")Integer projectId);
    Integer setMainView(@Param("viewId") Integer viewId);
    List<View> getViewListByProjectId(@Param("projectId") Integer projectId);
    Integer deleteView(@Param("viewId") Integer viewId);
}
