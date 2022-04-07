package com.example.danamonmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SqliteHelper db;
    private ArrayList<AllUser> users;
    private List<AllUser> listUser = new ArrayList<AllUser>();
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new ArrayList<>();
        db = new SqliteHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        users = db.allUsers();
        adapter = new UserAdapter(this, listUser);

        Log.d("apaini: ",listUser.toString());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}