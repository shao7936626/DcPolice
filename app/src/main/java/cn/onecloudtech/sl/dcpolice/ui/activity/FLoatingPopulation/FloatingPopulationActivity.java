package cn.onecloudtech.sl.dcpolice.ui.activity.FLoatingPopulation;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.onecloudtech.sl.dcpolice.C;
import cn.onecloudtech.sl.dcpolice.R;
import cn.onecloudtech.sl.dcpolice.adapter.FloatingPopulationPropertyAdapter;
import cn.onecloudtech.sl.dcpolice.base.BaseActivity;
import cn.onecloudtech.sl.dcpolice.iFly.IFlyManager;
import cn.onecloudtech.sl.dcpolice.model.UploadResult;
import cn.onecloudtech.sl.dcpolice.progress.ProgressCancelListener;
import cn.onecloudtech.sl.dcpolice.progress.ProgressDialogHandler;
import cn.onecloudtech.sl.dcpolice.utils.ButtonUtil;
import cn.onecloudtech.sl.dcpolice.utils.HashMapUtil;
import cn.onecloudtech.sl.dcpolice.utils.ToastUtil;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016/9/29.
 */
public class FloatingPopulationActivity extends BaseActivity<FloatingPopulationPresenter, FloatingPopulationModel> implements FloatingPopulationContract.View, WheelPicker.OnItemSelectedListener, WheelDatePicker.OnDateSelectedListener, FloatingPopulationPropertyAdapter.SaveEditListener, ProgressCancelListener, View.OnFocusChangeListener {
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.btn_rtype)
    Button btnRtype;
    @Bind(R.id.et_rname)
    EditText etRname;
    @Bind(R.id.btn_rperson_sex)
    Button btnRpersonSex;
    @Bind(R.id.et_nation)
    EditText etNation;
    @Bind(R.id.et_rperson_IdCard)
    EditText etRpersonIdCard;
    @Bind(R.id.btn_rperson_bornDate)
    Button btnRpersonBornDate;
    @Bind(R.id.et_rperson_qq)
    EditText etRpersonQq;
    @Bind(R.id.et_rperson_wechat)
    EditText etRpersonWechat;
    @Bind(R.id.et_rperson_registeredResidence)
    EditText etRpersonRegisteredResidence;
    @Bind(R.id.et_rperson_currentResidence)
    EditText etRpersonCurrentResidence;
    @Bind(R.id.et_rperson_workdunit)
    EditText etRpersonWorkdunit;
    @Bind(R.id.et_rperson_workduaddress)
    EditText etRpersonWorkduaddress;
    @Bind(R.id.et_rperson_remark)
    EditText etRpersonRemark;
    @Bind(R.id.btn_upload_Foaltingpopulation)
    Button btnUploadFoaltingpopulation;
    @Bind(R.id.et_rphone)
    EditText etRphone;
    @Bind(R.id.cb_rperson_ispermit)
    CheckBox cbRpersonIspermit;
    @Bind(R.id.tv_proterty_title)
    TextView tvProtertyTitle;
    @Bind(R.id.floating_property_recycler_view)
    RecyclerView floatingPropertyRecyclerView;
    @Bind(R.id.ll_floating_property)
    LinearLayout llFloatingProperty;
    @Bind(R.id.et_rplatenumber)
    EditText etRplatenumber;
    @Bind(R.id.et_rpostion)
    EditText etRpostion;
    @Bind(R.id.btn_add_address)
    Button btnAddAddress;
    @Bind(R.id.et_rrelationship)
    EditText etRrelationship;
    @Bind(R.id.ll_relationship)
    LinearLayout llRelationship;
    @Bind(R.id.btn_belongplace)
    Button btnBelongplace;
    @Bind(R.id.btn_ifly)
    Button btnIfly;


    private Map<String, RequestBody> map = new HashMap<>();
    private Integer ispermit = 0;
    private EditText selectedEditText;
    //    private SubscriberOnNextListener getUploadFloatingInfoOnNext;
    private ProgressDialogHandler mProgressDialogHandler;

    private ArrayList<String> addressList = new ArrayList<>();
    private FloatingPopulationPropertyAdapter floatingPopulationPropertyAdapter;

    private Integer belongplace;// 所属社区派出所 详见“德城分局社区警务室情况（10.14）.xls”

    private Integer policeroom; //所属警务室 详见“德城分局社区警务室情况（10.14）.xls”

    @Override
    public int getLayoutId() {
        return R.layout.activity_relativeperson;
    }

    @Override
    public void initView() {
        tvHead.setText(R.string.floatingpopulation);
        cbRpersonIspermit.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            ispermit = isChecked ? 1 : 0;
        }));


//        getUploadFloatingInfoOnNext =()-> {
//            if (uploadResult.getUploadResult() == C.UPLOAD_INFO_SUCCEED) {
//                ToastUtil.showLong("上传成功");
//                finish();
//            }
//
//        };

