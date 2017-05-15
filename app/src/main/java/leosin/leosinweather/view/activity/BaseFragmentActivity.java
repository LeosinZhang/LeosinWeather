package leosin.leosinweather.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import leosin.leosinweather.App;
import leosin.leosinweather.R;
import leosin.leosinweather.utils.titleBar.StatusBarUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Author: LeosinZhang
 * Time: 2017/1/5:14:50
 * Describe:
 */
public class BaseFragmentActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_left_in, R.anim.hold);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_right_out);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.existSys();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }
}
