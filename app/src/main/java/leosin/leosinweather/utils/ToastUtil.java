package leosin.leosinweather.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/14.
 */
public class ToastUtil {

    private Toast toast;
    private LinearLayout toastView;

    /**
     * 修改原布局的Toast
     */
    public ToastUtil() {
    }

    /**
     * 设置Toast字体及背景
     *
     * @param messageColor
     * @param background
     * @return
     */
    public ToastUtil setToastBackground(int messageColor, int background) {
        View view = toast.getView();
        if (view != null) {
            TextView message = ((TextView) view.findViewById(android.R.id.message));
            view.setBackgroundResource(background);
            message.setBackgroundResource(background);
            message.setTextColor(messageColor);
        }
        return this;
    }

    /**
     * 显示Toast
     */
    public ToastUtil showToast(Context context, CharSequence message) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        return this;
    }

    /**
     * 显示Toast
     */
    public ToastUtil showToast(Context context, int message) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        return this;
    }

    /**
     * 显示Toast
     *
     * @return
     */
    public ToastUtil show() {
        toast.show();
        return this;
    }

}


