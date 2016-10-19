package cn.onecloudtech.sl.dcpolice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.onecloudtech.sl.dcpolice.hotfix.HotFixManger;

/**
 * Created by Administrator on 2016/10/18.
 */

public class UpdateInfoReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        HotFixManger.init(context);//服务器下载的包
    }
}
