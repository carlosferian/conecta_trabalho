package com.example.conecta_trabalho.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conecta_trabalho.R;
import com.example.conecta_trabalho.model.Anuncio;
import com.example.conecta_trabalho.model.DataModel;

public class AnuncioAdapter extends RecyclerView.Adapter<AnuncioAdapter.ViewHolder>{

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener{
        boolean onItemLongClick(View view, int position);
    }
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textAnuncianteNome;
        TextView textAnuncianteTelefone;
        TextView textAnuncianteRamo;
        TextView textAnuncioDescricao;

        public ViewHolder(View itemView){
            super(itemView);
            textAnuncianteNome = itemView.findViewById(R.id.textAnuncianteNome);
            textAnuncianteTelefone = itemView.findViewById(R.id.textAnuncianteTelefone);
            textAnuncianteRamo = itemView.findViewById(R.id.textAnuncianteRamo);
            textAnuncioDescricao = itemView.findViewById(R.id.textAnuncioDescricao);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(clickListener != null){
                        clickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    if(longClickListener != null){
                        return longClickListener.onItemLongClick(view, getAdapterPosition());
                    }
                    return false;
                }
            });
        }
    }

    @NonNull
    @Override
    public AnuncioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recyclerview_anuncio, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnuncioAdapter.ViewHolder holder, int position) {
        Anuncio a = DataModel.getInstance().getAnuncio(position);
        holder.textAnuncianteNome.setText(a.getAnunciante());
        holder.textAnuncianteTelefone.setText(a.getTelefone());
        holder.textAnuncianteRamo.setText(a.getRamo());
        holder.textAnuncioDescricao.setText(a.getDescricao());
        Log.v("anuncio adapter", "item" + position + a.getAnunciante());
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getAnunciosSize();
    }
}

