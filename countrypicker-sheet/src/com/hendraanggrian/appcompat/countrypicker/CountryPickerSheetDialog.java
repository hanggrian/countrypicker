package com.hendraanggrian.appcompat.countrypicker;

import android.content.Context;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hendraanggrian.appcompat.widget.CountryPicker;

import androidx.annotation.NonNull;

public class CountryPickerSheetDialog extends BottomSheetDialog {

    private final CountryPicker picker;

    public CountryPickerSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
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
}