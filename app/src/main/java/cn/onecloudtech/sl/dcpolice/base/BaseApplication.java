package cn.onecloudtech.sl.dcpolice.base;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.dodola.rocoofix.RocooFix;

import cn.jpush.android.api.JPushInterface;
import cn.onecloudtech.sl.dcpolice.C;
import cn.onecloudtech.sl.dcpolice.hotfix.HotFixManger;
import cn.onecloudtech.sl.dcpolice.model.Person;
import cn.onecloudtech.sl.dcpolice.model.UpdateData;
import cn.onecloudtech.sl.dcpolice.model.User;
import cn.onecloudtech.sl.dcpolice.subscribers.SubscriberOnNextListener;
import cn.onecloudtech.sl.dcpolice.ui.activity.Login.LoginActivity;
import cn.onecloudtech.sl.dcpolice.utils.CheckVersion;
import cn.onecloudtech.sl.dcpolice.utils.MAppManager;
import cn.onecloudtech.sl.dcpolice.utils.SpUtil;
import cn.onecloudtech.sl.dcpolice.utils.ToastUtil;
import cn.onecloudtech.sl.dcpolice.utils.Util;


/**
 * Created by xcc on 2015/12/16.
 */
public class BaseApplication extends Application {

    public static String cacheDir;
    public static Context mAppContext = null;
    public static User user;
    private String currentVersionName;
    private int currentVersionCode;
    private SubscriberOnNextListener getFetchVersionOnNext;

    private static BaseApplication baseApplication;

    public static Context getAppContext() {
        return baseApplication;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        baseApplication = this;
        SpUtil.init(this);
        MAppManager.init(this);
//        HotFixManger.updatePatchJar();
        JPushInterface.setDebugMode(true);
//        checkUpdate();

        // 初始化 retrofit
//        RetrofitSingleton.init();
//        CrashHandler.init(new CrashHandler(getApplicationContext()));
//        if (!BuildConfig.DEBUG) {
//            CrashReport.initCrashReport(getApplicationContext(), "900028220", false);
//        }
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();
//        LeakCanary.install(this);
//        RxUtils.unifiedErrorHandler();
        //Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    private void checkUpdate() {
        currentVersionName = Util.getVersion(this);
        currentVersionCode = Util.getVersionCode(this);
        ToastUtil.showLong("当前版本是"+currentVersionName);
        getFetchVersionOnNext = new SubscriberOnNextListener<UpdateData>() {
            @Override
            public void onNext(UpdateData mUpdateData) {
                if (mUpdateData != null) {
                    System.out.println(C.TAG + "version from server is " + mUpdateData.getVersionname() + mUpdateData.getVersionshort());
                    if (currentVersionName.compareTo(mUpdateData.getVersionshort()) < 0) {
                        CheckVersion.showUpdateDialog(mUpdateData,mAppContext);
                    } else {

                    }
                    if(currentVersionCode < mUpdateData.getVersioncode() ){
                        HotFixManger.updatePatchJar(mAppContext);
                    }
                }
            }
        };

        CheckVersion.checkVersion(this, getFetchVersionOnNext);
    }

    public void setJPush() {
        JPushInterface.init(this);
    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static Context getmAppContext() {
        return mAppContext;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        BaseApplication.user = user;
    }
}
