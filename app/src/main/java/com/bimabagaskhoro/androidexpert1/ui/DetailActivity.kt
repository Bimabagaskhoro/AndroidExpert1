package com.bimabagaskhoro.androidexpert1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bimabagaskhoro.androidexpert1.R
import com.bimabagaskhoro.androidexpert1.databinding.ActivityDetailBinding
import com.bimabagaskhoro.androidexpert1.viewmodel.DetailViewModel
import com.bimabagaskhoro.submission.core.domain.model.TvShow
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DETAIL_TV_SHOW = "Detail"
        const val DATA = "data"
        const val LINK_IMAGE = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var detailBinding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val actionbar = supportActionBar
        actionbar!!.title = DETAIL_TV_SHOW

        val detailtvShow = intent.getParcelableExtra<TvShow>(DATA)
        showDetailtvShow(detailtvShow)
    }

    private fun showDetailtvShow(detailtvShow: TvShow?) {
        detailtvShow?.let {
            with(detailBinding) {
                Glide.with(this@DetailActivity)
                    .load(LINK_IMAGE + detailtvShow.avatar)
                    .transform(RoundedCorners(20))
                    .into(imgDetail)

                tvTitleDetail.text = detailtvShow.title
                tvDateDetail.text = detailtvShow.date
                tvRatingDetail.text = detailtvShow.original_language
                tvDescDetail.text = detailtvShow.desc
                tvVoteCount.text = detailtvShow.vote_average.toString()

                var favStatus = detailtvShow.isFav
                setStatusFav(favStatus)
                fab.setOnClickListener {
                    favStatus = !favStatus
                    detailViewModel.setTvShowFav(detailtvShow, favStatus)
                    setStatusFav(favStatus)
                }
            }
        }
    }

    private fun setStatusFav(statusFav: Boolean) {
        if (statusFav) {
            detailBinding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_love_true
                )
            )
        } else {
            detailBinding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_fav_false
                )
            )
        }
    }

}