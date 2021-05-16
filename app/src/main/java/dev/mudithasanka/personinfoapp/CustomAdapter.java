package dev.mudithasanka.personinfoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList person_id, person_division, person_hno, person_name, person_nic, person_gender;
    CustomAdapter(Context context, ArrayList person_id, ArrayList person_division, ArrayList person_hno,
                  ArrayList person_name, ArrayList person_nic, ArrayList person_gender){
        this.context = context;
        this.person_id = person_id;
        this.person_division = person_division;
        this.person_hno = person_hno;
        this.person_name = person_name;
        this.person_nic = person_nic;
        this.person_gender = person_gender;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.person_division_txt.setText(String.valueOf(person_division.get(position)));
        holder.person_hno_txt.setText(String.valueOf(person_hno.get(position)));
        holder.person_name_txt.setText(String.valueOf(person_name.get(position)));
        holder.person_nic_txt.setText(String.valueOf(person_nic.get(position)));
        holder.person_gender_txt.setText(String.valueOf(person_gender.get(position)));
    }

    @Override
    public int getItemCount() {
        return person_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView person_division_txt, person_hno_txt, person_name_txt, person_nic_txt, person_gender_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            person_division_txt = itemView.findViewById(R.id.person_division_txt);
            person_hno_txt = itemView.findViewById(R.id.person_hno_txt);
            person_name_txt = itemView.findViewById(R.id.person_name_txt);
            person_nic_txt = itemView.findViewById(R.id.person_nic_txt);
            person_gender_txt = itemView.findViewById(R.id.person_gender_txt);
        }
    }
}
