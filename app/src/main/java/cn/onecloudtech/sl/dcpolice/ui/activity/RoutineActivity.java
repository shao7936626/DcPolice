package cn.onecloudtech.sl.dcpolice.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.onecloudtech.sl.dcpolice.R;

/**
 * Created by Administrator on 2016/7/22.
 */
public class RoutineActivity extends Activity {
    @Bind(R.id.btn_entrypersonelinfo)
    Button btnEntrypersonelinfo;
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.btn_searchpersonelinfo)
    Button btnSearchpersonelinfo;
    @Bind(R.id.btn_rentalAndfloating)
    Button btnRentalAndfloating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        ButterKnife.bind(this);
        tvHead.setText("日常工作");
    }

    @OnClick({R.id.btn_entrypersonelinfo, R.id.btn_searchpersonelinfo,R.id.btn_rentalAndfloating})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_entrypersonelinfo:
                Intent entryInfo = new Intent(this, PersonelInfoEntyActivity.class);
                startActivity(entryInfo);
                break;
            case R.id.btn_searchpersonelinfo:
                Intent checkinfo = new Intent(this, PersonelInfoSerachActivity.class);
                startActivity(checkinfo);
                break;
            case R.id.btn_rentalAndfloating:
                Intent rentalinfo = new Intent(this, RentalAndFloatingActivity.class);
                startActivity(rentalinfo);
                break;
        }


    }


}
