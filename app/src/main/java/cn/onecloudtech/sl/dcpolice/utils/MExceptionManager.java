package cn.onecloudtech.sl.dcpolice.utils;

public class MExceptionManager {
	public static void throwApplictionInitEx(String msg){
		throw new MException(msg);
	}
}