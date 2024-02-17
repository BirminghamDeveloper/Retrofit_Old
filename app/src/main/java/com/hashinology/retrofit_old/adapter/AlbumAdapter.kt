package com.hashinology.retrofit_old.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hashinology.retrofit_old.ClickedNotifier
import com.hashinology.retrofit_old.R
import com.hashinology.retrofit_old.models.AlbumModel

class AlbumAdapter(
    val albumModelList: List<AlbumModel>,
    val clickedNotifier: ClickedNotifier
): RecyclerView.Adapter<AlbumAdapter.ViewHolders>() {
    val list = albumModelList
    class ViewHolders(itemView: View): RecyclerView.ViewHolder(itemView) {
        val albumId = itemView.findViewById<TextView>(R.id.tvAlbumValue)
        val id = itemView.findViewById<TextView>(R.id.tvIDValue)
        val title = itemView.findViewById<TextView>(R.id.tvTitleValue)
        val imgAlbum = itemView.findViewById<ImageView>(R.id.ivAlbum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.ViewHolders {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_recyclerview, parent, false)
        return ViewHolders(view)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolders, position: Int) {
        val album = albumModelList[position]
        Glide.with(holder.itemView.context)
            .load(album.thumbnailUrl)
            .into(holder.imgAlbum)

        holder.albumId.text = album.albumId.toString()
        holder.id.text = album.id.toString()
        holder.title.text = album.title

        holder.itemView.setOnClickListener {
            clickedNotifier.getClciked(album)
        }
    }

    override fun getItemCount(): Int {
        return albumModelList.size
    }


}