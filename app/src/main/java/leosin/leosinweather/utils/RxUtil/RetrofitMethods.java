package leosin.leosinweather.utils.RxUtil;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.concurrent.TimeUnit;

import leosin.leosinweather.App;
import leosin.leosinweather.R;
import leosin.leosinweather.adapter.MainAdapter;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.retrofit2.converter.gson.CustomConverterFactory;
import leosin.leosinweather.utils.Const;
import leosin.leosinweather.utils.ToastUtil;
import leosin.leosinweather.view.fragment.WeatherFragment;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: LeosinZhang
 * Time: 2016/12/20:12:05
 * Describle:
 */
public class RetrofitMethods {
    private Retrofit retrofit;
    private WeatherService mWeatherService;


    //构造方法私有
    private RetrofitMethods() {
/*        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);*/
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(Const.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(CustomConverterFactory.create()) //用自定义的类来解析Gson，以免result为空的异常
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Const.JISU_WEATHER_URL)
                .build();

        mWeatherService = retrofit.create(WeatherService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final RetrofitMethods INSTANCE = new RetrofitMethods();
    }

    //获取单例
    public static RetrofitMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取所有天气信息
     * @param city 获取城市
     */
    public void getWeatherInfo(String city, final Activity activity, final View view, WeatherFragment weatherFragment){
        mWeatherService.getWeatherInfo(Const.JISU_WEATHER_KEY,city)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil toastUtil = new ToastUtil();
                        toastUtil.showToast(App.context,e.getMessage()).setToastBackground(Color.WHITE, R.drawable.toast).show();
                        // 判断是否为无网络链接 或其他错误
                        if(e.getMessage()== ""){

                        }
                    }

                    @Override
                    public void onNext(WeatherBean bean) {
                        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.myRecyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                        recyclerView.setAdapter(new MainAdapter(bean, activity, view,weatherFragment,recyclerView));
                    }
                });
    }


}
