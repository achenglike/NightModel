package cn.like.nightmodel.attr.impl;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import cn.like.nightmodel.attr.AttrType;

/**
 * Created by like on 2017/7/25.
 */

public class AttrTypeProgressDrawable extends AttrType {
    public AttrTypeProgressDrawable() {
        super("progressDrawable");
    }

    @Override
    public void apply(View view, String resName) {
        if (TextUtils.isEmpty(resName)) return;
        Drawable drawable = getDrawable(view.getContext(), resName);
        if (drawable == null) return;
        ((ProgressBar)view).setProgressDrawable(drawable);
    }

    @Override
    public String getResourceName(String attrValue, Resources resources) {
        return getIntResourceName(attrValue, resources);
    }
}
