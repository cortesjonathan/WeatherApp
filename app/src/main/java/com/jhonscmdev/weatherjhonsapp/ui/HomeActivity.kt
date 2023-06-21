package com.jhonscmdev.weatherjhonsapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.jhonscmdev.weatherjhonsapp.databinding.ActivityMainBinding
import com.jhonscmdev.weatherjhonsapp.di.HomeMockyComponent
import com.jhonscmdev.weatherjhonsapp.di.HomeModule
import com.jhonscmdev.weatherjhonsapp.domain.ResponseData
import com.jhonscmdev.weatherjhonsapp.domain.WeatherResponse
import com.jhonscmdev.weatherjhonsapp.presentation.Event
import com.jhonscmdev.weatherjhonsapp.presentation.HomeViewModel
import com.jhonscmdev.weatherjhonsapp.utils.app
import com.jhonscmdev.weatherjhonsapp.utils.getViewModel
import com.jhonscmdev.weatherjhonsapp.utils.isLocationEnabled
import com.jhonscmdev.weatherjhonsapp.utils.showLongToast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.util.Locale


class HomeActivity : AppCompatActivity() {
    private lateinit var homeMockyComponent: HomeMockyComponent
    private lateinit var binding: ActivityMainBinding
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    private val homeViewModel: HomeViewModel by lazy {
        getViewModel { homeMockyComponent.homeViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        homeMockyComponent = app.component.inject(HomeModule())

        homeViewModel.getAllMocky()
        homeViewModel.events.observe(this, Observer(this::validateRequest ))
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        validateLocation()

    }
    fun validateRequest(event: Event<HomeViewModel.MockyInformation>?) {
        event?.getContentIfNotHandled()?.let { requestInf ->
            when  (requestInf) {
                is HomeViewModel.MockyInformation.ShowGeneralInfo -> requestInf.run {
                    infMocky(responseData)
                }
                is HomeViewModel.MockyInformation.ShowErrorGeneralInfo -> {
                    showLongToast("Error al obtener los datos")
                }
                is HomeViewModel.MockyInformation.ShowWeatherInfo -> requestInf.run {
                    infWeather(weatherResponse)
                }
                is HomeViewModel.MockyInformation.ShowErrorWeatherInfo -> {
                    showLongToast("Active la localizacion")
                }
            }
        }
    }
    fun infMocky(responseData: ResponseData){
        binding.txvWelcome.text = responseData.message
    }
    fun infWeather(weatherResponse: WeatherResponse) {
        binding.tvCondition.text = weatherResponse.weather[0].main
        binding.tvDegree.text = weatherResponse.wind.speed.toString()
        binding.tvMinimun.text = weatherResponse.main.temp_min.toString()
        binding.tvMaximun.text = weatherResponse.main.temp_max.toString()
        binding.tvWind.text = weatherResponse.wind.speed.toString()+'/'+weatherResponse.wind.deg.toString()
        binding.tvCountry.text = weatherResponse.name
        binding.tvSunrise.text = weatherResponse.sys.sunrise.toString()
        binding.tvSunset.text = weatherResponse.sys.sunset.toString()
    }
    private fun validateLocation() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ).withListener(object : MultiplePermissionsListener {
                @SuppressLint("MissingPermission")
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        if (isLocationEnabled(this@HomeActivity)) {
                            fusedLocationProviderClient.lastLocation.addOnCompleteListener(this@HomeActivity) { task ->
                                val location: Location? = task.result
                                if (location != null) {
                                    val geocoder = Geocoder(this@HomeActivity, Locale.getDefault())
                                    val list: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>
                                    Log.d("LATITUDE",list[0].latitude.toString())
                                    Log.d("LONGITUDE",list[0].longitude.toString())
                                    homeViewModel.getAllWeatherInfo(list[0].latitude,list[0].longitude)
                                }
                            }
                        }

                    } else {
                        showRationalDialogForPermissions();
                        showLongToast("Tienes deshabilitada la ubicacion en tu dispositivo, por favor enciendela")
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).onSameThread().check()
    }

    private fun showRationalDialogForPermissions() {
        val snack = Snackbar.make(binding.homeLinear,
            "¿Abrir la configuración para activar la localización?",Snackbar.LENGTH_INDEFINITE)
        snack.setAction("CONFIGURACION", View.OnClickListener {
            try {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        })
        snack.show()
    }
}