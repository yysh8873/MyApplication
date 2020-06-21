package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Payment extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview;
    private RecyclerAdapter adapter;
    SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
    Date time = new Date();
    int cost = 0; // 총 금액

    private static String IP_ADDRESS = "13.125.45.205";
    private static String TAG = "MyApplication";

    // 임의의 데이터입니다.
    List<String> listTitle = new ArrayList<String>();
    List<String> listContent = new ArrayList<String>();
    List<String> listContent2 = new ArrayList<String>();

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

        Intent intent = getIntent();
        int rice = intent.getExtras().getInt("rice");
        int juice = intent.getExtras().getInt("juice");
        int soju = intent.getExtras().getInt("soju");
        String menu = intent.getStringExtra("menu");
        int id1 = intent.getIntExtra("id1", 0); // R.id.rbtn_1_1:18000, R.id.rbtn_1_2:25000, R.id.rbtn_1_3:32000
        int id2 = intent.getIntExtra("id2", 0); // R.id.rbtn_2_1:순한맛,  R.id.rbtn_2_2:중간맛,  R.id.rbtn_2_3:매운맛
        boolean c1 = intent.getBooleanExtra("c1", false);   // 당면
        boolean c2 = intent.getBooleanExtra("c2", false);   // 감자
        boolean c3 = intent.getBooleanExtra("c3", false);   // 만두


        final String str_Oid = "5";   // DB에서 받아와야 됨
        final String str_Tdate = format1.format(time);    // 현재 날짜 받아오기
        String str_Menu = menu;
        final String str_Uid = "so"; // 로그인 세션 받아오기
        final String str_Price;
        final String str_Paycon = "0"; // 결제 여부
        final String str_Addr = "경기도 오산시";       // 로그인 관련 받아오기
        final String str_Phone = "010-1111-1111";   // 로그인 관련 받아오기
        final String str_Delcon = "0"; // 배달 여부

        int riceCost = rice*1000;
        int juiceCost = juice*2000;
        int sojuCost = soju*4000;
        cost = cost + riceCost + juiceCost + sojuCost;

        if (id1 == R.id.rbtn_1_1) {
            listTitle.add(menu);
            listContent.add("18000원");
            str_Menu += " (소) ";
            cost += 18000;
        } else if (id1 == R.id.rbtn_1_2) {
            listTitle.add(menu);
            listContent.add("25000원");
            str_Menu += " (중) ";
            cost += 25000;
        } else if ( id1 == R.id.rbtn_1_3) {
            listTitle.add(menu);
            listContent.add("32000원");
            str_Menu += " (대) ";
            cost += 32000;
        }

        if (id2 == R.id.rbtn_2_1) {
            listContent2.add("오리지널맛");
            str_Menu += "(오리지널맛) ";
        } else if (id2 == R.id.rbtn_2_2) {
            listContent2.add("순한맛");
            str_Menu += "(순한맛) ";
        } else if (id2 == R.id.rbtn_2_3){
            listContent2.add("매운맛");
            str_Menu += "(매운맛) ";
        }


        if (c1) {
            listTitle.add("당면 추가");
            str_Menu += "당면추가";
            listContent.add("2000원");
            listContent2.add("토핑추가");
            cost += 2000;
        }
        if (c2) {
            listTitle.add("감자 추가");
            str_Menu += "감자추가";
            listContent.add("2000원");
            listContent2.add("토핑추가");
            cost += 2000;
        }
        if (c3) {
            listTitle.add("만두 추가");
            str_Menu += "만두추가";
            listContent.add("2000원");
            listContent2.add("토핑추가");
            cost += 2000;
        }
        listTitle.add("공기밥 추가 "+rice+"개");
        listTitle.add("탄산음료(1.25L) 추가 "+juice+"개");
        listTitle.add("소주 추가 "+soju+"개");

        str_Price = Integer.toString(cost);

        Button btn_payment1 = (Button) findViewById(R.id.btn_payment1);
        final String finalStr_Menu = str_Menu;
        btn_payment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsertData task = new InsertData();
                task.execute("http://"+IP_ADDRESS+"/order.php", str_Oid, str_Tdate, finalStr_Menu,
                        str_Uid, str_Price, str_Paycon, str_Addr, str_Phone, str_Delcon);

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
                    Intent btintent = new Intent(getApplicationContext(), PayBTOrder.class);
                    btintent.putExtra("cost", cost);
                    startActivity(btintent);
                }
            };
        });
    }

    class InsertData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Payment.this,
                    "Please Wait", null, true, true);
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }



        @Override
        protected String doInBackground(String... params) {

            String oid = (String)params[1];
            String tdate = (String)params[2];
            String menu = (String)params[3];
            String uid = (String) params[4];
            String price = (String)params[5];
            String paycon = (String)params[6];
            String addr = (String)params[7];
            String phone = (String)params[8];
            String delcon = (String)params[9];

            String serverURL = (String)params[0];
            String postParameters = "oid=" + oid + "&tdate=" + tdate + "&uid=" + uid + "&menu=" + menu + "&price=" + price + "&paycon=" + paycon
                    + "&addr=" + addr + "&phone=" + phone + "&delcon=" + delcon;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString();

            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                return new String("Error: " + e.getMessage());
            }

        }
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
        int rice = intent.getExtras().getInt("rice");
        int juice = intent.getExtras().getInt("juice");
        int soju = intent.getExtras().getInt("soju");
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
        int cost = 0;

        int riceCost = rice*1000;
        int juiceCost = juice*2000;
        int sojuCost = soju*4000;
        cost = cost + riceCost + juiceCost + sojuCost;

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
            listContent2.add("오리지널맛");
        } else if (id2 == R.id.rbtn_2_2) {
            listContent2.add("순한맛");
        } else if (id2 == R.id.rbtn_2_3){
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
//        listTitle.add("공기밥 추가 "+rice+"개");
//        listContent.add(riceCost + "원");
//        listTitle.add("탄산음료(1.25L) 추가 "+juice+"개");
//        listContent.add(juiceCost + "원");
//        listTitle.add("소주 추가 "+soju+"개");
//        listContent.add(sojuCost + "원");

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
