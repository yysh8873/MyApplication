package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class App extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app);
        setTitle("한신이닭 정보");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭 정보");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기


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
