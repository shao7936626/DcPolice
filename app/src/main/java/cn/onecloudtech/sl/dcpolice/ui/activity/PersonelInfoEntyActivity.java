package cn.onecloudtech.sl.dcpolice.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker.OnDateSelectedListener;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.onecloudtech.sl.dcpolice.C;
import cn.onecloudtech.sl.dcpolice.R;
import cn.onecloudtech.sl.dcpolice.adapter.PhotoAdapter;
import cn.onecloudtech.sl.dcpolice.base.BaseApplication;
import cn.onecloudtech.sl.dcpolice.http.HttpMethods;
import cn.onecloudtech.sl.dcpolice.listener.RecyclerItemClickListener;
import cn.onecloudtech.sl.dcpolice.model.CheckPerson;
import cn.onecloudtech.sl.dcpolice.model.Person;
import cn.onecloudtech.sl.dcpolice.subscribers.ProgressSubscriber;
import cn.onecloudtech.sl.dcpolice.subscribers.SubscriberOnNextListener;
import cn.onecloudtech.sl.dcpolice.utils.HashMapUtil;
import cn.onecloudtech.sl.dcpolice.utils.ToastUtil;
import cn.qqtheme.framework.picker.OptionPicker;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by Administrator on 2016/7/22.
 */
public class PersonelInfoEntyActivity extends Activity implements WheelPicker.OnItemSelectedListener, OnDateSelectedListener {


    @Bind(R.id.btn_sex)
    Button btnSex;
    @Bind(R.id.btn_borndate)
    Button btnBorndate;
    @Bind(R.id.et_person_name)
    EditText etPersonName;
    @Bind(R.id.et_person_qq)
    EditText etPersonQq;
    @Bind(R.id.et_person_wechat)
    EditText etPersonWechat;
    @Bind(R.id.et_person_idcard)
    EditText etPersonIdcard;
    @Bind(R.id.et_person_phonenumber)
    EditText etPersonPhonenumber;
    @Bind(R.id.et_person_registerres)
    EditText etPersonRegisterres;
    @Bind(R.id.et_person_otherinfo)
    EditText etPersonOtherinfo;
    @Bind(R.id.et_person_currentresidence)
    EditText etPersonCurrentresidence;
    @Bind(R.id.et_person_bankcard)
    EditText etPersonBankcard;
    @Bind(R.id.et_person_carnumber)
    EditText etPersonCarnumber;
    @Bind(R.id.btn_person_entrySense)
    Button btnPersonEntrySense;
    @Bind(R.id.btn_person_type)
    Button btnPersonType;
    @Bind(R.id.btn_upload)
    Button btnUpload;
    @Bind(R.id.btn_facephoto)
    Button btnFacephoto;
    @Bind(R.id.btn_bodyphoto)
    Button btnBodyphoto;
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.facephoto_recycler_view)
    RecyclerView facephotoRecyclerView;
    @Bind(R.id.bodyphoto_recycler_view)
    RecyclerView bodyphotoRecyclerView;
    @Bind(R.id.btn_check)
    Button btnCheck;
    @Bind(R.id.btn_department)
    Button btnDepartment;

    private HashMap<String, EditText> mPersonEditMap;   //用来判断是否EditText为空的
    private HashMap<String, Button> mPersonBtnMap;
    private HashMap<String, EditText> mCheckPersonEditMap;
    private SubscriberOnNextListener getUploadPersonInfoOnNext;
    private SubscriberOnNextListener getCheckPersonInfoOnNext;
    private static final int WHEELNORMAL = 0;
    private static final int WHEELDATE = 1;

    private int unitname = -1;

    private RecyclerView facephoto_recyclerView;
    private RecyclerView bodyphoto_recyclerView;
    private PhotoAdapter facephotoAdapter;
    private PhotoAdapter bodyphotoAdapter;
    private ArrayList<String> faceselectedPhotos = new ArrayList<>();
    private ArrayList<String> bodyselectedPhotos = new ArrayList<>();
    private Map<String, RequestBody> map = new HashMap<>();
    private int photoPickerState = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personelinfoenty);
        ButterKnife.bind(this);
        tvHead.setText("人员信息录入");
        mPersonEditMap = new HashMap<String, EditText>();
        mPersonEditMap.put(getResources().getString(R.string.personName), etPersonName);
        mPersonEditMap.put(getResources().getString(R.string.IdCard), etPersonIdcard);
        mPersonEditMap.put(getResources().getString(R.string.phoneNumber), etPersonPhonenumber);
        mPersonEditMap.put(getResources().getString(R.string.registeredResidence), etPersonRegisterres);
        mPersonEditMap.put(getResources().getString(R.string.currentResidence), etPersonCurrentresidence);

        mPersonBtnMap = new HashMap<String, Button>();
        mPersonBtnMap.put(getResources().getString(R.string.sex), btnSex);

        mCheckPersonEditMap = new HashMap<String, EditText>();
        mCheckPersonEditMap.put(getResources().getString(R.string.personName), etPersonName);
        mCheckPersonEditMap.put(getResources().getString(R.string.IdCard), etPersonIdcard);

