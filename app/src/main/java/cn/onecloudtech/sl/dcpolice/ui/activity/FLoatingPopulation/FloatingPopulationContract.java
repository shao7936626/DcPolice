package cn.onecloudtech.sl.dcpolice.ui.activity.FLoatingPopulation;

import java.util.Map;

import cn.onecloudtech.sl.dcpolice.base.BaseModel;
import cn.onecloudtech.sl.dcpolice.base.BasePresenter;
import cn.onecloudtech.sl.dcpolice.base.BaseView;
import cn.onecloudtech.sl.dcpolice.model.UploadResult;
import cn.onecloudtech.sl.dcpolice.model.User;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/29.
 */
public interface FloatingPopulationContract {
    interface Model extends BaseModel {
        Observable<UploadResult> uploadFloatingPopulation(Map<String, RequestBody> map);

    }


    interface View extends BaseView {
        void uploadSuccess(UploadResult uploadResult);
        void showProgressDialog();
        void dissmissProgressDialog();
        void showMsg(String  msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void uploadFloatingPopulation(Map<String, RequestBody> map);
        @Override
        public void onStart() {}
    }
}
