package com.example.dogtask.data

class DataSource {

    companion object {

        fun getBreeds(): MutableList<String> {

            val listOfBreeds = mutableListOf<String>()
            listOfBreeds.add("Basset")
            listOfBreeds.add("Cairn")
            listOfBreeds.add("Airedale")
            listOfBreeds.add("Otterhound")
            listOfBreeds.add("Saluki")
            listOfBreeds.add("Redbone")
            listOfBreeds.add("Whippet")
            listOfBreeds.add("Borzoi")
            listOfBreeds.add("Bloodhound")
            listOfBreeds.add("Beagle")
            return listOfBreeds
        }
    }
}