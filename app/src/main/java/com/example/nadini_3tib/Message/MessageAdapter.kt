package com.example.nadini_3tib.Message

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nadini_3tib.databinding.ItemMessageBinding
import androidx.appcompat.app.AlertDialog

class MessageAdapter(
    private val context: Context,
    private val messages: List<MessageModel>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(
        val binding: ItemMessageBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MessageViewHolder,
        position: Int
    ) {
        val data = messages[position]

        Glide.with(context)
            .load(data.avatarUrl)
            .into(holder.binding.avatarImg)

        holder.binding.textSender.text = data.senderName
        holder.binding.textMessage.text = data.messageText

        holder.binding.textDistance.text =
            "Jarak: ${data.distance} • ±${data.travelTime}"

        // Klik biasa: buka halaman chat
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, MessageActivity::class.java)
            intent.putExtra("SENDER", data.senderName)
            intent.putExtra("PESAN", data.messageText)
            context.startActivity(intent)
        }

        holder.binding.root.setOnLongClickListener {
            AlertDialog.Builder(context)
                .setTitle("Kelola Tenaga Profesional")
                .setItems(arrayOf("Edit", "Hapus")) { _, pilihan ->

                    if (pilihan == 0) {
                        val intent = Intent(context, KonsultasiFormActivity::class.java)

                        intent.putExtra("ID", data.id)
                        intent.putExtra("NAMA", data.senderName)
                        intent.putExtra("PESAN", data.messageText)
                        intent.putExtra("AVATAR", data.avatarUrl)
                        intent.putExtra("KATEGORI", data.kategori)
                        intent.putExtra("DISTANCE", data.distance)
                        intent.putExtra("TRAVELTIME", data.travelTime)

                        context.startActivity(intent)

                    } else if (pilihan == 1) {
                        onDelete(data.id)
                    }
                }
                .show()

            true
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}
