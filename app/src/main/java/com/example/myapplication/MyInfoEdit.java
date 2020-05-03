package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyInfoEdit extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info_edit);
        setTitle("한신이닭 내정보 수정");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭 내정보 수정");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        Button btn_save = (Button) findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
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
