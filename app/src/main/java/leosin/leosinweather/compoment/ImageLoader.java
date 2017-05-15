package leosin.leosinweather.compoment;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Author: LeosinZhang
 * Time: 2016/12/15:10:26
 * Describle: 图片加载类,统一适配(方便换库,方便管理)
 */
public class ImageLoader {

    public static void load(Context context, @DrawableRes int imageRes, ImageView view) {
        Glide.with(context).load(imageRes).crossFade().into(view);
    }

    public static void clear(Context context) {
        Glide.get(context).clearMemory();
    }
}
