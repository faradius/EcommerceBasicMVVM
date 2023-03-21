package com.alex.ecommercebasicmvvm.presentation.product_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alex.ecommercebasicmvvm.databinding.FragmentProductListBinding

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvAdapter = ProductListAdapter(
            onClick = { selectedProduct ->
                val action = ProductListFragmentDirections.actionProductsFragmentToProductDetailFragment(selectedProduct)
                findNavController().navigate(action)
            }
        )

        binding.rvProductList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = rvAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.productList.collect{productList ->
                rvAdapter.submitList(productList) //Lista con elementos
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}