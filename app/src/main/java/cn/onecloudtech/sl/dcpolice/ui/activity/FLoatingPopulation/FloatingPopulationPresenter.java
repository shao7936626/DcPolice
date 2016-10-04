package cn.onecloudtech.sl.dcpolice.ui.activity.FLoatingPopulation;


import java.util.Map;

import cn.onecloudtech.sl.dcpolice.C;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016/9/29.
 */
public class FloatingPopulationPresenter  extends FloatingPopulationContract.Presenter{
    @Override
    public void uploadFloatingPopulation(Map<String, RequestBody> map) {
        mRxManager.add(mModel.uploadFloatingPopulation(map).subscribe(uploadResult -> {
                    mRxManager.post(C.EVENT_UPLOAD_FLOATING, uploadResult);
                    mView.uploadSuccess(uploadResult);
                }, e -> mView.dissmissProgressDialog()
        ));
    }

    @Override
    public void onStart() {

    }
}
