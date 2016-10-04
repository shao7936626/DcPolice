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
import cn.onecloudtech.sl.dcpolice.ui.activity.FLoatingPopulation.FloatingPopulationActivity;
import cn.onecloudtech.sl.dcpolice.ui.activity.RentalHousing.RentalHousingActivity;

/**
 * Created by Administrator on 2016/9/29.
 */
public class RentalAndFloatingActivity extends Activity {
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.btn_rentalhousing)
    Button btnRentalhousing;
    @Bind(R.id.btn_floatingpopulation)
    Button btnFloatingpopulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentalandfloating);
        ButterKnife.bind(this);
        tvHead.setText("日常工作");
    }

    @OnClick({R.id.btn_rentalhousing, R.id.btn_floatingpopulation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rentalhousing:
                Intent rentalInfo = new Intent(this, RentalHousingActivity.class);
                startActivity(rentalInfo);
                break;
            case R.id.btn_floatingpopulation:
                Intent relateInfo = new Intent(this, FloatingPopulationActivity.class);
                startActivity(relateInfo);
                break;
        }
    }
}
