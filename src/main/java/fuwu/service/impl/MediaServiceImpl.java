package fuwu.service.impl;

import fuwu.mapper.MediaMapper;
import fuwu.po.Media;
import fuwu.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/28 0:33
 * @Description:
 */
@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public List<Media> getMediaListByInteractionId(Integer interactionId) {

        return mediaMapper.getMediaListByInteractionId(interactionId);
    }

    @Override
    public Media getMediaUrlByMediaId(Integer mediaId) {
        return mediaMapper.getMediaUrlByMediaId(mediaId);
    }

    @Override
    public Integer addMedia(Media media) {
        return mediaMapper.addMedia(media);
    }


}
