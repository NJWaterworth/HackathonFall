package w.nick.safecar


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomListAdapter(private val context: Activity, private val itemname: Array<String>, private val imgid: Array<Int>)// TODO Auto-generated constructor stub
    : ArrayAdapter<String>(context, R.layout.ob_list, itemname) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.ob_list, null, true)

        val txtTitle = rowView.findViewById(R.id.item) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val extratxt = rowView.findViewById(R.id.textView1) as TextView

        txtTitle.text = itemname[position]
        imageView.setImageResource(imgid[position])
        extratxt.text = "Description " + itemname[position]
        return rowView

    }
}