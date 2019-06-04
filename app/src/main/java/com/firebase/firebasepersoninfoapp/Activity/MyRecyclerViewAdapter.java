package com.firebase.firebasepersoninfoapp.Activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.UsersHolder> {
    private ArrayList<Users> uArrayList;
    private static MyClickListener myClickListener;

    public class UsersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fname,lname,age,email,phone,bdate,country,state;
        public UsersHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.a_main_tv_fname);
            lname = itemView.findViewById(R.id.a_main_tv_lname);
            age = itemView.findViewById(R.id.a_main_tv_age);
            email = itemView.findViewById(R.id.a_main_tv_age);
            phone = itemView.findViewById(R.id.a_main_tv_phone);
            bdate = itemView.findViewById(R.id.a_main_tv_birthdate);
            country = itemView.findViewById(R.id.a_main_tv_country);
            state = itemView.findViewById(R.id.a_main_tv_state);

            Log.i("TAG","Adding listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(),v);
        }
//        return itemView;
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<Users> myDataset) {
        uArrayList = myDataset;
    }


    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.download_data,parent,false);
        UsersHolder usersHolder = new UsersHolder(v);
        return usersHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.UsersHolder usersHolder, int position) {
        usersHolder.fname.setText(uArrayList.get(position).getFirstname());
        usersHolder.lname.setText(uArrayList.get(position).getLastname());
        usersHolder.age.setText(uArrayList.get(position).getAge());
        usersHolder.email.setText(uArrayList.get(position).getEmail());
        usersHolder.phone.setText(uArrayList.get(position).getPhone());
        usersHolder.bdate.setText(uArrayList.get(position).getBirthdate());
        usersHolder.country.setText(uArrayList.get(position).getCountry());
        usersHolder.state.setText(uArrayList.get(position).getState());

    }

    public void addItem(Users users, int  position) {
        uArrayList.add(users);
        notifyItemInserted(position);
    }

    public void deleteItem(int index) {
        uArrayList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return uArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
