package com.lemonz.weatherapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.lemonz.weatherapp.R
import com.lemonz.weatherapp.common.AppPreferences
import com.lemonz.weatherapp.data.model.search.Place
import com.lemonz.weatherapp.data.utils.Result
import com.lemonz.weatherapp.databinding.CitySearchFragmentBinding
import com.lemonz.weatherapp.ui.adapter.CitySearchAdapter
import com.lemonz.weatherapp.viewmodel.ViewModelFactory
import com.lemonz.weatherapp.viewmodel.WeatherSearchViewModel

class CitySearchFragment : Fragment() {


    companion object {
        const val CITY_ARG = "CITY_ARG"
    }

    private lateinit var binding: CitySearchFragmentBinding
    var fromWeather = false

    private val weatherSearchViewModel by viewModels<WeatherSearchViewModel>(
        { this },
        { ViewModelFactory() })

    private val cityAdapter = CitySearchAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        CitySearchFragmentBinding.inflate(inflater).also {
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
            binding.cityList.adapter = cityAdapter
            binding.etCity.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    if (s.toString().length > 2) {
                        weatherSearchViewModel.search(s.toString())
                    } else {
                        weatherSearchViewModel.search("")
                    }
                }
            })

        }
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        weatherSearchViewModel.search()
        if ((!fromWeather && !AppPreferences.firstRun)) {
            val options = NavOptions.Builder().setPopUpTo(R.id.list_search_dest, true).build()
            view.findNavController().navigate(R.id.action_search_to_weather, null, options)
        }
        binding.ivClose.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    private fun initObservers() {
        weatherSearchViewModel.places.observe(viewLifecycleOwner, Observer { result ->
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


    private fun loadArguments() {
        arguments?.getBoolean(CITY_ARG).let {
            if (it != null) {
                fromWeather = it
            }
        }
    }

}