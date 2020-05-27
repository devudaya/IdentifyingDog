package com.example.dogtask.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dogtask.R
import com.example.dogtask.utilities.Utils
import com.example.dogtask.data.DataSource
import kotlinx.android.synthetic.main.activity_identify_breed.*


class IdentifyBreedActivity : AppCompatActivity() {

    // vars and vals
    private lateinit var randomBreedName: String
    private lateinit var selectedBreed: String
    private var isSubmitted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identify_breed)
        title = getString(R.string.title_identify_the_breed)
        setBreedsToSpinner()
        setRandomBreed()
        btSubmit.setOnClickListener { checkTheBreedAndNext() }
    }

    private fun checkTheBreedAndNext() {

        if (isSubmitted) {

            setRandomBreed()
            btSubmit.text = getString(R.string.submit)
        } else {

            if (randomBreedName == selectedBreed) {
                Utils.showDialog(this)
            } else {
                Utils.showDialog(this, randomBreedName)
            }
            btSubmit.text = getString(R.string.next)
        }
        isSubmitted = !isSubmitted

    }

    // -- Set random breed
    private fun setRandomBreed() {

        randomBreedName = DataSource.getBreeds()[Utils.generateRandomNumber()]
        var tempPath = randomBreedName
        tempPath += "_${Utils.generateSubCatDog()}"
        Glide.with(this).load(Utils.getImageFromDrawable(this, tempPath))
            .into(ivDogImage)
    }

    // -- Set breeds to spinner
    private fun setBreedsToSpinner() {

        // -- Creating the ArrayAdapter instance having the breeds list
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            DataSource.getBreeds()
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spBreeds.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedBreed = DataSource.getBreeds()[p2]
            }
        }
        spBreeds.adapter = arrayAdapter
    }

}
