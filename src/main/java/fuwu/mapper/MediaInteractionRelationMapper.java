package fuwu.mapper;

import fuwu.po.Interaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/22 19:10
 * @Description:
 */
public interface MediaInteractionRelationMapper {

    Integer batchInsertMediaInteractionRelation(@Param("interactionId") Integer interactionId, @Param("mediaIntList") List<Integer> mediaIntList);

    Integer deleteByInteractionId( @Param("interactionId") Integer interactionId);
}
