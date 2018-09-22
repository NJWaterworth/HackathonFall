package w.nick.safecar

import android.app.IntentService
import android.content.Intent
import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.content.Context
import android.util.Log
import w.nick.safecar.DogOrChild
import java.lang.ref.WeakReference

class MyIntentService(lat: Double = 0.0, lon: Double = 0.0, var MyActivity: MainActivity): IntentService("MyIntentService") {
    var Lat = lat
    var Lon = lon
    var checkIn = false
    private var cargo: DogOrChild = DogOrChild()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var staticXPos: Double = 0.0
    private var staticYPos: Double = 0.0
    private var prevXPos: Double = 0.0
    private var prevYPos: Double = 0.0
    private var myActivityRef = MyActivity
    private val TAG = "IntentService"
    private lateinit var notificationalert: NotificationAlert

    override fun onHandleIntent(intent: Intent?) {
        getLastLocation()
        performCheckIn()
        if(cargo.angry) {
            // TODO: Send notification to phone
            notificationalert = NotificationAlert()
            notificationalert.createNotificationStuff(myActivityRef.applicationContext)
            notificationalert.alert()
        }
        Thread.sleep(400)
    }


    private fun performCheckIn() {
        if(checkIn) {
            cargo.updateCords(prevXPos, prevYPos)
            cargo.badness(prevXPos, prevYPos)
        } else {
            checkIn = true
            staticXPos = prevXPos
            staticYPos = prevYPos
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        var xPos: String
        var yPos: String
        fusedLocationClient.lastLocation.addOnCompleteListener(myActivityRef) { task ->
            if(task.isSuccessful &&  task.result != null) {
                xPos = resources.getString(R.string.latitude_label, task.result.latitude)
                yPos = resources.getString(R.string.longitude_label, task.result.longitude)
                prevXPos = xPos.toDouble()
                prevYPos = yPos.toDouble()
            }
            else {
                Log.w(TAG, " getLastLocationException", task.exception)
            }
        }
    }
}