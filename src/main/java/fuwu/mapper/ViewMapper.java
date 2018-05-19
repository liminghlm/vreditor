package fuwu.mapper;

import fuwu.po.View;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * Created by LJW on 2018/4/19 - 23:17
 */
public interface ViewMapper {
   public View getMainViewByProjectId(@Param("projectId") Integer projectId);

   public View getViewByViewId(@Param("viewId") Integer viewId);

    boolean createView(View view);

    Integer deletePhysically(View view);
}
