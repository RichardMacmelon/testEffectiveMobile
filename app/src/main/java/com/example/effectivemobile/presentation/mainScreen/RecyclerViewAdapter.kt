package com.example.effectivemobile.presentation.mainScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.data.dto.EntityForCourseDto
import com.example.effectivemobile.data.tables.СoursesDb
import com.example.effectivemobile.databinding.CourseCardBinding
import com.example.effectivemobile.presentation.extensions.toReadableDate

class RecyclerViewAdapter(
    private val onLikeChanged: (СoursesDb, Boolean) -> Unit,
) : RecyclerView.Adapter<MyPlaylistViewHolder>() {

    private var data: List<EntityForCourseDto> = emptyList()
    private var likedIds: List<Int> = emptyList()

    fun setData(data: List<EntityForCourseDto>, likedIds: MutableList<Int>) {
        this.data = data
        this.likedIds = likedIds
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
        val item = data.getOrNull(position) ?: return
        val updatedItem = СoursesDb(
            id = item.id,
            title = item.title,
            description = item.text,
            price = item.price,
            rate = item.rate,
            date = item.startDate,
            hasLike = true
        )

        with(holder.binding) {
            cardTitle.text = item.title
            descriptionTextView.text = item.text
            priceTextView.text = String.format("%s ₽", item.price)
            textViewRating.text = item.rate
            textViewDate.text = item.startDate.toReadableDate()

            checkbox.setOnCheckedChangeListener(null)
            checkbox.isChecked = likedIds.contains(item.id)

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                onLikeChanged(updatedItem, isChecked)
            }

        }
    }
}

class MyPlaylistViewHolder(val binding: CourseCardBinding) :
    RecyclerView.ViewHolder(binding.root)
