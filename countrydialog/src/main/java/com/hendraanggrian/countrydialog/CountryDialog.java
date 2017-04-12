package com.hendraanggrian.countrydialog;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Window;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.Arrays;
import java.util.List;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class CountryDialog extends AppCompatDialog {

    @Nullable private final String title;
    @NonNull private final CountryAdapter adapter;
    @NonNull private final FastScrollerProperties properties;

    @Nullable private OnSelectedListener listener;

    public CountryDialog(@NonNull Builder builder) {
        super(builder.context);
        this.title = builder.title;
        this.listener = builder.listener;
        this.properties = builder.properties;
        this.adapter = new CountryAdapter(builder.context, builder.countries, builder.showFlags, builder.showDialCode) {
            @Override
            public void onSelected(@NonNull Country country) {
                if (listener != null)
                    listener.onSelected(country);
                dismiss();
            }
        };
        if (TextUtils.isEmpty(title))
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_country_picker);
        setTitle(title);

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

    public void setOnSelectedListener(@Nullable OnSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnSelectedListener {
        void onSelected(@NonNull Country country);
    }

    public static class Builder {
        @NonNull private final Context context;
        @NonNull private final List<Country> countries;
        @NonNull private final FastScrollerProperties properties;
        private boolean showFlags = true;
        private boolean showDialCode = false;
        @Nullable private String title;
        @Nullable private OnSelectedListener listener;

        public Builder(@NonNull Context context) {
            this(context, Arrays.asList(Country.values()));
        }

        public Builder(@NonNull Context context, @NonNull List<Country> countries) {
            this.context = context;
            this.countries = countries;
            this.properties = new FastScrollerProperties(context);
        }

        @NonNull
        public Builder title(@Nullable String title) {
            this.title = title;
            return this;
        }

        @NonNull
        public Builder exclude(@NonNull String... isoCodes) {
            for (String isoCode : isoCodes)
                this.countries.remove(Country.fromIsoCode(isoCode));
            return this;
        }

        @NonNull
        public Builder showFlags(boolean showFlags) {
            this.showFlags = showFlags;
            return this;
        }

        @NonNull
        public Builder showDialCode(boolean showCallingCode) {
            this.showDialCode = showCallingCode;
            return this;
        }

        @NonNull
        public Builder onSelected(OnSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        @NonNull
        public Builder scrollerThumbColor(@ColorRes int color) {
            this.properties.thumbColor = ContextCompat.getColor(context, color);
            return this;
        }

        @NonNull
        public Builder scrollerTrackColor(@ColorRes int color) {
            this.properties.trackColor = ContextCompat.getColor(context, color);
            return this;
        }

        @NonNull
        public Builder scrollerPopupBackgroundColor(@ColorRes int color) {
            this.properties.popupBgColor = ContextCompat.getColor(context, color);
            return this;
        }

        @NonNull
        public Builder scrollerPopupTextColor(@ColorRes int color) {
            this.properties.popupTextColor = ContextCompat.getColor(context, color);
            return this;
        }

        @NonNull
        public Builder scrollerPopupTextSize(int textSize, boolean useDp) {
            this.properties.popupTextSize = useDp
                    ? (int) (textSize * context.getResources().getDisplayMetrics().density)
                    : textSize;
            return this;
        }

        @NonNull
        public Builder scrollerPopupTextSize(int textSize) {
            return scrollerPopupTextSize(textSize, false);
        }

        @NonNull
        public Builder scrollerPopupTextTypeface(Typeface typeface) {
            this.properties.popupTextTypeface = typeface;
            return this;
        }

        @NonNull
        public Builder scrollerAutoHide(boolean autoHideEnabled, int autoHideDelay) {
            this.properties.autoHideEnabled = autoHideEnabled;
            this.properties.autoHideDelay = autoHideDelay;
            return this;
        }

        @NonNull
        public Builder scrollerAutoHide(boolean autoHideEnabled) {
            return scrollerAutoHide(autoHideEnabled, 1500);
        }

        @NonNull
        public CountryDialog build() {
            return new CountryDialog(this);
        }

        @NonNull
        public CountryDialog show() {
            CountryDialog dialog = build();
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
            thumbColor = AttrsUtils.getColor(context, R.attr.colorAccent);
            trackColor = ContextCompat.getColor(context, android.R.color.transparent);
            popupBgColor = AttrsUtils.getColor(context, R.attr.colorAccent);
            popupTextColor = AttrsUtils.getColor(context, R.attr.colorControlNormal);
        }
    }
}