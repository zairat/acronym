package zairat.android.acronyms

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.toast
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dictionary = mutableMapOf<String, String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream = resources.openRawResource(R.raw.acro_dictionary)
        val acronym = ArrayList<String>()
        val meanings = ArrayList<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach {
            var duostring = it.split(": ")
            acronym.add(duostring[0])
            meanings.add(duostring[1])
        } }

        for (item in acronym){
            dictionary[item]= meanings[acronym.indexOf(item)]
        }

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, acronym)
        acronym_list_view.adapter = myAdapter

        acronym_list_view.setOnItemClickListener{
            parent: AdapterView<*>, view: View?, position: Int, id: Long ->
            //toast the answer
            var acro = parent.getItemAtPosition(position).toString()
            var mean = dictionary[acro]
            toast(mean.toString())
            }


    }



}
