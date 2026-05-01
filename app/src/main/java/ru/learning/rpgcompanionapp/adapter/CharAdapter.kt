package ru.learning.rpgcompanionapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.learning.rpgcompanionapp.dto.CharData
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ru.learning.rpgcompanionapp.R
import android.net.Uri

class CharAdapter(
    private val onCharClick: (CharData) -> Unit
) : RecyclerView.Adapter<CharAdapter.CharViewHolder>() {

    private var chars = listOf<CharData>()


    class CharViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.charItemNameText)
        val infoText = itemView.findViewById<TextView>(R.id.charItemInfoText)
        val avatarImageView =
            itemView.findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.avatarImageView)
    }

    fun submitList(list: List<CharData>) {
        chars = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return chars.size
    }

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {

        val char = chars[position]

        if (char.charImage.isNotBlank()) {
            holder.avatarImageView.setImageURI(Uri.parse(char.charImage))
        } else {
            holder.avatarImageView.setImageResource(R.drawable.test_char_avatar_image)
        }

        holder.nameText.text = char.charName
        holder.infoText.text = "${char.charRace} • ${char.charClass} • level ${char.charLevel}"

        holder.itemView.setOnClickListener {
            onCharClick(char)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {


        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)

        return CharViewHolder(view)
    }
}