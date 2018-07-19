package com.hendraanggrian.appcompat.countrydialog;

import android.content.Context;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
final class AttrsUtils {

    static int getColor(@NonNull Context context, @AttrRes int attributeColor) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attributeColor, value, true);
        return value.data;
    }
}