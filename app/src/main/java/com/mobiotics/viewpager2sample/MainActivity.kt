package com.mobiotics.viewpager2sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.mobiotics.viewpager2sample.databinding.ActivityMainBinding
import java.util.*

/**
 * Things to be noted
 * 1. The itemView root width & height must be match parent
 * */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
    }

    private fun initRecyclerview() {
        recyclerAdapter = RecyclerAdapter(this) //set the mRecyclerAdapter to the recycler view
        binding.viewPager2.adapter = recyclerAdapter
        binding.viewPager2.orientation =
            ViewPager2.ORIENTATION_HORIZONTAL // setting up of orientation
        recyclerAdapter.submitListItem(dummyData())
    }

    private fun dummyData(): MutableList<ModelClass> { //dummy data
        val mListItems: MutableList<ModelClass> = ArrayList()
        for (i in 0..9) {
            mListItems.add(
                ModelClass(
                    i
                )
            )
            /** adding dummy data into the List */
        }
        return mListItems
    }
}
