package com.hendraanggrian.appcompat.countrydialog;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.SearchBar;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.Window.FEATURE_NO_TITLE;

public class CountryDialog extends AppCompatDialog implements TextWatcher,
        SearchView.OnQueryTextListener, View.OnClickListener {

    private final CountryAdapter adapter;

    private SearchBar searchBar;
    private ImageButton locateButton;
    private RecyclerView recyclerView;

    public CountryDialog(@NonNull Context context) {
        this(context, true, false);
    }

    public CountryDialog(@NonNull Context context, boolean isShowFlag, boolean isShowDialCode) {
        this(context, Arrays.asList(Country.values()), isShowFlag, isShowDialCode);
    }

    public CountryDialog(@NonNull Context context, @NonNull List<Country> countries, boolean isShowFlag, boolean isShowDialCode) {
        super(context);
        supportRequestWindowFeature(FEATURE_NO_TITLE);
        adapter = new CountryAdapter(context, countries, isShowFlag, isShowDialCode);
    }

    public CountryDialog(@NonNull Context context, @StyleRes int theme) {
        this(context, theme, true, false);
    }

    public CountryDialog(@NonNull Context context, @StyleRes int theme, boolean isShowFlag, boolean isShowDialCode) {
        this(context, theme, Arrays.asList(Country.values()), isShowFlag, isShowDialCode);
    }

    public CountryDialog(@NonNull Context context, @StyleRes int theme, @NonNull List<Country> countries, boolean isShowFlag, boolean isShowDialCode) {
        super(context, theme);
        supportRequestWindowFeature(FEATURE_NO_TITLE);
        adapter = new CountryAdapter(context, countries, isShowFlag, isShowDialCode);
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
        TelephonyManager tm = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String iso2 = tm.getSimCountryIso();
        final Country country = Country.fromISO2(getContext(), iso2);
        if (country != null) {
            adapter.listener.onSelected(country);
        } else {
            Toast.makeText(getContext(), "Unable to locate.", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    public CountryAdapter getAdapter() {
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
        private final List<Country> countries = new ArrayList<>(Arrays.asList(Country.values())); // ensure collection is mutable
        private boolean isShowFlag = true;
        private boolean isShowDialCode = false;

        @Nullable
        private OnSelectedListener listener;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        @NonNull
        public Builder setItems(@NonNull Collection<Country> countries) {
            this.countries.clear();
            this.countries.addAll(countries);
            return this;
        }

        @NonNull
        public Builder exclude(@NonNull Country... countries) {
            if (countries.length > 0) {
                this.countries.removeAll(Arrays.asList(countries));
            }
            return this;
        }

        @NonNull
        public Builder setShowFlag(boolean isShowFlag) {
            this.isShowFlag = isShowFlag;
            return this;
        }

        @NonNull
        public Builder setShowDialCode(boolean isShowDialCode) {
            this.isShowDialCode = isShowDialCode;
            return this;
        }

        @NonNull
        public Builder setOnSelectedListener(@Nullable OnSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        @NonNull
        public CountryDialog build() {
            CountryDialog dialog = new CountryDialog(context, countries, isShowFlag, isShowDialCode);
            if (listener != null) {
                dialog.setOnSelectedListener(listener);
            }
            return dialog;
        }

        @NonNull
        public CountryDialog show() {
            CountryDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }
}