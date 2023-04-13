package com.example.thuchanh2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2.R;
import com.example.thuchanh2.UpdateDeleteActivity;
import com.example.thuchanh2.adapter.RecyclerViewAdapter;
import com.example.thuchanh2.database.SQLiteHelper;
import com.example.thuchanh2.model.Book;

import java.util.List;

public class FragmentList extends Fragment implements RecyclerViewAdapter.ItemListener{
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
        adapter=new RecyclerViewAdapter();
        db=new SQLiteHelper(getContext());
        List<Book> list= db.getAllBook();
        adapter.setList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        List<Book> list= db.getAllBook();
        adapter=new RecyclerViewAdapter();
        adapter.setList(list);
    }

    @Override
    public void onItemClick(View view, int position) {
        Book book= adapter.getItem(position);
        Intent intent=new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }
}
