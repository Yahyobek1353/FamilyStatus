package com.geeks.example.familystatus.other.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geeks.example.familystatus.ui.fragment.amountOfMoney.SumFragment
import com.geeks.example.familystatus.ui.fragment.budget.BudgetFragment
import com.geeks.example.familystatus.ui.fragment.schedule.ScheduleFragment

class ViewPagerAdapter(fragment : FragmentManager , lifecycle: Lifecycle) : FragmentStateAdapter(fragment , lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                ScheduleFragment()
            }
            1 -> {
                BudgetFragment()
            }
            2 -> {
                SumFragment()
            }
            else -> {
                Fragment()
            }
        }
    }

}