//        mPersonEditList.add(etPersonQq);
//        mPersonEditList.add(etPersonWechat);
//        mPersonEditList.add(etPersonIdcard);
//        mPersonEditList.add(etPersonPhonenumber);
//        mPersonEditList.add(etPersonRegisterres);
//        mPersonEditList.add(etPersonOtherinfo);
//        mPersonEditList.add(etPersonCurrentresidence);
//        mPersonEditList.add(etPersonBankcard);
//        mPersonEditList.add(etPersonCarnumber);

        getUploadPersonInfoOnNext = new SubscriberOnNextListener<Person>() {

            @Override
            public void onNext(Person person) {
                if (person.getId() == -1) {
                    ToastUtil.showLong("上传成功");
                    finish();
//                    finish();
                }
            }
        };
        getCheckPersonInfoOnNext = new SubscriberOnNextListener<CheckPerson>() {
            @Override
            public void onNext(CheckPerson checkPerson) {
                if (checkPerson.getUploadResult() == 1) {

                    ToastUtil.showLong("上传检测数据成功");
                    if (checkPerson.getPushResult() != 1) {
                        ToastUtil.showLong("推送没有成功！");
                    }
                }
            }
        };
//     1:"赌博人员"，
//        2:"涉恶人员"，
//        3:"涉黄人员"，
//        4:"食药环人员"，
//        5:"涉毒人员"，
//        6:"留置盘问"，
//        7:"侵财人员"，
//        8:"刑事传唤"，
//        9:"负案在逃人员"，
//        10:"维稳人员"，
//        11:"失踪人员"，
//        12:"侵财人员分析"，
//        13:"技术比中人员"，
//        15："社会人员"

