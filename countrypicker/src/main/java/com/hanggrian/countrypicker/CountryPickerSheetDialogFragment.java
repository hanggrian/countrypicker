package com.hanggrian.countrypicker;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.Objects;

public class CountryPickerSheetDialogFragment extends BottomSheetDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new CountryPickerSheetDialog(
            Objects.requireNonNull(getContext(), "Fragment is not yet attached"),
            getTheme()
        );
    }
}
