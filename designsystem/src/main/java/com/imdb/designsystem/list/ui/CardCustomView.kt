package com.imdb.designsystem.list.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.imdb.designsystem.databinding.CustomViewCardBinding
import com.imdb.designsystem.list.model.CardModel

class CardCustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding by lazy {
        CustomViewCardBinding.inflate(LayoutInflater.from(context), this, true)
    }


    fun cardModel(cardModel: CardModel) {
        Glide.with(context).load(cardModel.url).into(binding.image)
        binding.title.text = cardModel.title
    }

}


@BindingAdapter("model")
fun CardCustomView.setModel(cardModel: CardModel) {
    cardModel(cardModel)
}