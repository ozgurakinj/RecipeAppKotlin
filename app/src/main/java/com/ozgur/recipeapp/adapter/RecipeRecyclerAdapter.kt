package com.ozgur.recipeapp.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozgur.recipeapp.databinding.RecyclerRowBinding
import com.ozgur.recipeapp.model.Recipe
import com.squareup.picasso.Picasso

class RecipeRecyclerAdapter(val recipeList: ArrayList<Recipe>): RecyclerView.Adapter<RecipeRecyclerAdapter.PostHolder>() {

    private lateinit var clicklistener : onItemClickListener
    public var myrecipeList = recipeList

    interface onItemClickListener{
        fun onItemClick(position: Int, recipe: Recipe)
    }

    fun setOnItemClickListener(listener : onItemClickListener){
        clicklistener = listener
    }

    class PostHolder(recipeList: ArrayList<Recipe>, val binding: RecyclerRowBinding, listener : onItemClickListener): RecyclerView.ViewHolder(binding.root) {
        init{
            itemView.setOnClickListener(){
                listener.onItemClick(adapterPosition,recipeList[adapterPosition])

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(recipeList,binding,clicklistener)

    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.recipeNameTextView.text = recipeList.get(position).name
        Picasso.get().load(recipeList.get(position).imageURL).into(holder.binding.recipeImageView)


    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}