package cn.onecloudtech.sl.dcpolice.model;

/**
 * 地点场所类
 * @author Administrator
 *
 */
public class Locate {

    private String address;//场所地址（详细地址,门牌号）

    private Integer islethouse;//是否出租房(0:否 1：是)

    private String belongplace;//所属派出所或社区

    private String policename;//社区民警姓名

    private String registername;//注册名称

    private String realregistername;//实际经营名称

    private String opentime;//开业时间

    private String wifipwd;//wifi密码

    private Integer numberofrelperson;//从业人数

    private String businesslicensenumber;//营业执照号码

    private String hygienelicensenumber;//卫生许可证号码

    private String taxregistrationcertificatenumber;//税务登记证

    private Integer placetype;//场所性质(1:娱乐场所 2：服务场所 3：特种行业 4：九小场所 5：物流快递)

    private Integer entertainmentplacetype;//娱乐场所(1:歌厅 2：卡拉OK厅 3：迪吧 4：夜总会 5：电子游戏厅 6：棋牌室 7：其他)注意：当选中娱乐场所再也是此选项

    private Integer serviceplacetype;//服务场所(1:桑拿洗浴 2：美容美发 3：足疗按摩 4：咖啡馆 5：小餐桌 6：网吧 7：娱乐城 8：茶馆 9：其他)同上

    private Integer specialplacetype;//特种行业（1:旅馆 2：开锁 3：刻字印刷 4：复印 5：废旧金属回收 6：二手机动车交易 7：机动车拆解 8：旧货业回收 9：机动车电动车修理，10、二手手机经营11、金店12、典当行）同上

    private Integer ninesmallplacetype;//九小场所(1:小学校 2：小商场 3：小医院 4：小旅馆 5：小歌舞娱乐 6：小餐饮 7：小网吧 8：小美容洗浴 9：小生产加工 10：其他)同上

    private String placearea;//经营面积

    private Integer numberoffloor;//层数

    private Integer numberofchannelport;//通道口个数

    private Integer numberofroom;//包间数量

    private Integer numberofholdperson;//容纳人数

    private String certificateofqualification;//合格证号

    private String firefacilities;//是否配备以下消防设施（灭火器 应急灯 消防拴 其他）多选

    private String chargepersonname;//保卫负责人姓名

    private String chargepersonphone;//保卫负责人电话

    private Integer numberofstaffperson;//保安人数

    private Integer numberofexternalmonitor;//外部监控数量

    private Integer numberofinsidemonitor;//内部监控数量

    private String protectcondition;//物防情况（防盗门 防盗窗 报警器 其他）

    private String businessscope;//经营范围



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getIslethouse() {
        return islethouse;
    }

    public void setIslethouse(Integer islethouse) {
        this.islethouse = islethouse;
    }

    public String getBelongplace() {
        return belongplace;
    }

    public void setBelongplace(String belongplace) {
        this.belongplace = belongplace == null ? null : belongplace.trim();
    }

    public String getPolicename() {
        return policename;
    }

    public void setPolicename(String policename) {
        this.policename = policename == null ? null : policename.trim();
    }

    public String getRegistername() {
        return registername;
    }

    public void setRegistername(String registername) {
        this.registername = registername == null ? null : registername.trim();
    }

    public String getRealregistername() {
        return realregistername;
    }

    public void setRealregistername(String realregistername) {
        this.realregistername = realregistername == null ? null : realregistername.trim();
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime == null ? null : opentime.trim();
    }

    public String getWifipwd() {
        return wifipwd;
    }

    public void setWifipwd(String wifipwd) {
        this.wifipwd = wifipwd == null ? null : wifipwd.trim();
    }

    public Integer getNumberofrelperson() {
        return numberofrelperson;
    }

    public void setNumberofrelperson(Integer numberofrelperson) {
        this.numberofrelperson = numberofrelperson;
    }

    public String getBusinesslicensenumber() {
        return businesslicensenumber;
    }

    public void setBusinesslicensenumber(String businesslicensenumber) {
        this.businesslicensenumber = businesslicensenumber == null ? null : businesslicensenumber.trim();
    }

    public String getHygienelicensenumber() {
        return hygienelicensenumber;
    }

