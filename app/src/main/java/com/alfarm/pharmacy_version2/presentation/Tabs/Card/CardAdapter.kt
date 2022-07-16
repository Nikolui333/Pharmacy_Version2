package com.alfarm.pharmacy_version2.presentation.Tabs.Card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alfarm.pharmacy_version2.R
import com.alfarm.pharmacy_version2.data.models.CardModel
import com.alfarm.pharmacy_version2.databinding.CardItemBinding
import com.squareup.picasso.Picasso

                    // Unit эквивалентен void в Java
class CardAdapter  (private val deleteFromCard:(CardModel)->Unit, private val lessCount:(CardModel)->Unit,
                    private val moreCount:(CardModel)->Unit):
    RecyclerView.Adapter<CardAdapter.CardHolder>() {

    private val productsFromCard = ArrayList<CardModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return CardHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsFromCard.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(productsFromCard[position], deleteFromCard,
        moreCount, lessCount)

    }

    fun setList(cardList: List<CardModel>) {
        productsFromCard.clear()
        productsFromCard.addAll(cardList)

    }

    class CardHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            cardModel: CardModel, deleteFromCard: (CardModel) -> Unit,
            moreCount: (CardModel) -> Unit, lessCount: (CardModel) -> Unit
        ) {

            val getImage = cardModel.image
            Picasso.get().load(getImage).into(binding.imageProductCard)
            binding.nameProductCard.text = cardModel.name
            binding.countProductBasket.text = cardModel.count
            binding.priceProductCard.text = cardModel.price
            binding.totalPriceProductCard.text = cardModel.totalPrice

            binding.removeFromCardProductCard.setOnClickListener(View.OnClickListener {
                deleteFromCard(cardModel) // удаление из карточки, когда пользоваетль находится в корзине
            })

            binding.moreProductBasket.setOnClickListener(View.OnClickListener {
                moreCount(cardModel) //увеличить колличество единиц товара

            })

            binding.lessProductBasket.setOnClickListener(View.OnClickListener {
                lessCount(cardModel) // уменьшить колличество единиц товара

            })
        }
    }
}