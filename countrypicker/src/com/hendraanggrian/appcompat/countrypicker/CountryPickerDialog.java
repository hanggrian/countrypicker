package com.hendraanggrian.appcompat.countrypicker;

import android.content.Context;
import android.content.DialogInterface;
import android.telephony.TelephonyManager;
import android.view.Window;

import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class CountryPickerDialog extends AlertDialog implements CountryPickerContainer {

    private final CountryPickerContainer container;

    public CountryPickerDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerDialog(@NonNull final Context context, int theme) {
        super(context, theme);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        container = new CountryPickerContainerImpl(this, context);
        setView(container.getPicker());

        final Country country = Country.fromIso(context,
                ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                        .getNetworkCountryIso());
        if (country != null) {
            setButton(DialogInterface.BUTTON_POSITIVE,
                    getContext().getString(R.string.use_default, country.getName(context)),
                    new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final CountryPicker.OnSelectedListener listener = container
                                    .getPicker().getAdapter().getListener();
                            if (listener != null) {
                                listener.onSelected(country);
                            }
                        }
                    });
        }
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