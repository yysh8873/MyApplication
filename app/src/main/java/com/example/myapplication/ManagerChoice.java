package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class ManagerChoice extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview;
    private RecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_choice);
        setTitle("한신이닭");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
        Button btn_do = (Button) findViewById(R.id.btn_do);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg1 = new AlertDialog.Builder(ManagerChoice.this);
                dlg1.setTitle("배달 상태 설정");
                dlg1.setMessage("배달 완료로 변경하시겠습니까?");
                dlg1.setIcon(R.drawable.con2);
                dlg1.setPositiveButton("확인",null);
                dlg1.setNegativeButton("취소", null);
                dlg1.show();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(ManagerChoice.this);
                dlg2.setTitle("배달 상태 설정");
                dlg2.setMessage("배달 취소로 변경하시겠습니까?");
                dlg2.setIcon(R.drawable.chicken);
                dlg2.setPositiveButton("확인",null);
                dlg2.setNegativeButton("취소", null);
                dlg2.show();
            }
        });

        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg3 = new AlertDialog.Builder(ManagerChoice.this);
                dlg3.setTitle("배달 상태 설정");
                dlg3.setMessage("배달중으로 변경하시겠습니까?");
                dlg3.setIcon(R.drawable.chicken);
                dlg3.setPositiveButton("확인",null);
                dlg3.setNegativeButton("취소", null);
                dlg3.show();
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), ManagerList.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}

