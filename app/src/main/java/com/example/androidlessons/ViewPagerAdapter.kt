package com.example.androidlessons

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidlessons.models.OnBoardingFragmentViewPagerModel

class ViewPagerAdapter(
    fragment: FragmentActivity,
    private val viewPagerList: MutableList<OnBoardingFragmentViewPagerModel>
) : FragmentStateAdapter(fragment){
    override fun getItemCount()= viewPagerList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerFragment()
        fragment.arguments = bundleOf("vp" to viewPagerList[position])
        return fragment
    }

}