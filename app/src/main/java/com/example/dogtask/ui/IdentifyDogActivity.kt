package com.example.dogtask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dogtask.R
import com.example.dogtask.utilities.Utils
import com.example.dogtask.adapters.BreedsAdapter
import com.example.dogtask.data.DataSource
import com.example.dogtask.utilities.RvItemDecoration
import kotlinx.android.synthetic.main.activity_identify_dog.*
import java.util.*
import kotlin.collections.ArrayList

class IdentifyDogActivity : AppCompatActivity() {

    // Vars
    private val dogList = mutableListOf<String>()
    private var breedsAdapter: BreedsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identify_dog)
        title = getString(R.string.title_identify_the_dog)
        makingRandomListOfDogs()
        setUpRecyclerView()
        btNextDog.setOnClickListener {
            updateDogList()
        }
    }

    // -- Making the Next Next List and update the Recycler View
    private fun updateDogList() {
        makingRandomListOfDogs()
        breedsAdapter?.updateDogList(dogList)
    }

    // -- Making random List of Dogs
    private fun makingRandomListOfDogs() {

        dogList.clear()
        val list = ArrayList<Int>()
        for (i in 1..9) {
            list.add(i)
        }
        list.shuffle()
        for (i in 0..2) {
            println(list[i])
            println(DataSource.getBreeds()[list[i]])
            dogList.add(DataSource.getBreeds()[list[i]])
        }
        dogList.forEach {
            println(it)
        }
        val randomNumForAnswer = (0..2).random()
        tvBreed.text = dogList[randomNumForAnswer]
    }

    // -- Set up the RecyclerView
    private fun setUpRecyclerView() {

        rvDogs.layoutManager = GridLayoutManager(this, 3)
        rvDogs.addItemDecoration(RvItemDecoration(RvItemDecoration.GRID))
        breedsAdapter = BreedsAdapter(this, dogList) {position ->
            val selectedDog = breedsAdapter!!.getBreeds()[position].toLowerCase(Locale.getDefault())
            if (tvBreed.text.toString().toLowerCase(Locale.getDefault()) == selectedDog) {
                Utils.showDialog(this)
            }else{
                Utils.showDialog(this, "")
            }

        }
        rvDogs.adapter = breedsAdapter
    }

}
