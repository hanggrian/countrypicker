package com.hendraanggrian.countrydialog;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;

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