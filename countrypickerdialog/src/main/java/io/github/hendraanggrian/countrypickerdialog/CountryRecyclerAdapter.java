package io.github.hendraanggrian.countrypickerdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.List;
import java.util.Locale;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
final class CountryRecyclerAdapter extends FastScrollRecyclerView.Adapter<CountryRecyclerAdapter.ViewHolder> implements FastScrollRecyclerView.SectionedAdapter {

    @NonNull private final Context context;
    @NonNull private final List<Country> countries;
    private final boolean showDialCode;
    @Nullable private final CountryPickerDialog.OnPickedListener listener;
    @NonNull private final DialogInterface dialog;

    CountryRecyclerAdapter(@NonNull Context context, @Nullable String[] exclude, boolean showDialCode, @Nullable CountryPickerDialog.OnPickedListener listener, @NonNull DialogInterface dialog) {
        this.context = context;
        this.countries = Country.listAll(context, exclude);
        this.showDialCode = showDialCode;
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
                if (listener != null)
                    listener.onPicked(countries.get(holder.getAdapterPosition()));
                dialog.dismiss();
            }
        });
        holder.imageView.setImageResource(countries.get(position).getFlagRes(context));
        holder.textView.setText(showDialCode
                ? String.format("%s (%s)", countries.get(position).getName(context), countries.get(position).getDialCode())
                : String.format("%s", countries.get(position).getName(context)));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return countries.get(position).getName(context).substring(0, 1).toUpperCase(Locale.ENGLISH);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewGroup container;
        private final ImageView imageView;
        private final TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            container = (ViewGroup) itemView.findViewById(R.id.container_country);
            imageView = (ImageView) itemView.findViewById(R.id.imageview_country_flag);
            textView = (TextView) itemView.findViewById(R.id.textview_country_name);
        }
    }
}