package com.picpay.desafio.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    var users: AsyncListDiffer<User> = AsyncListDiffer(this, DiffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return UserListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users.currentList[position])
    }

    override fun getItemCount(): Int = users.currentList.size

    fun updateUsers(userList: List<User>) = users.submitList(userList)
}