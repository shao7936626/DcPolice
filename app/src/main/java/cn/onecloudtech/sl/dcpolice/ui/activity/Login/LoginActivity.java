package cn.onecloudtech.sl.dcpolice.ui.activity.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.kyleduo.switchbutton.SwitchButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.onecloudtech.sl.dcpolice.C;
import cn.onecloudtech.sl.dcpolice.R;
import cn.onecloudtech.sl.dcpolice.base.BaseActivity;
import cn.onecloudtech.sl.dcpolice.base.BaseApplication;
import cn.onecloudtech.sl.dcpolice.http.HttpMethods;
import cn.onecloudtech.sl.dcpolice.model.UpdateData;
import cn.onecloudtech.sl.dcpolice.model.User;
import cn.onecloudtech.sl.dcpolice.progress.ProgressDialogHandler;
import cn.onecloudtech.sl.dcpolice.subscribers.ProgressSubscriber;
import cn.onecloudtech.sl.dcpolice.subscribers.SubscriberOnNextListener;
import cn.onecloudtech.sl.dcpolice.ui.activity.WorkBenchActivity;
import cn.onecloudtech.sl.dcpolice.utils.CheckVersion;
import cn.onecloudtech.sl.dcpolice.utils.SharedPreferenceUtil;
import cn.onecloudtech.sl.dcpolice.utils.SpUtil;
import cn.onecloudtech.sl.dcpolice.utils.ToastUtil;
import cn.onecloudtech.sl.dcpolice.utils.Util;
import rx.Subscriber;

;

/**
 * Created by Administrator on 2016/7/18.
 */
public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {


    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.sb_default)
    SwitchButton sbDefault;


    private SubscriberOnNextListener getLoginOnNext;
    private SubscriberOnNextListener getFetchVersionOnNext;

    private ProgressDialogHandler mProgressDialogHandler;

    private Subscriber subscriber;
    private boolean rememberPasswd;
    public SharedPreferenceUtil mSharedPreferenceUtil = null;
    private String currentVersionName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        // sp  记住密码


        mSharedPreferenceUtil = SharedPreferenceUtil.getInstance();
        if (mSharedPreferenceUtil.getRemPassWd()) {
            etAccount.setText(mSharedPreferenceUtil.getUserName());
            etPassword.setText(mSharedPreferenceUtil.getPassword());
            sbDefault.setChecked(mSharedPreferenceUtil.getRemPassWd());
        }
        //  swtichButton 记住密码
        sbDefault.setOnCheckedChangeListener((buttonView, isChecked) -> {
            rememberPasswd = isChecked ? true : false;
            if (rememberPasswd == false) {
                if (etAccount.getText().toString().length() > 0) {
                    etAccount.setText("");
                    etPassword.setText("");
                    mSharedPreferenceUtil.setRemPassWd(rememberPasswd);
                    mSharedPreferenceUtil.setUserName("");
                    mSharedPreferenceUtil.setPassword("");
                }
            } else {
                mSharedPreferenceUtil.setRemPassWd(rememberPasswd);
                mSharedPreferenceUtil.setUserName(etAccount.getText().toString());
                mSharedPreferenceUtil.setPassword(etPassword.getText().toString());
            }
        });


