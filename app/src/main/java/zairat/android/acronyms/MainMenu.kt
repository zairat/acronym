package zairat.android.acronyms

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        //make play button open main playing activity: activity_main
        val playButton = findViewById<Button>(R.id.play_button)
        playButton.setOnClickListener {
            //switch to the proper activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //make add button open activity to add a new acronym: activity_add_acronym
        val addButton = findViewById<Button>(R.id.add_acro_button)
        addButton.setOnClickListener {
            // switch to the proper activity
            val intent = Intent(this, AddAcronym::class.java)
            startActivity(intent)
        }
    }

}
