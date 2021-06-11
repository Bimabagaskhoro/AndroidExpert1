package com.bangkit.tvshow.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.tvshow.favorite.databinding.ActivityFavoriteBinding
import com.bangkit.tvshow.favorite.di.favoriteModule
import com.bimabagaskhoro.androidexpert1.ui.DetailActivity
import com.bimabagaskhoro.submission.core.ui.TvShowAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favBinding: ActivityFavoriteBinding
    private val favViewModel: FavoriteViewModel by viewModel()

    companion object {
        const val FAVORITE = "FAVORITE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favBinding.root)

        val actionbar = supportActionBar
        actionbar!!.title = FAVORITE

        loadKoinModules(favoriteModule)

        val favAdapter = TvShowAdapter()
        favAdapter.onItemClick = { tvShow ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, tvShow)
                startActivity(it)
            }
        }

        favViewModel.favTvShow.observe(this, {
            favAdapter.setData(it)
        })

        favBinding.rvTvShow.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = favAdapter
        }

    }
}