//        for (int i = 0; i < C.persontypeList.length; i++) {
//            C.PERSONTYPE.put(i+1,C.persontypeList[i]);
//        }

        initData();
        initRecyclerViews();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initRecyclerViews() {
//        initRecyclerView(facephoto_recyclerView, facephotoAdapter, faceselectedPhotos, R.id.facephoto_recycler_view);
//        initRecyclerView(bodyphoto_recyclerView, bodyphotoAdapter, bodyselectedPhotos, R.id.bodyphoto_recycler_view);
//        setRecyclerViewListenser(facephoto_recyclerView, faceselectedPhotos);
//        setRecyclerViewListenser(bodyphoto_recyclerView, bodyselectedPhotos);


        facephoto_recyclerView = (RecyclerView) this.findViewById(R.id.facephoto_recycler_view);
        bodyphoto_recyclerView = (RecyclerView) this.findViewById(R.id.bodyphoto_recycler_view);
        facephotoAdapter = new PhotoAdapter(this, faceselectedPhotos);
        bodyphotoAdapter = new PhotoAdapter(this, bodyselectedPhotos);
        initRecyclerViewAdapter(facephoto_recyclerView, facephotoAdapter);
        initRecyclerViewAdapter(bodyphoto_recyclerView, bodyphotoAdapter);


        facephoto_recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                photoPickerState = 0;
                PhotoPreview.builder()
                        .setPhotos(faceselectedPhotos)
                        .setCurrentItem(position)
                        .start(PersonelInfoEntyActivity.this);
            }
        }));

        bodyphoto_recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                photoPickerState = 1;
                PhotoPreview.builder()
                        .setPhotos(bodyselectedPhotos)
                        .setCurrentItem(position)
                        .start(PersonelInfoEntyActivity.this);
            }
        }));
    }

    private void initRecyclerView(RecyclerView mRecyclerView, PhotoAdapter mPhotoAdapter, ArrayList<String> selectedPhotos, int recycler_view) {
        //没有用。。。要想办法解决
        mRecyclerView = (RecyclerView) this.findViewById(recycler_view);
        mPhotoAdapter = new PhotoAdapter(this, selectedPhotos);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.HORIZONTAL));
        mRecyclerView.setAdapter(mPhotoAdapter);
    }

    private void initRecyclerViewAdapter(RecyclerView mRecyclerView, PhotoAdapter mPhotoAdapter) {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.HORIZONTAL));
        mRecyclerView.setAdapter(mPhotoAdapter);
    }


    private void setRecyclerViewListenser(RecyclerView recyclerView, ArrayList<String> mSelectedPhotos) {


    }


    private void initData() {
        //initHashMap(C.sexList, C.SEX);


        HashMapUtil.initHashMap(C.sexList, C.SEX);
        HashMapUtil.initHashMap(C.entrysenseList, C.ENTRYSENSE);
        HashMapUtil.initHashMap(C.persontypeList, C.PERSONTYPE);

    }

    private void initHashMap(String[] mStringlist, HashMap<Integer, String> mHashMap) {
        for (int i = 0; i < mStringlist.length; i++) {
            mHashMap.put(i, mStringlist[i]);
        }

    }

    @Override
    public void onDateSelected(WheelDatePicker picker, Date date) {
        switch (picker.getId()) {
            case R.id.wheel_date_picker:
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
                btnBorndate.setText(formatter.format(date));
                break;
        }
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String text = "";
        switch (picker.getId()) {
            case R.id.sex_wheel:
                if (position == 0)
                    btnSex.setText(R.string.pleaseselector);
                else if (position == 1)
                    btnSex.setText("男");
                else
                    btnSex.setText("女");
                break;
            case R.id.entrysense_wheel:
                btnPersonEntrySense.setText(C.entrysenseList[position]);
                break;
            case R.id.persontype_wheel:
                btnPersonType.setText(C.persontypeList[position]);
                break;
        }


    }


    @OnClick({R.id.btn_sex, R.id.btn_borndate, R.id.btn_person_entrySense, R.id.btn_person_type, R.id.btn_upload, R.id.btn_facephoto, R.id.btn_bodyphoto, R.id.btn_check,R.id.btn_department})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sex:
//                outerView = LayoutInflater.from(PersonelInfoEntyActivity.this).inflate(R.layout.sex_wheel_view, null);
//                wheel = (WheelPicker) outerView.findViewById(R.id.sex_wheel);
//                data.add(0, "请选择");
//                data.add(1, "男");
//                data.add(2, "女");
//                wheel.setData(data);
//                wheel.setOnItemSelectedListener(PersonelInfoEntyActivity.this);
//                new AlertDialog.Builder(PersonelInfoEntyActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
//                        .setTitle(R.string.sexSelector)
//                        .setView(outerView)
//                        .setPositiveButton(R.string.confirm, null)
//                        .show();

//                for (String E : C.sexList)
//                    data.add(E);
                showWheel(WHEELNORMAL, R.layout.sex_wheel_view, R.id.sex_wheel, R.string.sexSelector, C.sexList);

                break;
            case R.id.btn_borndate:
//                outerView = LayoutInflater.from(PersonelInfoEntyActivity.this).inflate(R.layout.date_wheel_view, null);
//                wheelDatePicker = (WheelDatePicker) outerView.findViewById(R.id.wheel_date_picker);
//                wheelDatePicker.setOnDateSelectedListener(PersonelInfoEntyActivity.this);
//                new AlertDialog.Builder(PersonelInfoEntyActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
//                        .setTitle(R.string.dateSelector)
//                        .setView(outerView)
//                        .setPositiveButton(R.string.confirm, null)
//                        .show();
                showWheel(WHEELDATE, R.layout.date_wheel_view, R.id.wheel_date_picker, R.string.dateSelector, null);
                break;
            case R.id.btn_person_entrySense:
