package com.eb.picasso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eb.picasso.commons.extensions.launchActivity
import com.eb.picasso.screens.search.Search
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            launchActivity<Search> {  }
        }


    }
}
