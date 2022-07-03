package com.example.news_app.ui.adapters.onboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.news_app.App
import com.example.news_app.database.Db
import com.example.news_app.databinding.FragmentWelcomeBinding
import com.example.news_app.retrofit.FakerAPI
import com.example.news_app.utils.Constants
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
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
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
    lateinit var binding: FragmentWelcomeBinding
    private val TAG = "WelcomeFragment"
    var word:String =""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentWelcomeBinding.inflate(layoutInflater,container,false)
        App.appComponent.injectWelcome(this)


        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)



        //room db
        mainViewModel.productsLiveData.observe(viewLifecycleOwner, Observer {
            binding.tarif.text=  it.joinToString { x -> x.name + "\n\n" }
        })






        //search api
        GlobalScope.launch(Dispatchers.Main) {
            mainViewModel.getWord("Apple").observe(viewLifecycleOwner) {

                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        Log.d(TAG, "onCreateView: ${it.message}")
                    }
                    Status.SUCCESS -> {
                        Log.d(TAG, "onCreateView: ${it.data}")
                    }
                }



            }
        }



        GlobalScope.launch(Dispatchers.Main) {
            mainViewModel.getCategory("science").observe(viewLifecycleOwner) {

                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        Log.d(TAG, "onCreateView: ${it.message}")
                    }
                    Status.SUCCESS -> {
                        Log.d(TAG, "onCreateView: ${it.data}")
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
         * @return A new instance of fragment WelcomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}