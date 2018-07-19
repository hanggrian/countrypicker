package com.hendraanggrian.appcompat.countrydialog;

import android.content.Context;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;

final class AttrsUtils {

    private AttrsUtils() {
    }

    static int getColor(@NonNull Context context, @AttrRes int attributeColor) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attributeColor, value, true);
        return value.data;
    }
}