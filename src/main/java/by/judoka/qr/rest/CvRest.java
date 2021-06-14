package by.judoka.qr.rest;

import by.judoka.qr.pojo.ConvertImage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface CvRest {

    @GET("/distortion/pincushion")
    Call<ConvertImage> getPincushion(@Query("data") String data);

    @GET("/distortion/barrel")
    Call<ConvertImage> getBarrel(@Query("data") String data);

}
