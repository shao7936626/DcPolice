package cn.onecloudtech.sl.dcpolice.utils;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/30.
 */
public class HashMapUtil {


    public static void initHashMap(String[] mStringlist, HashMap<Integer, String> mHashMap) {
        for (int i = 0; i < mStringlist.length; i++) {
            mHashMap.put(i, mStringlist[i]);
        }
    }

}