//                outerView = LayoutInflater.from(PersonelInfoEntyActivity.this).inflate(R.layout.entrysense_wheel_view, null);
//                wheel = (WheelPicker) outerView.findViewById(R.id.entrysense_wheel);
//                wheel.setOnItemSelectedListener(PersonelInfoEntyActivity.this);
//
//                for (String E : C.entrysenseList)
//                    data.add(E);
//                wheel.setData(data);
//
//                new AlertDialog.Builder(PersonelInfoEntyActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
//                        .setTitle(R.string.entrysenseSelector)
//                        .setView(outerView)
//                        .setPositiveButton(R.string.confirm, null)
//                        .show();


                showWheel(WHEELNORMAL, R.layout.entrysense_wheel_view, R.id.entrysense_wheel, R.string.entrysenseSelector, C.entrysenseList);

                break;
            case R.id.btn_person_type:
//                outerView = LayoutInflater.from(PersonelInfoEntyActivity.this).inflate(R.layout.persontype_wheel_view, null);
//                wheel = (WheelPicker) outerView.findViewById(R.id.persontype_wheel);
//                wheel.setOnItemSelectedListener(PersonelInfoEntyActivity.this);
//                for (String E : C.persontypeList)
//                    data.add(E);
//                wheel.setData(data);
//                new AlertDialog.Builder(PersonelInfoEntyActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
//                        .setTitle(R.string.personTypeSelector)
//                        .setView(outerView)
//                        .setPositiveButton(R.string.confirm, null)
//                        .show();

                showWheel(WHEELNORMAL, R.layout.persontype_wheel_view, R.id.persontype_wheel, R.string.personTypeSelector, C.persontypeList);
                break;
            case R.id.btn_upload:
                if (checkEdit(mPersonEditMap) == true && checkBtn(mPersonBtnMap) == true) {
//                    Person mPerson = new Person();
//                    mPerson.setName(Edit2String(etPersonName));
//                    mPerson.setQQ(Edit2String(etPersonQq));
//                    mPerson.setWeChat(Edit2String(etPersonWechat));
//                    mPerson.setIdCard(Edit2String(etPersonIdcard));
//                    mPerson.setPhoneNumber(Edit2String(etPersonPhonenumber));
//                    mPerson.setRegisteredResidence(Edit2String(etPersonRegisterres));
//                    mPerson.setOtherInfo(Edit2String(etPersonOtherinfo));
//                    mPerson.setCurrentResidence(Edit2String(etPersonCurrentresidence));
//                    mPerson.setBankCard(Edit2String(etPersonBankcard));
//                    mPerson.setCarNumber(Edit2String(etPersonCarnumber));
//
//                    //可能需要改
//                    mPerson.setSex(Button2String(btnSex));
//                    mPerson.setBorndate(Button2String(btnBorndate));
//                    mPerson.setEntrySense(Button2String(btnPersonEntrySense));
//                    mPerson.setPersonType(Button2String(btnPersonType));

                    // setRequestBody(Edit2String(etPersonName));

                    //RequestBody name = RequestBody.create(MediaType.parse("text/plain"), Edit2String(etPersonName));

                    if (map.isEmpty() == false) {
                        map.clear();
                    }
                    map.put("realname", setRequestBody(etPersonName));
                    map.put("gender", setRequestBody(btnSex));
                    map.put("birthday", setRequestBody(btnBorndate));
                    map.put("qq", setRequestBody(etPersonQq));
                    map.put("wechat", setRequestBody(etPersonWechat));
                    map.put("unitname",setRequestBody(unitname));
                    map.put("idcard", setRequestBody(etPersonIdcard));

                    map.put("phone", setRequestBody(etPersonPhonenumber));
                    map.put("regaddress", setRequestBody(etPersonRegisterres));
                    map.put("otherinfo", setRequestBody(etPersonOtherinfo));
                    map.put("liveaddress", setRequestBody(etPersonCurrentresidence));
                    map.put("creditcard", setRequestBody(etPersonBankcard));
                    map.put("carid", setRequestBody(etPersonCarnumber));
                    map.put("scene", setRequestBody(btnPersonEntrySense));
                    map.put("category", setRequestBody(btnPersonType));

                    addImage2Map("facepic", faceselectedPhotos);
                    addImage2Map("bodyphoto", bodyselectedPhotos);

//                if (faceselectedPhotos.size() > 0) {
//                    File mfile = new File(faceselectedPhotos.get(0));
//                    if (mfile != null) {
//                        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), mfile);
//                        map.put("facepic", fileBody);
//                        //return true;
//                    } else {
//                       // return false;
//                    }
//                } else {
//                    //return false;
//                }
//                if (faceselectedPhotos.size() > 0) {
//                    File mfile = new File(faceselectedPhotos.get(0));
//                    if (mfile != null){
//                        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), mfile);
//                        map.put("image\"; filename=\"" + faceselectedPhotos.get(0) + "", fileBody);
//                    }
//                }


//                RequestBody fileBody2 = RequestBody.create(MediaType.parse("image/png"), mfile);
//                map.put("image\"; filename=\"" + faceselectedPhotos.get(0) + "", fileBody);


//                    if(faceselectedPhotos.size()>0){
//                        File mfile = new File(faceselectedPhotos.get(0));
//                        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), mfile);
//                        map.put("image\"; filename=\""+faceselectedPhotos.get(0)+"", fileBody);
//                    }
                    HttpMethods.getInstance().doUploadPersonInfo(new ProgressSubscriber(getUploadPersonInfoOnNext, PersonelInfoEntyActivity.this), map);

                } else {
                    //System.out.println(faceselectedPhotos.get(0));
                    //System.out.println(bodyselectedPhotos.get(0));
//                    ToastUtil.showShort(getApplication().getString(R.string.errorpersoninfo));
                }

                break;
            case R.id.btn_department:
                onOptionPicker();

                break;
            case R.id.btn_check:
                if (map.isEmpty() == false) {
                    map.clear();
                }
                if (checkEdit(mCheckPersonEditMap) == true) {
                    map.put("realname", setRequestBody(etPersonName));
                    map.put("idcard", setRequestBody(etPersonIdcard));
                    map.put("userroleId", setRequestBody(((BaseApplication) getApplication()).getUser().getId()));
                    map.put("unitname",setRequestBody(unitname));
                    addImage2Map("facepic", faceselectedPhotos);


                    btnCheck.setClickable(false);
                    MyCountDownTimer timer = new MyCountDownTimer();
                    timer.start();
                    HttpMethods.getInstance().doCheckPersonInfo(new ProgressSubscriber(getCheckPersonInfoOnNext, PersonelInfoEntyActivity.this), map);
                } else {

                }
                break;

            case R.id.btn_facephoto:
                photoPickerState = 0;
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .start(this);
                break;
            case R.id.btn_bodyphoto:
                photoPickerState = 1;
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .start(this);
                break;
        }
    }

    public void onOptionPicker() {
        OptionPicker picker = new OptionPicker(this,C.departmentList);
        picker.setOffset(1); //1显示三条、2显示5条、3显示7条、4显示9条
        picker.setSelectedIndex(0);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
//                showToast(option);
                unitname = position;
                btnDepartment.setText(option);
            }
        });
        picker.show();
    }

    class MyCountDownTimer extends CountDownTimer {
        // 必须显示的调用父类的构造方法
        public MyCountDownTimer() {
            // millisInFuture 倒计时的时间 毫秒
            // countDownInterval间隔多少毫秒执行一次事件
            super(10000, 1000);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 每countDownInterval触发一次onTick事件
            btnCheck.setText("还剩" + millisUntilFinished / 1000 + "秒可以再次判断");
        }

        @Override
        public void onFinish() {
            btnCheck.setClickable(true);
            btnCheck.setText("判断");
        }

    }

    private boolean addImage2Map(String mString, ArrayList<String> selectedPhotos) {
        if (selectedPhotos.size() > 0) {
            File mfile = new File(selectedPhotos.get(0));
            if (mfile != null) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), mfile);
                map.put(mString + "\"; filename=\"" + selectedPhotos.get(0) + "", fileBody);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }


    private RequestBody setRequestBody(String mString) {
        return RequestBody.create(MediaType.parse("text/plain"), mString);
    }

    private RequestBody setRequestBody(File mImageFile) {
        return RequestBody.create(MediaType.parse("image/png"), mImageFile);
    }

    private RequestBody setRequestBody(EditText mEditText) {

        return RequestBody.create(MediaType.parse("text/plain"), Edit2String(mEditText));
    }

    private RequestBody setRequestBody(Button mButton) {
        if (mButton.getId() == btnSex.getId()) {
            return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(getBtnKey(mButton, C.SEX)));
        } else if (mButton.getId() == btnPersonEntrySense.getId()) {
            return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(getBtnKey(mButton, C.ENTRYSENSE)));
        } else if (mButton.getId() == btnPersonType.getId()) {
            return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(getBtnKey(mButton, C.PERSONTYPE)));
        }
        return RequestBody.create(MediaType.parse("text/plain"), Button2String(mButton));
    }

    private int getBtnKey(Button mButton, HashMap<Integer, String> mHashMap) {

        Iterator it = mHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Integer key = (Integer) entry.getKey();
            String val = (String) entry.getValue();
            if (mButton.getText().toString().equals(val)) {

                return key;
            }
        }
        return C.ERROR;

    }

    private RequestBody setRequestBody(int id) {

        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
    }

    private void showWheel(int type, int layout_wheel_view, int id_whell, int title, String[] data) {

        View outerView = LayoutInflater.from(PersonelInfoEntyActivity.this).inflate(layout_wheel_view, null);
        switch (type) {
            case WHEELNORMAL:
                WheelPicker wheel = (WheelPicker) outerView.findViewById(id_whell);
                wheel.setOnItemSelectedListener(PersonelInfoEntyActivity.this);
                List<String> mData = new ArrayList<>();
                for (String E : data)
                    mData.add(E);
                wheel.setData(mData);
                break;
            case WHEELDATE:
                WheelDatePicker wheelDatePicker = (WheelDatePicker) outerView.findViewById(id_whell);
                wheelDatePicker.setOnDateSelectedListener(PersonelInfoEntyActivity.this);
                break;
        }

        new AlertDialog.Builder(PersonelInfoEntyActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setTitle(title)
                .setView(outerView)
                .setPositiveButton(R.string.confirm, null)
                .show();
    }


    private String Edit2String(EditText mEditText) {
        // 有待改进需要换成Rxjava的异步
        return mEditText.getText().toString().trim();
    }

    private String Button2String(Button mbutton) {
        // 有待改进需要换成Rxjava的异步
        return mbutton.getText().toString().trim();
    }

    private boolean checkBtn(HashMap<String, Button> mButtonMap) {
        // 有待改进需要换成Rxjava的异步
        Iterator it = mButtonMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            Button val = (Button) entry.getValue();
            if (val.getText().toString().equals(getResources().getString(R.string.pleaseselector))) {
                ToastUtil.showLong(key + "不能为空");
                return false;
            }
        }
        return true;
    }

    private boolean checkEdit(HashMap<String, EditText> mEditTextMap) {
        // 有待改进需要换成Rxjava的异步
        Iterator it = mEditTextMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            EditText val = (EditText) entry.getValue();
            if (val.getText().toString().length() == 0) {
                ToastUtil.showLong(key + "不能为空");
                return false;
            }
            if (key.equals(getResources().getString(R.string.IdCard))) {
                String regEx = "(\\d{14}[0-9x|X])|(\\d{17}[0-9x|X])";
                Pattern pat = Pattern.compile(regEx);
                Matcher mat = pat.matcher(val.getText().toString());
                if (!mat.matches()) {
                    ToastUtil.showLong(key + "请输入正确的身份证号码！");
                    return false;
                }
            }
        }

        if(unitname == -1){
            ToastUtil.showLong("请选择部门！");
            return false;
        }

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);

            }
            switch (photoPickerState) {
                case 0:
                    setPhotos(photos, faceselectedPhotos);
                    facephotoAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    setPhotos(photos, bodyselectedPhotos);
                    bodyphotoAdapter.notifyDataSetChanged();
                    break;
            }


        }
    }

    private void setPhotos(List<String> photos, ArrayList<String> selectedPhotos) {
        selectedPhotos.clear();

        if (photos != null) {

            selectedPhotos.addAll(photos);
        }
    }


}
