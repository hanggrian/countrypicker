package com.hendraanggrian.appcompat.countrypicker;

import android.content.Context;

import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;

import static android.view.Window.FEATURE_NO_TITLE;

public class CountryPickerDialog extends AppCompatDialog implements CountryPickerComponent {

    private final CountryPickerComponentImpl impl;

    public CountryPickerDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerDialog(@NonNull Context context, int theme) {
        super(context, theme);
        supportRequestWindowFeature(FEATURE_NO_TITLE);
        impl = new CountryPickerComponentImpl(this, context);
        setContentView(impl.getPicker());
    }

    @NonNull
    public CountryPicker getPicker() {
        return impl.getPicker();
    }

    @Override
    public void setItems(@NonNull List<Country> countries) {
        impl.setItems(countries);
    }

    @Override
    public void setFlagShown(boolean shown) {
        impl.setFlagShown(shown);
    }

    @Override
    public void setOnSelectedListener(@Nullable CountryPicker.OnSelectedListener listener) {
        impl.setOnSelectedListener(listener);
    }

    public static class Builder {
        private final Context context;
        private List<Country> countries;
        private boolean isFlagShown = CountryPicker.DEFAULT_FLAG_SHOWN;
        private CountryPicker.OnSelectedListener listener;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        @NonNull
        public Builder setItems(@NonNull List<Country> countries) {
            this.countries = countries;
            return this;
        }

        @NonNull
        public Builder setFlagShown(boolean shown) {
            this.isFlagShown = shown;
            return this;
        }

        @NonNull
        public Builder setOnSelectedListener(@Nullable CountryPicker.OnSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        @NonNull
        public CountryPickerDialog build() {
            CountryPickerDialog dialog = new CountryPickerDialog(context);
            if (countries != null) {
                dialog.setItems(countries);
            }
            if (isFlagShown != CountryPicker.DEFAULT_FLAG_SHOWN) {
                dialog.setFlagShown(isFlagShown);
            }
            if (listener != null) {
                dialog.setOnSelectedListener(listener);
            }
            return dialog;
        }

        @NonNull
        public CountryPickerDialog show() {
            CountryPickerDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }
}