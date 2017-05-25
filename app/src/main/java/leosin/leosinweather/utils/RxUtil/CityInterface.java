package leosin.leosinweather.utils.RxUtil;

import leosin.leosinweather.bean.CityBean;
import leosin.leosinweather.bean.WeatherBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: LeosinZhang
 * Time: 2017/5/25
 * Describle: 请求城市的接口
 */
public interface CityInterface {
    @GET("weather/city")
    Observable<CityBean> getCityInfo(@Query("appkey") String key);

}
