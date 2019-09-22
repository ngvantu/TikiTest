package tunguyen.tikitest.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

import tunguyen.tikitest.interfaces.LoadDataListener;

public class LoadData {
    private static final String URL_DATA = "https://raw.githubusercontent.com/tikivn/android-home-test/v2/keywords.json";

    private Context context;
    private LoadDataListener loadDataListener;

    public LoadData(Context context, LoadDataListener loadDataListener) {
        this.context = context;
        this.loadDataListener = loadDataListener;
    }

    public void execute(){
        new DownloadRawData().execute(URL_DATA);
    }

    @SuppressLint("StaticFieldLeak")
    private class DownloadRawData extends AsyncTask<Object, String, String> {

        @Override
        protected String doInBackground(Object... objects) {
            String link = (String) objects[0];

            try {
                URL url = new URL(link);
                InputStream is = url.openConnection().getInputStream();
                StringBuilder buffer = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                return buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String jsonString){
            try {
                Log.d("DataParser","jsonobject ="+jsonString);

                List<String> hotKeywords = new ArrayList<>();
                JSONArray itemArray= new JSONArray(jsonString);
                for (int i = 0; i < itemArray.length(); i++) {
                    hotKeywords.add(itemArray.getString(i));
                }
                loadDataListener.onLoadDataSuccess(hotKeywords);
            } catch (JSONException e) {
                Toast.makeText(context.getApplicationContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
