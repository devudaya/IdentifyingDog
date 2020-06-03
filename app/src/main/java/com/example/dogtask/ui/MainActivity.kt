package com.example.dogtask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogtask.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // -- Navigate each activities
        btIdentifyTheBreed.setOnClickListener {
            startActivity(Intent(this, IdentifyBreedActivity::class.java))
        }
        btIdentifyTheDog.setOnClickListener {
            startActivity(Intent(this, IdentifyDogActivity::class.java))
        }
        btSearchDogBreeds.setOnClickListener {
            startActivity(Intent(this, SearchDogsBreedsActivity::class.java))
        }

        // -- Close application
        ivCloseApp.setOnClickListener { finish() }
    }
}
