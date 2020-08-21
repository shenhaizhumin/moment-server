package cn.example.moment.pojo;

public class StorageEntity {
    /**
     * id = Column('id', Integer, primary_key=True, unique=True)
     * file_name = Column('file_name', String)
     * file_path = Column('file_path', String)
     * file_url = Column('file_url', String)
     * moment_id = Column('moment_id', Integer, ForeignKey('moment.id'))
     */
    private Long id;
    private String file_name;
    private String file_path;
    private String file_url;
    private Long moment_id;
    private Long operator_id;

    public Long getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(Long operator_id) {
        this.operator_id = operator_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public Long getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(Long moment_id) {
        this.moment_id = moment_id;
    }
}
