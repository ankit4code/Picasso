package com.eb.picasso

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.eb.picasso.commons.extensions.launchActivity
import com.eb.picasso.databinding.MenuLayoutBinding
import com.eb.picasso.screens.search.Search
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mContext : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext=this

        fab.setOnClickListener {
            launchActivity<Search> {  }
        }

        bar.setNavigationOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(mContext)
            val binding :MenuLayoutBinding = DataBindingUtil
                .inflate(LayoutInflater.from(this),R.layout.menu_layout,null,false)

            binding.searchTv.setOnClickListener {
                bottomSheetDialog.hide()
                launchActivity<Search> {  }
            }

            bottomSheetDialog.setContentView(binding.root)
            bottomSheetDialog.show()
        }
    }
}
