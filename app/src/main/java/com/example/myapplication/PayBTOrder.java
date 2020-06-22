package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PayBTOrder extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_bt_order);
        setTitle("한신이닭 결제");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        Intent intent = getIntent();
        int cost = intent.getExtras().getInt("cost");
        int halfcost = cost/2;

        TextView result = (TextView) findViewById(R.id.result);
        TextView halfresult = (TextView) findViewById(R.id.halfresult);

        result.setText(Integer.toString(cost));
        halfresult.setText(Integer.toString(halfcost));
    }
}
