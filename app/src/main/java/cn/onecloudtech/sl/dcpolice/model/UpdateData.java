package cn.onecloudtech.sl.dcpolice.model;

import cn.onecloudtech.sl.dcpolice.C;

/**
 * Created by Administrator on 2016/8/2.
 */
public class UpdateData {
    private int id;
    private String versionname;
    private String versionshort;
    public String updateurl;


    public String getUpdateUrl() {
        return C.SERVER_IP + C.SERVER_PORT + "/dchceSrvice/" + updateurl;
        //return updateUrl;
    }

    public void setUpdateUrl(String updateurl) {
        this.updateurl = updateurl;
    }


    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    private String changelog;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getVersionshort() {
        return versionshort;
    }

    public void setVersionshort(String versionshort) {
        this.versionshort = versionshort;
    }
}
