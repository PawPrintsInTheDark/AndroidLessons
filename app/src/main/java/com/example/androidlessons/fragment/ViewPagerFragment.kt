package com.example.androidlessons.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.androidlessons.models.OnBoardingFragmentViewPagerModel
import com.example.androidlessons.R
import com.example.androidlessons.StartActivity

class ViewPagerFragment : Fragment() {
    private var check = true

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
        val startBTN: Button = view.findViewById(R.id.startBTN)


        val viewPagerItem = arguments?.getSerializable("vp") as OnBoardingFragmentViewPagerModel
        check = viewPagerItem.checkedButton
        viewPagerTitleTV.text = viewPagerItem.title
        imageView.setImageResource(viewPagerItem.imageView)
        if (check){
            startBTN.visibility = View.VISIBLE
            startBTN.setOnClickListener{
                startActivity(Intent(activity, StartActivity::class.java))

            }
        }
    }


}