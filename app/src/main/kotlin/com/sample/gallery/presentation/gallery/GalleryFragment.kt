package com.sample.gallery.presentation.gallery

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.sample.gallery.R
import com.sample.gallery.domain.model.Gallery
import com.sample.gallery.presentation.actions.*
import com.sample.gallery.presentation.gallery.adapter.GalleryAdapter
import com.sample.gallery.presentation.gallery.adapter.GalleryLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.floating_action_menu.*
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {
    private val itemClick: (Gallery) -> Unit = {
        findNavController().navigate(R.id.action_galleryFragment_to_galleryDetailFragment)
        galleryViewModel.selectGalleryItem(it)
    }

    private var rootView: View? = null
    private lateinit var galleryAdapter: GalleryAdapter

    private val galleryViewModel: GalleryViewModel by activityViewModels()
    private val sortByViewModel: SortByViewModel by viewModels()
    private val filterSectionViewModel: FilterSectionViewModel by viewModels()
    private val changeWindowViewModel: ChangeWindowViewModel by viewModels()
    private val includeViralViewModel: IncludeViralViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_gallery, container, false)
            galleryAdapter = GalleryAdapter(itemClick)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    private fun setUI() {
        retryButton.setOnClickListener { galleryAdapter.retry() }

        fabSortBy.setOnClickListener {
            floatingLayout.close(true)
            sortByViewModel.showSortByDialog(childFragmentManager)
        }

        fabWindow.setOnClickListener {
            floatingLayout.close(true)
            changeWindowViewModel.showChangeWindowDialog(childFragmentManager)
        }

        fabFilterList.setOnClickListener {
            floatingLayout.close(true)
            filterSectionViewModel.showFilterSectionDialog(childFragmentManager)
        }

        fabViral.setOnClickListener {
            floatingLayout.close(true)
            includeViralViewModel.showIncludeViralDialog(childFragmentManager)
        }

        setupRecyclerView()
    }

    private fun setupObservers() {
        galleryViewModel.getGalleryPagingData().observe(viewLifecycleOwner) { flow ->
            lifecycleScope.launch {
                flow.collectLatest {
                    galleryAdapter.submitData(it)
                }
            }
        }

        sortByViewModel.getSortPositionLiveData().observe(viewLifecycleOwner) { sortPosition ->
            if (sortPosition != sortByViewModel.selectedSortByPosition) {
                sortByViewModel.selectedSortByPosition = sortPosition
                galleryViewModel.nowPlaying(sort = Actions.sortBy[sortPosition])
            }
        }

        filterSectionViewModel.getFilterPositionLiveData().observe(viewLifecycleOwner) { sectionPosition ->
            if (sectionPosition != filterSectionViewModel.selectedSectionPosition) {
                filterSectionViewModel.selectedSectionPosition = sectionPosition
                galleryViewModel.nowPlaying(section = Actions.sections[sectionPosition])
            }
        }

        changeWindowViewModel.getWindowPositionLiveData().observe(viewLifecycleOwner) { windowPosition ->
            if (windowPosition != changeWindowViewModel.selectedWindowPosition) {
                changeWindowViewModel.selectedWindowPosition = windowPosition
                galleryViewModel.nowPlaying(window = Actions.window[windowPosition])
            }
        }

        includeViralViewModel.getIncludeViralLiveData().observe(viewLifecycleOwner) { newSelection ->
                if (newSelection != includeViralViewModel.isViralSelected) {
                    includeViralViewModel.isViralSelected = newSelection
                    galleryViewModel.nowPlaying(showViral = newSelection)
                }

        }
    }

    private fun initAdapter(currentFilter: ViewType) {
        val spanCount = if (requireContext().resources.configuration.orientation ==
            Configuration.ORIENTATION_PORTRAIT
        ) 2 else 3

        recyclerViewGallery.apply {
            when (currentFilter) {
                ViewType.GRID -> {
                    recyclerViewGallery.layoutManager = GridLayoutManager(context, spanCount)
                    (layoutManager as GridLayoutManager).spanSizeLookup =
                        object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                val viewType = galleryAdapter.getItemViewType(position)
                                return if (viewType == GalleryAdapter.ViewType.FOOTER.ordinal) 1
                                else spanCount
                            }
                        }
                }
                ViewType.STAGGERED -> {
                    recyclerViewGallery.layoutManager =
                        StaggeredGridLayoutManager(spanCount, VERTICAL)
                }
                else -> {
                    recyclerViewGallery.layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        initAdapter(galleryViewModel.lastViewType)

        recyclerViewGallery.apply {
            adapter = galleryAdapter.withLoadStateHeaderAndFooter(
                header = GalleryLoadStateAdapter { galleryAdapter.retry() },
                footer = GalleryLoadStateAdapter { galleryAdapter.retry() }
            )

            galleryAdapter.addLoadStateListener { loadState ->
                recyclerViewGallery.isVisible = loadState.source.refresh is LoadState.NotLoading
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                retryButton.isVisible = loadState.source.refresh is LoadState.Error

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                    ?: loadState.source.refresh as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        context,
                        "\uD83D\uDE28 Wooops! ${it.error.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setActionBar()
    }

    private fun setActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_list -> {
                ViewType.LIST.let {
                    galleryViewModel.lastViewType = it
                    initAdapter(it)
                }
                true
            }
            R.id.action_grid -> {
                ViewType.GRID.let {
                    galleryViewModel.lastViewType = it
                    initAdapter(it)
                }
                true
            }
            R.id.action_staggered -> {
                ViewType.STAGGERED.let {
                    galleryViewModel.lastViewType = it
                    initAdapter(it)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    enum class ViewType {
        LIST,
        GRID,
        STAGGERED
    }
}