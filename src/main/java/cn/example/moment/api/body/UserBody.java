package cn.example.moment.api.body;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserBody {

//    @JsonProperty(value = "username")
    private String username;
//    @JsonProperty(value = "password")
    private String password;
    private String uid;
    private String mobile;
    private String email;
    private String nickname;
    private Long id;
    private Long latest_time;
    private Long register_time;
    private short enable;
    private String latest_ip;
    private String avatar_url;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getLatest_time() {
        return latest_time;
    }

    public void setLatest_time(Long latest_time) {
        this.latest_time = latest_time;
    }

    public Long getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Long register_time) {
        this.register_time = register_time;
    }

    public short getEnable() {
        return enable;
    }

    public void setEnable(short enable) {
        this.enable = enable;
    }

    public String getLatest_ip() {
        return latest_ip;
    }

    public void setLatest_ip(String latest_ip) {
        this.latest_ip = latest_ip;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
