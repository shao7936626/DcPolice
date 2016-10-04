package cn.onecloudtech.sl.dcpolice.ui.activity.RentalHousing;

import java.util.Map;

import cn.onecloudtech.sl.dcpolice.C;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016/9/29.
 */
public class RentalHousingPresenter extends RentalHousingContract.Presenter{

    @Override
    public void uploadRentalHosuingInfo(Map<String, RequestBody> map) {
        mRxManager.add(mModel.uploadRentalHosuingInfo(map).subscribe(uploadResult -> {
                    mRxManager.post(C.EVENT_UPLOAD_RENTAL, uploadResult);
                    mView.uploadSuccess(uploadResult);
                }, e -> mView.dissmissProgressDialog()
        ));
    }

    @Override
    public void onStart() {

    }
}
