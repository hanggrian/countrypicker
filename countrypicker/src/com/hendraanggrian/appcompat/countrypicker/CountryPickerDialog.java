package com.hendraanggrian.appcompat.countrypicker;

import android.content.Context;
import android.os.Bundle;

import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;

import static android.view.Window.FEATURE_NO_TITLE;

public class CountryPickerDialog extends AppCompatDialog {

    private CountryPicker picker;

    public CountryPickerDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerDialog(@NonNull Context context, int theme) {
        super(context, theme);
        supportRequestWindowFeature(FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picker = new CountryPicker(getContext());
        setContentView(picker);
    }

    @NonNull
    public CountryPicker getPicker() {
        if (picker == null) {
            throw new IllegalStateException("Dialog must be inflated first.");
        }
        return picker;
    }

    /*public void setOnSelectedListener(@Nullable final CountryPicker.OnSelectedListener listener) {
        picker.getAdapter().listener = listener == null ? null : new CountryPicker.OnSelectedListener() {
            @Override
            public void onSelected(@NonNull Country country) {
                listener.onSelected(country);
                dismiss();
            }
        };
    }*/

    public static class Builder {
        private final Context context;
        @Nullable private List<Country> countries;
        private boolean isShowFlag = CountryPicker.DEFAULT_SHOW_FLAG;
        @Nullable private CountryPicker.OnSelectedListener listener;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        @NonNull
        public Builder setItems(@NonNull List<Country> countries) {
            this.countries = countries;
            return this;
        }

        @NonNull
        public Builder setShowFlag(boolean isShowFlag) {
            this.isShowFlag = isShowFlag;
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
                dialog.picker.setItems(countries);
            }
            if (isShowFlag != CountryPicker.DEFAULT_SHOW_FLAG) {
                dialog.picker.setShowFlag(isShowFlag);
            }
            if (listener != null) {
//                dialog.setOnSelectedListener(listener);
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