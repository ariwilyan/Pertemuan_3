package com.example.menumakanan;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// menu adapter menampilkan nilai dari array list, convert dari model menu array list ke bentuk tulisan
// hal ini perlu di alt enter karena merupakan pewarisan
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private Context context;
    private List<MenuModel> listMenu; // variabel global

    public MenuAdapter(Context context) {
        this.context = context;
        listMenu = new ArrayList<>();
    }

    public void setData(List<MenuModel> listMenu) {
        this.listMenu = listMenu; //merujuk ke variabel global yang ada di ini, supaya tidak ambigu
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup,false); // diinflate supaya jalan di activity main
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder viewHolder, int i) {
        MenuModel menuModel = listMenu.get(i);

        viewHolder.tvNamaMenu.setText(menuModel.getNamaMenu());
        viewHolder.tvHarga.setText(String.valueOf(menuModel.getHarga()));
        viewHolder.tvDetailMenu.setText(menuModel.getDetailMenu());
    }

    @Override
    public int getItemCount() {
        return listMenu.size(); // return ini akan mereturn banyak datanya sebanyak data yg ada di list menu
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivMenu;
        TextView tvNamaMenu, tvHarga, tvDetailMenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMenu = itemView.findViewById(R.id.iv_menu);
            tvNamaMenu = itemView.findViewById(R.id.tv_nama_menu);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvDetailMenu = itemView.findViewById(R.id.tv_detail_menu);
        }
    }
}
