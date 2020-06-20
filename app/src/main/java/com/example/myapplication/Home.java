package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeMain homeMain;
    HomeList homeList;
    Popular popular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // login에서 값 받아서 저장
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");

        //프래그먼트 생성
        homeMain = new HomeMain();
        homeList = new HomeList();
        popular = new Popular();

        //번들객체 생성, uid값 저장
        final Bundle bundle = new Bundle();
        bundle.putString("uid", uid);

        //제일 처음 띄워줄 뷰를 세팅해줍니다. commit();까지 해줘야 합니다.
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, homeMain).commitAllowingStateLoss();
        homeMain.setArguments(bundle);
        //bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스너를 추가합니다.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                    case R.id.tab1:{
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout, homeMain).commitAllowingStateLoss();
                        return true;
                    } case R.id.tab2:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout, homeList).commitAllowingStateLoss();
                            homeList.setArguments(bundle);
                    return true;
                    } case R.id.tab3:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout, popular).commitAllowingStateLoss();
                    return true;
                    } default: return false;
                }
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                return true;
        }
        return false;
    }


}
