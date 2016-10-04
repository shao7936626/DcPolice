package cn.onecloudtech.sl.dcpolice.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.onecloudtech.sl.dcpolice.R;
import cn.onecloudtech.sl.dcpolice.adapter.PersonelInfoSearchAdapter;
import cn.onecloudtech.sl.dcpolice.adapter.PhotoAdapter;
import cn.onecloudtech.sl.dcpolice.base.BaseActivity;
import cn.onecloudtech.sl.dcpolice.base.BaseApplication;
import cn.onecloudtech.sl.dcpolice.http.HttpMethods;
import cn.onecloudtech.sl.dcpolice.model.Jpushperson;
import cn.onecloudtech.sl.dcpolice.model.Person;
import cn.onecloudtech.sl.dcpolice.subscribers.ProgressSubscriber;
import cn.onecloudtech.sl.dcpolice.subscribers.SubscriberOnNextListener;
import cn.onecloudtech.sl.dcpolice.utils.ToastUtil;

/**
 * Created by Administrator on 2016/9/14.
 */
public class PersonelInfoSerachActivity extends Activity {

    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.personelinfo_serach_recycler_view)
    RecyclerView personelinfoSerachRecyclerView;

    private PersonelInfoSearchAdapter personelInfoSearchAdapter;
    private SubscriberOnNextListener getCheckedPersonInfoCountOnNext;
    private SubscriberOnNextListener getCheckedPersonInfoOnNext;
    private Integer CheckedPersonelInfoCount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personelinfoserach);
        ButterKnife.bind(this);
        tvHead.setText("历史信息查询");

        getCheckedPersonInfoCountOnNext = new SubscriberOnNextListener<Integer>() {

            @Override
            public void onNext(Integer count) {
                if (count == 0) {
                    ToastUtil.showShort("暂时没有信息");
                    finish();
                } else {
                    CheckedPersonelInfoCount = count;
                    getCheckedPersonInfo();
                }
            }
        };

        getCheckedPersonInfoOnNext = new SubscriberOnNextListener<ArrayList<Jpushperson>>() {

            @Override
            public void onNext(ArrayList<Jpushperson> jpushPersonList) {
                initViews(jpushPersonList);
            }
        };

        HttpMethods.getInstance().doGetCheckedPersonelInfoCount(new ProgressSubscriber(getCheckedPersonInfoCountOnNext, PersonelInfoSerachActivity.this), ((BaseApplication) getApplication()).getUser().getId());
    }


    private void getCheckedPersonInfo() {
        if (CheckedPersonelInfoCount < 40) {
            HttpMethods.getInstance().doGetCheckedPersonelInfo(new ProgressSubscriber(getCheckedPersonInfoOnNext, PersonelInfoSerachActivity.this), 0, CheckedPersonelInfoCount, ((BaseApplication) getApplication()).getUser().getId());
        } else {
            HttpMethods.getInstance().doGetCheckedPersonelInfo(new ProgressSubscriber(getCheckedPersonInfoOnNext, PersonelInfoSerachActivity.this), 0, CheckedPersonelInfoCount, ((BaseApplication) getApplication()).getUser().getId());
        }

    }

    private void initViews(ArrayList<Jpushperson> jpushpersonArrayList) {
        personelInfoSearchAdapter = new PersonelInfoSearchAdapter(this, jpushpersonArrayList);
        initRecyclerViewAdapter(personelinfoSerachRecyclerView, personelInfoSearchAdapter);

    }

    private void initRecyclerViewAdapter(RecyclerView mRecyclerView, PersonelInfoSearchAdapter mAdapter) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
