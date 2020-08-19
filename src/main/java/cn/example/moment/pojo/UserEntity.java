package cn.example.moment.pojo;

public class UserEntity {
    /**
     * id = Column('id', Integer, primary_key=True)
     * username = Column('username', String, default=token_util.generate_random_string(length=random.randint(4, 10),
     * type_='word'), unique=True)
     * uid = Column('uid', String, default=token_util.generate_by_sha1_random, unique=True)
     * nickname = Column('nickname', String)
     * latest_time = Column('latest_time', DateTime)
     * register_time = Column('register_time', DateTime, default=datetime.now())
     * type_role = Column('type_role', SMALLINT)
     * enable = Column('enable', Boolean, default=True)
     * email = Column('email', String)
     * latest_ip = Column('latest_ip', String)
     * mobile = Column('mobile', String)
     * avatar_url = Column('avatar_url', String)
     * moment_image = Column('moment_image', String)
     * password = Column('password', String)
     * role_id = Column('role_id', Integer, ForeignKey('user_role.id'))
     * # avatar_id = Column('avatar_id', Integer, ForeignKey('image.id'))
     * user_role = relationship('UserRole')
     */
    private Long id;
    private String username;
    private String uid;
    private String latest_time;
    private String register_time;
    private Boolean enable;
    private String email;
    private String latest_ip;
    private String mobile;
    private String avatar_url;
    private String moment_image;
    private String password;
    private Integer role_id;
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLatest_time() {
        return latest_time;
    }

    public void setLatest_time(String latest_time) {
        this.latest_time = latest_time;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatest_ip() {
        return latest_ip;
    }

    public void setLatest_ip(String latest_ip) {
        this.latest_ip = latest_ip;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getMoment_image() {
        return moment_image;
    }

    public void setMoment_image(String moment_image) {
        this.moment_image = moment_image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
}
