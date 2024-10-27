package com.news.product.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.news.product.R
import com.news.product.databinding.FragmentHomeBinding
import com.news.product.domain.model.ProductModel
import com.news.product.presentation.adapter.ProductAdapter
import com.news.product.utils.LoadingScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ProductAdapter.OnItemClick {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val productAdapter by lazy { ProductAdapter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        observeProductData()
        setUpRecyclerView()
        handleSearch()
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.adapter = productAdapter
    }

    private fun observeProductData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collectLatest {
                    when (it) {
                        HomeUiState.Empty -> {

                        }

                        is HomeUiState.Error -> {
                            LoadingScreen.operateDialog(false, requireContext())
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }

                        HomeUiState.Loading -> {
                            LoadingScreen.operateDialog(true, requireContext())
                        }

                        is HomeUiState.Success -> {
                            LoadingScreen.operateDialog(false, requireContext())
                            if (it.productList.isNotEmpty()) {
                                productAdapter.setOriginalList(it.productList ?: emptyList())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun handleSearch() {
        binding.apply {
            tietSearch.doOnTextChanged { text, _, _, _ ->
                productAdapter.filterItems(text.toString())
            }
            ivCancel.setOnClickListener {
                tietSearch.setText("")
                productAdapter.filterItems("")
            }
        }
    }

    override fun onItemClick(productModel: ProductModel) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(
                productModel.id.toString()
            )
        )
    }
}