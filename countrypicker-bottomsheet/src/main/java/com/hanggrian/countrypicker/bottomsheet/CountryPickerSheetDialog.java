package com.hanggrian.countrypicker.bottomsheet;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hanggrian.countrypicker.Country;
import com.hanggrian.countrypicker.CountryPicker;
import com.hanggrian.countrypicker.CountryPickerLayout;
import com.hanggrian.countrypicker.FlagDisplay;
import com.hanggrian.countrypicker.NameDisplay;
import com.hanggrian.countrypicker.internal.CountryPickerImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class CountryPickerSheetDialog extends BottomSheetDialog implements CountryPicker {
    private final CountryPicker picker;

    public CountryPickerSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);

        picker = new CountryPickerImpl(this, context);
        picker
            .getLayout()
            .<CardView>findViewById(com.hanggrian.countrypicker.R.id.countrypicker_card)
            .setCardBackgroundColor(
                getColorAttr(context, com.google.android.material.R.attr.colorSurface)
            );

        // root layout to avoid picker content being pushed because height is too small
        final FrameLayout root = new FrameLayout(context);
        root.setBackgroundColor(
            getColorAttr(context, android.R.attr.background)
        );
        root.setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        root.addView(picker.getLayout());
        setContentView(root);

        final BottomSheetBehavior<?> behavior = BottomSheetBehavior.from((View) root.getParent());
        behavior.setPeekHeight(getDisplayMetrics().heightPixels * 2 / 3);
        getLayout()
            .findViewById(com.hanggrian.countrypicker.R.id.countrypicker_search)
            .setOnFocusChangeListener((view, hasFocus) -> {
                if (behavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            });
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public CountryPickerLayout getLayout() {
        return picker.getLayout();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public List<Country> getItems() {
        return picker.getItems();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItems(@NonNull List<Country> countries) {
        picker.setItems(countries);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItems(@NonNull Country... countries) {
        picker.setItems(countries);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public FlagDisplay getFlagDisplay() {
        return picker.getFlagDisplay();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFlagDisplay(@NonNull FlagDisplay display) {
        picker.setFlagDisplay(display);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public NameDisplay getNameDisplay() {
        return picker.getNameDisplay();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNameDisplay(@NonNull NameDisplay display) {
        picker.setNameDisplay(display);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnSelectedListener(@Nullable CountryPickerLayout.OnSelectedListener listener) {
        picker.setOnSelectedListener(listener);
    }

    private DisplayMetrics getDisplayMetrics() {
        final DisplayMetrics dm = new DisplayMetrics();
        Objects
            .requireNonNull(getWindow(), "No window attached")
            .getWindowManager()
            .getDefaultDisplay()
            .getMetrics(dm);
        return dm;
    }

    private static int getColorAttr(Context context, int attrId) {
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, typedValue, true);
        return typedValue.data;
    }

    public static class Builder {
        private final Context context;
        private List<Country> countries;
        private FlagDisplay flagDisplay;
        private NameDisplay nameDisplay;
        private CountryPickerLayout.OnSelectedListener selectedListener;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        /**
         * @see CountryPicker#setItems(List)
         */
        @NonNull
        public Builder setItems(@NonNull List<Country> countries) {
            this.countries = countries;
            return this;
        }

        /**
         * @see CountryPicker#setItems(Country...)
         */
        @NonNull
        public Builder setItems(@NonNull Country... countries) {
            return setItems(Arrays.asList(countries));
        }

        /**
         * @see CountryPicker#setFlagDisplay(FlagDisplay)
         */
        @NonNull
        public Builder setFlagDisplay(@NonNull FlagDisplay display) {
            flagDisplay = display;
            return this;
        }

        /**
         * @see CountryPicker#setNameDisplay(NameDisplay)
         */
        @NonNull
        public Builder setNameDisplay(@NonNull NameDisplay display) {
            nameDisplay = display;
            return this;
        }

        /**
         * @see CountryPicker#setOnSelectedListener(CountryPickerLayout.OnSelectedListener)
         */
        @NonNull
        public Builder setOnSelectedListener(
            @Nullable CountryPickerLayout.OnSelectedListener listener) {
            this.selectedListener = listener;
            return this;
        }

        @NonNull
        public CountryPickerSheetDialog build() {
            final CountryPickerSheetDialog dialog = new CountryPickerSheetDialog(context);
            if (countries != null) {
                dialog.setItems(countries);
            }
            if (flagDisplay != null) {
                dialog.setFlagDisplay(flagDisplay);
            }
            if (nameDisplay != null) {
                dialog.setNameDisplay(nameDisplay);
            }
            if (selectedListener != null) {
                dialog.setOnSelectedListener(selectedListener);
            }
            return dialog;
        }

        @NonNull
        public CountryPickerSheetDialog show() {
            final CountryPickerSheetDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }
}
