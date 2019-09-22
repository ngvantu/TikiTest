package tunguyen.tikitest.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tunguyen.tikitest.R;
import tunguyen.tikitest.data.LoadData;
import tunguyen.tikitest.fragments.HotKeywords;
import tunguyen.tikitest.interfaces.LoadDataListener;

public class MainActivity extends AppCompatActivity {

    private List<String> hotKeywords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadHotKeywords();
    }

    private void loadHotKeywords() {
        LoadData loadData = new LoadData(this, new LoadDataListener() {
            @Override
            public void onLoadDataSuccess(List<String> hotKeywords) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(HotKeywords.LIST_KEYWORDS, (ArrayList<String>)hotKeywords);

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                Fragment hkwFrag = new HotKeywords();
                hkwFrag.setArguments(bundle);
                transaction.replace(R.id.frag_hot_keywords, hkwFrag);
                transaction.commit();
            }
        });

        loadData.execute();
    }
}
