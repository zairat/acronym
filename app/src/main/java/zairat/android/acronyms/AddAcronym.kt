package zairat.android.acronyms


import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class AddAcronym : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_acronym)

        val addNew = findViewById<Button>(R.id.add_new_acro)

        addNew.setOnClickListener {
            //get the values in the text boxes
            val newAcro = findViewById<EditText>(R.id.acro_text_box).text.toString()
            val newMean = findViewById<EditText>(R.id.mean_text_box).text.toString()

            if (newAcro != "" && newMean != ""){
                this.openFileOutput("new_words.txt", Context.MODE_APPEND). use {
                    it.write("$newAcro: $newMean \n".toByteArray())
                    it.close()
                }
                val path = this.filesDir
                Log.v("path", path.absolutePath)
            }
            finish()
        }
    }
}
