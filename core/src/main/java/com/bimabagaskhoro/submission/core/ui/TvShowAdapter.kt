package com.bimabagaskhoro.submission.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bimabagaskhoro.submission.core.R
import com.bimabagaskhoro.submission.core.databinding.ItemListTvshowBinding
import com.bimabagaskhoro.submission.core.domain.model.TvShow
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    companion object {
        const val LINK_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
    private var listTvShow = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    fun setData(newListData: List<TvShow>?) {
        if (newListData == null) return
        listTvShow.clear()
        listTvShow.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class TvShowViewHolder(private val binding: ItemListTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(LINK_IMAGE + tvShow.avatar)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgTvShow)

                tvTitleTvShow.text = tvShow.title
                tvDateTvShow.text = tvShow.date
                tvDescTvShow.text = tvShow.desc
                tvVoteCount.text = tvShow.vote_average.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listTvShow[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = ItemListTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size
}