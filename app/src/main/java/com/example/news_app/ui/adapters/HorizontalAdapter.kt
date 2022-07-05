package com.example.news_app.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.database.CategoryEntity
import com.example.news_app.databinding.CategoryItemBinding
import com.example.news_app.databinding.RvItemhorBinding
import com.example.news_app.databinding.SearchiItemBinding
import com.example.news_app.retrofit.models.Article

class HorizontalAdapter(
    var list: List<Article>,var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HorizontalAdapter.Vh>() {

    inner class Vh(var malumotItemBinding:RvItemhorBinding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {


        fun onBind(malumotlar: Article, position: Int) {
            Glide.with(malumotItemBinding.root).load(malumotlar.urlToImage).into(malumotItemBinding.iconmain);
            malumotItemBinding.titlee.text = malumotlar.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemhorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
        //dsdsdsdsdsdsdfdf
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemClick(malumotlar: Article)
    }


}