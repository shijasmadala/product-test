package com.news.product.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.news.product.databinding.ItemReviewBinding
import com.news.product.domain.model.ReviewModel

class ReviewAdapter() : ListAdapter<ReviewModel, RecyclerView.ViewHolder>(ReviewCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ReviewViewHolder).bind(getItem(position))
    }

    inner class ReviewViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reviewModel: ReviewModel) {
            binding.apply {
                reviewerNameContent.text = reviewModel.reviewerName
                reviewerMailContent.text = reviewModel.reviewerEmail
                commentContent.text = reviewModel.comment
                date.text = reviewModel.date
                rating.text = reviewModel.rating.toString()
            }
        }
    }
}


object ReviewCallBack : DiffUtil.ItemCallback<ReviewModel>() {
    override fun areItemsTheSame(
        oldItem: ReviewModel,
        newItem: ReviewModel
    ): Boolean {
        return oldItem.reviewerEmail == newItem.reviewerEmail
    }

    override fun areContentsTheSame(
        oldItem: ReviewModel,
        newItem: ReviewModel
    ): Boolean {
        return oldItem == newItem
    }
}