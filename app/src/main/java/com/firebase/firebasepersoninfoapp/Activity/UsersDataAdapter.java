package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.firebasepersoninfoapp.R;

import java.util.ArrayList;
import java.util.List;
    public class UsersDataAdapter extends RecyclerView.Adapter<UsersDataAdapter.ViewHolder> {

    public List<Users> getUsersList;
    public List<Users> userList = new ArrayList<Users>();
    public Context context;
    LayoutInflater inflater;
    ArrayList<Users> users;

    public UsersDataAdapter(Context context, List<Users> usersList) {
        this.getUsersList = usersList;
        this.context = context;
        this.getUsersList = new ArrayList<Users>();
        this.getUsersList.addAll(getUsersList);
    }

    public void startListening() {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView tvfname, tvlname, tvage, tvemail, tvphone, tvbirthdate, tvcountry, tvstate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;

            tvfname = mview.findViewById(R.id.tv_f_name);
            tvlname = mview.findViewById(R.id.tv_l_name);
            tvage = mview.findViewById(R.id.tv_age);
            tvemail = mview.findViewById(R.id.tv_email);
            tvphone = mview.findViewById(R.id.tv_phone);
            tvbirthdate = mview.findViewById(R.id.tv_birthdate);
            tvcountry = mview.findViewById(R.id.tv_country);
            tvstate = mview.findViewById(R.id.tv_state);
        }


        public View getView(int i, View view,ViewGroup viewGroup) {
            ViewHolder holder;
            if(view == null) {
                holder = new ViewHolder(view);
                view = inflater.inflate(R.layout.download_data,null);
                holder.tvfname = view.findViewById(R.id.tv_f_name);
                holder.tvlname = view.findViewById(R.id.tv_l_name);
                holder.tvage = view.findViewById(R.id.tv_age);
                holder.tvemail = view.findViewById(R.id.tv_email);
                holder.tvphone = view.findViewById(R.id.tv_phone);
                holder.tvbirthdate = view.findViewById(R.id.tv_birthdate);
                holder.tvcountry = view.findViewById(R.id.tv_country);
                holder.tvstate = view.findViewById(R.id.tv_state);

                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }

            holder.tvfname.setText(getUsersList.get(i).getFirstname());
            holder.tvlname.setText(getUsersList.get(i).getLastname());
            holder.tvage.setText(getUsersList.get(i).getAge());
            holder.tvemail.setText(getUsersList.get(i).getEmail());
            holder.tvphone.setText(getUsersList.get(i).getPhone());
            holder.tvbirthdate.setText(getUsersList.get(i).getBirthdate());
            holder.tvcountry.setText(getUsersList.get(i).getCountry());
            holder.tvstate.setText(getUsersList.get(i).getState());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return  view;
        }

    }



     @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.download_data,viewGroup,false);
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.download_data,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tvfname.setText(getUsersList.get(i).getFirstname());
        viewHolder.tvlname.setText(getUsersList.get(i).getLastname());
        viewHolder.tvage.setText(getUsersList.get(i).getAge());
        viewHolder.tvemail.setText(getUsersList.get(i).getEmail());
        viewHolder.tvphone.setText(getUsersList.get(i).getPhone());
        viewHolder.tvbirthdate.setText(getUsersList.get(i).getBirthdate());
        viewHolder.tvcountry.setText(getUsersList.get(i).getCountry());
        viewHolder.tvstate.setText(getUsersList.get(i).getState());

        final String userId = getUsersList.get(i).userId;


        viewHolder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"User Id : "+userId,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getUsersList.size();
    }

}
