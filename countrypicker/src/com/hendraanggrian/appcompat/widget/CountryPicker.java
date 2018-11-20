package com.hendraanggrian.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.hendraanggrian.appcompat.countrypicker.Country;
import com.hendraanggrian.appcompat.countrypicker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Root layout of country selection view, may be used independently.
 */
public class CountryPicker extends LinearLayoutCompat {

    public static final boolean DEFAULT_FLAG_SHOWN = true;
    public static final boolean DEFAULT_DIAL_SHOWN = false;

    private final CountryPickerAdapter adapter;

    private final CardView cardView;
    private final Toolbar toolbar;
    private final SearchBar searchBar;
    private final RecyclerView recyclerView;

    private final MenuItem locateItem;
    private final MenuItem dialItem;

    @SuppressWarnings("FieldCanBeLocal")
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            locateItem.setVisible(count == 0);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @SuppressWarnings("FieldCanBeLocal")
    private final SearchView.OnQueryTextListener queryListener = new SearchView.OnQueryTextListener() {
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
    };

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

        cardView = findViewById(R.id.cardView);
        toolbar = findViewById(R.id.toolbar);
        searchBar = findViewById(R.id.searchBar);
        recyclerView = findViewById(R.id.recyclerView);

        if (Build.VERSION.SDK_INT >= 21) {
            cardView.setCardElevation(8);
        }

        toolbar.inflateMenu(R.menu.countrypicker_menu);
        final Menu menu = toolbar.getMenu();
        final Country country = Country.getDefault(context);
        locateItem = menu.findItem(R.id.locateItem)
            .setVisible(country != null)
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    final CountryPicker.OnSelectedListener listener = getAdapter().getListener();
                    if (listener != null) {
                        //noinspection ConstantConditions
                        listener.onSelected(country);
                    }
                    return false;
                }
            });
        dialItem = menu.findItem(R.id.dialItem)
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    item.setChecked(!item.isChecked());
                    recyclerView.getRecycledViewPool().clear();
                    adapter.setDialShown(item.isChecked());
                    return false;
                }
            });

        final Drawable tintedIcon = DrawableCompat.wrap(locateItem.getIcon());
        DrawableCompat.setTint(tintedIcon.mutate(), getColorAttr(getContext(), R.attr.colorAccent));
        locateItem.setIcon(tintedIcon);

        searchBar.getInput().addTextChangedListener(textWatcher);
        searchBar.setOnQueryTextListener(queryListener);

        recyclerView.setAdapter(adapter);
    }

    @NonNull
    public CountryPickerAdapter getAdapter() {
        //noinspection ConstantConditions
        return (CountryPickerAdapter) recyclerView.getAdapter();
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