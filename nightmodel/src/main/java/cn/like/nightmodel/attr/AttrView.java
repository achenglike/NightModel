package cn.like.nightmodel.attr;

import android.view.View;

import java.util.List;

/**
 * Created by like on 16/7/20.
 */
public class AttrView {
    View view;
    List<Attr> attrs;

    public AttrView(View view, List<Attr> attrs) {
        this.view = view;
        this.attrs = attrs;
    }

    public void apply() {
        if (null == view) return;

        for (Attr attr : attrs) {
            attr.apply(view);
        }
    }
}
