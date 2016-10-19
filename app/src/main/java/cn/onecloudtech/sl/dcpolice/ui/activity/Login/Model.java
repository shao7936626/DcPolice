package cn.onecloudtech.sl.dcpolice.ui.activity.Login;

import cn.onecloudtech.sl.dcpolice.base.BaseModel;
import cn.onecloudtech.sl.dcpolice.model.User;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/19.
 */

public interface Model extends BaseModel{
    Observable<User> login(String name, String pass);
}
