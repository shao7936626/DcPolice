package cn.onecloudtech.sl.dcpolice.ui.activity.Login;

import android.content.Context;

import cn.onecloudtech.sl.dcpolice.base.BaseModel;
import cn.onecloudtech.sl.dcpolice.base.BasePresenter;
import cn.onecloudtech.sl.dcpolice.base.BaseView;
import cn.onecloudtech.sl.dcpolice.http.HttpMethods;
import cn.onecloudtech.sl.dcpolice.model.User;
import cn.onecloudtech.sl.dcpolice.subscribers.ProgressSubscriber;
import cn.onecloudtech.sl.dcpolice.subscribers.SubscriberOnNextListener;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/19.
 */
public interface LoginContract {
    interface Model extends BaseModel {
        Observable<User> login(String name, String pass);

    }


    interface View extends BaseView {
        void loginSuccess(User user);
        void showProgressDialog();
        void dissmissProgressDialog();
        void showMsg(String  msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void login(String name, String pass);
        @Override
        public void onStart() {}
    }
}
