package cn.like.nightmodel.utils;

import android.content.res.Resources;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import cn.like.nightmodel.attr.Attr;
import cn.like.nightmodel.attr.AttrType;

/**
 * Created by like on 16/7/21.
 */
public class AttrUtils {

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

    private static AttrType getSupportAttrType(String attrName) {
        for (AttrType attrType : AttrType.values()) {
            if (attrType.getAttrType().equals(attrName))
                return attrType;
        }
        return null;
    }

}
