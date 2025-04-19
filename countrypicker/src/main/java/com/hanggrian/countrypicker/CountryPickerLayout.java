package com.hanggrian.countrypicker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.CountrySearchView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;

/** Root layout of country selection view, may be used independently. */
public class CountryPickerLayout extends LinearLayoutCompat {
    private final CountryPickerAdapter adapter;

    private final CardView cardView;
    private final Toolbar toolbar;
    private final CountrySearchView searchView;
    private final RecyclerView recyclerView;
    private final MenuItem locateItem;

    private final TextWatcher textWatcher =
        new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                locateItem.setVisible(count == 0);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

    private final SearchView.OnQueryTextListener queryListener =
        new SearchView.OnQueryTextListener() {
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

    public CountryPickerLayout(Context context) {
        this(context, null);
    }

    public CountryPickerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountryPickerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        adapter = new CountryPickerAdapter(context);

        LayoutInflater.from(context).inflate(R.layout.countrypicker, this, true);
        cardView = findViewById(R.id.cardView);
        toolbar = findViewById(R.id.toolbar);
        searchView = findViewById(R.id.searchBar);
        recyclerView = findViewById(R.id.recyclerView);

        cardView.setCardElevation(8);

        toolbar.inflateMenu(R.menu.countrypicker_menu);
        final Menu menu = toolbar.getMenu();
        final Country country = Country.getDefault(context);
        locateItem = menu.findItem(R.id.countrypicker_locate)
            .setVisible(country != null)
            .setOnMenuItemClickListener(item -> {
                final OnSelectedListener listener = getAdapter().selectedListener;
                if (listener != null) {
                    listener.onSelected(Objects.requireNonNull(country));
                }
                return false;
            });
        // must set background after menu inflation
        toolbar.setBackgroundColor(
            getColorAttr(context, androidx.appcompat.R.attr.colorControlHighlight)
        );

        Drawable tintedIcon = locateItem.getIcon();
        if (tintedIcon != null) {
            DrawableCompat.wrap(tintedIcon);
            DrawableCompat.setTint(
                tintedIcon.mutate(),
                getColorAttr(getContext(), androidx.appcompat.R.attr.colorPrimary)
            );
            locateItem.setIcon(tintedIcon);
        }

        searchView.getInput().addTextChangedListener(textWatcher);
        searchView.setOnQueryTextListener(queryListener);

        recyclerView.setAdapter(adapter);
    }

    @NonNull
    public CountryPickerAdapter getAdapter() {
        return (CountryPickerAdapter) Objects.requireNonNull(recyclerView.getAdapter());
    }

    @NonNull
    public Toolbar getToolbar() {
        return toolbar;
    }

    @NonNull
    public CountrySearchView getSearchBar() {
        return searchView;
    }

    @NonNull
    public RecyclerView getRecyclerView() {
        return recyclerView;
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
