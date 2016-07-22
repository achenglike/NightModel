package cn.like.nightmodel.attr;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by like on 16/7/20.
 */
public enum  AttrType {
    BACKGROUND("background") {
        @Override
        public void apply(View view, String resName) {
            Drawable drawable = getDrawable(view.getContext(), resName);
            if (drawable == null) return;
            view.setBackgroundDrawable(drawable);
        }

        @Override
        public String getResourceName(String attrValue, Resources resources) {
            return getIntResourceName(attrValue, resources);
        }
    },
    COLOR("textColor") {
        @Override
        public void apply(View view, String resName) {
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
    },
    SYTLE("style") {
        @Override
        public void apply(View view, String resName) {
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
    },
    SRC("src") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof ImageView) {
                Drawable drawable = getDrawable(view.getContext(), resName);
                if (drawable == null) return;
                ((ImageView) view).setImageDrawable(drawable);
            }
        }

        @Override
        public String getResourceName(String attrValue, Resources resources) {
            return getIntResourceName(attrValue, resources);
        }
    };


    private static final String DEFTYPE_DRAWABLE = "drawable";
    private static final String DEFTYPE_COLOR = "color";
    private static final String DEFTYPE_STYLE = "style";

    String attrType;
    AttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrType()
    {
        return attrType;
    }

    public abstract void apply(View view, String resName);

    public abstract String getResourceName(String attrValue, Resources resources);

    String getIntResourceName(String attrValue, Resources resources) {
        int id = Integer.parseInt(attrValue.substring(1));
        return resources.getResourceEntryName(id);
    }

    Drawable getDrawable(Context context, String resName) {
        Drawable drawable = null;
        Resources resources = context.getResources();
        try {
            drawable = resources.getDrawable(resources.getIdentifier(resName, DEFTYPE_DRAWABLE,  context.getPackageName()));
        } catch (Resources.NotFoundException e) {
            try {
                drawable = resources.getDrawable(resources.getIdentifier(resName, DEFTYPE_COLOR,  context.getPackageName()));
            } catch (Resources.NotFoundException e2) {
            }
        }
        return drawable;
    }
}
