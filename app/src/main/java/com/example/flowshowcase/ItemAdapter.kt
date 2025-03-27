package com.example.flowshowcase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flowshowcase.databinding.ListItemTaskBinding


class ItemAdapter(private val tasks: List<Task>, private val onDeleteClick: (Int) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ListItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val task = tasks[position]
        holder.binding.taskTitle.text = task.title
        holder.binding.checkBox.isChecked = task.isCompleted

        holder.binding.removeTaskButton.setOnClickListener {
            onDeleteClick(task.id)
        }
    }

    override fun getItemCount() = tasks.size


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    }


}