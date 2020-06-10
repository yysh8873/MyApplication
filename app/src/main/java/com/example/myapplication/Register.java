package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;


public class Register extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_LOCATION = 10;
    private WebView webView;
    private TextView txt_address;
    private Handler handler;

    private static String IP_ADDRESS = "13.125.45.205";
    private static String TAG = "MyApplication";

    private EditText mEditTextId;
    private EditText mEditTextPw;
    private EditText mEditTextName;
    private EditText mEditTextPhone;
    private EditText mEditTextAddr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setTitle("한신이닭 회원가입");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        mEditTextId = (EditText) findViewById(R.id.edit_ID);
        mEditTextPw = (EditText) findViewById(R.id.edit_PW);
        mEditTextName = (EditText) findViewById(R.id.edit_Name);
        mEditTextPhone = (EditText) findViewById(R.id.edit_Phone);
        mEditTextAddr = (EditText) findViewById(R.id.edit_AD);

        Button btn_register = (Button) findViewById(R.id.btn_register);
/*
        final EditText edit_ID = (EditText) findViewById(R.id.edit_ID);
        String str_ID = edit_ID.getText().toString();

        final EditText edit_PW = (EditText) findViewById(R.id.edit_PW);
        String str_PW = edit_PW.getText().toString();

        final EditText edit_Name = (EditText) findViewById(R.id.edit_Name);
        String str_Name = edit_Name.getText().toString();

        final EditText edit_Phone = (EditText) findViewById(R.id.edit_Phone);
        String str_Phone = edit_Phone.getText().toString();
        // 연락처 입력시 하이픈(-) 자동 입력.
        edit_Phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        final EditText edit_AD = (EditText) findViewById(R.id.edit_AD);
 */

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_ID = mEditTextId.getText().toString();
                String str_PW = mEditTextPw.getText().toString();
                String str_Name = mEditTextName.getText().toString();
                String str_Phone = mEditTextPhone.getText().toString();
                String str_Adr = mEditTextAddr.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                InsertData task = new InsertData();

                task.execute("http://"+IP_ADDRESS+"/register.php",str_ID, str_PW,str_Name,str_Phone,str_Adr);
                mEditTextId.setText("");
                mEditTextPw.setText("");
                mEditTextName.setText("");
                mEditTextPhone.setText("");
                mEditTextAddr.setText("");
            }
        });
    }


    class InsertData extends AsyncTask<String, Void, String>{
        /*
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Register.this,
                    "Please Wait", null, true, true);
        }
        */

        @Override
        protected String doInBackground(String... params) {

            String name = (String)params[1];
            String country = (String)params[2];

            String serverURL = (String)params[0];
            String postParameters = "name=" + name + "&country=" + country;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

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
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString();

            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                return new String("Error: " + e.getMessage());
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                AlertDialog.Builder dlg1 = new AlertDialog.Builder(Register.this);
                dlg1.setTitle("레베럽");
                dlg1.setMessage("이소현 201758038 컴퓨터공학부\\n권시연 201658103 컴퓨터공학부\\n우주영 201746025 IT경영학과");
                dlg1.setIcon(R.drawable.con2);
                dlg1.setPositiveButton("확인",null);
                dlg1.show();

                return true;
            case R.id.item2:
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(Register.this);
                dlg2.setTitle("한신이닭");
                dlg2.setMessage("'내가찜한닭' 한신대점 어플,\n 우리 모두 한신이닭으로 맛있는 찜닭을\n 먹어봅시다 :)");
                dlg2.setIcon(R.drawable.chicken);
                dlg2.setPositiveButton("확인",null);
                dlg2.show();
                return true;
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}