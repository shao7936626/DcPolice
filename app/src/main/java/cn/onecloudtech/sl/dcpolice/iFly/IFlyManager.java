package cn.onecloudtech.sl.dcpolice.iFly;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import cn.onecloudtech.sl.dcpolice.hotfix.PatchManger;
import cn.onecloudtech.sl.dcpolice.utils.Singleton;

/**
 * Created by Administrator on 2016/10/19.
 */

public class IFlyManager {

    private static IFlyManager iFlyManager;

    private Context context;

    public IFlyManager(Context context) {
        this.context = context;
//        this.editText = editText;
    }

    public void btnVoice(EditText editText) {
        RecognizerDialog dialog = new RecognizerDialog(context,null);
        dialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        dialog.setParameter(SpeechConstant.ACCENT, "mandarin");

        dialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                printResult(recognizerResult,editText);
            }
            @Override
            public void onError(SpeechError speechError) {
            }
        });
        dialog.show();
        Toast.makeText(context, "请开始说话", Toast.LENGTH_SHORT).show();
    }

    private void printResult(RecognizerResult results,EditText editText) {
        String text = parseIatResult(results.getResultString());
        // 自动填写地址

        editText.append(text);

    }

    public static String parseIatResult(String json) {
        StringBuffer ret = new StringBuffer();
        try {
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);

            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length(); i++) {
                // 转写结果词，默认使用第一个结果
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                JSONObject obj = items.getJSONObject(0);
                ret.append(obj.getString("w"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ret.toString().equals("。"))
            return "";
        return ret.toString();
    }

    public static IFlyManager getInstance(Context context) {
        if (iFlyManager == null) {
            iFlyManager = new IFlyManager(context);
        }
        return iFlyManager;
    }


}
