package w.nick.safecar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.util.AndroidException
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.widget.TextView
import android.widget.AdapterView.OnItemClickListener



class MainActivity : AppCompatActivity() {
    var list: ListView? = null

    var itemname = arrayOf("Baby Boy", "DoggyDog")
    var id = arrayOf<Int>(0, 1)
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
