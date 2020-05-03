package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private RecyclerAdapter2 adapter;
    private Object HomeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home_list,container,false); 

        // 리스트 연결
        init();

        getData2();

        return viewGroup;
    }
    
    private void init() {
        RecyclerView recyclerView = viewGroup.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) HomeList);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter2();
        recyclerView.setAdapter(adapter);
    }

    private void getData2() {
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
            Data2 data = new Data2();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setDate(listDate.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}

