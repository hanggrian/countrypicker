package com.hendraanggrian.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.hendraanggrian.appcompat.countrypicker.Country;
import com.hendraanggrian.appcompat.countrypicker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Root layout of country selection view.
 */
public class CountryPicker extends LinearLayoutCompat implements MenuItem.OnMenuItemClickListener,
        SearchView.OnQueryTextListener {

    public static final boolean DEFAULT_FLAG_SHOWN = true;
    public static final boolean DEFAULT_DIAL_SHOWN = false;

    private final CountryPickerAdapter adapter;

    private final Toolbar toolbar;
    private final SearchBar searchBar;
    private final RecyclerView recyclerView;

    private final MenuItem dialItem;

    public CountryPicker(Context context) {
        this(context, null);
    }

    public CountryPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountryPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        adapter = new CountryPickerAdapter(context);

        LayoutInflater.from(context).inflate(R.layout.countrypicker, this, true);

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
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        dialItem.setChecked(!dialItem.isChecked());
        recyclerView.getRecycledViewPool().clear();
        adapter.setDialShown(dialItem.isChecked());
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

    @NonNull
    public CountryPickerAdapter getAdapter() {
        return adapter;
    }

    @NonNull
    public Toolbar getToolbar() {
        return toolbar;
    }

    @NonNull
    public SearchBar getSearchBar() {
        return searchBar;
    }

    @NonNull
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setItems(@NonNull List<Country> countries) {
        recyclerView.getRecycledViewPool().clear();
        adapter.setItems(countries);
    }

    public void setShowFlag(boolean shown) {
        recyclerView.getRecycledViewPool().clear();
        adapter.setFlagShown(shown);
    }

    private static int getColorAttr(Context context, int attrId) {
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, typedValue, true);
        return typedValue.data;
    }

    public interface OnSelectedListener {

        void onSelected(@NonNull Country country);
    }
}