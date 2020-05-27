package com.example.dogtask.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogtask.R
import com.example.dogtask.adapters.BreedsAdapter
import com.example.dogtask.data.DataSource
import com.example.dogtask.utilities.RvItemDecoration
import kotlinx.android.synthetic.main.activity_search_dogs_breeds.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.util.*
import kotlin.collections.ArrayList

class SearchDogsBreedsActivity : AppCompatActivity() {

    // Vars
    private var typedStr: String? = null
    private var breedsAdapter: BreedsAdapter? = null
    private var job: CompletableJob? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_dogs_breeds)
        title = getString(R.string.title_search_dog)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                typedStr = newText
                return false
            }
        })
        btSearch.setOnClickListener { searchBreeds() }
        // -- Stop the searching process and reset
        btStop.setOnClickListener {
            job?.cancel()
            typedStr = ""
            searchView.setQuery(typedStr,false)
            llSearchResult.visibility = View.GONE
            breedsAdapter?.getBreeds()!!.clear()
            breedsAdapter?.notifyDataSetChanged()
        }
        setUpRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel() // Cancel the concurrent job if it is exists
    }

    // -- Searching breeds
    private fun searchBreeds() {

        if (typedStr == null) {
            Toast.makeText(
                this, "Please type text you want search"
                , Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (checkTypedBreedIfExists(typedStr!!)) {

            val tempList = ArrayList<String>()
            for (i in 0..2) {
                tempList.add(i, typedStr + "_$i")
            }
            llSearchResult.visibility = View.VISIBLE
            iterateTheList(tempList)
        } else {
            Toast.makeText(
                this, "No breed related to your search!!"
                , Toast.LENGTH_SHORT
            ).show()
        }

    }

    // -- Iterate items with 5sec delay
    private fun iterateTheList(tempList: ArrayList<String>) {

        job = Job()
        job?.let { job ->
            CoroutineScope(Dispatchers.IO + job).launch {

                tempList.forEach { breed ->
                    withContext(Main) {
                        breedsAdapter?.updateItem(breed)
                    }
                    delay(5000)
                }
                job.complete()
            }
        }

    }

    // -- Set up the RecyclerView
    private fun setUpRecyclerView() {

        rvDogsSearchResult.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
        rvDogsSearchResult.addItemDecoration(RvItemDecoration(RvItemDecoration.HORIZONTAL))
        breedsAdapter = BreedsAdapter(context =this,isSearch = true) {}
        rvDogsSearchResult.adapter = breedsAdapter
    }

    // -- Check the Breed if exists in the main data set
    private fun checkTypedBreedIfExists(str: String): Boolean {

        for (breed in DataSource.getBreeds()) {

            if (breed.toLowerCase(Locale.getDefault()) == str.toLowerCase(Locale.getDefault())) {
                return true
            }
        }
        return false
    }
}
