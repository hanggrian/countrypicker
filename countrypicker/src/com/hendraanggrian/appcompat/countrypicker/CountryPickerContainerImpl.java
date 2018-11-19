package com.hendraanggrian.appcompat.countrypicker;

import android.app.Dialog;
import android.content.Context;

import com.hendraanggrian.appcompat.widget.CountryPicker;
import com.hendraanggrian.appcompat.widget.CountryPickerAdapter;

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
        getAdapter().setItems(countries);
    }

    @Override
    public void setFlagShown(boolean shown) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        getAdapter().setFlagShown(shown);
    }

    @Override
    public void setOnSelectedListener(@Nullable final CountryPicker.OnSelectedListener listener) {
        getAdapter().setListener(listener == null ? null : new CountryPicker.OnSelectedListener() {
            @Override
            public void onSelected(@NonNull Country country) {
                listener.onSelected(country);
                dialog.dismiss();
            }
        });
    }

    private CountryPickerAdapter getAdapter() {
        return (CountryPickerAdapter) picker.getRecyclerView().getAdapter();
    }
}