package cn.like.nightmodel.utils;

import android.content.res.Resources;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.like.nightmodel.attr.Attr;
import cn.like.nightmodel.attr.AttrType;
import cn.like.nightmodel.attr.impl.AttrTypeBackground;
import cn.like.nightmodel.attr.impl.AttrTypeImageSrc;
import cn.like.nightmodel.attr.impl.AttrTypeImageSrcCompat;
import cn.like.nightmodel.attr.impl.AttrTypeProgressDrawable;
import cn.like.nightmodel.attr.impl.AttrTypeTextColor;
import cn.like.nightmodel.attr.impl.AttrTypeTextStyle;
import cn.like.nightmodel.attr.impl.AttrTypeTint;

/**
 * Created by like on 16/7/21.
 */
public class AttrUtils {

    private static HashMap<String, AttrType> attrTypeHashMap = new HashMap<>();

    static {
        AttrType background = new AttrTypeBackground();
        attrTypeHashMap.put(background.getAttrType(), background);

        AttrType imageSrc = new AttrTypeImageSrc();
        attrTypeHashMap.put(imageSrc.getAttrType(), imageSrc);

        AttrType imageSrcCompat = new AttrTypeImageSrcCompat();
        attrTypeHashMap.put(imageSrcCompat.getAttrType(),  imageSrcCompat);

        AttrType progressDrawable = new AttrTypeProgressDrawable();
        attrTypeHashMap.put(progressDrawable.getAttrType(), progressDrawable);

        AttrType textColor = new AttrTypeTextColor();
        attrTypeHashMap.put(textColor.getAttrType(), textColor);

        AttrType textStyle = new AttrTypeTextStyle();
        attrTypeHashMap.put(textStyle.getAttrType(), textStyle);

        AttrType tint = new AttrTypeTint();
        attrTypeHashMap.put(tint.getAttrType(), tint);
    }

    public static void addExpandAttrType(AttrType... attrTypes) {
        if (attrTypes == null || attrTypes.length == 0) return;
        for (AttrType attrType: attrTypes) {
            attrTypeHashMap.put(attrType.getAttrType(), attrType);
        }
    }

    private static AttrType getSupportAttrType(String attrName) {
        return attrTypeHashMap.get(attrName);
    }

    public static List<Attr> getNightModelAttr(Object[] args, Resources resources) {
        List<Attr> nightModelAttrs = new ArrayList<>();
        if (args != null && args.length > 0) {
            for (Object obj: args) {
                if (obj instanceof AttributeSet) {
                    AttributeSet attrs = (AttributeSet) obj;
                    for (int i = 0; i < attrs.getAttributeCount(); i++)
                    {
                        String attrName = attrs.getAttributeName(i);
                        String attrValue = attrs.getAttributeValue(i);
                        AttrType attrType = getSupportAttrType(attrName);
                        if (attrType == null) continue;

                        if (attrValue.startsWith("@")) {
                            String resourceName = attrType.getResourceName(attrValue, resources);
                            Attr attr = new Attr(resourceName, attrType);
                            nightModelAttrs.add(attr);
                        }
                    }
                }
            }
        }
        return nightModelAttrs;
    }
}
