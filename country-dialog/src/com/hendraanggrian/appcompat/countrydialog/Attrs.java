package com.hendraanggrian.appcompat.countrydialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Px;

public final class Attrs {

    private Attrs() {
    }

    @Px
    public static int getDimen(@NonNull Context context, @AttrRes int attrId) {
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, typedValue, true);
        final int[] textSizeAttr = new int[]{attrId};
        TypedArray a = context.obtainStyledAttributes(typedValue.data, textSizeAttr);
        final int value = a.getDimensionPixelSize(0, 0);
        a.recycle();
        return value;
    }
}