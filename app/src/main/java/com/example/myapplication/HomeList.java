package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class HomeList extends Fragment{
    ViewGroup viewGroup;
    private RecyclerView recyclerview;
    private RecyclerAdapter adapter;
    private Object HomeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home_list,container,false); 

        // 리스트 연결
        init();

        getData();

        return viewGroup;
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

