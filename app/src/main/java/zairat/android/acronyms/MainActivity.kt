package zairat.android.acronyms

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.jetbrains.anko.toast
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private val dictionary = mutableMapOf<String, String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream = resources.openRawResource(R.raw.acro_dictionary)
        val acronym = ArrayList<String>()
        val meanings = ArrayList<String>()
        val pathName = this.filesDir.absolutePath +"/new_words.txt"

        inputStream.bufferedReader().useLines { lines -> lines.forEach {
            val duostring = it.split(": ")
            acronym.add(duostring[0])
            meanings.add(duostring[1])
        } }

        val newFile = File(pathName)
        if (newFile.exists()) {      //if (fileList().contains("new_words.txt")) {
            Log.v("testing", "the newFile Exists!")
            Log.v("testing", fileList().toString())

            val inputStreamNewWords = newFile.inputStream()
            inputStreamNewWords.bufferedReader().useLines { lines ->
                lines.forEach {
                    val duostring = it.split(": ")
                    acronym.add(duostring[0])
                    meanings.add(duostring[1])
                }
            }
        }
        else {
            Log.v("testing", "the newFile doesn't Exist :(")
        }

        for (item in acronym){
            dictionary[item]= meanings[acronym.indexOf(item)]
        }

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, acronym)
        acronym_list_view.adapter = myAdapter

        acronym_list_view.setOnItemClickListener{
            parent: AdapterView<*>, view: View?, position: Int, id: Long ->
            //toast the answer
            val acro = parent.getItemAtPosition(position).toString()
            val mean = dictionary[acro]
            toast(mean.toString())
            }
    }
}
