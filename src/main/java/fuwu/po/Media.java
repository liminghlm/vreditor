package fuwu.po;

/**
 * Created by LJW on 2018/4/19 - 19:11
 */
public class Media {
    private Integer id;
    private String mediaUrl;    //热点资源地址
    private  String mediaName;//用户上传媒体名称
    private Integer mediaType;      //媒体类型，1文字，2图片，3音频，4视频,5全景图，6缩率图
    private Integer deleted;        //逻辑删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }
}

