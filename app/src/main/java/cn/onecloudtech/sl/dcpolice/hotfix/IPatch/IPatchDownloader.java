package cn.onecloudtech.sl.dcpolice.hotfix.IPatch;

import android.content.Context;

/**
 * Created by shoyu666@163.com on 16/7/5.
 */
public interface IPatchDownloader {
    /**
     * patch下载操作
     */
    public void downloadPatch(Context mContext);

    /**
     * patch文件下载地址
     * @return
     */
    public String getUrl();
}
