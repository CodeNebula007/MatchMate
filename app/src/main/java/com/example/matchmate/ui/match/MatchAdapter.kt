package com.example.matchmate.ui.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchmate.R
import com.example.matchmate.data.model.Match
import com.example.matchmate.data.model.MatchStatus

class MatchAdapter(
    private var matches: List<Match>,
    private val onStatusUpdate: (Match, MatchStatus) -> Unit
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]

        holder.textName.text = match.fullName
        holder.textLocation.text = "${match.city}, ${match.country}"
        holder.textAge.text = "Age: ${match.age}"
        holder.textStatus.text = when (match.status) {
            MatchStatus.ACCEPTED -> "Member Accepted"
            MatchStatus.DECLINED -> "Member Declined"
            else -> ""
        }

        Glide.with(holder.itemView).load(match.imageUrl).into(holder.imageProfile)

        holder.btnAccept.setOnClickListener {
            onStatusUpdate(match, MatchStatus.ACCEPTED)
        }

        holder.btnDecline.setOnClickListener {
            onStatusUpdate(match, MatchStatus.DECLINED)
        }
    }

    override fun getItemCount(): Int = matches.size

    fun updateData(newMatches: List<Match>) {
        matches = newMatches
        notifyDataSetChanged()
    }

    inner class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageProfile: ImageView = view.findViewById(R.id.imageProfile)
        val textName: TextView = view.findViewById(R.id.textName)
        val textLocation: TextView = view.findViewById(R.id.textLocation)
        val textAge: TextView = view.findViewById(R.id.textAge)
        val textStatus: TextView = view.findViewById(R.id.textStatus)
        val btnAccept: Button = view.findViewById(R.id.btnAccept)
        val btnDecline: Button = view.findViewById(R.id.btnDecline)
    }
}
