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


public class Register extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_LOCATION = 10;
    private WebView webView;
    private TextView txt_address;
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setTitle("한신이닭 회원가입");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        final TextView status = (TextView) findViewById(R.id.status);
        // 위치에 대한 권한 요구
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
        // 위치 관리자에 대한 참조값
        LocationManager locationManager = (LocationManager)
                this.getSystemService(Context.LOCATION_SERVICE);


        Button btn_register = (Button) findViewById(R.id.btn_register);

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
        edit_AD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchAddress.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        LocationListener locationListener = new LocationListener() {
            // 새로운 위치가 발견되면 호출
            public void onLocationChanged(Location location) {
                status.setText("위도; " + location.getLatitude()
                        + "\n경도:" + location.getLongitude()
                        + "\n고도:" + location.getAltitude());
            }
            // 위치가 업데이트되면 호출되는 리스너 정의
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(Register.this, "First enable LOCATION ACCESS in settings.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        // 위치를 업데이트 받기 위해 리스너를 위치 관리자에 등록
        locationManager.requestLocationUpdates(
                LocationManager. GPS_PROVIDER , 0, 0, locationListener);
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
