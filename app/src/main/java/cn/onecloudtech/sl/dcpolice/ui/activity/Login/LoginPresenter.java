package cn.onecloudtech.sl.dcpolice.ui.activity.Login;

import cn.onecloudtech.sl.dcpolice.C;
import cn.onecloudtech.sl.dcpolice.model.User;
import cn.onecloudtech.sl.dcpolice.utils.SpUtil;

/**
 * Created by Administrator on 2016/9/19.
 */
public class LoginPresenter extends LoginContract.Presenter{


    @Override
    public void login(String name, String pass) {
        mRxManager.add(mModel.login(name, pass).subscribe(user -> {
                    mRxManager.post(C.EVENT_LOGIN, user);
                    mView.loginSuccess(user);
                }, e -> mView.dissmissProgressDialog()
        ));
    }

    @Override
    public void onStart() {

    }
}
