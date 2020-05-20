package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("한신이닭");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.baseLayout);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_register = (Button) findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                AlertDialog.Builder dlg1 = new AlertDialog.Builder(MainActivity.this);
                dlg1.setTitle("레베럽");
                dlg1.setMessage("이소현 201758038 컴퓨터공학부\\n권시연 201658103 컴퓨터공학부\\n우주영 201746025 IT경영학과");
                dlg1.setIcon(R.drawable.con2);
                dlg1.setPositiveButton("확인",null);
                dlg1.show();

                return true;
            case R.id.item2:
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(MainActivity.this);
                dlg2.setTitle("한신이닭");
                dlg2.setMessage("'내가찜한닭' 한신대점 어플,\n 우리 모두 한신이닭으로 맛있는 찜닭을\n 먹어봅시다 :)");
                dlg2.setIcon(R.drawable.chicken);
                dlg2.setPositiveButton("확인",null);
                dlg2.show();
                return true;
        }
        return false;
    }
}
