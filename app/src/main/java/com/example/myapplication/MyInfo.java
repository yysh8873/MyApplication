package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MyInfo extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info);
        setTitle("한신이닭 내정보");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭 내정보");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        final Button btn_edit = (Button) findViewById(R.id.btn_edit);
        final Button btn_save = (Button) findViewById(R.id.btn_save);

        final TextView text_Name = (TextView) findViewById(R.id.text_Name);
        final TextView text_PW = (TextView) findViewById(R.id.text_PW);
        final TextView text_Phone = (TextView) findViewById(R.id.text_Phone);
        final TextView text_AD = (TextView) findViewById(R.id.text_AD);

        final EditText edit_Name = (EditText) findViewById(R.id.edit_Name);
        final EditText edit_PW = (EditText) findViewById(R.id.edit_PW);
        final EditText edit_Phone = (EditText) findViewById(R.id.edit_Phone);
        final EditText edit_AD = (EditText) findViewById(R.id.edit_AD);


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
