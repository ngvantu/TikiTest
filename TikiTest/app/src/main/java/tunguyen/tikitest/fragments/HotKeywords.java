package tunguyen.tikitest.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import tunguyen.tikitest.HorizontalSpacingItemDecoration;
import tunguyen.tikitest.R;
import tunguyen.tikitest.adapters.HotKeywordsAdapter;

public class HotKeywords extends Fragment{
    private static String TAG = "HotKeywords";
    public static String LIST_KEYWORDS = "LIST_KEYWORDS";

    private List<String> hotKeywords;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotkeywords, container, false);
        final RecyclerView rcvHotKeywords = view.findViewById(R.id.rcv_hot_keywords);
        rcvHotKeywords.setHasFixedSize(true);

        if (getArguments() != null){
            hotKeywords = getArguments().getStringArrayList(HotKeywords.LIST_KEYWORDS);
        }

        final HotKeywordsAdapter hkwAdapter = new HotKeywordsAdapter(container.getContext(), hotKeywords, new HotKeywordsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(getContext(), hotKeywords.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        rcvHotKeywords.setAdapter(hkwAdapter);
        rcvHotKeywords.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rcvHotKeywords.addItemDecoration(new HorizontalSpacingItemDecoration(20));

        autoSmoothScroll(rcvHotKeywords, hkwAdapter);

        return view;
    }

    private void autoSmoothScroll(final RecyclerView rcv, final RecyclerView.Adapter adapter) {
        final int speedScroll = 1500;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            int itemCount = adapter.getItemCount();
            boolean flag = true; // auto scroll from right to left
            @Override
            public void run() {
                if (count < itemCount){
                    if (count == itemCount - 1){
                        flag = false; // reverse from right to left
                    } else if (count == 0){
                        flag = true;
                    }

                    // scroll 2 item/time
                    if (flag)
                        count = (count + 2 > itemCount - 1) ? count + 1 : count + 2;
                    else count = (count - 2 < 0) ? 0 : count - 2;

                    rcv.smoothScrollToPosition(count);
                    handler.postDelayed(this, speedScroll);
                }
            }
        };

        handler.postDelayed(runnable, speedScroll);
    }
}
