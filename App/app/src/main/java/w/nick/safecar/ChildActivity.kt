package w.nick.safecar

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.object_layout.*

class ChildActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getStringExtra("ID")
        setContentView(R.layout.object_layout)
        makeHeader()
        var Thing: DogOrChild = DogOrChild()
        //CheckButton()

        SetUpAnimal(Thing)
    }

    fun makeHeader() {
        getSupportActionBar()!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        getSupportActionBar()!!.setCustomView(R.layout.cbs_layout)
    }

    fun SetUpAnimal(t: DogOrChild) {
        t.update(SystemClock.elapsedRealtimeNanos(),100.0,100.0)

        if(t.angry)
        {
            //Do ALERT
        }
    }

    /*fun CheckButton() {
        //val toggle: ToggleButton = findViewById(R.layout.abc_alert_dialog_button_bar_material)
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
            } else {
                // The toggle is disabled
            }
        }
    }*/
}