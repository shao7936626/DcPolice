package cn.onecloudtech.sl.dcpolice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 * Info: 常量类
 */
public class C {
    public static final String TAG = ":------DCPOLICE------:";
    public static final Boolean DEBUG = true;
    public static final int ERROR = -1;


    public static final String SERVER_IP = "http://139.129.212.126";
    //    public static final String SERVER_IP = "http://192.168.1.27";
    public static final String SERVER_PORT = ":8080";


    public static final String[] persontypeList = new String[]{"请选择", "赌博人员", "涉恶人员", "食药环人员", "涉毒人员", "留置盘问", "侵财人员", "刑事传唤", "负案在逃人员", "维稳人员", "失踪人员", "侵财人员分析", "技术比中人员", "社会人员"};
    public static final HashMap<Integer, String> PERSONTYPE = new HashMap<Integer, String>();

    public static final String[] entrysenseList = new String[]{"请选择", "日常盘问"};
    public static final HashMap<Integer, String> ENTRYSENSE = new HashMap<Integer, String>();
    public static final HashMap<Integer, String> SEX = new HashMap<Integer, String>();
    public static final String[] sexList = new String[]{"请选择", "男", "女"};

    public static final String[] placetypeList = new String[]{"请选择", "娱乐场所", "服务场所", "特种行业", "九小场所", "物流快递"};
    public static final String[] entertainmentplacetypeList = new String[]{"歌厅", "卡拉OK厅", "迪吧", "夜总会", "电子游戏厅", "棋牌室", "其他"};
    public static final String[] serviceplacetypeList = new String[]{"桑拿洗浴", "美容美发", "足疗按摩", "咖啡馆", "小餐桌", "网吧", "娱乐城", "茶馆", "其他"};
    public static final String[] specialplacetypeList = new String[]{"旅馆", "开锁", "刻字印刷", "复印", "废旧金属回收", "二手机动车交易", "机动车拆解", "旧货业回收", "机动车电动车修理","二手手机经营","金店","典当行"};
    public static final String[] ninesmallplacetypeList = new String[]{"小学校", "小商场", "小医院", "小旅馆", "小歌舞娱乐", "小餐饮", "小网吧", "小美容洗浴", "小生产加工", "其他"};

    public static final ArrayList<HashMap<Integer, String>> serList = new ArrayList<HashMap<Integer, String>>();


    public static final String[] firefacilitiesList = new String[]{"灭火器", "应急灯", "消防拴", "其他"};
    public static final String[] protectconditionList = new String[]{"防盗门", "防盗窗", "报警器", "其他"};


    public static final HashMap<Integer, String> PLACETYPE = new HashMap<Integer, String>();
    public static final HashMap<Integer, String> ENTERTARINMENTPLACETYPE = new HashMap<Integer, String>();
    public static final HashMap<Integer, String> SERVICEPLACETYPE = new HashMap<Integer, String>();
    public static final HashMap<Integer, String> SPECIALPLACETYPE = new HashMap<Integer, String>();
    public static final HashMap<Integer, String> NINESMALLPLACETYPE = new HashMap<Integer, String>();


    public static final String[] rtypeList = new String[]{"请选择", "房东", "房客", "业主", "从业人员"}; //1： 2： 3： 4：

    public static final HashMap<Integer, String> RTYPELIST = new HashMap<Integer, String>();


    public static final int ENT_CHOICE_DIALOG = 1;
    public static final int SER_CHOICE_DIALOG = 2;
    public static final int SPC_CHOICE_DIALOG = 3;
    public static final int NIN_CHOICE_DIALOG = 4;
    public static final int FIR_CHOICE_DIALOG = 5;
    public static final int PRO_CHOICE_DIALOG = 6;

    // RxBusEventName
    public static final String EVENT_LOGIN = "login";
    public static final String EVENT_UPLOAD_RENTAL = "upload_rental";
    public static final String EVENT_UPLOAD_FLOATING = "upload_floating";
    //wheel
    public static final int WHEELNORMAL = 0;
    public static final int WHEELDATE = 1;

    public static final int UPLOAD_INFO_FAILED = 0;
    public static final int UPLOAD_INFO_SUCCEED = 1;


}
