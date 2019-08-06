package com.firebase.firebasepersoninfoapp.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
    private ArrayList<Users> listOfUsers;
    private List<Users> uArrayList;
    private Context ctx;
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
        TextView tvFname, tvLname, tvAge, tvEmail, tvPhone, tvBdate, tvCountry, tvState;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;

        public UsersHolder(@NonNull final View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            relativeLayout = itemView.findViewById(R.id.relative);
            tvFname = itemView.findViewById(R.id.tv_f_name);
            tvLname = itemView.findViewById(R.id.tv_l_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvCountry = itemView.findViewById(R.id.tv_country);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvFname.setText(uArrayList.get(getAdapterPosition()).getFirstname());
                    tvLname.setText(uArrayList.get(getAdapterPosition()).getLastname());
                    tvAge.setText(uArrayList.get(getAdapterPosition()).getAge());
                    tvEmail.setText(uArrayList.get(getAdapterPosition()).getEmail());
                    tvPhone.setText(uArrayList.get(getAdapterPosition()).getPhone());
                    tvBdate.setText(uArrayList.get(getAdapterPosition()).getBirthdate());
                    tvCountry.setText(uArrayList.get(getAdapterPosition()).getCountry());
                    tvState.setText(uArrayList.get(getAdapterPosition()).getState());
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Users user = uArrayList.get(getAdapterPosition());
        }
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
        usersHolder.tvFname.setText(uArrayList.get(position).getFirstname());
        usersHolder.tvLname.setText(uArrayList.get(position).getLastname());
        usersHolder.tvPhone.setText(uArrayList.get(position).getPhone());
        usersHolder.tvCountry.setText(uArrayList.get(position).getCountry());

       usersHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return uArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position);
    }
}