package cn.onecloudtech.sl.dcpolice.ui.activity.RentalHousing;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.alibaba.fastjson.JSON;
import com.kyleduo.switchbutton.SwitchButton;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
import cn.onecloudtech.sl.dcpolice.base.BaseActivity;
import cn.onecloudtech.sl.dcpolice.iFly.IFlyManager;
import cn.onecloudtech.sl.dcpolice.model.UploadResult;
import cn.onecloudtech.sl.dcpolice.progress.ProgressCancelListener;
import cn.onecloudtech.sl.dcpolice.progress.ProgressDialogHandler;
import cn.onecloudtech.sl.dcpolice.ui.activity.PersonelInfoEntyActivity;
import cn.onecloudtech.sl.dcpolice.utils.ButtonUtil;
import cn.onecloudtech.sl.dcpolice.utils.HashMapUtil;
import cn.onecloudtech.sl.dcpolice.utils.ToastUtil;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by Administrator on 2016/9/29.
 */
public class RentalHousingActivity extends BaseActivity<RentalHousingPresenter, RentalHousingModel> implements RentalHousingContract.View, WheelPicker.OnItemSelectedListener, WheelDatePicker.OnDateSelectedListener, ProgressCancelListener, View.OnFocusChangeListener {
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.sb_isrental)
    SwitchButton sbIsrental;
    @Bind(R.id.nonrentalinfo)
    LinearLayout nonrentalinfo;
    @Bind(R.id.firefighting)
    LinearLayout firefighting;
    @Bind(R.id.preventioninfo)
    LinearLayout preventioninfo;
    @Bind(R.id.et_rentaladdress)
    EditText etRentaladdress;
    @Bind(R.id.et_policename)
    EditText etPolicename;
    @Bind(R.id.et_registername)
    EditText etRegistername;
    @Bind(R.id.et_realregistername)
    EditText etRealregistername;
    @Bind(R.id.btn_opentime)
    Button btnOpentime;
    @Bind(R.id.et_businessscope)
    EditText etBusinessscope;
    @Bind(R.id.et_wifipwd)
    EditText etWifipwd;
    @Bind(R.id.et_numberofrelperson)
    EditText etNumberofrelperson;
    @Bind(R.id.et_businesslicensenumber)
    EditText etBusinesslicensenumber;
    @Bind(R.id.et_hygienelicensenumber)
    EditText etHygienelicensenumber;
    @Bind(R.id.et_taxregistrationcertificatenumber)
    EditText etTaxregistrationcertificatenumber;
    @Bind(R.id.btn_placetype)
    Button btnPlacetype;
    @Bind(R.id.btn_serviceplacetype)
    Button btnServiceplacetype;
    @Bind(R.id.et_placearea)
    EditText etPlacearea;
    @Bind(R.id.et_numberoffloor)
    EditText etNumberoffloor;
    @Bind(R.id.et_numberofchannelport)
    EditText etNumberofchannelport;
    @Bind(R.id.et_numberofroom)
    EditText etNumberofroom;
    @Bind(R.id.et_numberofholdperson)
    EditText etNumberofholdperson;
    @Bind(R.id.et_certificateofqualification)
    EditText etCertificateofqualification;
    @Bind(R.id.btn_firefacilities)
    Button btnFirefacilities;
    @Bind(R.id.et_chargepersonname)
    EditText etChargepersonname;
    @Bind(R.id.et_chargepersonphone)
    EditText etChargepersonphone;
    @Bind(R.id.et_numberofstaffperson)
    EditText etNumberofstaffperson;
    @Bind(R.id.et_numberofexternalmonitor)
    EditText etNumberofexternalmonitor;
    @Bind(R.id.et_numberofinsidemonitor)
    EditText etNumberofinsidemonitor;
    @Bind(R.id.btn_protectcondition)
    Button btnProtectcondition;
    @Bind(R.id.ll_serviceplacetype)
    LinearLayout llServiceplacetype;
    @Bind(R.id.btn_upload_rental)
    Button btnUploadRental;
    @Bind(R.id.btn_belongplace)
    Button btnBelongplace;
    @Bind(R.id.btn_ifly)
    Button btnIfly;


    private Map<String, RequestBody> map = new HashMap<>();
    private Integer islethouse = 0;
    private ProgressDialogHandler mProgressDialogHandler;
    private Integer belongplace;// 所属社区派出所 详见“德城分局社区警务室情况（10.14）.xls”

    private Integer policeroom; //所属警务室 详见“德城分局社区警务室情况（10.14）.xls”

    @Override
    public int getLayoutId() {
        return R.layout.activity_rentalhousing;
    }

    @Override
    public void initView() {
        tvHead.setText("地点场所登记");
//        sbIsrental.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            islethouse = isChecked ? 1 : 0;
//            if (isChecked) {
//                preventioninfo.setVisibility(View.GONE);
//                firefighting.setVisibility(View.GONE);
//                nonrentalinfo.setVisibility(View.GONE);
//            } else {
//                preventioninfo.setVisibility(View.VISIBLE);
//                firefighting.setVisibility(View.VISIBLE);
//                nonrentalinfo.setVisibility(View.VISIBLE);
//            }
//        });

//        DataBindingUtil.setContentView(this, R.layout.activity_rentalhousing);

    }

    @Override
    public void doInitView() {
        initData();
    }

    private void initData() {
        HashMapUtil.initHashMap(C.placetypeList, C.PLACETYPE);
        HashMapUtil.initHashMap(C.entertainmentplacetypeList, C.ENTERTARINMENTPLACETYPE);
        HashMapUtil.initHashMap(C.serviceplacetypeList, C.SERVICEPLACETYPE);
        HashMapUtil.initHashMap(C.specialplacetypeList, C.SPECIALPLACETYPE);
        HashMapUtil.initHashMap(C.ninesmallplacetypeList, C.NINESMALLPLACETYPE);

        C.serList.add(C.ENTERTARINMENTPLACETYPE);
        C.serList.add(C.SERVICEPLACETYPE);
        C.serList.add(C.SPECIALPLACETYPE);
        C.serList.add(C.NINESMALLPLACETYPE);

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

    private void InitOnFocusChangeListener() {
        List<View> allViews = getAllChildViews();
        for (View v : allViews) {
            if (v instanceof EditText) {
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


    @OnClick({R.id.btn_placetype, R.id.btn_serviceplacetype, R.id.btn_firefacilities, R.id.btn_protectcondition, R.id.btn_opentime, R.id.btn_upload_rental, R.id.btn_belongplace,R.id.btn_ifly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_placetype:
                showWheel(C.WHEELNORMAL, R.layout.rental_wheels, R.id.wheel_rentsl_wheels, R.string.placetype, C.placetypeList);
                break;
            case R.id.btn_serviceplacetype:
                for (int i = 0; i < C.placetypeList.length - 1; i++) {
                    if (btnPlacetype.getText().toString().equals(C.PLACETYPE.get(i)))
                        showDialog(i);
                }
                break;
            case R.id.btn_firefacilities:
                selected_fire = new boolean[C.firefacilitiesList.length];
                showDialog(C.FIR_CHOICE_DIALOG);
                break;
            case R.id.btn_protectcondition:
                selected_protect = new boolean[C.protectconditionList.length];
                showDialog(C.PRO_CHOICE_DIALOG);
                break;
            case R.id.btn_opentime:
                showWheel(C.WHEELDATE, R.layout.date_wheel_view, R.id.wheel_date_picker, R.string.dateSelector, null);
                break;
            case R.id.btn_upload_rental:
//                doUploadRentalHousingInfo();
                uploadRental();
                break;
            case R.id.btn_belongplace:
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
            ArrayList<Province> data = new ArrayList<Province>();
            String json = ConvertUtils.toString(getAssets().open("city3.json"));
            data.addAll(JSON.parseArray(json, Province.class));
            AddressPicker picker = new AddressPicker(this, data);
            picker.setHideProvince(true);
//            picker.setSelectedItem("贵州", "贵阳", "花溪");
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(Province province, City city, County county) {
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

    private void uploadRental() {
        if (!map.isEmpty())
            map.clear();


        map.put("address", setRequestBody(etRentaladdress));
        map.put("islethouse", setRequestBody(islethouse));
        map.put("belongplace", setRequestBody(belongplace));
        map.put("policeroom", setRequestBody(policeroom));
        map.put("policename", setRequestBody(etPolicename));
        map.put("registername", setRequestBody(etRegistername));
        map.put("realregistername", setRequestBody(etRealregistername));
        map.put("opentime", setRequestBody(btnOpentime));
        map.put("wifipwd", setRequestBody(etWifipwd));
        map.put("numberofrelperson", setRequestBody(etNumberofrelperson));
        map.put("businesslicensenumber", setRequestBody(etBusinesslicensenumber));
        map.put("hygienelicensenumber", setRequestBody(etHygienelicensenumber));
        map.put("taxregistrationcertificatenumber", setRequestBody(etTaxregistrationcertificatenumber));
        map.put("placetype", setRequestBody(btnPlacetype));

        if (btnPlacetype.getText().toString().equals(C.placetypeList[1])) {
            map.put("entertainmentplacetype", setRequestBody(btnServiceplacetype));
        } else if (btnPlacetype.getText().toString().equals(C.placetypeList[2])) {
            map.put("serviceplacetype", setRequestBody(btnServiceplacetype));
        } else if (btnPlacetype.getText().toString().equals(C.placetypeList[3])) {
            map.put("specialplacetype", setRequestBody(btnServiceplacetype));
        } else if (btnPlacetype.getText().toString().equals(C.placetypeList[4])) {
            map.put("ninesmallplacetype", setRequestBody(btnServiceplacetype));
        }
//        map.put("entertainmentplacetype", setRequestBody(btnServiceplacetype));


        map.put("placearea", setRequestBody(etPlacearea));
        map.put("numberoffloor", setRequestBody(etNumberoffloor));
        map.put("numberofchannelport", setRequestBody(etNumberofchannelport));
        map.put("numberofroom", setRequestBody(etNumberofroom));
        map.put("numberofholdperson", setRequestBody(etNumberofholdperson));
        map.put("certificateofqualification", setRequestBody(etCertificateofqualification));
        map.put("firefacilities", setRequestBody(btnFirefacilities));
        map.put("chargepersonname", setRequestBody(etChargepersonname));
        map.put("chargepersonphone", setRequestBody(etChargepersonphone));
        map.put("numberofstaffperson", setRequestBody(etNumberofstaffperson));
        map.put("numberofexternalmonitor", setRequestBody(etNumberofexternalmonitor));
        map.put("numberofinsidemonitor", setRequestBody(etNumberofinsidemonitor));
        map.put("protectcondition", setRequestBody(btnProtectcondition));
        map.put("businessscope", setRequestBody(etBusinessscope));

        showProgressDialog();
        mPresenter.uploadRentalHosuingInfo(map);

    }


    private Method getMethodFromName(Class cls, String name) throws NoSuchMethodException {
        Method method = null;
        Field[] fs = cls.getDeclaredFields();
        String paramName = name;
        String methodName = "get";
        String letter = paramName.substring(0, 1).toUpperCase();
        methodName = methodName + letter + paramName.substring(1);
        method = cls.getDeclaredMethod(methodName);

        System.out.println("methodName:" + methodName);
        return method;
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            f.setAccessible(true); // 设置些属性是可以访问的
//            String paramName = f.getName();
//
//            String methodName = "get";
//
//            String letter = paramName.substring(0, 1).toUpperCase();
//
//            methodName = methodName + letter + paramName.substring(1);
//
//            System.out.println("paramName:" + f.getName());
//            System.out.println("methodName:" + methodName);
//
//                method = cls.getMethod(methodName, null);
//
//        }
//
//        return method;
    }

    public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {

        Class ownerClass = owner.getClass();

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);

        return method.invoke(owner, args);
    }

//    private void doUploadRentalHousingInfo() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        if (map.isEmpty() == false) {
//            map.clear();
//        }
//
//        Locate mLocate = new Locate();
//
//        Field[] fields = mLocate.getClass().getDeclaredFields();
//
//        for (Field f : fields) {
//            map.put(f.getName(), getInvokeResult(mLocate, f.getName()));
//        }
//
//
//    }

    private RequestBody setRequestBody(Object obj) {
        if (obj instanceof View) {
            View v = (View) obj;
            if (v instanceof EditText)
                return RequestBody.create(MediaType.parse("text/plain"), ((EditText) v).getText().toString());
            if (v instanceof Button) {
                if (v.getId() == btnPlacetype.getId()) {
                    return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(ButtonUtil.getBtnKey((Button) v, C.PLACETYPE)));
                } else if (v.getId() == btnServiceplacetype.getId()) {
                    for (int i = 0; i < 5; i++) {
                        if (ButtonUtil.getBtnKey(btnPlacetype, C.PLACETYPE) == (C.ENT_CHOICE_DIALOG + i)) {
                            return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(ButtonUtil.getBtnKey((Button) v, C.serList.get(i))));
                        }
                    }
                } else {
                    return RequestBody.create(MediaType.parse("text/plain"), ((Button) v).getText().toString());
                }

            }
        }
        if (obj instanceof Integer)
            return RequestBody.create(MediaType.parse("text/plain"), String.valueOf((Integer) obj));
        return null;
    }

//    private RequestBody getInvokeResult(Locate mLocate, String name) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        Method method = getMethodFromName(mLocate.getClass(), name);
//        Object obj = method.invoke((Object) mLocate, null);
//        return setRequestBody(obj);
//    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String text = "";
        switch (picker.getId()) {
            case R.id.wheel_rentsl_wheels:
                if (position > 0 && position < C.FIR_CHOICE_DIALOG)
                    llServiceplacetype.setVisibility(View.VISIBLE);
                else
                    llServiceplacetype.setVisibility(View.GONE);
                btnPlacetype.setText(C.placetypeList[position]);
                break;


        }


    }

    private void showWheel(int type, int layout_wheel_view, int id_whell, int title, String[] data) {

        View outerView = LayoutInflater.from(RentalHousingActivity.this).inflate(layout_wheel_view, null);
        switch (type) {
            case C.WHEELNORMAL:
                WheelPicker wheel = (WheelPicker) outerView.findViewById(id_whell);
                wheel.setOnItemSelectedListener(RentalHousingActivity.this);
                List<String> mData = new ArrayList<>();
                for (String E : data)
                    mData.add(E);
                wheel.setData(mData);
                break;
            case C.WHEELDATE:
                WheelDatePicker wheelDatePicker = (WheelDatePicker) outerView.findViewById(id_whell);
                wheelDatePicker.setOnDateSelectedListener(RentalHousingActivity.this);
                break;
        }

        new AlertDialog.Builder(RentalHousingActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setTitle(title)
                .setView(outerView)
                .setPositiveButton(R.string.confirm, null)
                .show();
    }

    @Override
    public void onDateSelected(WheelDatePicker picker, Date date) {
        switch (picker.getId()) {
            case R.id.wheel_date_picker:
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
                btnOpentime.setText(formatter.format(date));
                break;
        }
    }

    private boolean[] selected;
    private boolean[] selected_fire;
    private boolean[] selected_protect;

    private String[] list;
    private DialogInterface.OnClickListener btnListener;


    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case C.ENT_CHOICE_DIALOG:
                selected = new boolean[C.entertainmentplacetypeList.length];
                dialog = setSingleDialogInfo(C.placetypeList[C.ENT_CHOICE_DIALOG], selected, C.entertainmentplacetypeList);
                break;
            case C.SER_CHOICE_DIALOG:
                selected = new boolean[C.serviceplacetypeList.length];
                dialog = setSingleDialogInfo(C.placetypeList[C.SER_CHOICE_DIALOG], selected, C.serviceplacetypeList);
                break;
            case C.SPC_CHOICE_DIALOG:
                selected = new boolean[C.specialplacetypeList.length];
                dialog = setSingleDialogInfo(C.placetypeList[C.SPC_CHOICE_DIALOG], selected, C.specialplacetypeList);
                break;
            case C.NIN_CHOICE_DIALOG:
                selected = new boolean[C.ninesmallplacetypeList.length];
                dialog = setSingleDialogInfo(C.placetypeList[C.NIN_CHOICE_DIALOG], selected, C.ninesmallplacetypeList);
                break;
            case C.FIR_CHOICE_DIALOG:
                selected = new boolean[C.firefacilitiesList.length];
                dialog = setMulitDialogInfo("消防设备", selected, C.firefacilitiesList, btnFirefacilities);
                break;
            case C.PRO_CHOICE_DIALOG:
                selected = new boolean[C.protectconditionList.length];
                dialog = setMulitDialogInfo("物防设备", selected, C.protectconditionList, btnProtectcondition);
                break;
            default:
                break;
        }
        return dialog;
    }

    private Dialog setSingleDialogInfo(String title, boolean[] selected, String[] list) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        DialogInterface.OnClickListener Listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnServiceplacetype.setText(list[which]);
            }
        };
        builder.setSingleChoiceItems(list, -1, Listener);
        btnListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String selectedStr = "";
            }
        };
        builder.setPositiveButton("确定", btnListener);
        dialog = builder.create();
        return dialog;
    }


    private Dialog setMulitDialogInfo(String title, boolean[] selected, String[] list, Button mButton) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
//        builder.setIcon(R.drawable.basketball);


        DialogInterface.OnMultiChoiceClickListener mutiListener =
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,
                                        int which, boolean isChecked) {
                        selected[which] = isChecked;
                    }
                };
        builder.setMultiChoiceItems(list, selected, mutiListener);
        DialogInterface.OnClickListener btnListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String selectedStr = "";
                        for (int i = 0; i < selected.length; i++) {
                            if (selected[i]) {
                                selectedStr = selectedStr + "," + list[i];
                            }
                            mButton.setText(selectedStr);
                        }
                        if (selectedStr.length() > 0) {
                            String m = selectedStr.substring(0, 1);
                            if (m.equals(","))
                                selectedStr = selectedStr.substring(1, selectedStr.length());
                            mButton.setText(selectedStr);
                        } else {
                            mButton.setText("请选择");
                        }

                    }
                };
        builder.setPositiveButton("确定", btnListener);
        dialog = builder.create();
        return dialog;
    }


    @Override
    public void onCancelProgress() {
        dissmissProgressDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private EditText selectedEditText;

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

