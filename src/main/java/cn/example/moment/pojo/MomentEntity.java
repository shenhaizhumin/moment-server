package cn.example.moment.pojo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MomentEntity {
    private Long id;
    private String content;
    private Long user_id;
    private Timestamp publish_time;
    private String content_url;
    private List<CollectEntity> collects;
    private List<CommentEntity> comments;
    private List<StorageEntity> storages;

    public List<StorageEntity> getStorages() {
        return storages;
    }

    public void setStorages(List<StorageEntity> storages) {
        this.storages = storages;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public List<CollectEntity> getCollects() {
        return collects;
    }

    public void setCollects(List<CollectEntity> collects) {
        this.collects = collects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Timestamp getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Timestamp publish_time) {
        this.publish_time = publish_time;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }
}
