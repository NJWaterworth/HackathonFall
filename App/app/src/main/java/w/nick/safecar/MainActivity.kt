package w.nick.safecar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    var list: ListView? = null

    var itemname = arrayOf("Baby Boy", "DoggyDog")
    var imgid = arrayOf<Int>(R.drawable.ic_back, R.drawable.ic_plus)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeHeader()
        popData()
    }

    fun makeHeader() {
        getSupportActionBar()!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        getSupportActionBar()!!.setCustomView(R.layout.abs_layout)
    }

    fun popData(){
        val adapter = CustomListAdapter(this, itemname, imgid)
        list = findViewById<View>(R.id.list) as ListView
        list!!.setAdapter(adapter)


    }

   override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_settings -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
