package com.example.weatherapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.ActivityMainBinding
import com.example.weatherapplication.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            if (binding.edtCity.text.toString().trim().isNotEmpty())
                mainViewModel.fetchWeatherData(binding.edtCity.text.toString().trim())
            else
                binding.edtCity.error = "Please enter a city"
        }

        mainViewModel.weatherData.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { weatherResponse ->
                        if (weatherResponse.main != null) {
                            binding.tempLayout.visibility = View.VISIBLE
                            binding.tvTemp.text =
                                getString(R.string.temp, weatherResponse.main!!.temp!!.toString())
                            binding.tvTempMin.text = getString(
                                R.string.temp_min,
                                weatherResponse.main!!.tempMin!!.toString()
                            )
                            binding.tvTempMax.text = getString(
                                R.string.temp_max,
                                weatherResponse.main!!.tempMax!!.toString()
                            )
                            binding.tvPressure.text = getString(
                                R.string.pressure,
                                weatherResponse.main!!.pressure!!.toString()
                            )
                            binding.tvHumidity.text = getString(
                                R.string.humidity,
                                weatherResponse.main!!.humidity!!.toString()
                            )
                        } else {
                            binding.tempLayout.visibility = View.GONE
                        }
                    }
                }
                Status.ERROR -> {
                    binding.tempLayout.visibility = View.GONE
                    Toast.makeText(this, "Not found!", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }


}