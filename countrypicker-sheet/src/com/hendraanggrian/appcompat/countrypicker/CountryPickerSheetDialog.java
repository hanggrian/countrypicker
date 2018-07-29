package com.hendraanggrian.appcompat.countrypicker;

import android.content.Context;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hendraanggrian.appcompat.widget.CountryPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CountryPickerSheetDialog extends BottomSheetDialog {

    private final CountryPicker picker;

    public CountryPickerSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);

        picker = new CountryPicker(getContext());
        setContentView(picker);

        final BottomSheetBehavior behavior = BottomSheetBehavior.from((View) picker.getParent());
        picker.getSearchView().getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (behavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }

    @NonNull
    public CountryPicker getPicker() {
        if (picker == null) {
            throw new IllegalStateException("Dialog must be inflated first.");
        }
        return picker;
    }

    public void setOnSelectedListener(@Nullable final CountryPicker.OnSelectedListener listener) {
        picker.getAdapter().setListener(listener == null ? null : new CountryPicker.OnSelectedListener() {
            @Override
            public void onSelected(@NonNull Country country) {
                listener.onSelected(country);
                dismiss();
            }
        });
    }
}