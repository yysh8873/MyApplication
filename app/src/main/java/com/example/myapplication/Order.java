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
    OrderListArray ol;
    int rice;
    int juice;
    int soju;

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

        // 리스트 연결
//        init();
//
//        getData();
        String[] sidemenu = new String[3];
        sidemenu[0] = "공기밥 "+rice+"개";
        sidemenu[1] = "탄산음료(1.25L) "+juice+"개";
        sidemenu[2] = "소주 "+soju+"개";

//        final ListView listView = findViewById(R.id.listView);
//        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
//                new String[]{"공기밥 "+rice+"개", "탄산음료(1.25L) "+juice+"개", "소주 "+soju+"개"}));
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        AlertDialog.Builder dlg1 = new AlertDialog.Builder(Order.this);
//                        dlg1.setTitle("장바구니 삭제");
//                        dlg1.setMessage("장바구니에서 " + parent.getItemAtPosition(position) + "를 빼시겠습니까?");
//                        dlg1.setIcon(R.drawable.delete);
//                        dlg1.setPositiveButton("확인",null);
//                        dlg1.show();
//                    }
//                });
//            }
//        });


    }

//    private void init() {
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        adapter = new RecyclerAdapter();
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void getData() {
//        // 임의의 데이터입니다.
//        List<String> listTitle = Arrays.asList("공기밥", "탄산음료(1.25L)", "소주");
//        List<String> listContent = Arrays.asList(
//                "1000원",
//                "2000원",
//                "4000원"
//        );
//
//        for (int i = 0; i < listTitle.size(); i++) {
//            // 각 List의 값들을 data 객체에 set 해줍니다.
//            Data data = new Data();
//            data.setTitle(listTitle.get(i));
//            data.setContent(listContent.get(i));
//            data.setBtn("선택");
//            data.setDlgTitle("장바구니 담기");
//            data.setDlgMsg("선택한 항목을 장바구니에 담으시겠습니까?");
//            final int finalI = i;
//            data.setDlgPB(new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(Order.this, "장바구니 담았습니다",
//                            Toast.LENGTH_SHORT).show();
//                    if(finalI == 0) rice++;
//                    else if(finalI == 1) juice++;
//                    else if(finalI == 2) soju++;
//                }
//            });
//
//            // 각 값이 들어간 data를 adapter에 추가합니다.
//            adapter.addItem(data);
//        }
//
//        // adapter의 값이 변경되었다는 것을 알려줍니다.
//        adapter.notifyDataSetChanged();
//
//    }

    public int[] getItemCount() {
        int[] itemCount = new int[3];
        itemCount[0] = rice;
        itemCount[1] = juice;
        itemCount[2] = soju;
        return itemCount;
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
