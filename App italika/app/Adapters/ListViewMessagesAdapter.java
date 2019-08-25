package com.example.italika.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.italika.DataClass.ChatMessages;
import com.example.italika.R;
import com.example.italika.Request.Login;

import java.util.List;


public class ListViewMessagesAdapter extends RecyclerView.Adapter<ListViewMessagesAdapter.ViewHolder> {

    private List<ChatMessages> list;
    private Context vContext;
    private int idIncidence;

    public ListViewMessagesAdapter(Context vContext, List<ChatMessages> list) {
        this.vContext= vContext;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_messages,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewMessagesAdapter.ViewHolder holder, final int position) {
        if (holder == null) {
            return;
        }
        holder.bindView(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView twDate,twNameBlue,twNameWhite,twMessageBlue,twMessageWhite,vistoB,vistoW;
        private CardView cardViewBlue,cardViewWhite;
        private View layout;
        private int position;


        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            twDate=layout.findViewById(R.id.adapterListMessagesTwDate);
            twNameBlue=layout.findViewById(R.id.adapterListMessagesTwNameBlue);
            twNameWhite=layout.findViewById(R.id.adapterListMessagesTwNameWhite);
            twMessageBlue=layout.findViewById(R.id.adapterListMessagesTwMessageBlue);
            twMessageWhite=layout.findViewById(R.id.adapterListMessagesTwMessageWhite);
            cardViewBlue=layout.findViewById(R.id.adapterListMessagesCardVwBlue);
            cardViewWhite=layout.findViewById(R.id.adapterListMessagesCardVwWhite);
            vistoB=layout.findViewById(R.id.adapterListMessagesVistoB);
            vistoW=layout.findViewById(R.id.adapterListMessagesVistoW);



        }

        public void bindView(final int pos) {
            this.position = pos;

            twDate.setText(list.get(position).getDate());
            if(list.get(position).getRol()== Login.Instance().getId()){
                cardViewBlue.setVisibility(View.VISIBLE);
                twNameBlue.setText(list.get(position).getNameSender());
                twMessageBlue.setText(list.get(position).getMessage());
                if (list.get(position).getRol()==1){
                    if (list.get(position).getReadC()==1 && list.get(position).getReadS()==1){
                        vistoB.setVisibility(View.VISIBLE);
                    }
                }else if (list.get(position).getRol()==2){
                    if (list.get(position).getReadC()==1){
                        vistoB.setVisibility(View.VISIBLE);
                    }
                }else if (list.get(position).getRol()==4){
                    if (list.get(position).getReadS()==1){
                        vistoB.setVisibility(View.VISIBLE);
                    }
                }

            }else {
                cardViewWhite.setVisibility(View.VISIBLE);
                twNameWhite.setText(list.get(position).getNameSender());
                twMessageWhite.setText(list.get(position).getMessage());
                if (list.get(position).getRol()==1){
                    if (list.get(position).getReadC()==1 && list.get(position).getReadS()==1){
                        vistoW.setVisibility(View.VISIBLE);
                    }
                }else if (list.get(position).getRol()==2){
                    if (list.get(position).getReadC()==1){
                        vistoW.setVisibility(View.VISIBLE);
                    }
                }else if (list.get(position).getRol()==4){
                    if (list.get(position).getReadS()==1){
                        vistoW.setVisibility(View.VISIBLE);
                    }
                }
            }

        }

    }

}
