package w.nick.safecar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_INDEFINITE
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.util.AndroidException
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.widget.TextView
import android.widget.AdapterView.OnItemClickListener
import com.google.android.gms.location.LocationServices
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.provider.Settings
import w.nick.safecar.BuildConfig.APPLICATION_ID


class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    var list: ListView? = null
    var TAG = "MainActivity"
    var itemname = arrayOf("Baby Boy", "DoggyDog")
    var id = arrayOf<Int>(0, 1)
    var imgid = arrayOf<Int>(R.drawable.ic_back, R.drawable.ic_plus)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeHeader()
        popData()

    }

    override fun onStart() {
        super.onStart()
        if(!checkPermissions()) {
            Log.e(TAG, "Checking Permissions")
        }
    }

    private fun showSnackbar(
            snackStrId: Int,
            actionStrId: Int = 0,
            listener: View.OnClickListener? = null
    ) {
         val snackBar = Snackbar.make(findViewById(android.R.id.content), getString(snackStrId), LENGTH_INDEFINITE)
         if(actionStrId != 0 && listener != null) {
             snackBar.setAction(getString(actionStrId), listener)
         }
        snackBar.show()

    }

    private fun checkPermissions() = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    private fun requestPermissons() {
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

                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> MyIntentService(0.0, 0.0,this).getLastLocation()

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


    fun makeHeader() {
        getSupportActionBar()!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        getSupportActionBar()!!.setCustomView(R.layout.abs_layout)
    }

    private fun popData() {
        val adapter = CustomListAdapter(this, itemname, imgid)
        list = findViewById<View>(R.id.list) as ListView
        list!!.setAdapter(adapter)

        //Item Clicker
        list!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            Toast.makeText(baseContext,id.toString(), Toast.LENGTH_LONG).show()
            if(id == 0.toLong()) {
                val intent = Intent(this, w.nick.safecar.ChildActivity::class.java).apply {
                    putExtra("ID", 0) }
                startActivity(intent)
            }
            else if(id == 1.toLong())
            {
                val intent = Intent(this, ChildActivity::class.java).apply {
                    putExtra("ID", 1)}
                startActivity(intent)
            }
        }
    }

}
