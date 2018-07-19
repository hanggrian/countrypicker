package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.hendraanggrian.appcompat.countrydialog.R;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

public final class SearchView2 extends SearchView {

    public SearchView2(Context context) {
        super(context, null);
    }

    public SearchView2(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.searchViewStyle);
    }

    public SearchView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewCompat.setBackground(mSearchSrcTextView, null);
    }

    @NonNull
    public EditText getEditText() {
        return mSearchSrcTextView;
    }
}