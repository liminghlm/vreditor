package fuwu.mapper;

import fuwu.po.Media;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/28 0:32
 * @Description:
 */
public interface MediaMapper {
    public List<Media> getMediaListByInteractionId(@Param("interactionId") Integer interactionId );

    public Media getMediaUrlByMediaId(@Param("mediaId") Integer mediaId);

    public Integer addMedia(Media media);

}
