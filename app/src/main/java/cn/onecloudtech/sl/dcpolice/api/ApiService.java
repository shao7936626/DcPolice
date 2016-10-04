package cn.onecloudtech.sl.dcpolice.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.onecloudtech.sl.dcpolice.model.CheckPerson;
import cn.onecloudtech.sl.dcpolice.model.HttpResult;
import cn.onecloudtech.sl.dcpolice.model.Jpushperson;
import cn.onecloudtech.sl.dcpolice.model.Person;
import cn.onecloudtech.sl.dcpolice.model.Subject;
import cn.onecloudtech.sl.dcpolice.model.UpdateData;
import cn.onecloudtech.sl.dcpolice.model.UploadResult;
import cn.onecloudtech.sl.dcpolice.model.User;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/7/19.
 */
public interface ApiService {
    //    @GET("dchcService/cxf/rest/userRoleService/userlogin/{user}/{password}")
//    Call<User> Repos(@Path("user") String user,@Path("password") String password);
//    Call<List<User>> listRepos(@Path("user") String user);
//    Call<List<Repo>> listRepos(@Path("user") String user);
//
//    @GET("/microservice/weather")
//    Call<String> getResult(@Query("citypinyin") String citypinyin);
//    @GET("/dchcService/login/{username}/{password}")
//    Observable<HttpResult<User>> login(@Path("username") String username, @Path("password") String password);

    @GET("/dchcService/login/login/{username}/{password}")
    Observable<User> login(@Path("username") String username, @Path("password") String password);


//    @Multipart
//    @POST("/dchcService/upload/personinfo")
//    Observable<User> uploadPersonInfo(@Body Person person, @PartMap Map<String, RequestBody> params );

    @Multipart
    @POST("/dchcService/person/addPerson")
    Observable<Person> uploadPersonInfo(@PartMap Map<String, RequestBody> params );

    @Multipart
    @POST("/dchcService/push/check")
    Observable<CheckPerson> checkPersonInfo(@PartMap Map<String, RequestBody> params );

    @GET("/dchcService/appversion")
    Observable<UpdateData> fetchVersion();

    @GET("/dchcService/checkCount/{userid}")
    Observable<Integer> getCheckedPersonelInfoCount(@Path("userid") int id);

    @GET("/dchcService/clientList/{begin}/{end}/{userid}")
    Observable<ArrayList<Jpushperson>> getCheckedPersonelInfo(@Path("begin") int begin, @Path("end") int end, @Path("userid") int id);

    @Multipart
    @POST(" /dchcService/locate/addLocate")
    Observable<UploadResult> uploadRentalHousing(@PartMap Map<String, RequestBody> params );

    @Multipart
    @POST(" /dchcService/relperson/addRelperson")
    Observable<UploadResult> uploadFloatingPopulation(@PartMap Map<String, RequestBody> params );

}