//        getUploadFloatingInfoOnNext = new SubscriberOnNextListener<UploadResult>() {
//
//            @Override
//            public void onNext(UploadResult uploadResult) {
//                if (uploadResult.getUploadResult() == C.UPLOAD_INFO_SUCCEED) {
//                    ToastUtil.showLong("上传成功");
//                    finish();
////                    finish();
//                }
//
////                System.out.println(result);
//            }
//        };
    }

    @Override
    public void doInitView() {

        HashMapUtil.initHashMap(C.sexList, C.SEX);
        HashMapUtil.initHashMap(C.rtypeList, C.RTYPELIST);

    }

    @Override
    public void didInitView() {
        InitOnFocusChangeListener();
    }

    @Override
    public void uploadSuccess(UploadResult uploadResult) {
        if (uploadResult.getUploadResult() == C.UPLOAD_INFO_SUCCEED) {
            ToastUtil.showLong("上传成功");
            this.finish();
        } else {
            dissmissProgressDialog();
            ToastUtil.showLong("上传失败");
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
        showMsg("上传失败！");
    }

    @Override
    public void showMsg(String msg) {

    }


    @OnClick({R.id.btn_rtype, R.id.btn_rperson_sex, R.id.btn_rperson_bornDate, R.id.btn_upload_Foaltingpopulation, R.id.btn_add_address, R.id.btn_belongplace,R.id.btn_ifly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rtype:
                showWheel(C.WHEELNORMAL, R.layout.rental_wheels, R.id.wheel_rentsl_wheels, R.string.rtype, C.rtypeList);
                break;
            case R.id.btn_rperson_sex:
                showWheel(C.WHEELNORMAL, R.layout.sex_wheel_view, R.id.sex_wheel, R.string.sexSelector, C.sexList);
                break;
            case R.id.btn_rperson_bornDate:
                showWheel(C.WHEELDATE, R.layout.date_wheel_view, R.id.wheel_date_picker, R.string.dateSelector, null);
                break;
            case R.id.btn_upload_Foaltingpopulation:
                uploadFloating();
                break;
            case R.id.btn_add_address:

                if (addressList.size() < 3) {
                    addressList.add("");
                    floatingPopulationPropertyAdapter.notifyDataSetChanged();
                }

                break;
            case R.id.btn_belongplace:
//                showWheel(C.WHEELDATE, R.layout.date_wheel_view, R.id.wheel_date_picker, R.string.dateSelector, null);

                onLinkagePicker();

                break;

            case R.id.btn_ifly:
                IFlyManager iFlyManager = IFlyManager.getInstance(this);
                iFlyManager.btnVoice(selectedEditText);
                break;
            default:
                break;
        }

    }

    public void onLinkagePicker() {
        try {
            ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
            String json = ConvertUtils.toString(getAssets().open("city3.json"));
            data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
            AddressPicker picker = new AddressPicker(this, data);
            picker.setHideProvince(true);
//            picker.setSelectedItem("贵州", "贵阳", "花溪");
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(AddressPicker.Province province, AddressPicker.City city, AddressPicker.County county) {
//                    if (county == null) {
//                        ToastUtil.showShort("province : " + province + ", city: " + city);
//                    } else {
//                        ToastUtil.showShort("province : " + province + ", city: " + city + ", county: " + county);
//                    }
                    btnBelongplace.setText(city.getAreaName() + " " + county.getAreaName());
                    belongplace = Integer.parseInt(city.getAreaId());
                    policeroom = Integer.parseInt(county.getAreaId());
                }
            });
            picker.show();
        } catch (Exception e) {
//            showToast(e.toString());
        }
    }

    private void uploadFloating() {

        if (!map.isEmpty())
            map.clear();


        map.put("rtype", setRequestBody(btnRtype));
        map.put("name", setRequestBody(etRname));
        map.put("sex", setRequestBody(btnRpersonSex));
        map.put("nation", setRequestBody(etNation));
        map.put("idcard", setRequestBody(etRpersonIdCard));
        map.put("birthday", setRequestBody(btnRpersonBornDate));
        map.put("qq", setRequestBody(etRpersonQq));
        map.put("wechat", setRequestBody(etRpersonWechat));
        map.put("registeraddress", setRequestBody(etRpersonRegisteredResidence));
        map.put("currentaddress", setRequestBody(etRpersonCurrentResidence));
        map.put("workdunit", setRequestBody(etRpersonWorkdunit));
        map.put("workdunitaddress", setRequestBody(etRpersonWorkduaddress));
        map.put("telphone", setRequestBody(etRphone));
        map.put("ispermit", setRequestBody(ispermit));

        map.put("belongplace", setRequestBody(belongplace));
        map.put("policeroom", setRequestBody(policeroom));

        map.put("remark", setRequestBody(etRpersonRemark));
        map.put("position", setRequestBody(etRpostion));
        map.put("platenumber", setRequestBody(etRplatenumber));
        if (addressList.size() > 0)
            map.put("propertyandequipment", setRequestBody(addressList));
        map.put("relationshipwithlandlord", setRequestBody(etRrelationship));
        showProgressDialog();
        mPresenter.uploadFloatingPopulation(map);


//        HttpMethods.getInstance().doUploadFloatingInfo(new ProgressSubscriber(getUploadFloatingInfoOnNext, FloatingPopulationActivity.this), map);
    }

    private RequestBody setRequestBody(Object obj) {
        if (obj instanceof View) {
            View v = (View) obj;
            if (v.getId() == btnRtype.getId())
                return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(ButtonUtil.getBtnKey((Button) v, C.RTYPELIST)));
            else if (v.getId() == btnRpersonSex.getId()) {
                return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(ButtonUtil.getBtnKey((Button) v, C.SEX)));
            } else {
                if (v instanceof EditText)
                    return RequestBody.create(MediaType.parse("text/plain"), ((EditText) v).getText().toString());
                if (v instanceof Button)
                    return RequestBody.create(MediaType.parse("text/plain"), ((Button) v).getText().toString());
//                else
//                    return RequestBody.create(MediaType.parse("text/plain"), ((EditText) v).getText().toString());
            }
        } else {
            if (obj instanceof Integer)
                return RequestBody.create(MediaType.parse("text/plain"), String.valueOf((Integer) obj));
            if (obj instanceof ArrayList) {
                ArrayList<String> arrayList = (ArrayList<String>) obj;
                String body = "";
                for (String s : arrayList)
                    body = body + s + ",";
                return RequestBody.create(MediaType.parse("text/plain"), body);
            }
        }

        return null;
    }

    private void showWheel(int type, int layout_wheel_view, int id_whell, int title, String[] data) {

        View outerView = LayoutInflater.from(FloatingPopulationActivity.this).inflate(layout_wheel_view, null);
        switch (type) {
            case C.WHEELNORMAL:
                WheelPicker wheel = (WheelPicker) outerView.findViewById(id_whell);
                wheel.setOnItemSelectedListener(FloatingPopulationActivity.this);
                List<String> mData = new ArrayList<>();
                for (String E : data)
                    mData.add(E);
                wheel.setData(mData);
                break;
            case C.WHEELDATE:
                WheelDatePicker wheelDatePicker = (WheelDatePicker) outerView.findViewById(id_whell);
                wheelDatePicker.setOnDateSelectedListener(FloatingPopulationActivity.this);
                break;
        }

        new AlertDialog.Builder(FloatingPopulationActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setTitle(title)
                .setView(outerView)
                .setPositiveButton(R.string.confirm, null)
                .show();
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        switch (picker.getId()) {
            case R.id.wheel_rentsl_wheels:
                btnRtype.setText(C.rtypeList[position]);
                llRelationship.setVisibility(View.GONE);
                if (position == 1 || position == 3) {

                    llFloatingProperty.setVisibility(View.VISIBLE);
                    addressList.clear();
                    addressList.add("");
                    if (position == 1) {
                        tvProtertyTitle.setText("房产地址");
                    } else {
                        tvProtertyTitle.setText("产业地址");
                    }
                    floatingPopulationPropertyAdapter = new FloatingPopulationPropertyAdapter(this, addressList);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    floatingPropertyRecyclerView.setLayoutManager(mLayoutManager);
                    floatingPropertyRecyclerView.setAdapter(floatingPopulationPropertyAdapter);
                    floatingPropertyRecyclerView.setHasFixedSize(true);

                } else if (position == 2) {
                    addressList.clear();
                    llFloatingProperty.setVisibility(View.GONE);
                    llRelationship.setVisibility(View.VISIBLE);
                } else {
                    addressList.clear();
                    llFloatingProperty.setVisibility(View.GONE);
                }
                break;
            case R.id.sex_wheel:
                if (position == 0)
                    btnRpersonSex.setText(R.string.pleaseselector);
                else if (position == 1)
                    btnRpersonSex.setText("男");
                else
                    btnRpersonSex.setText("女");
                break;
        }
    }

    @Override
    public void onDateSelected(WheelDatePicker picker, Date date) {
        switch (picker.getId()) {
            case R.id.wheel_date_picker:
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
                btnRpersonBornDate.setText(formatter.format(date));
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    private void InitOnFocusChangeListener() {
        List<View > allViews = getAllChildViews();
        for(View v :allViews){
            if(v instanceof EditText){
                v.setOnFocusChangeListener(this);
            }
        }
    }

    public List<View> getAllChildViews() {

        View view = this.getWindow().getDecorView();

        return getAllChildViews(view);

    }

    private List<View> getAllChildViews(View view) {

        List<View> allchildren = new ArrayList<View>();

        if (view instanceof ViewGroup) {

            ViewGroup vp = (ViewGroup) view;

            for (int i = 0; i < vp.getChildCount(); i++) {

                View viewchild = vp.getChildAt(i);

                allchildren.add(viewchild);

                allchildren.addAll(getAllChildViews(viewchild));

            }

        }

        return allchildren;

    }

    @Override
    public void SaveEdit(int position, String string) {
        addressList.set(position, string);
    }

    @Override
    public void onCancelProgress() {
        dissmissProgressDialog();
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (view instanceof EditText) {
            if (hasFocus) {
                selectedEditText = (EditText) view;
                btnIfly.setVisibility(View.VISIBLE);
            } else {
                btnIfly.setVisibility(View.GONE);
            }
        }
    }
}
