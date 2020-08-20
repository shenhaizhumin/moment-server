package cn.example.moment.pojo;

import java.sql.Timestamp;

public class CommentEntity {
    private Long id;
    private String content;
    private Long operator_user_id;
    private Long moment_id;
    private Timestamp publish_time;
    private UserEntity publisher;

    public Timestamp getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Timestamp publish_time) {
        this.publish_time = publish_time;
    }

    public UserEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(UserEntity publisher) {
        this.publisher = publisher;
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

    public Long getOperator_user_id() {
        return operator_user_id;
    }

    public void setOperator_user_id(Long operator_user_id) {
        this.operator_user_id = operator_user_id;
    }

    public Long getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(Long moment_id) {
        this.moment_id = moment_id;
    }
}
