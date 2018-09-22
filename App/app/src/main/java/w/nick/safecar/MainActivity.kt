package com.example.jasperharrison.locationtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_INDEFINITE
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.TextView

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.example.jasperharrison.locationtest.BuildConfig.APPLICATION_ID
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var latitudeText: TextView
    private lateinit var longitudeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        latitudeText = findViewById(R.id.latitude_text) as TextView
        longitudeText = findViewById(R.id.longitude_text) as TextView
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStart() {
        super.onStart()
        if(!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
            if (task.isSuccessful && task.result != null) {
                latitudeText.text = resources.getString(R.string.latitude_label, task.result.latitude)
                longitudeText.text = resources.getString(R.string.longitude_label, task.result.longitude)
            } else {
                Log.w(TAG, "getLastLocation:exception", task.exception)
                showSnackbar(R.string.no_location_detected)
            }
        }
    }

    private fun showSnackbar(
            snackStrId: Int,
            actionStrId: Int = 0,
            listener: View.OnClickListener? = null
    ) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), getString(snackStrId), LENGTH_INDEFINITE)
        if(actionStrId != 0 && listener != null) {
            snackbar.setAction(getString(actionStrId), listener)
        }
        snackbar.show()
    }

    private fun checkPermissions() = ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_COARSE_LOCATION), REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    private fun requestPermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_COARSE_LOCATION)) {
            Log.i(TAG, "Diplaying permission rationale to provide additional context")
            showSnackbar(R.string.permission_rationale, android.R.string.ok, View.OnClickListener {
                startLocationPermissionRequest()
            })
        }
        else {
            Log.i(TAG, "Requesting Permission")
            startLocationPermissionRequest()
        }
    }

    private fun onRequestPermissionResult (
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        Log.i(TAG, "onRequestPermissionResult")
        if(requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> Log.i(TAG, "User interaction was cancelled")

                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> getLastLocation()

                else -> {
                    showSnackbar(R.string.permission_denied_explanation, R.string.settings, View.OnClickListener {
                        val intent = Intent().apply {
                            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            data = Uri.fromParts("package", APPLICATION_ID, null)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        startActivity(intent)
                    })
                }
            }
        }
    }
}

