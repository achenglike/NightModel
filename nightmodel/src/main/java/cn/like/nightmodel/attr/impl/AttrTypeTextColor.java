package cn.like.nightmodel.attr.impl;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import cn.like.nightmodel.attr.AttrType;

/**
 * Created by like on 2017/7/25.
 */

public class AttrTypeTextColor extends AttrType {

    public AttrTypeTextColor() {
        super("textColor");
    }

    @Override
    public void apply(View view, String resName) {
        if (TextUtils.isEmpty(resName)) return;
        Resources mResources = view.getResources();
        int resId = mResources.getIdentifier(resName, DEFTYPE_COLOR, view.getContext().getPackageName());
        if (0 != resId) {
            ColorStateList colorList = mResources.getColorStateList(resId);
            if (colorList == null) return;

            ((TextView) view).setTextColor(colorList);
        }
    }

    @Override
    public String getResourceName(String attrValue, Resources resources) {
        return getIntResourceName(attrValue, resources);
    }
}
