package leosin.leosinweather.utils;

/**
 * Author: LeosinZhang
 * Time: 2016/12/8:14:50
 * Describle:
 */
public class Const {
        //和风天气接口地址
    //public static final String WEATHER_URL = "http://free-api.heweather.com/v5/";
    //public static final String HEFENG_WEATHER_KEY = "018bba0662bb4e1a8fd678348ed899b1";

    /**
     * Broadcast 极速天气接口地址
     * http://api.jisuapi.com/weather/query?appkey=de708bfe98066c16&city=成都
     */
    public static final String JISU_WEATHER_URL = "http://api.jisuapi.com/";
    public static final String JISU_WEATHER_KEY = "de708bfe98066c16";
    public static final int DEFAULT_TIMEOUT = 5; //超时时间

    /**
     * 请求位置
     * http://api.map.baidu.com/location/ip?ak=请输入您的AK&coor=bd09ll
     * *http://api.map.baidu.com/location/ip?ak=K3Q8jEDGvpC2XhkcNLuXhoKxsEwu3kl5&coor=bd09ll
     * 根据经纬度逆编码
     * http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=39.983424,116.322987&output=json&pois=1&ak=您的ak
     * http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=30.67994285,104.06792346&output=json&pois=1&ak=K3Q8jEDGvpC2XhkcNLuXhoKxsEwu3kl5
     */
    public static final String BAIDU_API_URL = "http://api.map.baidu.com/";
    public static final String BAIDU_WEB_API_KEY = "K3Q8jEDGvpC2XhkcNLuXhoKxsEwu3kl5";





    /**
     * Broadcast 宏定义
     */
    public static final String BROADCAST_ACTION_OPEN_NAVI = "open_NavigationView"; //打开NavigationView
    public static final String BROADCAST_ACTION_ADD_VIEWPAGER = "Add_ViewPager"; //新增viewPager
    public static final String BROADCAST_ACTION_SHOW_WEATHER = "Show_WeatherInfo"; //新增viewPager
    public static final String BROADCAST_ACTION_SWIPEREFRESH_ENABLE = "SwipeRefresh_Enable"; //允许下拉刷新
    public static final String BROADCAST_ACTION_SWIPEREFRESH_DISABLE = "SwipeRefresh_Disable"; //禁止下拉刷新

    /**
     * 字体全局宏定义
     */
    public static final String ArefRuqaa = "fonts/ArefRuqaa-Regular.ttf"; //默认字体
    public static final String OpenSans = "fonts/OpenSans-Light.ttf"; //纤细字体

    /**
     * 决定风扇大小变量
     */
    public static final int WINDFAN_BIG = 1; //大风扇
    public static final int WINDFAN_SMALL = 2; //小风扇

}
