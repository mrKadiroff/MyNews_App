package com.example.news_app.ui.adapters.onboard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.news_app.App
import com.example.news_app.R
import com.example.news_app.database.CategoryEntity
import com.example.news_app.database.Db
import com.example.news_app.databinding.CategoryItemBinding
import com.example.news_app.databinding.FragmentSelectBinding
import com.example.news_app.databinding.FragmentWelcomeBinding
import com.example.news_app.ui.adapters.CategoryAdapter
import com.example.news_app.ui.mainUi.BottomnavActivity
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
 * Use the [SelectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectFragment : Fragment() {
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
    lateinit var categoryAdapter: CategoryAdapter
    private val TAG = "SelectFragment"
    lateinit var binding: FragmentSelectBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectBinding.inflate(layoutInflater,container,false)
        App.appComponent.injectWelcome(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)


        //byBoolean



        binding.next.setOnClickListener {

            val categoriesByBoolean = db.categoryDao().getCategoriesByBoolean(true)
            if (categoriesByBoolean.isNotEmpty()){
                val intent = Intent(binding.root.context,BottomnavActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(
                    binding.root.context,
                    "Please choose some of the categories above",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }






        //room db
        mainViewModel.productsLiveData.observe(viewLifecycleOwner, Observer {
            categoryAdapter = CategoryAdapter(it,object :CategoryAdapter.OnItemClickListener{
                var a = 100
                override fun onItemClick(
                    malumotlar: CategoryEntity,
                    position: Int,
                    malumotItemBinding: CategoryItemBinding
                ) {


                    if (a==position) {
                        malumotItemBinding.qolip.setBackgroundColor(Color.parseColor("#F3F4F6"))
                        val categoryEntity = CategoryEntity(malumotlar.id, malumotlar.name, false)
                        db.categoryDao().updateCategory(categoryEntity)
                        malumotItemBinding.categoryname.setTextColor(Color.parseColor("#333647"))
                        Toast.makeText(binding.root.context, "Unselected", Toast.LENGTH_SHORT)
                            .show()
                        a++

                    }
                    else {
                        val categoryEntity = CategoryEntity(malumotlar.id,malumotlar.name,true)
                        db.categoryDao().updateCategory(categoryEntity)
                        malumotItemBinding.qolip.setBackgroundColor(Color.parseColor("#475AD7"))
                        malumotItemBinding.categoryname.setTextColor(Color.parseColor("#FFFFFF"))
                        Toast.makeText(binding.root.context, "tanlandi", Toast.LENGTH_SHORT)
                            .show()
                        a= position
                    }
                }


            })

            binding.categoryRv.adapter = categoryAdapter
        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}