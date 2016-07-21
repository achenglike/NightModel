package cn.like.nightmodel.attr;

import android.view.View;

/**
 * Created by like on 16/7/20.
 */
public class Attr {
    String resName;
    AttrType attrType;

    public Attr(String resName, AttrType attrType) {
        this.resName = resName;
        this.attrType = attrType;
    }

    public void apply(View view) {
        attrType.apply(view, resName);
    }
}
