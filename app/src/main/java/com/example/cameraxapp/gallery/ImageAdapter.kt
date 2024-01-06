package com.example.cameraxapp.gallery

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cameraxapp.R
import com.example.cameraxapp.databinding.RowCustomRecyclerItemBinding
import java.io.File

class ImageAdapter(private var context: Context, private var imagesList: ArrayList<Image>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(private var context: Context, private val binding: RowCustomRecyclerItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            Glide.with(binding.root)
                .load(image.imagePath)
                .apply(RequestOptions().centerCrop())
                .into(binding.rowImage)

            fun isVideoFile(fileName: String): Boolean {
                val videoExtensions = arrayOf(".mp4", ".avi", ".mov", ".mkv")
                val lowerCaseFileName = fileName.toLowerCase()
                return videoExtensions.any { lowerCaseFileName.endsWith(it) }
            }

            binding.rowImage.setOnClickListener {
                val intent = Intent(context, ImageFullActivity::class.java)
                intent.putExtra("path", image.imagePath)
                intent.putExtra("name", image.imageName)
                context.startActivity(intent)
            }

            if(isVideoFile(image.imageName.toString())){
                binding.rowImagePlay.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(context, RowCustomRecyclerItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imagesList[position])
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}