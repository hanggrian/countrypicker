package com.hendraanggrian.appcompat.countrypicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.SearchBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.Window.FEATURE_NO_TITLE;

public class CountryPickerDialog extends AppCompatDialog
        implements MenuItem.OnMenuItemClickListener, SearchView.OnQueryTextListener {

    static final boolean DEFAULT_SHOW_FLAG = true;
    static final boolean DEFAULT_SHOW_DIAL = false;

    private CountryPickerAdapter adapter;

    private Toolbar toolbar;
    private SearchBar searchBar;
    private RecyclerView recyclerView;
    private MenuItem dialItem;

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
        toolbar = findViewById(R.id.toolbar);
        searchBar = findViewById(R.id.searchBar);
        recyclerView = findViewById(R.id.recyclerView);

        toolbar.inflateMenu(R.menu.countrydialog_menu);
        final Drawable tintedOverflow = DrawableCompat.wrap(toolbar.getOverflowIcon());
        DrawableCompat.setTint(tintedOverflow.mutate(), getColorAttr(getContext(), R.attr.colorAccent));
        toolbar.setOverflowIcon(tintedOverflow);

        dialItem = toolbar.getMenu().getItem(0);
        dialItem.setOnMenuItemClickListener(this);

        searchBar.setOnQueryTextListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        dialItem.setChecked(!dialItem.isChecked());
        recyclerView.getRecycledViewPool().clear();
        adapter.setShowDial(dialItem.isChecked());
        return false;
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

    public void setItems(@NonNull List<Country> countries) {
        recyclerView.getRecycledViewPool().clear();
        adapter.setItems(countries);
    }

    public void setShowFlag(boolean isShowFlag) {
        recyclerView.getRecycledViewPool().clear();
        adapter.setShowFlag(isShowFlag);
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

    private static int getColorAttr(Context context, int attrId) {
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, typedValue, true);
        return typedValue.data;
    }

    public interface OnSelectedListener {

        void onSelected(@NonNull Country country);
    }

    public static class Builder {
        private final Context context;
        @Nullable private List<Country> countries;
        private boolean isShowFlag = DEFAULT_SHOW_FLAG;
        @Nullable private OnSelectedListener listener;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        @NonNull
        public Builder setItems(@NonNull List<Country> countries) {
            this.countries = countries;
            return this;
        }

        @NonNull
        public Builder setShowFlag(boolean isShowFlag) {
            this.isShowFlag = isShowFlag;
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
            if (countries != null) {
                dialog.setItems(countries);
            }
            if (isShowFlag != DEFAULT_SHOW_FLAG) {
                dialog.setShowFlag(isShowFlag);
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