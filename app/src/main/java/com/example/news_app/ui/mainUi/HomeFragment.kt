package com.example.news_app.ui.mainUi

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.news_app.App
import com.example.news_app.R
import com.example.news_app.database.Db
import com.example.news_app.databinding.FragmentHomeBinding
import com.example.news_app.databinding.ItemTabBinding
import com.example.news_app.retrofit.models.Article
import com.example.news_app.ui.adapters.HorizontalAdapter
import com.example.news_app.ui.adapters.SearchAdapter
import com.example.news_app.ui.adapters.VerticalAdapter
import com.example.news_app.ui.adapters.ViewPagerCategoryAdapter
import com.example.news_app.utils.Status
import com.example.news_app.viewmodels.MainViewModel
import com.example.news_app.viewmodels.MainViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var db: Db

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val TAG = "HomeFragment"
    lateinit var searchAdapter: SearchAdapter
    lateinit var verticalAdapter: VerticalAdapter
    lateinit var binding: FragmentHomeBinding
    val tabArray = arrayOf(
        "entertainment",
        "business",
        "general",
        "health",
        "science",
        "sports",
        "technology"
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        App.appComponent.injectHome(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)


        setSearch()
        setViewPager()
        setRv()

        return binding.root
    }

    private fun setRv() {
        val categoriesByBoolean = db.categoryDao().getCategoriesByBoolean(true)
        val name = categoriesByBoolean[0].name
        GlobalScope.launch(Dispatchers.Main) {
            mainViewModel.getCategory(name).observe(viewLifecycleOwner) {

                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        Log.d(TAG, "onCreateView: ${it.message}")
                    }
                    Status.SUCCESS -> {
                        Log.d(TAG, "onCreateView: ${it.data}")
                      verticalAdapter = VerticalAdapter(it.data!!.articles,object :VerticalAdapter.OnItemClickListener{
                          override fun onItemClick(malumotlar: Article) {

                          }

                      })
                        binding.verticalRv.adapter = verticalAdapter
                    }
                }



            }
        }
    }

    private fun setViewPager() {
        val viewPager = binding.viewPager2
        val tabLayoutgl =  binding.tabLayoutgalvni

        val dbCategories = db.categoryDao().getDbCategories()

        val adapter = ViewPagerCategoryAdapter(dbCategories,childFragmentManager, lifecycle)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayoutgl, viewPager) { tab, position ->
            val itemBinding = ItemTabBinding.inflate(layoutInflater)
            tab.customView = itemBinding.root
            itemBinding.titleTv.text = dbCategories[position].name




            if (position == 0) {
                itemBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_selected)
                itemBinding.titleTv.setTextColor(Color.WHITE)

            } else {
                itemBinding.titleTv.setTextColor(Color.parseColor("#303236"))
                itemBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_unselected)
            }

            //Some implementation
        }.attach()



        binding.tabLayoutgalvni.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                itemTabBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_selected)

                itemTabBinding.titleTv.setTextColor(Color.WHITE)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                itemTabBinding.titleTv.setTextColor(Color.parseColor("#303236"))
                itemTabBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_unselected)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setSearch() {
        binding.edittext.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Toast.makeText(binding.root.context, "search clicked", Toast.LENGTH_SHORT).show()
                val searchResult = binding.edittext.text.toString()

                        //search api
        GlobalScope.launch(Dispatchers.Main) {
            mainViewModel.getWord(searchResult,"ru").observe(viewLifecycleOwner) {

                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        Log.d(TAG, "onCreateView: ${it.message}")
                    }
                    Status.SUCCESS -> {
                        Log.d(TAG, "onCreateView: ${it.data}")
                        searchAdapter = SearchAdapter(it.data!!.articles,object :SearchAdapter.OnItemClickListener{
                            override fun onItemClick(malumotlar: Article) {

                            }

                        })
                        binding.searchrv.adapter = searchAdapter
                    }
                }



            }
        }
            }


            true
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}