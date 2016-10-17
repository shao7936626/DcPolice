package cn.onecloudtech.sl.dcpolice.model;

public class Jpushperson {
    private Integer id;

    private String idcard;// 身份证

    private String realname;// 真实姓名

    private String picurl;// 人脸照片地址

    private int unitname;

    private Integer istrue;// 判断接口（0:错误，1：正确,其他:错误）

    private Integer userroleId;// 客户端用户id

    private String checkstarttime;// 客户端前端上传判断信息时间

    private String backcheckresulttime;// 平台判断后下发时间

    private Integer isoutoftime;// 是否超期

    private String remark;// 备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public Integer getIstrue() {
        return istrue;
    }

    public void setIstrue(Integer istrue) {
        this.istrue = istrue;
    }

    public Integer getUserroleId() {
        return userroleId;
    }

    public void setUserroleId(Integer userroleId) {
        this.userroleId = userroleId;
    }

    public String getCheckstarttime() {
        return checkstarttime;
    }

    public void setCheckstarttime(String checkstarttime) {
        this.checkstarttime = checkstarttime == null ? null : checkstarttime.trim();
    }

    public String getBackcheckresulttime() {
        return backcheckresulttime;
    }

    public void setBackcheckresulttime(String backcheckresulttime) {
        this.backcheckresulttime = backcheckresulttime == null ? null : backcheckresulttime.trim();
    }

    public Integer getIsoutoftime() {
        return isoutoftime;
    }

    public void setIsoutoftime(Integer isoutoftime) {
        this.isoutoftime = isoutoftime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}