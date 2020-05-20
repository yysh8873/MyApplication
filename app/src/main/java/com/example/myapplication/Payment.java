package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class Payment extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview;
    private RecyclerAdapter adapter;

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
                dlg1.setMessage("잔여 포인트: 포인트 받아오기\n 결제액: 결과 받아오기\n\n\n 차감 후 잔액: 결과 받아오기");
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
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("안동찜닭", "공기밥", "소주");
        List<String> listContent = Arrays.asList(
                "18000원",
                "1000원",
                "4000원"
        );

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setBtn("삭제");
            data.setDlgTitle("장바구니 삭제");
            data.setDlgMsg("선택한 항목을 삭제하시겠습니까?");
            data.setDlgPB(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Payment.this, "장바구니에서 삭제되었습니다",
                            Toast.LENGTH_SHORT).show();
                }
            });

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
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
