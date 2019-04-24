package com.example.menumakanan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private FloatingActionButton fabAdd;
    private MenuAdapter adapter;
    private List<MenuModel> listMenu = new ArrayList<>();

    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("menu_makanan");

        rvList = findViewById(R.id.rv_list);
        fabAdd = findViewById(R.id.fab_add);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddMenuActivity.class));
            }
        });

        /*MenuModel m1 = new MenuModel("Menu 1", 15000,"Ayam bakar madu + es teh");
        MenuModel m2 = new MenuModel("Menu 2", 15000,"Ayam bakar madu + es teh");
        MenuModel m3 = new MenuModel("Menu 3", 15000,"Ayam bakar madu + es teh");
        MenuModel m4 = new MenuModel("Menu 4", 15000,"Ayam bakar madu + es teh");
        MenuModel m5 = new MenuModel("Menu 5", 15000,"Ayam bakar madu + es teh");

        listMenu.add(0, m1);
        listMenu.add(0, m2);
        listMenu.add(0, m3);
        listMenu.add(0, m4);
        listMenu.add(0, m5);*/

        adapter = new MenuAdapter(this);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);

        getMenu(); // akan listener dia melihat atau mendengar terus jika ada perubahan maka akan dilakukan looping taro ke dalam modelnya, terus ditaro di list menu
    }

    private void getMenu() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listMenu.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    MenuModel menuModel = ds.getValue(MenuModel.class); // setiap data yg ada didatabase/firebase dimasukkan ke menu model
                    listMenu.add(menuModel);
                }
                adapter.setData(listMenu);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
