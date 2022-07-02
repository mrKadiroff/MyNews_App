package com.example.news_app.ui.adapters.onboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.news_app.R
import com.example.news_app.databinding.FragmentBoardHomeBinding
import com.example.news_app.databinding.FragmentTabBinding
import com.example.news_app.ui.OnboardActivity
import com.example.news_app.ui.adapters.ViewPagerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BoardHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BoardHomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    val animalsArray = arrayOf(
        "Cat",
        "Dog"
    )
    lateinit var binding: FragmentBoardHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentBoardHomeBinding.inflate(layoutInflater,container,false)



        setonBoard()

        return binding.root
    }

    private fun setonBoard() {
        val viewPager2 = binding.viewPager2
        val adapter = ViewPagerAdapter(animalsArray,childFragmentManager, lifecycle)
        viewPager2.adapter = adapter
        val wormDotsIndicator = binding.springDotsIndicator
        wormDotsIndicator.setViewPager2(viewPager2)

//        findNavController().navigate(R.id.selectFragment)




        viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (viewPager2.currentItem + 1 == adapter.itemCount) {

                }else{

                }



            }



        })

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.welcomeFragment)
        }
    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BoardHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BoardHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}