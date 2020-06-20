package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ManagerListSold extends AppCompatActivity {

    String serverURL = "http://13.125.45.205/orderlistall1.php";
    private static String IP_ADDRESS = "13.125.45.205";
    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_OID = "oid";
    private static final String TAG_TDATE = "tdate";
    private static final String TAG_MENU = "menu";
    private static final String TAG_UID ="uid";
    private static final String TAG_PRICE ="price";
    private static final String TAG_ADDR ="addr";
    private static final String TAG_PHONE ="phone";
    private static final String TAG_DELCON ="delcon";

    private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList;
    String mJsonString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_list);
        setTitle("한신이닭");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        mTextViewResult = (TextView) findViewById(R.id.textView_main_result);
        mListViewList = (ListView) findViewById(R.id.listView_main_list);

        //mArrayList.clear();

        GetData task = new GetData();
        task.execute("*");

        mArrayList = new ArrayList<>();
    }

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ManagerListSold.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String searchKeyword1 = params[0];
            String postParameters = "uid=" + searchKeyword1;

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "Data: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                /*    private static final String TAG_OID = "oid";
                      private static final String TAG_TDATE = "tdate";
                      private static final String TAG_MENU = "menu";
                      private static final String TAG_UID ="uid";
                      private static final String TAG_PRICE ="price";
                      private static final String TAG_ADDR ="addr";
                      private static final String TAG_PHONE ="phone";
                      private static final String TAG_DELCON ="delcon";*/

                String oid = item.getString(TAG_OID);
                String tdate = item.getString(TAG_TDATE);
                String menu = item.getString(TAG_MENU);
                String uid = item.getString(TAG_UID);
                String price = item.getString(TAG_PRICE);
                String addr = item.getString(TAG_ADDR);
                String phone = item.getString(TAG_PHONE);
                String delcon = item.getString(TAG_DELCON);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_OID, oid);
                hashMap.put(TAG_TDATE, tdate);
                hashMap.put(TAG_MENU, menu);
                hashMap.put(TAG_UID, uid);
                hashMap.put(TAG_PRICE, price);
                hashMap.put(TAG_ADDR, addr);
                hashMap.put(TAG_PHONE, phone);
                hashMap.put(TAG_DELCON, delcon);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    ManagerListSold.this, mArrayList, R.layout.item_list2,
                    new String[]{TAG_MENU, TAG_UID, TAG_PRICE, TAG_ADDR, TAG_TDATE},
                    new int[]{R.id.textView1, R.id.textView5, R.id.textView4, R.id.textView2, R.id.textView3}
            );

            mListViewList.setAdapter(adapter);


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), HomeManager.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}

