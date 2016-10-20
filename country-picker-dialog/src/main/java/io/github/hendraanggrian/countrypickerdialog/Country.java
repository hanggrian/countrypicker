package io.github.hendraanggrian.countrypickerdialog;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class Country {

    @NonNull private final String isoCode;
    @NonNull private final String callingCode;
    @NonNull private final String name;
    private final int flagRes;

    Country(@NonNull Context context, @NonNull String line) {
        isoCode = line.split(":")[0];
        callingCode = "+" + line.split(":")[1];
        name = new Locale(context.getResources().getConfiguration().locale.getLanguage(), getIsoCode()).getDisplayCountry();
        flagRes = context.getResources().getIdentifier(("flag_" + getIsoCode().toLowerCase(Locale.ENGLISH)).toLowerCase(Locale.ENGLISH), "drawable", context.getPackageName());
    }

    @NonNull
    public String getIsoCode() {
        return isoCode;
    }

    @NonNull
    public String getCallingCode() {
        return callingCode;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @DrawableRes
    public int getFlagRes() {
        return flagRes;
    }
}