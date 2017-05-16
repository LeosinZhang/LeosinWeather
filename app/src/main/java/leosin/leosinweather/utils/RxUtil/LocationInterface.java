package leosin.leosinweather.utils.RxUtil;

import leosin.leosinweather.bean.LocationBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: LeosinZhang
 * Time: 2017/5/16 9:22
 * Describle:请求当前城市位置信息
 */
public interface LocationInterface {
   // http://api.map.baidu.com/location/ip?ak=请输入您的AK&coor=bd09ll
   @GET("location/ip")
   Observable<LocationBean> getLocationInfo(@Query("ak") String key);
}
