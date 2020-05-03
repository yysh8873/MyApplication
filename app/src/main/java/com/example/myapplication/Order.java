package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        setTitle("한신이닭 주문하기");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        Button btn_menu1 = (Button) findViewById(R.id.btn_menu1);
        btn_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu1.class);
                startActivity(intent);
            }
        });

        Button btn_menu2 = (Button) findViewById(R.id.btn_menu2);
        btn_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu2.class);
                startActivity(intent);
            }
        });

        Button btn_menu3 = (Button) findViewById(R.id.btn_menu3);
        btn_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu3.class);
                startActivity(intent);
            }
        });

        Button btn_menu4 = (Button) findViewById(R.id.btn_menu4);
        btn_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu4.class);
                startActivity(intent);
            }
        });

        Button btn_menu5 = (Button) findViewById(R.id.btn_menu5);
        btn_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Order_menu5.class);
                startActivity(intent);
            }
        });


        // 리스트 연결
        init();

        getData();

        final ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new String[]{"공기밥", "소주"}));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position+" 번째 값 : " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
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
        List<String> listTitle = Arrays.asList("공기밥", "탄산음료(1.25L)", "소주");
        List<String> listContent = Arrays.asList(
                "1000원",
                "2000원",
                "4000원"
        );

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
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
