package com.mahostudios.ntc.fragments

import android.app.Activity
import android.media.audiofx.DynamicsProcessing
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mahostudios.ntc.R
import com.mahostudios.ntc.SectionsPagerAdapter
import com.mahostudios.ntc.SpeedFragment
import com.mahostudios.ntc.TableFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var inflatedView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView = inflater.inflate(R.layout.fragment_main, container, false);

        return inflatedView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragList = ArrayList<Fragment>()
            fragList.add(SpeedFragment())
            fragList.add(TableFragment())
            fragList.add(UsageFragment())

        val pagerAdapter = SectionsPagerAdapter(fragList, childFragmentManager)
        val viewpager: ViewPager = inflatedView.findViewById(R.id.viewpager)
        viewpager.adapter = pagerAdapter
        //disable swipe
//        viewpager.setOnTouchListener { _, _ ->
//            return@setOnTouchListener true
//        }
        var newInitialPosition = 0

        viewpager.setCurrentItem(newInitialPosition, false)

        fun unsusedCode(){
            //        val equal_navigation_bar : BubbleNavigationLinearView = inflatedView.findViewById(R.id.equal_navigation_bar)

//        equal_navigation_bar.setNavigationChangeListener { _, position ->
//            viewpager.setCurrentItem(position, true)
//        }
            //change the initial activate element

//        equal_navigation_bar.setCurrentActiveItem(newInitialPosition)
//        Toast.makeText(requireContext(), equal_navigation_bar.currentActiveItemPosition.toString(), Toast.LENGTH_SHORT).show()


//        val fab : FloatingActionButton = inflatedView.findViewById(R.id.fab)
//        fab.setOnClickListener{
//            equal_navigation_bar.isVisible = !equal_navigation_bar.isVisible
//        }
        }
    }
}