package com.lemonz.weatherapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.lemonz.weatherapp.R
import com.lemonz.weatherapp.common.AppPreferences
import com.lemonz.weatherapp.data.model.search.Place
import com.lemonz.weatherapp.data.utils.Result
import com.lemonz.weatherapp.databinding.WeatherFragmentBinding
import com.lemonz.weatherapp.ui.adapter.DailyWeatherAdapter
import com.lemonz.weatherapp.viewmodel.ViewModelFactory
import com.lemonz.weatherapp.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {

    private lateinit var binding: WeatherFragmentBinding

    companion object {
        const val WEATHER_ARG = "WEATHER_ARG"
    }

    private var place: Place? = null
    val dailyWeatherAdapter = DailyWeatherAdapter()
    private val weatherViewModel by viewModels<WeatherViewModel>(
        { this },
        { ViewModelFactory() })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        WeatherFragmentBinding.inflate(inflater).also {
            place = AppPreferences.getCurrentCity()
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
            val lat = place?.latitude
            val lon = place?.longitude
            weatherViewModel.weather("$lat, $lon")
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.ivCityAdd.setOnClickListener {
            view.findNavController().navigate(R.id.action_details)
        }
    }

    private fun initObservers() {
        weatherViewModel.weatherData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    binding.data = result.data
                    binding.place = place
                    binding.dailyWeatherList.adapter = dailyWeatherAdapter
                    dailyWeatherAdapter.weatherList =
                        result.data!!.weather.subList(1, result.data.weather.size)
                    dailyWeatherAdapter.notifyDataSetChanged()
                    binding.progressBar.visibility = View.GONE
                    binding.tvFollowingDays.visibility = View.VISIBLE
                    binding.ivCityAdd.visibility = View.VISIBLE
                }
                is Result.InProgress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvFollowingDays.visibility = View.GONE
                    binding.ivCityAdd.visibility = View.GONE
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, result.exception, Toast.LENGTH_LONG).show()
                }
            }
        })


    }

    private fun loadArguments() {
        arguments?.getParcelable<Place>(WEATHER_ARG)?.let {
            place = it
        }
    }
}