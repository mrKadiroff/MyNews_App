package com.example.news_app.ui.mainUi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.news_app.App
import com.example.news_app.R
import com.example.news_app.database.Db
import com.example.news_app.databinding.FragmentCategoryTabBinding
import com.example.news_app.databinding.FragmentTabBinding
import com.example.news_app.retrofit.models.Article
import com.example.news_app.ui.adapters.HorizontalAdapter
import com.example.news_app.utils.Status
import com.example.news_app.viewmodels.MainViewModel
import com.example.news_app.viewmodels.MainViewModelFactory
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
 * Use the [CategoryTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryTabFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var db: Db

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var binding: FragmentCategoryTabBinding
    lateinit var horizontalAdapter: HorizontalAdapter
    private val TAG = "CategoryTabFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentCategoryTabBinding.inflate(layoutInflater,container,false)

        App.appComponent.injectCategorytab(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)


                GlobalScope.launch(Dispatchers.Main) {
            mainViewModel.getCategory(param2!!).observe(viewLifecycleOwner) {

                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        Log.d(TAG, "onCreateView: ${it.message}")
                    }
                    Status.SUCCESS -> {
                        Log.d(TAG, "onCreateView: ${it.data}")
                        horizontalAdapter = HorizontalAdapter(it.data!!.articles,object :HorizontalAdapter.OnItemClickListener{
                            override fun onItemClick(malumotlar: Article) {

                            }

                        })
                        binding.horizonItem.adapter = horizontalAdapter
                    }
                }



            }
        }




        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoryTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int,param2: String) =
            CategoryTabFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2,param2)
                }
            }
    }
}