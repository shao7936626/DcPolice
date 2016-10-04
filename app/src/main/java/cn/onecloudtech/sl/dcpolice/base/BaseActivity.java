package cn.onecloudtech.sl.dcpolice.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.WindowManager;


import java.util.Set;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.onecloudtech.sl.dcpolice.R;
import cn.onecloudtech.sl.dcpolice.utils.ACache;
import cn.onecloudtech.sl.dcpolice.utils.SharedPreferenceUtil;
import cn.onecloudtech.sl.dcpolice.utils.TUtil;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hugo on 2015/12/16.
 */
public abstract class BaseActivity <T extends BasePresenter, E extends BaseModel> extends Activity {

    // public ACache aCache;
    public SharedPreferenceUtil mSharedPreferenceUtil = null;
    public T mPresenter;
    public E mModel;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
        this.setContentView(this.getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        //  aCache = ACache.get(getApplication());
        mSharedPreferenceUtil = SharedPreferenceUtil.getInstance();
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.doInitView();
        this.initView();
        this.didInitView();
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);


    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void doInitView();

    public abstract void didInitView();

    public void stopPush() {
        if (JPushInterface.isPushStopped(getApplicationContext()) == false) {
            JPushInterface.stopPush(getApplicationContext());
        }
    }

    public void resumePush() {
        if (JPushInterface.isPushStopped(getApplicationContext()) == true) {
            JPushInterface.resumePush(getApplicationContext());
        }
    }

    public void setPushInfo(String alias) {
        JPushInterface.setAlias(getApplicationContext(),alias,null);
        //JPushInterface.setTags(getApplicationContext(), tags, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
        ButterKnife.unbind(this);
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }


}
