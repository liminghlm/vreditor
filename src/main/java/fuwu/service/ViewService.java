package fuwu.service;

import fuwu.bo.ViewDetail;
import fuwu.po.View;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by LJW on 2018/4/19 - 23:13
 */
public interface ViewService {
   public View getMainViewByProjectId(Integer projectId);;

   public ViewDetail getViewDetailByViewId(Integer viewId);

   boolean createView(View view) throws FileNotFoundException, Exception;
   List<View> getViewListByProjectId(Integer projectId);
   Integer deleteView(Integer viewId);
}
