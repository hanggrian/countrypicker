package io.github.hendraanggrian.countrypickerdialog;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import io.github.hendraanggrian.countrypicker.R;
import io.github.hendraanggrian.countrypickerdialog.internal.CountryRecyclerAdapter;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class CountryPickerDialog extends AppCompatDialog {

    @Nullable private final String title;
    private final boolean cancellable;
    @NonNull private final CountryRecyclerAdapter adapter;
    @NonNull private final FastScrollerProperties properties;

    private CountryPickerDialog(@NonNull Context context, @Nullable String title, boolean cancellable, boolean showDialingCode, @Nullable OnPickedListener listener, @NonNull FastScrollerProperties properties) {
        super(context);
        List<Country> countries = new ArrayList<>();
        for (String line : context.getResources().getStringArray(R.array.countries))
            countries.add(new Country(context, line));
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                final Locale locale = getContext().getResources().getConfiguration().locale;
                final Collator collator = Collator.getInstance(locale);
                collator.setStrength(Collator.PRIMARY);
                return collator.compare(
                        new Locale(locale.getLanguage(), country1.getIsoCode()).getDisplayCountry(),
                        new Locale(locale.getLanguage(), country2.getIsoCode()).getDisplayCountry());
            }
        });
        this.title = title;
        this.cancellable = cancellable;
        this.adapter = new CountryRecyclerAdapter(countries, showDialingCode, listener, this);
        this.properties = properties;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_country_picker);
        setTitle(title);
        setCancelable(cancellable);
        FastScrollRecyclerView recyclerView = (FastScrollRecyclerView) findViewById(R.id.recyclerview_country);
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);

            recyclerView.setThumbColor(properties.thumbColor);
            recyclerView.setTrackColor(properties.trackColor);
            recyclerView.setPopupBgColor(properties.popupBgColor);
            recyclerView.setPopupTextColor(properties.popupTextColor);
            if (properties.popupTextSize != null)
                recyclerView.setPopupTextSize(properties.popupTextSize);
            if (properties.popupTextTypeface != null)
                recyclerView.setPopUpTypeface(properties.popupTextTypeface);
            if (properties.autoHideEnabled != null)
                recyclerView.setAutoHideEnabled(properties.autoHideEnabled);
            if (properties.autoHideDelay != null)
                recyclerView.setAutoHideDelay(properties.autoHideDelay);
        }
    }

    public interface OnPickedListener {
        void onPicked(Country country);
    }

    public static class Builder {
        @NonNull Context context;
        @NonNull String title;
        boolean showCallingCode = false;
        @Nullable OnPickedListener listener;
        @NonNull FastScrollerProperties properties;
        boolean cancellable = true;

        public Builder(@NonNull Context context, @NonNull String title) {
            this.context = context;
            this.title = title;
            this.properties = new FastScrollerProperties(context);
        }

        public Builder showCallingCode(boolean showCallingCode) {
            this.showCallingCode = showCallingCode;
            return this;
        }

        public Builder onPicked(OnPickedListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder scrollerThumbColor(@ColorRes int color) {
            this.properties.thumbColor = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder scrollerTrackColor(@ColorRes int color) {
            this.properties.trackColor = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder scrollerPopupBackgroundColor(@ColorRes int color) {
            this.properties.popupBgColor = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder scrollerPopupTextColor(@ColorRes int color) {
            this.properties.popupTextColor = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder scrollerPopupTextSize(int textSize, boolean useDp) {
            this.properties.popupTextSize = useDp
                    ? (int) (textSize * context.getResources().getDisplayMetrics().density)
                    : textSize;
            return this;
        }

        public Builder scrollerPopupTextSize(int textSize) {
            return scrollerPopupTextSize(textSize, false);
        }

        public Builder scrollerPopupTextTypeface(Typeface typeface) {
            this.properties.popupTextTypeface = typeface;
            return this;
        }

        public Builder scrollerAutoHide(boolean autoHideEnabled, int autoHideDelay) {
            this.properties.autoHideEnabled = autoHideEnabled;
            this.properties.autoHideDelay = autoHideDelay;
            return this;
        }

        public Builder scrollerAutoHide(boolean autoHideEnabled) {
            return scrollerAutoHide(autoHideEnabled, 1500);
        }

        public Builder cancellable(boolean cancellable) {
            this.cancellable = cancellable;
            return this;
        }

        public CountryPickerDialog build() {
            return new CountryPickerDialog(context, title, cancellable, showCallingCode, listener, properties);
        }

        public CountryPickerDialog show() {
            CountryPickerDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }

    private static class FastScrollerProperties {
        @NonNull @ColorInt Integer thumbColor;
        @NonNull @ColorInt Integer trackColor;
        @NonNull @ColorInt Integer popupBgColor;
        @NonNull @ColorInt Integer popupTextColor;
        @Nullable Integer popupTextSize;
        @Nullable Typeface popupTextTypeface;
        @Nullable Integer autoHideDelay;
        @Nullable Boolean autoHideEnabled;

        FastScrollerProperties(Context context) {
            thumbColor = getThemeColor(context, R.attr.colorAccent);
            trackColor = ContextCompat.getColor(context, android.R.color.transparent);
            popupBgColor = getThemeColor(context, R.attr.colorAccent);
            popupTextColor = getThemeColor(context, R.attr.colorControlNormal);
        }

        private int getThemeColor(@NonNull final Context context, @AttrRes final int attributeColor) {
            final TypedValue value = new TypedValue();
            context.getTheme().resolveAttribute(attributeColor, value, true);
            return value.data;
        }
    }
}