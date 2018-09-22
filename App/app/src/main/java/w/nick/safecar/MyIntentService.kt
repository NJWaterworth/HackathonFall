package com.example.jasperharrison.locationtest

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log

class MyIntentService(lat: Double, lon: Double){
    var Lat = 0.0
    var Lon = 0.0

    constructor(): super(null) {
        super.onCreate()
    }
    constructor(lat : Double, lon : Double) : super("MyIntentService") {
        this.Lat = lat
        this.Lon = lon
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        // setContentView(R.layout.activity_main)
//        for (i in 1..5) {
//            Log.d("MyIntentSerice", "Service doing something " + i)
//            Thread.sleep(4000)
//        }
    }

    private fun setContentView(activity_main: Unit) {

    }

}