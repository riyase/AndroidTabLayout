package com.example.navigationcomponenttablet.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationcomponenttablet.dummy.DummyContent

class ItemViewModel : ViewModel() {

    val dummyList = ArrayList<DummyContent>(
        mutableListOf(
            DummyContent("Harry", "Potter"),
            DummyContent(
                "Alex",
                "James"
            ),
            DummyContent(
                "Clin",
                "Wood"
            ),
        )
    )

    //initial List of Items
    var liveData = MutableLiveData<ArrayList<DummyContent>>(dummyList)

    // Item Selected in RecyclerView
    var selectedItem = ObservableField<DummyContent>()
    var selectedItemPos: Int = 0

    //Item That was added via Input
    var newItem = MutableLiveData<DummyContent>()

    fun itemSelected(pos: Int, item: DummyContent) {
        selectedItemPos = pos
        selectedItem.set(item)
    }

}