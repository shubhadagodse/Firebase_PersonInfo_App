package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.firebasepersoninfoapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.UsersHolder> {
    public static String USER_KEY = "user_key";
    private ArrayList<Users> listOfUsers;
    private List<Users> uArrayList;
    Context ctx;
    private static MyClickListener myClickListener;

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

        public UsersHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.list_of_users_from_firebase);
            fname = itemView.findViewById(R.id.tv_f_name);
            lname = itemView.findViewById(R.id.tv_l_name);
            age = itemView.findViewById(R.id.tv_age);
            email = itemView.findViewById(R.id.tv_email);
            phone = itemView.findViewById(R.id.tv_phone);
            bdate = itemView.findViewById(R.id.tv_birthdate);
            country = itemView.findViewById(R.id.tv_country);
            state = itemView.findViewById(R.id.tv_state);

            Log.i("TAG", "Adding listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            fname = itemView.findViewById(R.id.tv_f_name);
            lname = itemView.findViewById(R.id.tv_l_name);
            age = itemView.findViewById(R.id.tv_age);
            email = itemView.findViewById(R.id.tv_email);
            phone = itemView.findViewById(R.id.tv_phone);
            bdate = itemView.findViewById(R.id.tv_birthdate);
            country = itemView.findViewById(R.id.tv_country);
            state = itemView.findViewById(R.id.tv_state);

////            myClickListener.onItemClick(getAdapterPosition());
//            Log.i("TAG","uArrayList.get()"+uArrayList.get(getAdapterPosition()).getFirstname());
//            uArrayList.get(getAdapterPosition()).getFirstname(); //null
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
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.UsersHolder usersHolder, final int position) {
        final Users user = uArrayList.get(position);

        Log.i("TAG", "Fname==" + usersHolder.fname);
        usersHolder.fname.setText(uArrayList.get(position).getFirstname());
        usersHolder.lname.setText(uArrayList.get(position).getLastname());
        usersHolder.phone.setText(uArrayList.get(position).getPhone());
        usersHolder.country.setText(uArrayList.get(position).getCountry());

        usersHolder.fname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,UserDetailsActivity.class);
                intent.putExtra("firstname",user.getFirstname());
                intent.putExtra("lastname",user.getLastname());
                intent.putExtra("age",user.getAge());
                intent.putExtra("phone",user.getPhone());
                intent.putExtra("email",user.getEmail());
                intent.putExtra("birthdate",user.getBirthdate());
                intent.putExtra("country",user.getCountry());
                intent.putExtra("state",user.getState());
                ctx.startActivity(intent);
                Toast.makeText(ctx,"You clicked"+uArrayList.get(position),Toast.LENGTH_SHORT).show();
            }
        });

        usersHolder.lname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = user.getUid();
                Intent intent = new Intent(ctx,UserDetailsActivity.class);
                Log.i("TAG","userId in adapter=="+uid);
                intent.putExtra(USER_KEY,uid);
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
//        return data.length;
        return uArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position);
    }
}
