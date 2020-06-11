package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class HomeList extends Fragment{
    ViewGroup viewGroup;
    private RecyclerView recyclerview;
    private RecyclerAdapter adapter;
    private Object HomeList;

    String serverURL = "http://13.125.45.205/orderlist.php";
    private static String IP_ADDRESS = "13.125.45.205";
    private static String TAG = "myapplication";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_OID = "oid";
    private static final String TAG_TDATE = "tdate";
    private static final String TAG_MENU = "menu";
    private static final String TAG_UID ="uid";
    private static final String TAG_PRICE ="price";
    private static final String TAG_ADDR ="addr";
    private static final String TAG_PHONE ="phone";
    private static final String TAG_DELCON ="delcon";

    private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList;
    EditText mEditTextSearchKeyword1, mEditTextSearchKeyword2;
    String mJsonString;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home_list,container,false);

        mTextViewResult = (TextView)viewGroup.findViewById(R.id.textView_main_result);
        mListViewList = (ListView) viewGroup.findViewById(R.id.listView_main_list);
        mEditTextSearchKeyword1 = (EditText) viewGroup.findViewById(R.id.editText_main_searchKeyword1);
        System.out.println("로그" + mEditTextSearchKeyword1.getText().toString());

        Button button_search = (Button) viewGroup.findViewById(R.id.button_main_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();
                task.execute(mEditTextSearchKeyword1.getText().toString());

            }
        });


        mArrayList = new ArrayList<>();

        // 리스트 연결
        init();

        getData();

        return viewGroup;

    }

    class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show((Context) HomeList,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText("sival");
            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String searchKeyword1 = params[0];
            String searchKeyword2 = params[1];

            String serverURL = "http://13.125.45.205/orderlist.php";
            String postParameters = "uid=" + searchKeyword1;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

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
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                /*    private static final String TAG_OID = "oid";
                      private static final String TAG_TDATE = "tdate";
                      private static final String TAG_MENU = "menu";
                      private static final String TAG_UID ="uid";
                      private static final String TAG_PRICE ="price";
                      private static final String TAG_ADDR ="addr";
                      private static final String TAG_PHONE ="phone";
                      private static final String TAG_DELCON ="delcon";*/

                String oid = item.getString(TAG_OID);
                String tdate = item.getString(TAG_TDATE);
                String menu = item.getString(TAG_MENU);
                String uid = item.getString(TAG_UID);
                String price = item.getString(TAG_PRICE);
                String addr = item.getString(TAG_ADDR);
                String phone = item.getString(TAG_PHONE);
                String delcon = item.getString(TAG_DELCON);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_OID, oid);
                hashMap.put(TAG_TDATE, tdate);
                hashMap.put(TAG_MENU, menu);
                hashMap.put(TAG_UID, uid);
                hashMap.put(TAG_PRICE, price);
                hashMap.put(TAG_ADDR, addr);
                hashMap.put(TAG_PHONE, phone);
                hashMap.put(TAG_DELCON, delcon);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    (Context) HomeList, mArrayList, R.layout.item_list,
                    new String[]{TAG_OID,TAG_TDATE, TAG_UID},
                    new int[]{R.id.textView_list_id, R.id.textView_list_name, R.id.textView_list_address}
            );

            mListViewList.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }


    
    private void init() {
        RecyclerView recyclerView = viewGroup.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) HomeList);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {

        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("안동찜닭", "치즈순살찜닭", "고추장찜닭");
        List<String> listContent = Arrays.asList(
                "18000원",
                "20000원",
                "18000원"
        );
        List<String> listDate = Arrays.asList(
                "2020-05-03",
                "2020-05-02",
                "2020-05-01"
        );


        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setDate(listDate.get(i));
            data.setBtn("선택");
            data.setDlgTitle("결제 내역 상세보기");
            data.setDlgMsg("주문 내역:  \n주문 일시: \n 주소: \n총 금액: \n결제 방법:  등\n\n\n\n포함 정보 가져오기");
            data.setDlgPB(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText((Context) HomeList, "장바구니 담았습니다",
                            Toast.LENGTH_SHORT).show();
                }
            });

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}

