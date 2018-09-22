package w.nick.safecar

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

class ChildActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getStringExtra("ID")
        setContentView(R.layout.object_layout)
        //makeHeader()
    }

    fun makeHeader(){
        getSupportActionBar()!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        getSupportActionBar()!!.setCustomView(R.layout.cbs_layout)
    }
}