package tunguyen.tikitest.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tunguyen.tikitest.R;
import tunguyen.tikitest.WrapText;

public class HotKeywordsAdapter extends RecyclerView.Adapter<HotKeywordsAdapter.ViewHolder>{

    // constructor
    private Context context;
    private List<String> hotKeywords;
    private int[] arrColors;
    private OnItemClickListener onItemClickListener;

    public HotKeywordsAdapter(Context context, List<String> hotKeywords, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.hotKeywords = hotKeywords;
        this.onItemClickListener = onItemClickListener;

        this.arrColors = context.getResources().getIntArray(R.array.hotkeywords_colors);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hot_keywords, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.keyword.setText(WrapText.to2Lines(hotKeywords.get(i)));

        // prepare
        int roundRadius = 15; // 15px
        int solidColor = arrColors[i % arrColors.length];

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(solidColor);
        gd.setCornerRadius(roundRadius);

        viewHolder.keyword.setBackground(gd);
    }

    @Override
    public int getItemCount() {
        return hotKeywords.size();
    }

    // interface OnItemClickListener
    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    // ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView keyword;

        ViewHolder(View itemView) {
            super(itemView);
            keyword = itemView.findViewById(R.id.keyword);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(getAdapterPosition());
                }
            });
        }
    }
}
