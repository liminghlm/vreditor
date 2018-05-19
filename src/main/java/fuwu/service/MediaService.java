package fuwu.service;

import fuwu.po.Media;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/28 0:32
 * @Description:
 */
public interface MediaService {

   public List<Media> getMediaListByInteractionId( Integer interactionId );
   public Media getMediaUrlByMediaId(Integer mediaId);
   public Integer addMedia(Media media);
}
