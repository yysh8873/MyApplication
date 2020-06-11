package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Payment extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview;
    private RecyclerAdapter adapter;
    int cost = 0; // 총 금액

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        setTitle("한신이닭 결제");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기




        // 리스트 연결
        init();

        getData();

        Button btn_payment1 = (Button) findViewById(R.id.btn_payment1);
        btn_payment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg1 = new AlertDialog.Builder(Payment.this);
                dlg1.setTitle("결제");
                dlg1.setMessage("잔여 포인트: 포인트 받아오기\n결제액: " + cost + "\n\n\n 차감 후 잔액: 결과 받아오기");
                dlg1.setIcon(R.drawable.chicken);
                dlg1.setPositiveButton("결제",null);
                dlg1.show();
            }
        });

        Button btn_payment2 = (Button) findViewById(R.id.btn_payment2);
        btn_payment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg1 = new AlertDialog.Builder(Payment.this);
                dlg1.setTitle("초대하기");
                dlg1.setMessage("주변 기기를 검색하시겠습니까?");
                dlg1.setIcon(R.drawable.chicken);
                dlg1.setPositiveButton("검색", yesButtonClickListener);
                dlg1.show();

                }

            private DialogInterface.OnClickListener yesButtonClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  // 블루투스 연결 코드
                }
            };
        });
    }



    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        Intent intent = getIntent();
        String menu = intent.getStringExtra("menu");
        int id1 = intent.getIntExtra("id1", 0);
        int id2 = intent.getIntExtra("id2", 0);
        boolean c1 = intent.getBooleanExtra("c1", false);
        boolean c2 = intent.getBooleanExtra("c2", false);
        boolean c3 = intent.getBooleanExtra("c3", false);

        // 임의의 데이터입니다.
        final List<String> listTitle = new ArrayList<String>();
        final List<String> listContent = new ArrayList<String>();
        final List<String> listContent2 = new ArrayList<String>();



        if (id1 == R.id.rbtn_1_1) {
            listTitle.add(menu);
            listContent.add("18000원");
            cost += 18000;
        } else if (id1 == R.id.rbtn_1_2) {
            listTitle.add(menu);
            listContent.add("25000원");
            cost += 25000;
        } else if ( id1 == R.id.rbtn_1_3) {
            listTitle.add(menu);
            listContent.add("32000원");
            cost += 32000;
        }

        if (id2 == R.id.rbtn_2_1) {
            listContent2.add("순한맛");
        } else if (id1 == R.id.rbtn_2_2) {
            listContent2.add("중간맛");
        } else {
            listContent2.add("매운맛");
        }


        if (c1) {
            listTitle.add("당면 추가");
            listContent.add("2000원");
            listContent2.add("토핑추가");
            cost += 2000;
        }
        if (c2) {
            listTitle.add("감자 추가");
            listContent.add("2000원");
            listContent2.add("토핑추가");
            cost += 2000;
        }
        if (c3) {
            listTitle.add("만두 추가");
            listContent.add("2000원");
            listContent2.add("토핑추가");
            cost += 2000;
        }


        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            final Data data = new Data();
            data.setTitle(listTitle.get(i));
            //data.setContent(listContent.get(i));
            data.setContent2(listContent2.get(i));
            data.setBtn("확인");
            data.setDlgTitle("가격 정보");
            data.setDlgMsg("메뉴명: " + listTitle.get(i) + "\n가격: " + listContent.get(i) + "\n옵션: " + listContent2.get(i));
            data.setDlgPB(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   Toast.makeText(Payment.this, "메뉴 가격 정보 확인 완료",
                            Toast.LENGTH_SHORT).show();
                }
            });

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);


            // adapter의 값이 변경되었다는 것을 알려줍니다.
            adapter.notifyDataSetChanged();

            TextView result = (TextView) findViewById(R.id.result);
            result.setText("총액: " + cost + "원");
        }

    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), Order.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

}
