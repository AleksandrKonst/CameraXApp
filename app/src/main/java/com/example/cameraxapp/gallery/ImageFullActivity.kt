package com.example.cameraxapp.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.cameraxapp.R
import com.example.cameraxapp.VideoFragment
import com.example.cameraxapp.databinding.ActivityImageFullBinding

class ImageFullActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageFullBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageFullBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imagePath = intent.getStringExtra("path")
        val imageName = intent.getStringExtra("name")

        Glide.with(this)
            .load(imagePath).into(findViewById(R.id.imageView))

        binding.imageText.setText(imageName.toString())
    }
}