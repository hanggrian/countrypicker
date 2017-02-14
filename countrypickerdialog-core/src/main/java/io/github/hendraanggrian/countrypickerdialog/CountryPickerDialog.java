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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class CountryPickerDialog extends AppCompatDialog {

    @NonNull private final String title;
    private final boolean cancellable;
    @NonNull private final CountryRecyclerAdapter adapter;
    @NonNull private final FastScrollerProperties properties;

    private CountryPickerDialog(@NonNull Builder builder) {
        super(builder.context);
        this.title = builder.title;
        this.cancellable = builder.cancellable;
        this.adapter = new CountryRecyclerAdapter(builder.context, Arrays.asList(Country.values()), builder.showDialCode, builder.listener, this);
        this.properties = builder.properties;
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
        @NonNull private final Context context;
        @NonNull private final String title;
        @NonNull private final List<Country> countries;
        @NonNull private final FastScrollerProperties properties;
        private boolean cancellable = true;
        private boolean showDialCode = false;
        @Nullable OnPickedListener listener;

        public Builder(@NonNull Context context, @NonNull String title) {
            this(context, title, new ArrayList<>(Arrays.asList(Country.values())));
        }

        public Builder(@NonNull Context context, @NonNull String title, @NonNull List<Country> countries) {
            this.context = context;
            this.title = title;
            this.countries = countries;
            this.properties = new FastScrollerProperties(context);
        }

        public Builder exclude(@NonNull String... isoCodes) {
            for (String isoCode : isoCodes)
                this.countries.remove(Country.fromIsoCode(isoCode));
            return this;
        }

        public Builder cancellable(boolean cancellable) {
            this.cancellable = cancellable;
            return this;
        }

        public Builder showDialCode(boolean showCallingCode) {
            this.showDialCode = showCallingCode;
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

        public CountryPickerDialog build() {
            return new CountryPickerDialog(this);
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

        FastScrollerProperties(@NonNull Context context) {
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