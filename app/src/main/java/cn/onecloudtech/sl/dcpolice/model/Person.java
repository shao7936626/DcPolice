package cn.onecloudtech.sl.dcpolice.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/28.
 */
public class Person  implements Serializable {


    public int id;
    public String name;
    public String sex;
    public String borndate;
    public String QQ;
    public String weChat;
    public String IdCard;
    public String phoneNumber;
    public String registeredResidence;
    public String otherInfo;
    public String currentResidence;
    public String bankCard;
    public String carNumber;
    public String facephoto;
    public String bodyphoto;
    public String entrySense;
    public String personType;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBorndate() {
        return borndate;
    }

    public void setBorndate(String borndate) {
        this.borndate = borndate;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegisteredResidence() {
        return registeredResidence;
    }

    public void setRegisteredResidence(String registeredResidence) {
        this.registeredResidence = registeredResidence;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getCurrentResidence() {
        return currentResidence;
    }

    public void setCurrentResidence(String currentResidence) {
        this.currentResidence = currentResidence;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getFacephoto() {
        return facephoto;
    }

    public void setFacephoto(String facephoto) {
        this.facephoto = facephoto;
    }

    public String getBodyphoto() {
        return bodyphoto;
    }

    public void setBodyphoto(String bodyphoto) {
        this.bodyphoto = bodyphoto;
    }

    public String getEntrySense() {
        return entrySense;
    }

    public void setEntrySense(String entrySense) {
        this.entrySense = entrySense;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
}
