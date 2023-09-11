package com.lab.samplenavigation

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lab.samplenavigation.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadImage()
        binding.refreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.black))
        binding.refreshLayout.setOnRefreshListener {
            loadImage()
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun loadImage() {
        val randomSize = Random.nextInt(200, 800)
        Glide.with(this)
            .load("https://source.unsplash.com/user/c_v_r/${randomSize}x${randomSize}")
            .centerCrop()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    val bitmap = resource.toBitmap()
                    binding.imageView.setImageBitmap(bitmap)
                    createPaletteAsync(bitmap)
                    return true
                }
            })
            .into(binding.imageView)
    }

    private fun createPaletteAsync(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            if (palette == null) return@generate
            val vibrant =
                palette.vibrantSwatch ?: palette.dominantSwatch ?: palette.darkVibrantSwatch ?: palette.lightVibrantSwatch ?: return@generate
            val vibrantRgb = vibrant.rgb
            val gradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(vibrantRgb, Color.TRANSPARENT))
            binding.backgroundGradient.background = gradient
        }
    }
}