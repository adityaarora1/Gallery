package com.sample.gallery.presentation.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.gallery.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gallery_detail.*

@AndroidEntryPoint
class GalleryDetailFragment : Fragment() {
    private val viewModel: GalleryViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (activity is AppCompatActivity) {
                        findNavController().navigateUp()
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_gallery_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        initUI()
    }

    private fun setActionBar() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        }
    }

    private fun initUI() {
        viewModel.getSelectedGalleryItem().observe(viewLifecycleOwner, { gallery ->

            (activity as AppCompatActivity).supportActionBar?.title = gallery.title
            textTitle.text = gallery.title
            textScore.text = gallery.score.toString()
            textDesc.text = gallery.description
            textUps.text = gallery.ups.toString()
            textDowns.text = gallery.downs.toString()

            Glide.with(this)
                .load(gallery.coverUrl)
                .centerInside()
                .thumbnail(0.5f)
                .placeholder(R.drawable.ic_launcher_background)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageGallery)
        })
    }
}