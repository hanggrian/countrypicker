package com.hanggrian.countrypicker;

import android.content.Context;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import java.util.Arrays;
import java.util.List;

public class CountryPickerDialog extends AppCompatDialog implements CountryPicker {
    private final CountryPicker picker;

    public CountryPickerDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerDialog(@NonNull final Context context, int theme) {
        super(context, theme);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        picker = new CountryPickerImpl(this, context);
        setContentView(picker.getLayout());
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
        public CountryPickerDialog build() {
            final CountryPickerDialog dialog = new CountryPickerDialog(context);
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
        public CountryPickerDialog show() {
            final CountryPickerDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }
}
