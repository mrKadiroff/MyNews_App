package com.example.news_app.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.R
import com.example.news_app.database.CategoryEntity
import com.example.news_app.databinding.CategoryItemBinding

class CategoryAdapter(
    var list: List<CategoryEntity>,var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.Vh>() {

    inner class Vh(var malumotItemBinding:CategoryItemBinding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        @SuppressLint("ResourceType")
        fun onBind(malumotlar: CategoryEntity, position: Int) {

         malumotItemBinding.categoryname.text = malumotlar.name
//            malumotItemBinding.qolip.setBackgroundColor(Color.parseColor("#475AD7"))

            if (malumotlar.selected == true){
                malumotItemBinding.qolip.setBackgroundColor(Color.parseColor("#475AD7"))
                malumotItemBinding.categoryname.setTextColor(Color.parseColor("#FFFFFF"))
            }


            malumotItemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(malumotlar,adapterPosition,malumotItemBinding)
            }

            when (malumotlar.id){

                1 ->  malumotItemBinding.icons.setImageResource(R.drawable.business)
                2 ->  malumotItemBinding.icons.setImageResource(R.drawable.entertainment)
                3 ->  malumotItemBinding.icons.setImageResource(R.drawable.general)
                4 ->  malumotItemBinding.icons.setImageResource(R.drawable.healthcare)
                5 ->  malumotItemBinding.icons.setImageResource(R.drawable.science)
                6 ->  malumotItemBinding.icons.setImageResource(R.drawable.sport)
                7 ->  malumotItemBinding.icons.setImageResource(R.drawable.technology)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
        //dsdsdsdsdsdsdfdf
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemClick(malumotlar: CategoryEntity,position: Int,malumotItemBinding: CategoryItemBinding)
    }


}