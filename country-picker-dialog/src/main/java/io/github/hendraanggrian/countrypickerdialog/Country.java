package io.github.hendraanggrian.countrypickerdialog;

import android.content.Context;
import android.support.annotation.DrawableRes;

import java.util.Locale;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class Country {

    private final String isoCode;
    private final String callingCode;
    private final String name;
    private final int flagRes;

    Country(Context context, String line) {
        isoCode = line.split(":")[0];
        callingCode = "+" + line.split(":")[1];
        name = new Locale(context.getResources().getConfiguration().locale.getLanguage(), getIsoCode()).getDisplayCountry();
        flagRes = context.getResources().getIdentifier(("flag_" + getIsoCode().toLowerCase(Locale.ENGLISH)).toLowerCase(Locale.ENGLISH), "drawable", context.getPackageName());
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public String getName() {
        return name;
    }

    @DrawableRes
    public int getFlagRes() {
        return flagRes;
    }
}