package com.imdb.designsystem.list.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imdb.designsystem.databinding.CustomViewCarrouselImagesBinding
import com.imdb.designsystem.databinding.ItemCarrouselLayoutBinding
import com.imdb.designsystem.list.model.CardModel
import com.imdb.designsystem.list.model.ItemListModel


class CarrouselCustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private var items: List<ItemListModel> = mutableListOf()

    private val binding by lazy {
        CustomViewCarrouselImagesBinding.inflate(LayoutInflater.from(context), this, true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        configureList()
    }

    private fun configureList() {
        binding.rvList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = Adapter(listOf())
        }
    }


    fun setItems(items: List<ItemListModel>) {
        this.items = items
        (binding.rvList.adapter as? Adapter)?.apply {
            this.items = items
            this.notifyDataSetChanged()
        }
    }

    class Adapter(var items: List<ItemListModel>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            ItemCarrouselLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount() = items.size
    }

    class ViewHolder(private val itemCarrouselLayoutBinding: ItemCarrouselLayoutBinding) :
        RecyclerView.ViewHolder(itemCarrouselLayoutBinding.root) {

        fun bind(itemListModel: ItemListModel) {
            itemCarrouselLayoutBinding.cardModel =
                CardModel(itemListModel.title, itemListModel.imageUrl)
            itemCarrouselLayoutBinding.viewHolder = this
        }

        fun onItemClicked(cardModel: CardModel) {

        }
    }
}

@BindingAdapter("modelList")
fun CarrouselCustomView.setModel(model: List<ItemListModel>) {
    setItems(model)
}