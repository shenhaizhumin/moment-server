package cn.example.moment.pojo;

import java.sql.Date;

public class CollectEntity {
    private Long id;
    private Date create_time;
    private Long moment_id;
    private Long operator_user_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(Long moment_id) {
        this.moment_id = moment_id;
    }

    public Long getOperator_user_id() {
        return operator_user_id;
    }

    public void setOperator_user_id(Long operator_user_id) {
        this.operator_user_id = operator_user_id;
    }
}
