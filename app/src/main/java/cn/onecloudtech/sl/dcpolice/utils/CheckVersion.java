package cn.onecloudtech.sl.dcpolice.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;


import cn.onecloudtech.sl.dcpolice.C;
import cn.onecloudtech.sl.dcpolice.http.HttpMethods;
import cn.onecloudtech.sl.dcpolice.model.UpdateData;
import cn.onecloudtech.sl.dcpolice.subscribers.ProgressSubscriber;
import cn.onecloudtech.sl.dcpolice.subscribers.SubscriberOnNextListener;

/**
 * Created by hugo on 2016/2/21 0021.
 */
public class CheckVersion {

   // private SubscriberOnNextListener getFetchVersionOnNext;

    public static void checkVersion(final Context context, SubscriberOnNextListener getFetchVersionOnNext) {
//        RetrofitSingleton.getInstance().fetchVersion()
//            .subscribe(versionAPI -> {
//                String firVersionName = versionAPI.versionShort;
//                String currentVersionName = Util.getVersion(context);
//                if (currentVersionName.compareTo(firVersionName) < 0) {
//                    showUpdateDialog(versionAPI, context);
//                } else {
//                    ToastUtil.showShort("已经是最新版本(⌐■_■)");
//                }
//            }, throwable -> {
//                PLog.e(throwable.toString());
//            });

        HttpMethods.getInstance().dofetchVersion(new ProgressSubscriber(getFetchVersionOnNext, context));
    }

    public static void showUpdateDialog(final UpdateData mUpdateData, final Context context) {
        String title = "发现新版" + mUpdateData.getVersionname() + "版本号：" + mUpdateData.getVersionshort();

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setPositiveButton("下载",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Uri uri = Uri.parse(mUpdateData.getUpdateUrl());   //指定网址
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);           //指定Action
                                intent.setData(uri);                            //设置Uri
                                context.startActivity(intent);        //启动Activity
                            }
                        }
                )
                .show();

//        new AlertDialog.Builder(context).setTitle(title)
//            .setMessage(mUpdateData.getChangelog())
//            .setPositiveButton("下载", (dialog, which) -> {
//                Uri uri = Uri.parse(mUpdateData.updateUrl);   //指定网址
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);           //指定Action
//                intent.setData(uri);                            //设置Uri
//                context.startActivity(intent);        //启动Activity
//            })
//            .show();
    }
}
