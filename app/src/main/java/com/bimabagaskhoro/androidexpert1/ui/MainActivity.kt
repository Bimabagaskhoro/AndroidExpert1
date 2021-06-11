package com.bimabagaskhoro.androidexpert1.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bimabagaskhoro.androidexpert1.R
import com.bimabagaskhoro.androidexpert1.databinding.ActivityMainBinding
import com.bimabagaskhoro.androidexpert1.viewmodel.MainViewModel
import com.bimabagaskhoro.submission.core.source.Resource
import com.bimabagaskhoro.submission.core.ui.TvShowAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val tvShowViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvShowAdapter = TvShowAdapter()
        tvShowAdapter.onItemClick = { tvShow ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, tvShow)
                startActivity(it)
            }
        }

        tvShowViewModel.tvShow.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> true.progressbar()
                    is Resource.Success -> {
                        false.progressbar()
                        tvShowAdapter.setData(it.data)
                    }
                    is Resource.Error -> {
                        false.progressbar()
                    }
                }
            }
        })

        setRecyclerView()
    }

    private fun setRecyclerView() {
        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    private fun Boolean.progressbar() {
        binding.progressBar.visibility = if (this) View.VISIBLE else View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                val uri = Uri.parse("tvshow://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}