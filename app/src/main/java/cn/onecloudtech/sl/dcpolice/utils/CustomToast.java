package cn.onecloudtech.sl.dcpolice.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/4.
 */
public class CustomToast {
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    public static void showToast(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else{
            if(duration == Toast.LENGTH_LONG)
                mToast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
            else if(duration == Toast.LENGTH_SHORT)
                mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
            else
                mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        }

        mHandler.postDelayed(r, duration);

        mToast.show();
    }

    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }

}
