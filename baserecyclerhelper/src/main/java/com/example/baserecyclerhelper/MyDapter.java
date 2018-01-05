package com.example.baserecyclerhelper;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zhaoke on 2018/1/4.
 */

public class MyDapter extends BaseItemDraggableAdapter<HomeItem, BaseViewHolder> {

    public MyDapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.buttonitem, item.getName())
                .addOnClickListener(R.id.buttonitem);
        //helper.setImageResource(R.id.icon, item.getImageResource());
        // 加载网络图片
        //Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }


}
