package tunguyen.tikitest;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HorizontalSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private final int spacing;

    public HorizontalSpacingItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildViewHolder(view).getAdapterPosition();
        int itemCount = state.getItemCount();

        outRect.right = (position < itemCount - 1) ? spacing : 0;
    }
}
