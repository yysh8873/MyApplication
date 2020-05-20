package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class ManagerList extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview;
    private RecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_list);
        setTitle("한신이닭");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        // 리스트 연결
        init();

        getData();

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
        List<String> listTitle = Arrays.asList("안동찜닭", "치즈순살찜닭", "고추장찜닭");
        List<String> listContent2 = Arrays.asList("이소현", "권시연", "우주영");
        List<String> listContent = Arrays.asList(
                "188888원",
                "20000원",
                "18000원"
        );
        List<String> listDate = Arrays.asList(
                "2020-05-03",
                "2020-05-02",
                "2020-05-01"
        );

        String[] items = {"배달중", "배달완료", "취소"};

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent2(listContent2.get(i));
            data.setContent(listContent.get(i));
            data.setDate(listDate.get(i));
            data.setBtn("설정");
            data.setDlgTitle("주문 상태 설정");
            data.setDlgMsg("선택한 주문의 설정을 선택해주세요");
            data.setDlgItems(items, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ManagerList.this, "설정 변경 완료",
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
                Intent intent = new Intent(getApplicationContext(), HomeManager.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}

