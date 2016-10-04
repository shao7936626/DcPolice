package cn.onecloudtech.sl.dcpolice.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/6.
 */
public class CheckPerson implements Serializable {
    private int id;

    private int pushResult;

    private int uploadResult;

    private int checkResult;

    public int getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(int checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckResultMsg() {
        return checkResultMsg;
    }

    public void setCheckResultMsg(String checkResultMsg) {
        this.checkResultMsg = checkResultMsg;
    }

    private String checkResultMsg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPushResult() {
        return pushResult;
    }

    public void setPushResult(int pushResult) {
        this.pushResult = pushResult;
    }

    public int getUploadResult() {
        return uploadResult;
    }

    public void setUploadResult(int uploadResult) {
        this.uploadResult = uploadResult;
    }
}
