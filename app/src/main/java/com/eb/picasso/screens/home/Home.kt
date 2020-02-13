package com.eb.picasso.screens.home
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eb.movieapp.adapter.PicAdapter
import com.eb.picasso.commons.ApiService
import com.eb.picasso.commons.extensions.hide
import com.eb.picasso.commons.extensions.show
import com.eb.picasso.commons.models.Picture
import com.eb.picasso.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class Home : Fragment() {

    private val apiService: ApiService by inject()

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(apiService,context!!)
    }

    var page=1
    var total=0
    private var intArray:IntArray= intArrayOf(0,0)
    private var loading = false

    lateinit var layoutManager: StaggeredGridLayoutManager




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)

        val adapter= PicAdapter(mutableListOf())

        layoutManager = binding.plantList.layoutManager as StaggeredGridLayoutManager

        binding.plantList.adapter = adapter

        binding.plantList.addOnScrollListener(setScrollListener())
        subscribeUi(adapter)
        return binding.root
    }




    private fun subscribeUi(adapter: PicAdapter) {

            viewModel.getAllPictures().observe(viewLifecycleOwner, Observer {
                total=it.size
                adapter.refresh(it);
            })

            viewModel.loading.observe(viewLifecycleOwner, Observer {
                if(it) pb.show() else pb.hide()
            })
    }

    private fun setScrollListener():RecyclerView.OnScrollListener{
        return object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                intArray= layoutManager.findLastCompletelyVisibleItemPositions(intArray)
                if(intArray.isNotEmpty() && !loading)
                    if (intArray[1] >= total - 2)
                        viewModel.fetchMovies(++page)
            }
        }
    }
}
