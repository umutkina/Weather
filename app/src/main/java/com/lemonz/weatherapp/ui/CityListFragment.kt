package com.lemonz.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.lemonz.weatherapp.R
import com.lemonz.weatherapp.data.model.search.Place
import com.lemonz.weatherapp.data.utils.Result
import com.lemonz.weatherapp.databinding.CityListFragmentBinding
import com.lemonz.weatherapp.ui.adapter.CityListAdapter
import com.lemonz.weatherapp.viewmodel.ViewModelFactory
import com.lemonz.weatherapp.viewmodel.WeatherCityListViewModel


class CityListFragment : Fragment() {


    private lateinit var binding: CityListFragmentBinding

    private val weatherCityListViewModel by viewModels<WeatherCityListViewModel>(
        { this },
        { ViewModelFactory() })

    private val cityAdapter = CityListAdapter(arrayListOf()) { item -> removeItem(item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        CityListFragmentBinding.inflate(inflater).also {
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
            binding.cityList.adapter = cityAdapter
        }
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        weatherCityListViewModel.getCities()
        binding.tvAddNewLocation.setOnClickListener {
            val bundle = bundleOf(CitySearchFragment.CITY_ARG to true)
            val options = NavOptions.Builder().setPopUpTo(R.id.city_list_dest, true).build()
            view.findNavController().navigate(R.id.action_list_to_search, bundle, options)
        }

    }

    private fun initObservers() {
        weatherCityListViewModel.places.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    renderList(result.data!!)
                }
                is Result.InProgress -> {
                }
                is Result.Error -> {

                    Toast.makeText(activity, result.exception, Toast.LENGTH_LONG).show()
                }
            }
        })


    }

    private fun renderList(data: List<Place>) {
        data.let {
            cityAdapter.placeList = it
            cityAdapter.notifyDataSetChanged()
        }
    }


    fun removeItem(place: Place) {
        weatherCityListViewModel.removeCity(place)
    }
}