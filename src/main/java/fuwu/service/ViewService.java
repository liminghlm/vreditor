package fuwu.service;

import fuwu.bo.ViewDetail;
import fuwu.po.View;

import java.util.List;

/**
 * Created by LJW on 2018/4/19 - 23:13
 */
public interface ViewService {
   public View getMainViewByProjectId(Integer projectId);;

   public ViewDetail getViewDetailByViewId(Integer viewId);

   public List<View> getViewListByProjectId(Integer projectId);


   boolean createView(View view);
}
