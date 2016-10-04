package cn.onecloudtech.sl.dcpolice.utils;

import android.widget.Button;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.onecloudtech.sl.dcpolice.C;

/**
 * Created by Administrator on 2016/10/2.
 */
public class ButtonUtil {
    public static int getBtnKey(Button mButton, HashMap<Integer, String> mHashMap) {

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
}
