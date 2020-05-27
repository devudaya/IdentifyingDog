package com.example.dogtask.utilities

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.dogtask.R
import com.example.dogtask.data.DataSource
import java.lang.StringBuilder
import java.util.*

class Utils {

    companion object {

        // -- Generate a random number
        fun generateRandomNumber(
            start: Int = 0,
            end: Int = (DataSource.getBreeds().size) - 1
        ): Int {
            return (start..end).random()
        }

        // -- Create and show Dialog box
        fun showDialog(context: Context, message: String? = null) {

            val builder1: AlertDialog.Builder = AlertDialog.Builder(context)

            if (message != null) {
                val mView = View.inflate(context,
                    R.layout.wrong_dialog, null)
                val breedName = mView.findViewById<TextView>(R.id.breedNameInDialog)
                breedName.text = if (message == "") "" else StringBuilder("No!! It 's $message")
                builder1.setView(mView)
            } else {
                val mView = View.inflate(context,
                    R.layout.correct_dialog, null)
                builder1.setView(mView)
            }
            builder1.setCancelable(true)

            builder1.setPositiveButton(
                "Ok"
            ) { dialog, _ -> dialog.cancel() }

            val alert11: AlertDialog = builder1.create()
            alert11.show()
        }

        // -- Get image drawable by it's name
        fun getImageFromDrawable(context: Context, name: String): Drawable {

            val resources: Resources = context.resources
            val resourceId: Int = resources.getIdentifier(
                name.toLowerCase(Locale.getDefault()), "drawable",
                context.packageName
            )
            return context.getDrawable(resourceId)!!
        }

        // -- Generate random number for sub dogs 
        fun generateSubCatDog(): Int {

            return (0..2).random()
        }
    }
}