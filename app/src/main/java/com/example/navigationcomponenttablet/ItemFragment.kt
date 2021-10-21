package com.example.navigationcomponenttablet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationcomponenttablet.databinding.FragmentItemListBinding
import com.example.navigationcomponenttablet.dummy.DummyContent
import com.example.navigationcomponenttablet.viewmodel.ItemViewModel

class ItemFragment : Fragment() {

    private var columnCount = 1
    private lateinit var viewModel: ItemViewModel
    private lateinit var binding: FragmentItemListBinding
    private var isTablet : Boolean = false
    private lateinit var itemList : ArrayList<DummyContent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ItemViewModel::class.java)
        isTablet = resources.getBoolean(R.bool.isTablet)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isTablet) {
            setupNavHost()
        }
        itemList = viewModel.liveData.value!!
        setupViewModelObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.list?.apply {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyItemRecyclerViewAdapter(
                isTablet, itemList, viewModel,
                this@ItemFragment::triggerSelection
            )
        }
    }

    private fun triggerSelection(isLongPressed: Boolean, pos: Int, item: DummyContent) {
        if (isLongPressed) {
            itemList.removeAt(pos)
            binding.list?.adapter!!.notifyItemRemoved(pos)
        } else {
            viewModel.itemSelected(pos, item)
            if (!isTablet) {
                findNavController().navigate(R.id.action_itemFragment_to_detailFragment2)
            }
        }
    }

    private fun setupViewModelObservers() {
        viewModel.newItem.observe(viewLifecycleOwner, { item ->
            if (item != null) {
                itemList.add(item)
                binding.list?.adapter?.notifyItemInserted(itemList.size - 1)
                viewModel.newItem.value = null
            }
        })
    }

    private fun setupNavHost() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.detailContainer) as NavHostFragment
    }
}