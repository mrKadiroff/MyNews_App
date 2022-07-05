package com.example.news_app.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.news_app.database.CategoryEntity
import com.example.news_app.ui.adapters.onboard.TabFragment
import com.example.news_app.ui.mainUi.CategoryTabFragment

class ViewPagerCategoryAdapter(
    var list: List<CategoryEntity>, fragmentManager: FragmentManager, lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return CategoryTabFragment.newInstance(position,list[position].name)
    }
}