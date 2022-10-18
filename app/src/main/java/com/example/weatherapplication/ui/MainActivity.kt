package com.example.weatherapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.ActivityMainBinding
import com.example.weatherapplication.utils.Result
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
            mainViewModel.fetchWeatherData(binding.edtCity.text.toString().trim())
        }

        mainViewModel.response.observe(this) { response ->
            when (response) {
                is Result.Success -> {
                    response.data?.let {
                        if (it.main != null) {
                            binding.tempLayout.visibility = View.VISIBLE
                            binding.tvTemp.text =
                                getString(R.string.temp, it.main!!.temp!!.toString())
                            binding.tvTempMin.text = getString(
                                R.string.temp_min,
                                it.main!!.tempMin!!.toString()
                            )
                            binding.tvTempMax.text = getString(
                                R.string.temp_max,
                                it.main!!.tempMax!!.toString()
                            )
                            binding.tvPressure.text = getString(
                                R.string.pressure,
                                it.main!!.pressure!!.toString()
                            )
                            binding.tvHumidity.text = getString(
                                R.string.humidity,
                                it.main!!.humidity!!.toString()
                            )
                        }
                    }
                }
                is Result.Error -> {
                    binding.tempLayout.visibility = View.GONE
                    Toast.makeText(
                        this,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }
    }
}