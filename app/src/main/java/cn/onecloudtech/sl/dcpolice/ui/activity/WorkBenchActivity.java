package cn.onecloudtech.sl.dcpolice.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.onecloudtech.sl.dcpolice.R;
import cn.onecloudtech.sl.dcpolice.base.BaseApplication;
import cn.onecloudtech.sl.dcpolice.model.User;

/**
 * Created by Administrator on 2016/7/21.
 */
public class WorkBenchActivity extends Activity {
    @Bind(R.id.btn_routine)
    RelativeLayout btnToutine;
    @Bind(R.id.policeNumber)
    TextView policeNumber;
    @Bind(R.id.realname)
    TextView realname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workbench);
        ButterKnife.bind(this);

        User mUser = ((BaseApplication) getApplication()).getUser();

        if (mUser != null)
            if (mUser.getRealname() != null && mUser.getJobnumber() != null)
                if (mUser.getRealname().length() > 0 && mUser.getJobnumber().length() > 0) {
                    realname.setText(mUser.getRealname());
                    policeNumber.setText(mUser.getJobnumber());
                }
    }

    @OnClick({R.id.btn_routine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_routine:
                Intent workbench = new Intent(this, RoutineActivity.class);
                startActivity(workbench);
                break;
        }

    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
