package cn.like.nightmodel.attr.impl;

import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import cn.like.nightmodel.attr.AttrType;

/**
 * Created by like on 2017/7/25.
 */

public class AttrTypeTextStyle extends AttrType {

    public AttrTypeTextStyle() {
        super("style");
    }

    @Override
    public void apply(View view, String resName) {
        if (TextUtils.isEmpty(resName)) return;
        // style的特殊性需要考虑,只能对TextView进行
        if (view instanceof TextView) {
            int resId = view.getResources().getIdentifier(resName, DEFTYPE_STYLE, view.getContext().getPackageName());
            if (Build.VERSION.SDK_INT < 23) {
                ((TextView) view).setTextAppearance(view.getContext(), resId);
            } else {
                ((TextView) view).setTextAppearance(resId);
            }
        }
    }

    @Override
    public String getResourceName(String attrValue, Resources resources) {
        return attrValue.substring(1);
    }
}
