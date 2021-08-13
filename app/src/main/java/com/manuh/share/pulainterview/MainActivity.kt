package com.manuh.share.pulainterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.manuh.share.pulainterview.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[AppViewModel::class.java]
        viewModel.getItem()
        viewModel.getQuestion("q_farmer_name")?.observe(this) { item ->
            Toast.makeText(this, item?.question_text.toString(), Toast.LENGTH_LONG).show()
        }

    }
}