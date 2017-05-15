package leosin.leosinweather.view.customView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
public class ToolbarHeadSimple extends Toolbar {
    @BindView(R.id.img_custom_toolbar_head_simple_navi)
    ImageView mImgCustomToolbarHeadSimpleBack;
    @BindView(R.id.iv_custom_toolbar_head_simple_temp)
    ImageView mIvCustomToolbarHeadSimpleTemp;
    @BindView(R.id.tv_custom_toolbar_head_simple_temp)
    TextView mTvCustomToolbarHeadSimpleTemp;
    @BindView(R.id.iv_custom_toolbar_head_simple_add)
    ImageView mIvCustomToolbarHeadSimpleAdd;
    private Context mContext;


    public ToolbarHeadSimple(Context context) {
        super(context);
        this.mContext = context;
    }

    public ToolbarHeadSimple(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //加载布局
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar_title_simple, this);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.img_custom_toolbar_head_simple_navi, R.id.iv_custom_toolbar_head_simple_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_custom_toolbar_head_simple_navi:{
                Intent intent = new Intent();
                intent.setAction(Const.BROADCAST_ACTION_OPEN_NAVI);
                mContext.sendBroadcast(intent);
            }
                break;
            case R.id.iv_custom_toolbar_head_simple_add:{
                Intent intent = new Intent();
                intent.setAction(Const.BROADCAST_ACTION_ADD_VIEWPAGER);
                mContext.sendBroadcast(intent);
            }
                break;
        }
    }


    public void setTv_Temp(String str) {
        mTvCustomToolbarHeadSimpleTemp.setText(str);
    }

    public void setIV_Temp_Icon(Bitmap bitmap){
        mIvCustomToolbarHeadSimpleTemp.setImageBitmap(bitmap);
    }

}
