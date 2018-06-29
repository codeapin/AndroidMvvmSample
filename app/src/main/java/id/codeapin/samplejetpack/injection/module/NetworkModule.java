package id.codeapin.samplejetpack.injection.module;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import id.codeapin.samplejetpack.data.network.service.ApiService;
import id.codeapin.samplejetpack.data.pref.PrefHelper;
import id.codeapin.samplejetpack.injection.annotation.PerApplication;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "http://124.40.248.179/HealthBooking/";

    @Provides
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor logging, Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .build();
    }

    @Provides
    @PerApplication
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public Interceptor provideAuthInterceptor(PrefHelper prefHelper) {
        return chain -> {
            Request originalRequest = chain.request();

            Request newRequest = originalRequest.newBuilder()
                    .build();
            return chain.proceed(newRequest);
        };
    }


    @Provides
    @PerApplication
    public Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm")
                .create();
    }
}
