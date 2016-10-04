package cn.onecloudtech.sl.dcpolice.ui.activity.RentalHousing;

import java.util.Map;

import cn.onecloudtech.sl.dcpolice.base.BaseModel;
import cn.onecloudtech.sl.dcpolice.base.BasePresenter;
import cn.onecloudtech.sl.dcpolice.base.BaseView;
//import cn.onecloudtech.sl.dcpolice.model.User;
import cn.onecloudtech.sl.dcpolice.model.UploadResult;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/29.
 */
public interface RentalHousingContract {
    interface Model extends BaseModel {
        Observable<UploadResult> uploadRentalHosuingInfo(Map<String, RequestBody> map);

    }


    interface View extends BaseView {
        void uploadSuccess(UploadResult uploadResult);
        void showProgressDialog();
        void dissmissProgressDialog();
        void showMsg(String  msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void uploadRentalHosuingInfo(Map<String, RequestBody> map);
        @Override
        public void onStart() {}
    }
}
