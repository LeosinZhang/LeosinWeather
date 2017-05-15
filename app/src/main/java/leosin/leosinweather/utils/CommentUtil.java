package leosin.leosinweather.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/**
 * Author: LeosinZhang
 * Time: 2016/12/8:15:12
 * Describle:
 */
public class CommentUtil {

    //Context 转 Activity

    /**
     *
     * @param cont
     * @return
     */
    public static Activity ContextToActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return ContextToActivity(((ContextWrapper) cont).getBaseContext());
        return null;
    }
}
