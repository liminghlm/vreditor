package fuwu.service;

import fuwu.bo.ViewDetail;
import fuwu.po.View;

/**
 * Created by LJW on 2018/4/19 - 23:13
 */
public interface ViewService {
   public View getMainViewByProjectId(Integer projectId);;



   public ViewDetail getViewDetailByViewId(Integer viewId);

}
