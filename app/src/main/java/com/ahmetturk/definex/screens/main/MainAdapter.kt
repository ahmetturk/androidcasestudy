package com.ahmetturk.definex.screens.main

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.ahmetturk.definex.R
import com.ahmetturk.definex.databinding.ItemProductBinding
import com.ahmetturk.definex.network.main.Product
import com.bumptech.glide.Glide

private const val THREE_COLUMN = 3
private const val DESCRIPTION_TEXT_SIZE_AT_THREE_COLUMN = 12f
private const val PRICE_TEXT_SIZE_WITHOUT_OLD_PRICE = 20f
private const val PRICE_TEXT_SIZE_WITH_OLD_PRICE = 14f
private const val RATING_BAR_DIVIDER = 20f // 100% divided by 5 stars

class MainAdapter(private val horizontalSpanCount: Int? = null) : RecyclerView.Adapter<MainViewHolder>() {

    private var list: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)

        horizontalSpanCount?.let {
            val totalWidth = binding.root.resources.displayMetrics.widthPixels
            binding.root.layoutParams = binding.root.layoutParams.apply {
                width = ((totalWidth - it * (binding.root.marginStart + binding.root.marginEnd)) / it)
            }

            if (it == THREE_COLUMN) {
                binding.descriptionTextView.textSize = DESCRIPTION_TEXT_SIZE_AT_THREE_COLUMN
            }
        }

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setList(newList: List<Product>) {
        list = newList
        notifyDataSetChanged()
    }

}

class MainViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        Glide.with(binding.productImageView).load(product.imageUrl).into(binding.productImageView)
        binding.descriptionTextView.text = product.description
        binding.priceTextView.text = "${product.price.value} ${product.price.currency}"

        binding.priceTextView.textSize = if (product.oldPrice == null) {
            PRICE_TEXT_SIZE_WITHOUT_OLD_PRICE
        } else {
            PRICE_TEXT_SIZE_WITH_OLD_PRICE
        }

        if (product.oldPrice == null) {
            binding.oldPriceTextView.visibility = View.GONE
        } else {
            binding.oldPriceTextView.visibility = View.VISIBLE

            val oldPriceText = SpannableStringBuilder().apply {
                append(
                    "${product.oldPrice.value} ${product.oldPrice.currency}",
                    StrikethroughSpan(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                if (product.discount.isNotEmpty()) {
                    append(" • ")
                    append(
                        product.discount,
                        ForegroundColorSpan(ContextCompat.getColor(binding.root.context, R.color.discountText)),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
            binding.oldPriceTextView.text = oldPriceText
        }

        if (product.ratePercentage == null) {
            binding.ratingBar.visibility = View.INVISIBLE
        } else {
            binding.ratingBar.visibility = View.VISIBLE
            binding.ratingBar.rating = product.ratePercentage / RATING_BAR_DIVIDER
        }
    }
}
