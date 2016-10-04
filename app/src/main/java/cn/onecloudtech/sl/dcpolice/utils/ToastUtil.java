package cn.onecloudtech.sl.dcpolice.utils;

import android.widget.Toast;

import cn.onecloudtech.sl.dcpolice.base.BaseApplication;


/**
 * Created by HugoXie on 16/5/23.
 *
 * Email: Hugo3641@gamil.com
 * GitHub: https://github.com/xcc3641
 * Info:
 */
public class ToastUtil extends CustomToast{

    public static void showShort(String msg) {
        CustomToast.showToast(BaseApplication.getmAppContext(), msg, 1000);
    }

    public static void showLong(String msg) {
        CustomToast.showToast(BaseApplication.getmAppContext(), msg, 2000);
    }
}
