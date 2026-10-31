package com.example.nadini_3tib

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nadini_3tib.databinding.ItemTutorialBinding

class TutorialAdapter(
    private val tutorialList: List<TutorialModel>
) : RecyclerView.Adapter<TutorialAdapter.TutorialViewHolder>() {

    class TutorialViewHolder(
        val binding: ItemTutorialBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TutorialViewHolder {

        val binding = ItemTutorialBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TutorialViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TutorialViewHolder,
        position: Int
    ) {
        val tutorial = tutorialList[position]

        holder.binding.txtIllustration.text = tutorial.illustration
        holder.binding.txtTitle.text = tutorial.title
        holder.binding.txtDescription.text = tutorial.description
    }

    override fun getItemCount(): Int {
        return tutorialList.size
    }
}