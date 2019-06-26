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
            relativeLayout = itemView.findViewById(R.id.relative);
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
            final Users user = uArrayList.get(getAdapterPosition());

            Intent intent = new Intent(ctx,UserDetailsActivity.class);
            intent.putExtra("firstname",user.getFirstname());
            intent.putExtra("lastname",user.getLastname());
            intent.putExtra("age",user.getAge());
            intent.putExtra("email",user.getEmail());
            intent.putExtra("phone",user.getPhone());
            intent.putExtra("birthdate",user.getBirthdate());
            intent.putExtra("country",user.getCountry());
            intent.putExtra("state",user.getState());
            ctx.startActivity(intent);

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
    public void onBindViewHolder(@NonNull final MyRecyclerViewAdapter.UsersHolder usersHolder, final int position) {
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
//                usersHolder.fname.setText(uArrayList.get(position).getFirstname());
//                usersHolder.lname.setText(uArrayList.get(position).getLastname());
//                usersHolder.age.setText(uArrayList.get(position).getAge());
//                usersHolder.phone.setText(uArrayList.get(position).getPhone());
//                usersHolder.email.setText(uArrayList.get(position).getEmail());
//                usersHolder.bdate.setText(uArrayList.get(position).getBirthdate());
//                usersHolder.country.setText(uArrayList.get(position).getCountry());
//                usersHolder.state.setText(uArrayList.get(position).getState());

                Intent intent = new Intent(ctx,UserDetailsActivity.class);
                intent.putExtra("firstname",user.getFirstname());
                intent.putExtra("lastname",user.getLastname());
                intent.putExtra("age",user.getAge());
                intent.putExtra("phone",user.getPhone());
                intent.putExtra("email",user.getEmail());
                intent.putExtra("birthdate",user.getBirthdate());
                intent.putExtra("country",user.getCountry());
                intent.putExtra("state",user.getState());

                Log.i("TAG","fname=="+user.getFirstname());
                Log.i("TAG","intent value = "+intent.putExtra("firstname",user.getFirstname()));
                Toast.makeText(ctx,"You clicked"+uArrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });


       usersHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
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
        return uArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(View view, int position , boolean isLongClick);
    }
}