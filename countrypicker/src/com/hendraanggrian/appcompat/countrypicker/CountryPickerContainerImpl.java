package com.hendraanggrian.appcompat.countrypicker;

import android.app.Dialog;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class CountryPickerContainerImpl implements CountryPickerContainer {

    private final Dialog dialog;
    private final CountryPicker picker;

    CountryPickerContainerImpl(Dialog dialog, Context context) {
        this.dialog = dialog;
        this.picker = new CountryPicker(context);
    }

    @NonNull
    @Override
    public CountryPicker getPicker() {
        return picker;
    }

    @Override
    public void setItems(@NonNull List<Country> countries) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        picker.getAdapter().setItems(countries);
    }

    @Override
    public void setFlagShown(boolean shown) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        picker.getAdapter().setFlagShown(shown);
    }

    @Override
    public void setOnSelectedListener(@Nullable final CountryPicker.OnSelectedListener listener) {
        picker.getAdapter()
            .setListener(listener == null ? null : new CountryPicker.OnSelectedListener() {
                @Override
                public void onSelected(@NonNull Country country) {
                    listener.onSelected(country);
                    dialog.dismiss();
                }
            });
    }

    @Nullable
    @Override
    public Runnable defaultRunnable() {
        final TelephonyManager tm = (TelephonyManager)
            picker.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            return null;
        }
        final Country country = Country.fromIso(picker.getContext(), tm.getNetworkCountryIso());
        if (country == null) {
            return null;
        }
        return new Runnable() {
            @Override
            public void run() {
                final CountryPicker.OnSelectedListener listener = picker.getAdapter().getListener();
                if (listener != null) {
                    listener.onSelected(country);
                }
            }
        };
    }
}