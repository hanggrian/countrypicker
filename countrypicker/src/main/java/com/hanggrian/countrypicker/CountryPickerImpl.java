package com.hanggrian.countrypicker;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

class CountryPickerImpl implements CountryPicker {
    private final Dialog dialog;
    private final CountryPickerLayout picker;

    CountryPickerImpl(Dialog dialog, Context context) {
        this.dialog = dialog;
        this.picker = new CountryPickerLayout(context);
    }

    @NonNull
    @Override
    public CountryPickerLayout getLayout() {
        return picker;
    }

    @NonNull
    @Override
    public List<Country> getItems() {
        return picker.getAdapter().countries;
    }

    @Override
    public void setItems(@NonNull List<Country> countries) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        picker.getAdapter().setItems(countries);
    }

    @Override
    public void setItems(@NonNull Country... countries) {
        setItems(Arrays.asList(countries));
    }

    @NonNull
    @Override
    public FlagDisplay getFlagDisplay() {
        return picker.getAdapter().flagDisplay;
    }

    @Override
    public void setFlagDisplay(@NonNull FlagDisplay display) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        picker.getAdapter().setFlagDisplay(display);
    }

    @NonNull
    @Override
    public NameDisplay getNameDisplay() {
        return picker.getAdapter().nameDisplay;
    }

    @Override
    public void setNameDisplay(@NonNull NameDisplay display) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        picker.getAdapter().setNameDisplay(display);
    }

    @Override
    public void setOnSelectedListener(
        @Nullable final CountryPickerLayout.OnSelectedListener listener) {
        picker.getAdapter().setListener(listener == null ? null : country -> {
            listener.onSelected(country);
            dialog.dismiss();
        });
    }
}
