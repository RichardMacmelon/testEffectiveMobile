package com.example.effectivemobile.presentation.favoritesScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.data.tables.СoursesDb
import com.example.effectivemobile.databinding.CourseCardBinding
import com.example.effectivemobile.presentation.extensions.toReadableDate
import com.example.effectivemobile.presentation.mainScreen.MyPlaylistViewHolder

class FavoritesScreenAdapter(private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<MyPlaylistViewHolder>() {

    private var data: List<СoursesDb> = emptyList()

    fun setData(data: List<СoursesDb>) {
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
            descriptionTextView.text = item?.description
            priceTextView.text = String.format("%s ₽", item?.price ?: "—")
            textViewRating.text = item?.rate
            textViewDate.text = item?.date?.toReadableDate()
            checkbox.isChecked = true

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked) {
                    item?.id?.let { onClick(it) }
                }
            }
        }
    }
}