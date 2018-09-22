import android.Manifest.permission.ACCESS_FINE_LOCATION
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
import com.example.jasperharrison.locationtest.BuildConfig.APPLICATION_ID // TODO: Change to your package
class ParentActivity: {
    private val TAG = "MainActivity"
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var latitudeText: TextView
    private lateinit var longitudeText: TextView
    private lateinit var cargo: DogOrChild
    private var checkIn: Boolean = false
    private var staticXPos: Double = 0.0
    private var staticYPos: Double = 0.0
    private var prevXPos: Double = 0.0
    private var prevYPos: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        latitudeText = findViewById(R.id.latitude_text) as TextView
        longitudeText = findViewById(R.id.longitude_text) as TextView
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        cargo = DogOrChild()
        onStart()
        while(true)
        {
            getLastLocation()
            performCheckIn()
            if(cargo.angry) {
                // TODO: Send Notification to phone

            }
//            if(checkIn)
//            {
//                // Takes previous location once checking in to app.
//                staticXPos = prevXPos
//                staticYPos = prevYPos
//            }

        }
    }

    override fun onStart() {
        super.onStart()
        if(!checkPermissions()) {
            Log.e(TAG, "Checking Permissions")
            requestPermissions()
        } else {
            getLastLocation()
        }
    }

    fun performCheckIn() {
        if(checkIn) {
            // perform calculations with difference between current location and static locations.
            cargo.updateCords(prevXPos, prevYPos)
            cargo.badness(staticXPos, staticYPos)

        }
        else {
            checkIn = true
            staticXPos = prevXPos
            staticYPos = prevYPos
        }
    }
    // NOTE: Two methods below are when the mother/father checks in once leaving the car or checks out when coming back to vehicle.
    fun checkingIn() {
        checkIn = true
    }

    fun checkoutOut() {
        checkIn = false
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        var xPos: String
        var yPos: String
        var xPosFeet: Double
        var yPosFeet : Double
        fusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
            if (task.isSuccessful && task.result != null) {
                // TODO: Need other GPS location to calculate difference, then call degToFeet in order to get eDist to check if past threshold or not
                xPos = resources.getString(R.string.longitude_label, task.result.longitude)
                yPos = resources.getString(R.string.latitude_label, task.result.latitude)
                prevXPos = xPos.toDouble()
                prevYPos = yPos.toDouble()
//                xPosFeet = cargo.degToFeet(xPos.toDouble())
//                yPosFeet = cargo.degToFeet(yPos.toDouble())

                latitudeText.text = resources.getString(R.string.latitude_label, task.result.latitude)
                longitudeText.text = resources.getString(R.string.longitude_label, task.result.longitude)
                println(latitudeText.text)
                println(longitudeText.text)
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

    private fun checkPermissions() = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    private fun requestPermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)) {
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
