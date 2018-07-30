package com.hendraanggrian.appcompat.countrypicker;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CountryPickerSheetDialog extends BottomSheetDialog implements CountryPickerComponent {

    private final CountryPickerComponentImpl impl;

    public CountryPickerSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerSheetDialog(@NonNull final Context context, int theme) {
        super(context, theme);
        impl = new CountryPickerComponentImpl(this, context);
        setContentView(impl.getPicker());

        final BottomSheetBehavior behavior = BottomSheetBehavior.from((View) getPicker().getParent());
        behavior.setPeekHeight(getDisplayMetrics().heightPixels * 2 / 3);
        getPicker().getSearchBar()
                .getInput()
                .setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
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

    private DisplayMetrics getDisplayMetrics() {
        final DisplayMetrics dm = new DisplayMetrics();
        Window window = getWindow();
        if (window == null) {
            throw new IllegalStateException();
        }
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }
}