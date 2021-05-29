package com.jarvis.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.tab.model.ListItemLocal

class Lokal : Fragment() {

    lateinit var mView: View
    lateinit var mRecycle : RecyclerView
    lateinit var mListLocalItemData : ArrayList<ListItemLocal>
    var mLocalAdapter : LokalAdapter = LokalAdapter(requireContext(),  ArrayList())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_lokal, container, false)
        initView(mView)
        return mView
    }

    private fun initView(mView: View) {
        mRecycle = mView.findViewById(R.id.rv_local)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateLocalItemData()
        fetchData(mListLocalItemData)

        setUpRecycle()




    }


    private fun populateLocalItemData() : List<ListItemLocal> {
        mListLocalItemData = ArrayList()
        val itemName = resources.getStringArray(R.array.localtem)
        val descItem = resources.getStringArray(R.array.descLocaltem)
        val originItem = resources.getStringArray(R.array.originLocaltem)
        val imgRes = resources.obtainTypedArray(R.array.imgLocaltem)
        for (i in itemName.indices){
            mListLocalItemData.add(ListItemLocal(imgRes.getResourceId(i, 0), itemName[i],originItem[i],descItem[i]))

        }
        imgRes.recycle()

        return mListLocalItemData
    }

    private fun fetchData(mListData : List<ListItemLocal>)  {
        if (mListData != null){
           mLocalAdapter.addItemListLocal(mListData)
            mLocalAdapter.setOnItemClickListener(object : LokalAdapter.OnItemCLickListener{
                override fun onItemSelected(localItem: ListItemLocal) {
                    Toast.makeText(requireContext(), "DATA NAMA BUAH ${localItem.nameItem}", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }


    private fun setUpRecycle() {

        mRecycle.adapter = mLocalAdapter
        mRecycle.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mRecycle.setHasFixedSize(true)

    }
}