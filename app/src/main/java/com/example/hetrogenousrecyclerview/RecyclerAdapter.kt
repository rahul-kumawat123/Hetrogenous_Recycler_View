package com.example.hetrogenousrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException

class RecyclerAdapter(private val context: Context, private  val listViewType: List<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TEXT_VIEW = 1
        const val IMAGE_VIEW = 2
        const val IMAGE_TEXT_VIEW = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return  when (viewType){
            TEXT_VIEW -> ViewHolderText(layoutInflater.inflate(R.layout.text_layout,parent,false))
            IMAGE_VIEW -> ViewHolderImage(layoutInflater.inflate(R.layout.image_layout,parent,false))
            IMAGE_TEXT_VIEW -> ViewHolderImageText(layoutInflater.inflate(R.layout.text_image_layout,parent,false))
            else -> throw IllegalArgumentException("No ViewHolder")
        }
    }

    override fun getItemCount(): Int {
        return listViewType.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(listViewType[position]){
            TEXT_VIEW -> {
                val viewHolderText = holder as ViewHolderText
                viewHolderText.text.text = context.getString(R.string.new_summer_collection)
            }
            IMAGE_VIEW -> {
                val viewHolderImage = holder as ViewHolderImage
                viewHolderImage.image.setBackgroundResource(R.drawable.myntra)
            }
            IMAGE_TEXT_VIEW -> {
                val viewHolderImageText = holder as ViewHolderImageText
                viewHolderImageText.textWithImage.text = context.getString(R.string.earn_upto_20_cashback_on_credit_cards)
                viewHolderImageText.imageWithText.setBackgroundResource(R.drawable.myntratext)
            }
            else -> {
                throw IllegalArgumentException("No Bind Holder")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listViewType[position]
    }

    inner class ViewHolderText(itemView: View) : RecyclerView.ViewHolder(itemView){
        val text: TextView = itemView.findViewById(R.id.textView)
    }

    inner class ViewHolderImage(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.imageView)
    }

    inner class ViewHolderImageText(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textWithImage: TextView = itemView.findViewById(R.id.textViewOnImageView)
        val imageWithText: ImageView = itemView.findViewById(R.id.imageViewWithTextView)
    }
}