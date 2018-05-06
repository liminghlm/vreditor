package fuwu.service;

import fuwu.bo.RealInteraction;
import fuwu.po.Interaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/28 1:07
 * @Description:
 */

public interface InteractionService {
    public List<RealInteraction> getInterationlistByViewId(Integer viewId);
}
