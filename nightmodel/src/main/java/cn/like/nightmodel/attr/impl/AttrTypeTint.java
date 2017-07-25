package cn.like.nightmodel.attr.impl;

import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import cn.like.nightmodel.attr.AttrType;

/**
 * Created by like on 2017/7/25.
 */

public class AttrTypeTint extends AttrType {

    public AttrTypeTint() {
        super("tint");
    }

    @Override
    public void apply(View view, String resName) {
        if (TextUtils.isEmpty(resName)) return;
        Resources mResources = view.getResources();
        int resId = mResources.getIdentifier(resName, DEFTYPE_COLOR, view.getContext().getPackageName());
        if (0 != resId) {
            ((ImageView) view).setColorFilter(mResources.getColor(resId));
        }
    }

    @Override
    public String getResourceName(String attrValue, Resources resources) {
        return getIntResourceName(attrValue, resources);
    }
}
