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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eb.picasso.adapter.PictureAdapter
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
        HomeViewModelFactory(apiService)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)

        val adapter = PictureAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: PictureAdapter) {
        viewModel.movieLiveData.observe(viewLifecycleOwner) { movieDao ->
            adapter.submitList(movieDao.hits)
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if(it) pb.show() else pb.hide()
        })
    }
}
