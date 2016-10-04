package cn.onecloudtech.sl.dcpolice.http;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.onecloudtech.sl.dcpolice.C;
import cn.onecloudtech.sl.dcpolice.api.ApiService;
import cn.onecloudtech.sl.dcpolice.model.CheckPerson;
import cn.onecloudtech.sl.dcpolice.model.HttpResult;
import cn.onecloudtech.sl.dcpolice.model.Jpushperson;
import cn.onecloudtech.sl.dcpolice.model.Person;
import cn.onecloudtech.sl.dcpolice.model.Subject;
import cn.onecloudtech.sl.dcpolice.model.UpdateData;
import cn.onecloudtech.sl.dcpolice.model.UploadResult;
import cn.onecloudtech.sl.dcpolice.model.User;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by liukun on 16/3/9.
 */
public class HttpMethods {

    //public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 30;

    private Retrofit retrofit;
    private ApiService apiService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(C.SERVER_IP + C.SERVER_PORT)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

//    /**
//     * 用于获取豆瓣电影Top250的数据
//     *
//     * @param subscriber 由调用者传过来的观察者对象
//     * @param start      起始位置
//     * @param count      获取长度
//     */
//    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count) {
//
////        movieService.getTopMovie(start, count)
////                .map(new HttpResultFunc<List<Subject>>())
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
//
//        Observable observable = movieService.getTopMovie(start, count)
//                .map(new HttpResultFunc<List<Subject>>());
//
//        toSubscribe(observable, subscriber);
//    }

    public void doLogin(Subscriber<User> subscriber, String username, String password) {

        Observable observable = apiService.login(username, password)
                .map(new Func1() {
                    @Override
                    public Object call(Object o) {
                        //User mUser = (User)o;
                        return (User) o;
                    }
                });

        toSubscribe(observable, subscriber);
    }


    public void dofetchVersion(Subscriber<UpdateData> subscriber) {
        Observable observable = apiService.fetchVersion()
                .map(new Func1() {
                    @Override
                    public Object call(Object o) {

                        return (UpdateData) o;
                    }
                });

        toSubscribe(observable, subscriber);
    }


    public void doUploadPersonInfo(Subscriber<Person> subscriber, Map<String, RequestBody> map) {
        Observable observable = apiService.uploadPersonInfo(map)
                .map(new Func1() {
                    @Override
                    public Object call(Object o) {
                        //User mUser = (User)o;
                        return (Person) o;
                    }
                });

        toSubscribe(observable, subscriber);
    }

    public void doCheckPersonInfo(Subscriber<CheckPerson> subscriber, Map<String, RequestBody> map) {
        Observable observable = apiService.checkPersonInfo(map)
                .map(new Func1() {
                    @Override
                    public Object call(Object o) {
                        //User mUser = (User)o;
                        return (CheckPerson) o;
                    }
                });

        toSubscribe(observable, subscriber);
    }

    public void doGetCheckedPersonelInfoCount(Subscriber<Integer> subscriber, int userId) {
        Observable observable = apiService.getCheckedPersonelInfoCount(userId)
                .map(new Func1() {
                    @Override
                    public Object call(Object o) {
                        //User mUser = (User)o;
                        return (Integer) o;
                    }
                });

        toSubscribe(observable, subscriber);
    }

    public void doGetCheckedPersonelInfo(Subscriber<ArrayList<Jpushperson>> subscriber, int begin, int end, int userId) {
        Observable observable = apiService.getCheckedPersonelInfo(begin, end, userId)
                .map(new Func1() {
                    @Override
                    public Object call(Object o) {
                        //User mUser = (User)o;
                        return (ArrayList<Jpushperson>) o;
                    }
                });

        toSubscribe(observable, subscriber);
    }

    public void doUploadRentslInfo(Subscriber<UploadResult> subscriber, Map<String, RequestBody> map){
        Observable observable = apiService.uploadRentalHousing(map)
                .map(new Func1() {
                    @Override
                    public Object call(Object o) {
                        //User mUser = (User)o;
                        return (UploadResult) o;
                    }
                });

        toSubscribe(observable, subscriber);
    }
    public void doUploadFloatingInfo(Subscriber<UploadResult> subscriber, Map<String, RequestBody> map){
        Observable observable = apiService.uploadFloatingPopulation(map)
                .map(new Func1() {
                    @Override
                    public Object call(Object o) {
                        //User mUser = (User)o;
                        return (UploadResult) o;
                    }
                });

        toSubscribe(observable, subscriber);
    }


    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
//            if (httpResult.getCount() == 0) {
//                throw new ApiException(100);
//            }
//            User temp = (User)httpResult.getSubjects();
//            if(temp.getUnitid() == -1){
//                throw new ApiException(101);
//
//            }
            return httpResult.getSubjects();
        }
    }


}
