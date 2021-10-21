package com.example.navigationcomponenttablet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

class SportsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var view: View

        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)

        when {
            isTablet -> {
                view = inflater.inflate(R.layout.fragment_sports_land, container, false)
                displayMasterDetailLayout(view)
            }
            else -> {
                view = inflater.inflate(R.layout.fragment_sports, container, false)
                displaySingleLayout(view)
            }
        }

        return view
    }

    private fun displaySingleLayout(view: View) {
        view.findViewById<TextView>(R.id.cricket_textview).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_sportsFragment_to_cricketFragment)
        )
        view.findViewById<TextView>(R.id.soccer_textview).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_sportsFragment_to_soccerFragment)
        )
        view.findViewById<TextView>(R.id.volley_textview).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_sportsFragment_to_volleyBallFragment)
        )
    }

    private fun displayMasterDetailLayout(view: View) {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.sports_nav_container) as NavHostFragment

        view.findViewById<TextView>(R.id.cricket_textview).setOnClickListener {
            navHostFragment.navController.navigate(R.id.cricketFragment)
        }

        view.findViewById<TextView>(R.id.soccer_textview).setOnClickListener {
            navHostFragment.navController.navigate(R.id.soccerFragment2)
        }

        view.findViewById<TextView>(R.id.volley_textview).setOnClickListener {
            navHostFragment.navController.navigate(R.id.volleyBallFragment2)
        }
    }
}