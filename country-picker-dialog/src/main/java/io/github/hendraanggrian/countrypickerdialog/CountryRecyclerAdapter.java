package io.github.hendraanggrian.countrypickerdialog;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.hendraanggrian.countrypicker.R;
import io.github.hendraanggrian.countrypickerdialog.internal.FastScrollRecyclerView;

/**
 * Created by hendraanggrian on 19/10/2016.
 */
class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.ViewHolder> implements FastScrollRecyclerView.SectionedAdapter {

    private final List<Country> countries;
    private final boolean showCountryCode;
    private final CountryPickerDialog.OnPickedListener listener;
    private final DialogInterface dialog;

    CountryRecyclerAdapter(List<Country> countries, boolean showCountryCode, CountryPickerDialog.OnPickedListener listener, DialogInterface dialog) {
        this.countries = countries;
        this.showCountryCode = showCountryCode;
        this.listener = listener;
        this.dialog = dialog;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_country, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPicked(countries.get(holder.getAdapterPosition()));
                dialog.dismiss();
            }
        });
        holder.imageView.setImageResource(countries.get(position).getFlagRes());
        holder.textView.setText(showCountryCode
                ? String.format("%s (%s)", countries.get(position).getName(), countries.get(position).getCallingCode())
                : String.format("%s", countries.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return countries.get(position).getName().substring(0, 1).toUpperCase();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewGroup container;
        private final ImageView imageView;
        private final TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            container = (ViewGroup) itemView.findViewById(R.id.container);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}