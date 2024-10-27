package com.news.product.presentation.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.news.product.R
import com.news.product.databinding.FragmentProductDetailsBinding
import com.news.product.domain.model.ProductModel
import com.news.product.utils.LoadingScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel by viewModels<ProductDetailViewModel>()
    private val args by navArgs<ProductDetailsFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailsBinding.bind(view)
        observeDetailsData()

        //call the detail api from initial time
        viewModel.getProductDetail(args.id ?: "")
    }

    private fun setDataToView(product: ProductModel) {
        binding.apply {
            title.text = product.title
            content.text = product.description
            rating.text = product.rating
            releaseDate.text = product.createdDate
            width.text = "Width: ${product.width}"
            height.text = "Height: ${product.height}"
            depth.text = "Depth: ${product.depth}"
            imageView.load(product.thumbnail) {
                crossfade(true)
                placeholder(R.drawable.error) // Placeholder image
                error(R.drawable.error)
            }
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun observeDetailsData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailState.collectLatest { data ->
                    when (data) {
                        is DetailUiState.Empty -> {

                        }

                        is DetailUiState.Error -> {
                            LoadingScreen.operateDialog(false, requireContext())
                            Toast.makeText(requireContext(), data.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is DetailUiState.Loading -> {
                            LoadingScreen.operateDialog(true, requireContext())
                        }

                        is DetailUiState.Success -> {
                            LoadingScreen.operateDialog(false, requireContext())
                            //setting the data to UI
                            setDataToView(data.productDetail)
                        }
                    }
                }
            }
        }
    }
}