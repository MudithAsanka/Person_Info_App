package dev.mudithasanka.personinfoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;      //for refresh card view after update
    ArrayList person_id, person_division, person_hno, person_name, person_nic, person_gender;

    //ArrayList sid, sdivision, shno, sname, snic, sgender;

    //ArrayList fid, fdivision, fhno, fname, fnic, fgender;
    //private ArrayList<> source;

    //private Timer timer;

    CustomAdapter(Activity activity, Context context, ArrayList person_id, ArrayList person_division, ArrayList person_hno,
                  ArrayList person_name, ArrayList person_nic, ArrayList person_gender){
        this.activity = activity;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.person_division_txt.setText(String.valueOf(person_division.get(position)));
        holder.person_hno_txt.setText(String.valueOf(person_hno.get(position)));
        holder.person_name_txt.setText(String.valueOf(person_name.get(position)));
        holder.person_nic_txt.setText(String.valueOf(person_nic.get(position)));
        holder.person_gender_txt.setText(String.valueOf(person_gender.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdatePerson.class);
                intent.putExtra("id", String.valueOf(person_id.get(position)));
                intent.putExtra("division", String.valueOf(person_division.get(position)));
                intent.putExtra("hno", String.valueOf(person_hno.get(position)));
                intent.putExtra("name", String.valueOf(person_name.get(position)));
                intent.putExtra("nic", String.valueOf(person_nic.get(position)));
                intent.putExtra("gender", String.valueOf(person_gender.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return person_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView person_division_txt, person_hno_txt, person_name_txt, person_nic_txt, person_gender_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            person_division_txt = itemView.findViewById(R.id.person_division_txt);
            person_hno_txt = itemView.findViewById(R.id.person_hno_txt);
            person_name_txt = itemView.findViewById(R.id.person_name_txt);
            person_nic_txt = itemView.findViewById(R.id.person_nic_txt);
            person_gender_txt = itemView.findViewById(R.id.person_gender_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    /*
    private void searchPerson(final String searchKeyword){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(searchKeyword.trim().isEmpty()){
                    sid = person_id;
                    sdivision = person_division;
                    shno = person_hno;
                    sname = person_name;
                    snic = person_nic;
                    sgender = person_gender;
                    Toast.makeText(context,sname, Toast.LENGTH_SHORT).show();
                }else{
                    for(int i=0; i<=getItemCount(); i++){
                        if(sname.toString().toLowerCase().contains(searchKeyword.toLowerCase())
                        || snic.toString().toLowerCase().contains(searchKeyword.toLowerCase())) {
                            sid = person_id;
                            sdivision = person_division;
                            shno = person_hno;
                            sname = person_name;
                            snic = person_nic;
                            sgender = person_gender;
                        }
                    }

                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }, 500);
    }


    public void cancelTimer(){
        if(timer != null){
            timer.cancel();
        }
    }

    */
}
