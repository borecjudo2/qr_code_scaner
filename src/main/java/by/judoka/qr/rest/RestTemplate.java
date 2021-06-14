package by.judoka.qr.rest;

import by.judoka.qr.image.Image;
import by.judoka.qr.pojo.ConvertImage;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestTemplate {

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

    @SneakyThrows
    public ConvertImage getPincushionImage(Image image){
        CvRest cvRest = retrofit.create(CvRest.class);
        Call<ConvertImage> callSync = cvRest.getPincushion(image.getDataBase64());
        Response<ConvertImage> response = callSync.execute();
        return response.body();
    }

    @SneakyThrows
    public ConvertImage getBarrelImage(Image image){
        CvRest cvRest = retrofit.create(CvRest.class);
        Call<ConvertImage> callSync = cvRest.getBarrel(image.getDataBase64());
        Response<ConvertImage> response = callSync.execute();
        return response.body();
    }
}
