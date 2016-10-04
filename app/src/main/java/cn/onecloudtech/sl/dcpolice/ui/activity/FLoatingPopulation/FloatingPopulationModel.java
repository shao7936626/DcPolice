package cn.onecloudtech.sl.dcpolice.ui.activity.FLoatingPopulation;

import java.util.Map;

import cn.onecloudtech.sl.dcpolice.api.Api;
import cn.onecloudtech.sl.dcpolice.model.UploadResult;
import cn.onecloudtech.sl.dcpolice.utils.helper.RxSchedulers;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/29.
 */
public class FloatingPopulationModel implements FloatingPopulationContract.Model{
    @Override
    public Observable<UploadResult> uploadFloatingPopulation(Map<String, RequestBody> map) {
        return Api.getInstance().service
                .uploadFloatingPopulation(map)
                .compose(RxSchedulers.io_main());
    }
}
