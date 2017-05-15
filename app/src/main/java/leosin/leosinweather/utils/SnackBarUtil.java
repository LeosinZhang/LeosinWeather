package leosin.leosinweather.utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import leosin.leosinweather.R;

/**
 * Author: LeosinZhang
 * Time: 2016/12/21:15:35
 * Describle:
 */
public class SnackBarUtil {

    public static void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();//获取Snackbar的view
        if (view != null) {
            //修改view的背景色
            view.setBackgroundColor(backgroundColor);
            //获取Snackbar的message控件，修改字体颜色
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);
        }
    }
}
