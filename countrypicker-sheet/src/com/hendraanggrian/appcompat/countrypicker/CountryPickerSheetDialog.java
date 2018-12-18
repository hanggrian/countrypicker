package com.hendraanggrian.appcompat.countrypicker;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class CountryPickerSheetDialog extends BottomSheetDialog implements CountryPickerContainer {

    private final CountryPickerContainer container;

    public CountryPickerSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);

        // root layout to avoid picker content being pushed because height is too small
        container = new CountryPickerContainerImpl(this, context);
        final FrameLayout root = new FrameLayout(context);
        root.setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        root.addView(container.getPicker());
        setContentView(root);

        final BottomSheetBehavior behavior = BottomSheetBehavior.from((View) root.getParent());
        behavior.setPeekHeight(getDisplayMetrics().heightPixels * 2 / 3);
        getPicker()
            .getSearchBar()
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

    /**
     * {@inheritDoc}
     */
    @NonNull
    public CountryPicker getPicker() {
        return container.getPicker();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItems(@NonNull List<Country> countries) {
        container.setItems(countries);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFlagShown(boolean shown) {
        container.setFlagShown(shown);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnSelectedListener(@Nullable CountryPicker.OnSelectedListener listener) {
        container.setOnSelectedListener(listener);
    }

    private DisplayMetrics getDisplayMetrics() {
        final DisplayMetrics dm = new DisplayMetrics();
        Preconditions.checkNotNull(getWindow())
            .getWindowManager()
            .getDefaultDisplay()
            .getMetrics(dm);
        return dm;
    }
}