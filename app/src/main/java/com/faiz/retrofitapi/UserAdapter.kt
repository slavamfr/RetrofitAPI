package com.faiz.retrofitapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faiz.retrofitapi.databinding.ItemUserBinding
import com.faiz.retrofitapi.model.Data

class UserAdapter(
    private val userList: List<Data>,
    private val onItemClick: (Data) -> Unit
) : RecyclerView.Adapter<UserAdapter.ItemUserViewHolder>() {

    inner class ItemUserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Data) {
            binding.userName.text = "${user.firstName} ${user.lastName}"
            binding.userEmail.text = user.email

            Glide.with(binding.userImg.context).load(user.avatar).into(binding.userImg)

            binding.root.setOnClickListener {
                onItemClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemUserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size
}