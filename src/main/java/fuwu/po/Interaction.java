package fuwu.po;

/**
 * Created by LJW on 2018/4/19 - 19:14
 */
public class Interaction {

    private Integer id;
    private Integer viewId;             //热点所属场景id
    private Integer interactionType;    //热点类型，分为0基本，1组合，基本即文字，图片，音频，视频四类，组合为这四种的组合
    private String interactionPosition; //热点的位置
    private String interactionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public Integer getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(Integer interactionType) {
        this.interactionType = interactionType;
    }

    public String getInteractionPosition() {
        return interactionPosition;
    }

    public void setInteractionPosition(String interactionPosition) {
        this.interactionPosition = interactionPosition;
    }

    public String getInteractionName() {
        return interactionName;
    }

    public void setInteractionName(String interactionName) {
        this.interactionName = interactionName;
    }
}
