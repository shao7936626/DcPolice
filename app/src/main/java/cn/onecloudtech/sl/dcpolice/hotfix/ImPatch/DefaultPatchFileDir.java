package cn.onecloudtech.sl.dcpolice.hotfix.ImPatch;

import android.Manifest;
import android.os.Environment;



import java.io.File;

import cn.onecloudtech.sl.dcpolice.hotfix.IPatch.IPatchFileDir;
import cn.onecloudtech.sl.dcpolice.utils.MAppManager;
import cn.onecloudtech.sl.dcpolice.utils.MPermissionUtil;

/**
 * 默认patch 路径管理
 * Created by shoyu666@163.com on 16/7/5.
 */
public class DefaultPatchFileDir implements IPatchFileDir {
    public static final String PachDir = "shoyu666";
    @Override
    public File getPatchJarDir() {
//        return getHotFixPachDir();
        return getHotFixPachDirSDKCard();
    }

    @Override
    public File getCurrentPatchJar() {
         return new File(getPatchJarDir(),getPatchFileName());
    }

    @Override
    public String getPatchFileName() {
        return "sl.jar";
    }

    public static File getHotFixPachDir() {
        File file= new File(MAppManager.getApplication().getFilesDir(), PachDir);
        if(file!=null&&!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    /**
     * path放在sd卡上
     * @return
     */
    public static File getHotFixPachDirSDKCard() {
        File dir=null;
        if(MPermissionUtil.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)&&MPermissionUtil.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)){
            dir=new File(getSDCardPath());
            if(!dir.exists()){
                dir.mkdirs();
            }
            if(!dir.canRead()){
                dir=getHotFixPachDir();
            }
        }else{
            dir= getHotFixPachDir();
        }
          return dir;
    }
     public static String getSDCardPath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator;
    }
}
