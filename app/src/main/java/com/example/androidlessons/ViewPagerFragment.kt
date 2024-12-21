package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        return view
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerTitleTV: TextView = view.findViewById(R.id.ViewPagerTitleTV)
        val imageView: ImageView = view.findViewById(R.id.viewPagerIV)
        val viewPagerTV: TextView = view.findViewById(R.id.ViewPagerTV)

        val viewPagerItem = arguments?.getSerializable("vp") as OnBoardingFragmentViewPagerModel
        viewPagerTitleTV.text = viewPagerItem.title
        imageView.setImageResource(viewPagerItem.imageView)
        viewPagerTV.text = viewPagerItem.author
    }


}