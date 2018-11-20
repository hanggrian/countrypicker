package com.hendraanggrian.appcompat.countrypicker;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;

public class CountryPickerSheetDialog extends BottomSheetDialog implements CountryPickerContainer {

    private final CountryPickerContainer container;

    public CountryPickerSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerSheetDialog(@NonNull final Context context, int theme) {
        super(context, theme);

        // use fab as dialog button replacement
        container = new CountryPickerContainerImpl(this, context);
        final FloatingActionButton fab = new FloatingActionButton(context);
        final Runnable runnable = defaultRunnable();
        if (runnable != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    runnable.run();
                }
            });
        }
        container.getPicker().getCoordinatorLayout().addView(fab);
        final CoordinatorLayout.LayoutParams fabLp = (CoordinatorLayout.LayoutParams)
            fab.getLayoutParams();
        fabLp.setAnchorId(R.id.recyclerView);
        fabLp.anchorGravity = Gravity.BOTTOM | GravityCompat.END;
        fabLp.setMargins(0, 0, 20, 20);

        // root layout to avoid picker content being pushed because height is too small
        final FrameLayout root = new FrameLayout(context);
        root.setLayoutParams(new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ));
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

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Runnable defaultRunnable() {
        return container.defaultRunnable();
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