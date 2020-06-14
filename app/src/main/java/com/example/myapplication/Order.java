package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class Order extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview;
    private RecyclerAdapter adapter;
    private int rice = 0;
    private int juice = 0;
    private int soju = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        setTitle("한신이닭 주문하기");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        Button sideminus1 = (Button) findViewById(R.id.sideminus1);
        sideminus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView sidecount1 = (TextView) findViewById(R.id.sidecount1);
                String count1 = sidecount1.getText().toString();
                rice = Integer.parseInt(count1);
                if (rice > 0) rice--;
                sidecount1.setText(Integer.toString(rice));
            }
        });

        Button sideplus1 = (Button) findViewById(R.id.sideplus1);
        sideplus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView sidecount1 = (TextView) findViewById(R.id.sidecount1);
                String count1 = sidecount1.getText().toString();
                rice = Integer.parseInt(count1);
                rice++;
                sidecount1.setText(Integer.toString(rice));
            }
        });

        Button sideminus2 = (Button) findViewById(R.id.sideminus2);
        sideminus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView sidecount2 = (TextView) findViewById(R.id.sidecount2);
                String count2 = sidecount2.getText().toString();
                juice = Integer.parseInt(count2);
                if (juice > 0) juice--;
                sidecount2.setText(Integer.toString(juice));
            }
        });

        Button sideplus2 = (Button) findViewById(R.id.sideplus2);
        sideplus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView sidecount2 = (TextView) findViewById(R.id.sidecount2);
                String count2 = sidecount2.getText().toString();
                juice = Integer.parseInt(count2);
                juice++;
                sidecount2.setText(Integer.toString(juice));
            }
        });

        Button sideminus3 = (Button) findViewById(R.id.sideminus3);
        sideminus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView sidecount3 = (TextView) findViewById(R.id.sidecount3);
                String count3 = sidecount3.getText().toString();
                soju = Integer.parseInt(count3);
                if (soju > 0) soju--;
                sidecount3.setText(Integer.toString(soju));
            }
        });

        Button sideplus3 = (Button) findViewById(R.id.sideplus3);
        sideplus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView sidecount3 = (TextView) findViewById(R.id.sidecount3);
                String count3 = sidecount3.getText().toString();
                soju = Integer.parseInt(count3);
                soju++;
                sidecount3.setText(Integer.toString(soju));
            }
        });

        Button btn_menu1 = (Button) findViewById(R.id.btn_menu1);
        btn_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu1.class);
                intent.putExtra("rice", rice);
                intent.putExtra("juice", juice);
                intent.putExtra("soju", soju);
                startActivity(intent);
            }
        });

        Button btn_menu2 = (Button) findViewById(R.id.btn_menu2);
        btn_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu2.class);
                intent.putExtra("rice", rice);
                intent.putExtra("juice", juice);
                intent.putExtra("soju", soju);
                startActivity(intent);
            }
        });

        Button btn_menu3 = (Button) findViewById(R.id.btn_menu3);
        btn_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu3.class);
                intent.putExtra("rice", rice);
                intent.putExtra("juice", juice);
                intent.putExtra("soju", soju);
                startActivity(intent);
            }
        });

        Button btn_menu4 = (Button) findViewById(R.id.btn_menu4);
        btn_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu4.class);
                intent.putExtra("rice", rice);
                intent.putExtra("juice", juice);
                intent.putExtra("soju", soju);
                startActivity(intent);
            }
        });

        Button btn_menu5 = (Button) findViewById(R.id.btn_menu5);
        btn_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu5.class);
                intent.putExtra("rice", rice);
                intent.putExtra("juice", juice);
                intent.putExtra("soju", soju);
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
