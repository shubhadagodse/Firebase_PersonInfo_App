package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.UsersHolder> {
    public static String USER_KEY = "user_key";
    private ArrayList<Users> listOfUsers;
    private List<Users> uArrayList;
    Context ctx;
    private MyClickListener myClickListener;
    public void setMyClickListener(AdapterView.OnItemClickListener listener) {
        myClickListener = (MyClickListener) listener;
    }

    public MyRecyclerViewAdapter(Context context, List<Users> userList) {
        this.ctx = context;
        this.uArrayList = userList;
    }

    public MyRecyclerViewAdapter(ArrayList<Users> ulist) {
        listOfUsers = ulist;
    }

    public class UsersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fname, lname, age, email, phone, bdate, country, state;
        MyClickListener myClickListener;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;

        public UsersHolder(@NonNull final View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            relativeLayout = itemView.findViewById(R.id.relative);
            fname = itemView.findViewById(R.id.tv_f_name);
            lname = itemView.findViewById(R.id.tv_l_name);
            phone = itemView.findViewById(R.id.tv_phone);
            country = itemView.findViewById(R.id.tv_country);

            Log.i("TAG", "Adding listener");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fname.setText(uArrayList.get(getAdapterPosition()).getFirstname());
                    lname.setText(uArrayList.get(getAdapterPosition()).getLastname());
                    age.setText(uArrayList.get(getAdapterPosition()).getAge());
                    email.setText(uArrayList.get(getAdapterPosition()).getEmail());
                    phone.setText(uArrayList.get(getAdapterPosition()).getPhone());
                    bdate.setText(uArrayList.get(getAdapterPosition()).getBirthdate());
                    country.setText(uArrayList.get(getAdapterPosition()).getCountry());
                    state.setText(uArrayList.get(getAdapterPosition()).getState());
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Users user = uArrayList.get(getAdapterPosition());
//            myClickListener.onItemClick(getAdapterPosition());

            Log.i("TAG","User in Adapter"+user.getFirstname());
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(ctx)
                .inflate(R.layout.download_data, parent, false);
        UsersHolder usersHolder = new UsersHolder(v);
        return usersHolder;
    }

    @Override
    public void onBindViewHolder(final MyRecyclerViewAdapter.UsersHolder usersHolder, final int position) {
        final Users user = uArrayList.get(position);

        Log.i("TAG", "Fname before settext==" + usersHolder.fname);
        usersHolder.fname.setText(uArrayList.get(position).getFirstname());
        usersHolder.lname.setText(uArrayList.get(position).getLastname());
        usersHolder.phone.setText(uArrayList.get(position).getPhone());
        usersHolder.country.setText(uArrayList.get(position).getCountry());

        Log.i("TAG", "Fname after settext==" + usersHolder.fname);

       usersHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = user.getUid();
                Intent intent = new Intent(ctx,UserDetailsActivity.class);
                Log.i("TAG","userId in adapter=="+uid);
                intent.putExtra(USER_KEY,uid);
                intent.putExtra("firstname",user.getFirstname());
                intent.putExtra("lastname",user.getLastname());
                intent.putExtra("age",user.getAge());
                intent.putExtra("email",user.getEmail());
                intent.putExtra("phone",user.getPhone());
                intent.putExtra("birthdate",user.getBirthdate());
                intent.putExtra("country",user.getCountry());
                intent.putExtra("state",user.getState());

                ctx.startActivity(intent);
            }
        });
    }

    public void addItem(Users users, int position) {
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
        public void onItemClick(int position);
    }
}