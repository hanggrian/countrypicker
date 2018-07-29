package com.hendraanggrian.appcompat.countrypicker;

import android.content.Context;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CountryPickerSheetDialog extends BottomSheetDialog implements CountryPickerComponent {

    private final CountryPicker picker;
    private final CountryPickerComponentImpl impl;

    public CountryPickerSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);

        picker = new CountryPicker(getContext());
        impl = new CountryPickerComponentImpl(picker) {
            @Override
            void onDismiss() {
                dismiss();
            }
        };
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
}