package com.example.gooru.feature.presentation.parsers.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gooru.feature.presentation.parsers.parser.favorite.FavoriteParserFragment
import com.example.gooru.feature.presentation.parsource.MyParSourceFragment

class ParSourceTabAdapter(
    fragment: Fragment,
    private val size: Int
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = size

    override fun createFragment(position: Int): Fragment {
       return when (position) {
            0 -> MyParSourceFragment()
            1 -> FavoriteParserFragment()
            else -> Fragment()
        }
    }
}