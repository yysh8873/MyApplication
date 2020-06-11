package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Order_menu5 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_menu5);
        setTitle("한신이닭 주문하기");

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("한신이닭");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기

        Button btn_register = (Button) findViewById(R.id.btn_register);

        final RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg1);
        RadioButton rbtn_1_1 = (RadioButton) findViewById(R.id.rbtn_1_1);
        RadioButton rbtn_1_2 = (RadioButton) findViewById(R.id.rbtn_1_2);
        RadioButton rbtn_1_3 = (RadioButton) findViewById(R.id.rbtn_1_3);

        final RadioGroup rg2 = (RadioGroup) findViewById(R.id.rg2);
        RadioButton rbtn_2_1 = (RadioButton) findViewById(R.id.rbtn_2_1);
        RadioButton rbtn_2_2 = (RadioButton) findViewById(R.id.rbtn_2_2);
        RadioButton rbtn_2_3 = (RadioButton) findViewById(R.id.rbtn_2_3);

        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Payment.class);
                int id1 = rg1.getCheckedRadioButtonId();
                int id2 = rg2.getCheckedRadioButtonId();
                boolean c1 = checkBox1.isChecked();
                boolean c2 = checkBox2.isChecked();
                boolean c3 = checkBox3.isChecked();

                intent.putExtra("menu", "순살고추장찜닭");
                intent.putExtra("id1", id1);
                intent.putExtra("id2", id2);
                intent.putExtra("c1", c1);
                intent.putExtra("c2", c2);
                intent.putExtra("c3", c3);

                startActivity(intent);
            }
        });
        final ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new String[]{"공기밥", "소주"}));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder dlg1 = new AlertDialog.Builder(Order_menu5.this);
                        dlg1.setTitle("장바구니 삭제");
                        dlg1.setMessage("장바구니에서 " + parent.getItemAtPosition(position) + "를 빼시겠습니까?");
                        dlg1.setIcon(R.drawable.delete);
                        dlg1.setPositiveButton("확인",null);
                        dlg1.show();
                    }
                });
            }
        });

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