//        sbDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//                                                 @Override
//                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                                                     rrememberPasswd = isChecked ? true : false;
//                                                     if (rrememberPasswd == false)
//                                                         if (etAccount.getText().toString().length() > 0) {
//                                                             etAccount.setText("");
//                                                             etPassword.setText("");
//                                                             mSharedPreferenceUtil.setRemPassWd(rrememberPasswd);
//                                                             mSharedPreferenceUtil.setUserName("");
//                                                             mSharedPreferenceUtil.setPassword("");
//                                                         }
//                                                     // System.out.println(C.TAG + rrememberPasswd + mSharedPreferenceUtil.getRemPassWd());
//                                                 }
//                                             }
//
//        );

        //登陆
        btnLogin.setOnClickListener(v -> {
            String name = etAccount.getText().toString();
            String pass = etPassword.getText().toString();
            String msg = TextUtils.isEmpty(name) ? "用户名不能为空!" : TextUtils.isEmpty(pass) ? "密码不能为空!" : "";
            if (!TextUtils.isEmpty(msg)) showMsg(msg);
            else {
                showProgressDialog();
                mPresenter.login(name, pass);
            }
        });
    }


    @Override
    public void doInitView() {
        checkUpdate();
    }

    @Override
    public void didInitView() {

    }

    private void checkUpdate() {
        currentVersionName = Util.getVersion(this);
        getFetchVersionOnNext = new SubscriberOnNextListener<UpdateData>() {
            @Override
            public void onNext(UpdateData mUpdateData) {
                if (mUpdateData != null) {
                    System.out.println(C.TAG + "version from server is " + mUpdateData.getVersionname() + mUpdateData.getVersionshort());
                    if (currentVersionName.compareTo(mUpdateData.getVersionshort()) < 0) {
                        CheckVersion.showUpdateDialog(mUpdateData, LoginActivity.this);
                    } else {

                    }
                }
            }
        };

        CheckVersion.checkVersion(this, getFetchVersionOnNext);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        stopPush();
//        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);
//
//
//        currentVersionName = Util.getVersion(this);
////         System.out.println(C.TAG + "version from loacl  is " + currentVersionName.compareTo("2"));
//        getFetchVersionOnNext = new SubscriberOnNextListener<UpdateData>() {
//            @Override
//            public void onNext(UpdateData mUpdateData) {
//                if (mUpdateData != null) {
//                    System.out.println(C.TAG + "version from server is " + mUpdateData.getVersionname() + mUpdateData.getVersionshort());
//                    if (currentVersionName.compareTo(mUpdateData.getVersionshort()) < 0) {
//                        CheckVersion.showUpdateDialog(mUpdateData, LoginActivity.this);
//                    } else {
//
//                    }
//                }
//            }
//        };
//
//        CheckVersion.checkVersion(this, getFetchVersionOnNext);
//
//        mSharedPreferenceUtil = SharedPreferenceUtil.getInstance();
//        if (mSharedPreferenceUtil.getRemPassWd())
//        {
//            etAccount.setText(mSharedPreferenceUtil.getUserName());
//            etPassword.setText(mSharedPreferenceUtil.getPassword());
//            sbDefault.setChecked(mSharedPreferenceUtil.getRemPassWd());
//        }
//
//        getLoginOnNext = new SubscriberOnNextListener<User>()
//        {
//            @Override
//            public void onNext(User mUser) {
//                if (mUser != null) {
//                    if (mUser.getUsername() != null) {
//                        ((BaseApplication) getApplication()).setUser(mUser);
//                        Intent workbench = new Intent(LoginActivity.this, WorkBenchActivity.class);
//                        setPushInfo(((BaseApplication) getApplication()).getUser().getUsername());
//                        ((BaseApplication) getApplication()).setJPush();
//                       // resumePush();
//                        startActivity(workbench);
//                        finish();
//                    } else if (mUser.getUnitid() == -1) {
//                        ToastUtil.showShort("密码错误");
//                    }
//                } else {
//
//                }
//            }
//        }
//
//        ;
//
//        sbDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//                                                 @Override
//                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                                                     rrememberPasswd = isChecked ? true : false;
//                                                     if (rrememberPasswd == false)
//                                                         if (etAccount.getText().toString().length() > 0) {
//                                                             etAccount.setText("");
//                                                             etPassword.setText("");
//                                                             mSharedPreferenceUtil.setRemPassWd(rrememberPasswd);
//                                                             mSharedPreferenceUtil.setUserName("");
//                                                             mSharedPreferenceUtil.setPassword("");
//                                                         }
//                                                     // System.out.println(C.TAG + rrememberPasswd + mSharedPreferenceUtil.getRemPassWd());
//                                                 }
//                                             }
//
//        );
//    }
//
//    private void dologin() {
//        String username = etAccount.getText().toString().trim();
//        String password = etPassword.getText().toString().trim();
//        if ((username.length() > 0) && (password.length() > 0)) {
//            if (rrememberPasswd) {
//                mSharedPreferenceUtil.setRemPassWd(rrememberPasswd);
//                mSharedPreferenceUtil.setUserName(username);
//                mSharedPreferenceUtil.setPassword(password);
//            }
//            HttpMethods.getInstance().doLogin(new ProgressSubscriber(getLoginOnNext, LoginActivity.this), username, password);
//        } else {
//            ToastUtil.showShort("用户名和密码不能为空");
//        }
//}

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(C.SERVER_IP + C.SERVER_PORT)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        ApiService service = retrofit.create(ApiService.class);
//        service.login("test", "123")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResult<User>>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(LoginActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        //resultTV.setText(e.getMessage());
//                        Log.e(C.TAG, String.valueOf(e));
//                        // Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<User> httpResult) {
//                        if(httpResult != null)
//                         Toast.makeText(LoginActivity.this, String.valueOf(httpResult.getSubjects().getId()), Toast.LENGTH_SHORT).show();
//                    }
//                });
//        subscriber = new Subscriber<User>() {
//            @Override
//            public void onCompleted() {
//                //Toast.makeText(LoginActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                //resultTV.setText(e.getMessage());
//            }
//
//            @Override
//            public void onNext(User mUser) {
//                //resultTV.setText(movieEntity.toString());
//                if (mUser != null)
//                    Toast.makeText(LoginActivity.this, "here", Toast.LENGTH_SHORT).show();
//            }
//        };
//        HttpMethods.getInstance().doLogin(subscriber, "test", "123");
//        HttpMethods.getInstance().doLogin(new ProgressSubscriber(getLoginOnNext, LoginActivity.this), "test", "123");
//        Call<User>  repos = service.login("test", "123");
//
//        repos.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//               // resultTV.setText(response.body().toString());
//                if(response != null){
//                    User mUser = (User)response.body();
//                    System.out.println(C.TAG +  mUser.id);
//                }else{
//                    System.out.println("response is null ");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User>  call, Throwable t) {
//               // resultTV.setText(t.getMessage());
//            }
//        });
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_URL)
////                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        String[] names = {"sl", "lq", "wq", "zq"};
//        Observable.from(names)
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String name) {
//                        System.out.println(TAG + name);
//                    }
//                });

//    @OnClick(R.id.btn_login)
//    public void onClick() {
//       dologin();
    // ((BaseApplication) getApplication()).setUser();
//        Intent workbench = new Intent(this, WorkBenchActivity.class);
//        startActivity(workbench);
//        setPushInfo("test");
//        ((BaseApplication) getApplication()).setJPush();
//    }


    @Override
    public void loginSuccess(User user) {
        if (user != null) {
            if (user.getUsername() != null) {
                ((BaseApplication) getApplication()).setUser(user);
                Intent workbench = new Intent(LoginActivity.this, WorkBenchActivity.class);
                setPushInfo(((BaseApplication) getApplication()).getUser().getUsername());
                ((BaseApplication) getApplication()).setJPush();
                // resumePush();
                startActivity(workbench);
                finish();
            } else if (user.getUnitid() == -1) {
                //ToastUtil.showShort("密码错误");
                showMsg("密码错误");
            }
        } else {

        }
    }

    @Override
    public void showProgressDialog() {
        mProgressDialogHandler = new ProgressDialogHandler(this, null, false);
        mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
    }

    @Override
    public void dissmissProgressDialog() {
        mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
        mProgressDialogHandler = null;
        showMsg("登陆失败！");
    }

    @Override
    public void showMsg(String msg) {
        Snackbar.make(btnLogin, msg, Snackbar.LENGTH_LONG).show();
    }
}
