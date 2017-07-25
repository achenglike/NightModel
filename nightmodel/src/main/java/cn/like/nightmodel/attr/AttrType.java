package cn.like.nightmodel.attr;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by like on 16/7/20.
 */
public abstract class AttrType {
    protected static final String DEFTYPE_DRAWABLE = "drawable";
    protected static final String DEFTYPE_COLOR = "color";
    protected static final String DEFTYPE_STYLE = "style";

    String attrType;
    public AttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrType()
    {
        return attrType;
    }

    public abstract  void apply(View view, String resName);

    public abstract String getResourceName(String attrValue, Resources resources);

    protected String getIntResourceName(String attrValue, Resources resources) {
        int id = Integer.parseInt(attrValue.substring(1));
        if (id==0) return null;
        return resources.getResourceEntryName(id);
    }

    protected Drawable getDrawable(Context context, String resName) {
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
