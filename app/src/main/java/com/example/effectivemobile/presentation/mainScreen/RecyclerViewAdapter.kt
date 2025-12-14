package com.example.effectivemobile.presentation.mainScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.data.dto.EntityForCourseDto
import com.example.effectivemobile.databinding.CourseCardBinding
import com.example.effectivemobile.presentation.extensions.toReadableDate

class RecyclerViewAdapter() : RecyclerView.Adapter<MyPlaylistViewHolder>() {

    private var data: List<EntityForCourseDto> = emptyList()

    fun setData(data: List<EntityForCourseDto>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPlaylistViewHolder {
        return MyPlaylistViewHolder(
            CourseCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MyPlaylistViewHolder,
        position: Int
    ) {
        val item = data.getOrNull(position)
        with(holder.binding) {
            cardTitle.text = item?.title
            descriptionTextView.text = item?.text
            priceTextView.text = String.format("%s ₽", item?.price ?: "—")
            textViewRating.text = item?.rate
            textViewDate.text = item?.startDate?.toReadableDate()
        }
    }
}

class MyPlaylistViewHolder(val binding: CourseCardBinding) :
    RecyclerView.ViewHolder(binding.root)