    public void setHygienelicensenumber(String hygienelicensenumber) {
        this.hygienelicensenumber = hygienelicensenumber == null ? null : hygienelicensenumber.trim();
    }

    public String getTaxregistrationcertificatenumber() {
        return taxregistrationcertificatenumber;
    }

    public void setTaxregistrationcertificatenumber(String taxregistrationcertificatenumber) {
        this.taxregistrationcertificatenumber = taxregistrationcertificatenumber == null ? null : taxregistrationcertificatenumber.trim();
    }

    public Integer getPlacetype() {
        return placetype;
    }

    public void setPlacetype(Integer placetype) {
        this.placetype = placetype;
    }

    public Integer getEntertainmentplacetype() {
        return entertainmentplacetype;
    }

    public void setEntertainmentplacetype(Integer entertainmentplacetype) {
        this.entertainmentplacetype = entertainmentplacetype;
    }

    public Integer getServiceplacetype() {
        return serviceplacetype;
    }

    public void setServiceplacetype(Integer serviceplacetype) {
        this.serviceplacetype = serviceplacetype;
    }

    public Integer getSpecialplacetype() {
        return specialplacetype;
    }

    public void setSpecialplacetype(Integer specialplacetype) {
        this.specialplacetype = specialplacetype;
    }

    public Integer getNinesmallplacetype() {
        return ninesmallplacetype;
    }

    public void setNinesmallplacetype(Integer ninesmallplacetype) {
        this.ninesmallplacetype = ninesmallplacetype;
    }

    public String getPlacearea() {
        return placearea;
    }

    public void setPlacearea(String placearea) {
        this.placearea = placearea == null ? null : placearea.trim();
    }

    public Integer getNumberoffloor() {
        return numberoffloor;
    }

    public void setNumberoffloor(Integer numberoffloor) {
        this.numberoffloor = numberoffloor;
    }

    public Integer getNumberofchannelport() {
        return numberofchannelport;
    }

    public void setNumberofchannelport(Integer numberofchannelport) {
        this.numberofchannelport = numberofchannelport;
    }

    public Integer getNumberofroom() {
        return numberofroom;
    }

    public void setNumberofroom(Integer numberofroom) {
        this.numberofroom = numberofroom;
    }

    public Integer getNumberofholdperson() {
        return numberofholdperson;
    }

    public void setNumberofholdperson(Integer numberofholdperson) {
        this.numberofholdperson = numberofholdperson;
    }

    public String getCertificateofqualification() {
        return certificateofqualification;
    }

    public void setCertificateofqualification(String certificateofqualification) {
        this.certificateofqualification = certificateofqualification;
    }

    public String getFirefacilities() {
        return firefacilities;
    }

    public void setFirefacilities(String firefacilities) {
        this.firefacilities = firefacilities == null ? null : firefacilities.trim();
    }

    public String getChargepersonname() {
        return chargepersonname;
    }

    public void setChargepersonname(String chargepersonname) {
        this.chargepersonname = chargepersonname == null ? null : chargepersonname.trim();
    }

    public String getChargepersonphone() {
        return chargepersonphone;
    }

    public void setChargepersonphone(String chargepersonphone) {
        this.chargepersonphone = chargepersonphone == null ? null : chargepersonphone.trim();
    }

    public Integer getNumberofstaffperson() {
        return numberofstaffperson;
    }

    public void setNumberofstaffperson(Integer numberofstaffperson) {
        this.numberofstaffperson = numberofstaffperson;
    }

    public Integer getNumberofexternalmonitor() {
        return numberofexternalmonitor;
    }

    public void setNumberofexternalmonitor(Integer numberofexternalmonitor) {
        this.numberofexternalmonitor = numberofexternalmonitor;
    }

    public Integer getNumberofinsidemonitor() {
        return numberofinsidemonitor;
    }

    public void setNumberofinsidemonitor(Integer numberofinsidemonitor) {
        this.numberofinsidemonitor = numberofinsidemonitor;
    }

    public String getProtectcondition() {
        return protectcondition;
    }

    public void setProtectcondition(String protectcondition) {
        this.protectcondition = protectcondition == null ? null : protectcondition.trim();
    }

    public String getBusinessscope() {
        return businessscope;
    }

    public void setBusinessscope(String businessscope) {
        this.businessscope = businessscope == null ? null : businessscope.trim();
    }
}