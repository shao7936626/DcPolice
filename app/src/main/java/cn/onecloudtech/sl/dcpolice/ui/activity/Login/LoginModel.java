package cn.onecloudtech.sl.dcpolice.ui.activity.Login;

import cn.onecloudtech.sl.dcpolice.api.Api;
import cn.onecloudtech.sl.dcpolice.model.User;
import cn.onecloudtech.sl.dcpolice.utils.helper.RxSchedulers;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/19.
 */
public class LoginModel implements Model {
    @Override
    public Observable<User> login(String name, String pass) {
        return Api.getInstance().service
                .login(name, pass)
                .compose(RxSchedulers.io_main());
    }
}
