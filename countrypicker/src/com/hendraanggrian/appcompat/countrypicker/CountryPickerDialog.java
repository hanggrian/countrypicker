package com.hendraanggrian.appcompat.countrypicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.SearchBar;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.TELEPHONY_SERVICE;
import static android.view.Window.FEATURE_NO_TITLE;

public class CountryPickerDialog extends AppCompatDialog implements TextWatcher,
        SearchView.OnQueryTextListener, View.OnClickListener {

    static final boolean DEFAULT_SHOW_FLAG = true;
    static final boolean DEFAULT_SHOW_DIAL = false;

    private CountryPickerAdapter adapter;

    private SearchBar searchBar;
    private ImageButton locateButton;
    private RecyclerView recyclerView;

    public CountryPickerDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryPickerDialog(@NonNull Context context, int theme) {
        super(context, theme);
        supportRequestWindowFeature(FEATURE_NO_TITLE);
        adapter = new CountryPickerAdapter(context);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countrydialog_content);

        searchBar = findViewById(R.id.searchBar);
        assert searchBar != null;
        locateButton = findViewById(android.R.id.button1);
        assert locateButton != null;
        recyclerView = findViewById(android.R.id.list);
        assert recyclerView != null;

        searchBar.getEditText().addTextChangedListener(this);
        searchBar.setOnQueryTextListener(this);

        locateButton.setOnClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        locateButton.setVisibility(TextUtils.isEmpty(charSequence) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.getFilter().filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public void onClick(View view) {
        TelephonyManager manager = (TelephonyManager) getContext().getSystemService(TELEPHONY_SERVICE);
        if (manager == null) {
            Toast.makeText(getContext(), "Unsupported", Toast.LENGTH_SHORT).show();
        } else {
            String iso2 = manager.getSimCountryIso();
            final Country country = Country.fromISO2(getContext(), iso2);
            if (country != null) {
                adapter.listener.onSelected(country);
            } else {
                Toast.makeText(getContext(), "Unable to locate.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setShowFlag(boolean isShowFlag) {
        adapter.setShowFlag(isShowFlag);
    }

    public void setShowDial(boolean isShowDial) {
        adapter.setShowDial(isShowDial);
    }

    @NonNull
    public CountryPickerAdapter getAdapter() {
        return adapter;
    }

    @NonNull
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setOnSelectedListener(@Nullable final OnSelectedListener listener) {
        adapter.listener = listener == null ? null : new OnSelectedListener() {
            @Override
            public void onSelected(@NonNull Country country) {
                listener.onSelected(country);
                dismiss();
            }
        };
    }

    public interface OnSelectedListener {

        void onSelected(@NonNull Country country);
    }

    public static class Builder {
        private final Context context;
        private boolean isShowFlag = DEFAULT_SHOW_FLAG;
        private boolean isShowDial = DEFAULT_SHOW_DIAL;
        @Nullable private OnSelectedListener listener;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        @NonNull
        public Builder setItems(@NonNull Collection<Country> countries) {
            // this.countries.clear();
            // this.countries.addAll(countries);
            return this;
        }

        @NonNull
        public Builder exclude(@NonNull Country... countries) {
            if (countries.length > 0) {
                // this.countries.removeAll(Arrays.asList(countries));
            }
            return this;
        }

        @NonNull
        public Builder setShowFlag(boolean isShowFlag) {
            this.isShowFlag = isShowFlag;
            return this;
        }

        @NonNull
        public Builder setShowDial(boolean isShowDialCode) {
            this.isShowDial = isShowDialCode;
            return this;
        }

        @NonNull
        public Builder setOnSelectedListener(@Nullable OnSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        @NonNull
        public CountryPickerDialog build() {
            CountryPickerDialog dialog = new CountryPickerDialog(context);
            if (isShowFlag != DEFAULT_SHOW_FLAG) {
                dialog.setShowFlag(isShowFlag);
            }
            if (isShowDial != DEFAULT_SHOW_DIAL) {
                dialog.setShowDial(isShowDial);
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