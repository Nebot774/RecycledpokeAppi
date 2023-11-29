package com.example.recycledpokeappi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    //atributos
    private List<Pokemon> pokemonList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // Constructor
    PokemonAdapter(Context context, List<Pokemon> data) {
        this.mInflater = LayoutInflater.from(context);
        this.pokemonList = data;
    }

    // Infla el layout del item
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    // Vincula datos al TextView en cada item
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = pokemonList.get(position).getName();
        holder.myTextView.setText(name);
    }

    // Total de items
    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    // Almacena y recicla vistas
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.textViewName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // Permite clics en items
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    //metodo para actualizar la lista de Pokemons
    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    //Metodo para obtener un Pokemon en una posicion especifica
    public Pokemon getPokemonAtPosition(int position) {
        return pokemonList.get(position);
    }


}

