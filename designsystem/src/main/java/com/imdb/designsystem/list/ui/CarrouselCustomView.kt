package com.imdb.designsystem.list.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
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

    var onClick: ((cardModel: CardModel) -> Unit)? = null

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
            adapter = Adapter(listOf()){
                onClick?.invoke(it)
            }
        }
    }


    fun setItems(items: List<ItemListModel>) {
        this.items = items
        (binding.rvList.adapter as? Adapter)?.apply {
            this.items = items
            this.notifyDataSetChanged()
        }
    }

    class Adapter(
        var items: List<ItemListModel>,
        var onClick: ((cardModel: CardModel) -> Unit)? = null
    ) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            ItemCarrouselLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {
            onClick?.invoke(it)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount() = items.size
    }

    class ViewHolder(
        private val itemCarrouselLayoutBinding: ItemCarrouselLayoutBinding,
        private val onClick: (cardModel: CardModel) -> Unit
    ) :
        RecyclerView.ViewHolder(itemCarrouselLayoutBinding.root) {

        fun bind(itemListModel: ItemListModel) {
            itemCarrouselLayoutBinding.cardModel =
                CardModel(itemListModel.id, itemListModel.title, itemListModel.imageUrl)
            itemCarrouselLayoutBinding.viewHolder = this
        }

        fun onItemClicked(cardModel: CardModel) {
            onClick(cardModel)
        }
    }
}

@BindingAdapter("modelList")
fun CarrouselCustomView.setModel(model: List<ItemListModel>) {
    setItems(model)
}
