package leosin.leosinweather.view.customView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import leosin.leosinweather.R;
import leosin.leosinweather.utils.Const;


/**
 * Created by Administrator on 2016/11/7.
 */
public class ToolbarHeadDetail extends Toolbar {
    @BindView(R.id.iv_custom_toolbar_head_detail_navigation)
    ImageView mIvCustomToolbarHeadDetailNavigation;
    @BindView(R.id.tv_custom_toolbar_head_detail_city)
    TextView mTvCustomToolbarHeadDetailBuild;
    @BindView(R.id.iv_custom_toolbar_head_detail_add)
    ImageView mIvCustomToolbarHeadDetailAdd;
    private Context mContext;


    public ToolbarHeadDetail(Context context) {
        super(context);
        this.mContext = context;
    }

    public ToolbarHeadDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //加载布局
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar_title_detail, this);
        ButterKnife.bind(this);
    }



    public void setTv_Build(String str) {
        mTvCustomToolbarHeadDetailBuild.setText(str);
    }


    @OnClick({R.id.iv_custom_toolbar_head_detail_navigation, R.id.iv_custom_toolbar_head_detail_add})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_custom_toolbar_head_detail_navigation: {
                Intent intent = new Intent();
                intent.setAction(Const.BROADCAST_ACTION_OPEN_NAVI);
                mContext.sendBroadcast(intent);
            }
            break;

            case R.id.iv_custom_toolbar_head_detail_add: {
                Intent intent = new Intent();
                intent.setAction(Const.BROADCAST_ACTION_ADD_VIEWPAGER);
                mContext.sendBroadcast(intent);
            }
            break;

            default:
                break;
        }
    }
}
