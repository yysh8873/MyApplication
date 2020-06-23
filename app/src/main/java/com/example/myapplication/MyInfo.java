package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;


public class MyInfo extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String serverURL = "http://13.125.45.205/user.php";
    private static String IP_ADDRESS = "13.125.45.205";
    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_UID = "uid";
    private static final String TAG_PW = "pw";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDR ="addr";
    private static final String TAG_PHONE ="phone";

    private static String na = null;
    private static String p = null;
    private static String add = null;
    private static String ph = null;

    private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;
    String mJsonString;
    String uid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info);
        setTitle("한신이닭 내정보");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭 내정보");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");

        Button btn_edit = (Button) findViewById(R.id.btn_edit);
        Button btn_save = (Button) findViewById(R.id.btn_save);

        TextView text_ID = (TextView) findViewById(R.id.text_ID);
        TextView text_Name = (TextView) findViewById(R.id.text_Name);
        TextView text_PW = (TextView) findViewById(R.id.text_PW);
        TextView text_Phone = (TextView) findViewById(R.id.text_Phone);
        TextView text_AD = (TextView) findViewById(R.id.text_AD);

        EditText edit_Name = (EditText) findViewById(R.id.edit_Name);
        EditText edit_PW = (EditText) findViewById(R.id.edit_PW);
        EditText edit_Phone = (EditText) findViewById(R.id.edit_Phone);
        EditText edit_AD = (EditText) findViewById(R.id.edit_AD);

        GetData task = new GetData();
        task.execute(uid);
        mArrayList = new ArrayList<>();
        text_ID.setText(uid);

        text_Name.setText(na);
        System.out.println("na =" + na);
        text_PW.setText(p);
        text_Phone.setText(ph);
        text_AD.setText(add);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg1 = new AlertDialog.Builder(MyInfo.this);
                dlg1.setTitle("회원 정보 수정");
                dlg1.setMessage("회원 정보를 수정 하시겠습니까?");
                dlg1.setIcon(R.drawable.chicken);
                dlg1.setPositiveButton("수정", yesButtonClickListener);
                dlg1.setNegativeButton("취소", null);
                dlg1.show();
            }
            private DialogInterface.OnClickListener yesButtonClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    text_Name.setVisibility(View.GONE);
                    text_PW.setVisibility(View.GONE);
                    text_Phone.setVisibility(View.GONE);
                    text_AD.setVisibility(View.GONE);
                    btn_edit.setVisibility(View.GONE);

                    edit_Name.setVisibility(View.VISIBLE);
                    edit_PW.setVisibility(View.VISIBLE);
                    edit_Phone.setVisibility(View.VISIBLE);
                    edit_AD.setVisibility(View.VISIBLE);

                    btn_save.setVisibility(View.VISIBLE);
                }
            };
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg1 = new AlertDialog.Builder(MyInfo.this);
                dlg1.setTitle("회원 정보 수정");
                dlg1.setMessage("수정한 회원 정보를 저장 하시겠습니까?");
                dlg1.setIcon(R.drawable.chicken);
                dlg1.setPositiveButton("저장", yesButtonClickListener);
                dlg1.setNegativeButton("취소", null);
                dlg1.show();
            }
            private DialogInterface.OnClickListener yesButtonClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    text_Name.setVisibility(View.VISIBLE);
                    text_PW.setVisibility(View.VISIBLE);
                    text_Phone.setVisibility(View.VISIBLE);
                    text_AD.setVisibility(View.VISIBLE);
                    btn_edit.setVisibility(View.VISIBLE);

                    edit_Name.setVisibility(View.GONE);
                    edit_PW.setVisibility(View.GONE);
                    edit_Phone.setVisibility(View.GONE);
                    edit_AD.setVisibility(View.GONE);

                    btn_save.setVisibility(View.GONE);
                }
            };
        });
    }

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MyInfo.this,
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

                /*  private static final String TAG_JSON="webnautes";
                    private static final String TAG_UID = "uid";
                    private static final String TAG_PW = "pw";
                    private static final String TAG_NAME = "name";
                    private static final String TAG_ADDR ="addr";
                    private static final String TAG_PHONE ="phone";*/

                String uid = item.getString(TAG_UID);
                String pw = item.getString(TAG_PW);
                String name = item.getString(TAG_NAME);
                String addr = item.getString(TAG_ADDR);
                String phone = item.getString(TAG_PHONE);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_UID, uid);
                hashMap.put(TAG_PW, pw);
                hashMap.put(TAG_NAME, name);
                hashMap.put(TAG_ADDR, addr);
                hashMap.put(TAG_PHONE, phone);

                mArrayList.add(hashMap);

                na = mArrayList.get(0).get(TAG_NAME);
                System.out.println(na);
                p = mArrayList.get(0).get(TAG_PW);
                System.out.println(p);
                add = mArrayList.get(0).get(TAG_ADDR);
                System.out.println(add);
                ph = mArrayList.get(0).get(TAG_PHONE);
                System.out.println(ph);

            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}