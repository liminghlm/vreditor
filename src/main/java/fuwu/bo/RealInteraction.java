package fuwu.bo;


import fuwu.po.Interaction;
import fuwu.po.Media;

import java.util.List;

/**
 * @Author: ypc
 * @Date: 2018/4/24 0:03
 * @Description:
 */
public class RealInteraction extends Interaction {
    private List<Media> mediaList;

    public RealInteraction() {
    }

    public RealInteraction(Interaction interaction) {
        super.setId(interaction.getId());
        super.setViewId(interaction.getViewId());
        super.setInteractionType(interaction.getInteractionType());
        super.setInteractionPosition(interaction.getInteractionPosition());
        super.setInteractionName(interaction.getInteractionName());
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }
}
