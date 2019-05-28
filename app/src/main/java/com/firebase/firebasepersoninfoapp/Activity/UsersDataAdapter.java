package com.firebase.firebasepersoninfoapp.Activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;

import java.util.List;

public class UsersDataAdapter extends RecyclerView.Adapter<UsersDataAdapter.ViewHolder> {

    public List<Users> getUsersList;

    public UsersDataAdapter(List<Users> usersList) {
        this.getUsersList = usersList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView tvfname, tvlname, tvage, tvemail, tvphone, tvbirthdate, tvcountry, tvstate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;

            tvfname = mview.findViewById(R.id.first_name);
            tvlname = mview.findViewById(R.id.last_name);
            tvage = mview.findViewById(R.id.age);
            tvemail = mview.findViewById(R.id.email);
            tvphone = mview.findViewById(R.id.phone);
            tvbirthdate = mview.findViewById(R.id.birth_date);
            tvcountry = mview.findViewById(R.id.country);
            tvstate = mview.findViewById(R.id.state);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users,viewGroup,false);
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

    }

    @Override
    public int getItemCount() {
        return getUsersList.size();
    }
}
