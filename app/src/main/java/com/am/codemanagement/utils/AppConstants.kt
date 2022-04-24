package com.am.codemanagement.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import com.am.codemanagement.R
import com.am.codemanagement.data.vos.OfflineRealmVO
import com.am.codemanagement.data.vos.RecommendedVO
import com.am.codemanagement.data.vos.UpcommingMovieVO
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.text.SimpleDateFormat

const val API_KEY = "3875342a650d7bd12a1bc92bee310358"
const val BASEURL = "https://api.themoviedb.org/3/movie/"
const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
const val upcomming_url = "upcoming?"

fun isNetwork(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected) {
        return true
    }
    return false
}

fun showErrorDialog(context: Context, message: String) {
    val builder = AlertDialog.Builder(context)
    builder.setIcon(R.drawable.ic_info_outline_black_24dp)
    builder.setTitle(context.getString(R.string.fail))
    builder.setMessage(message)
    builder.setPositiveButton(context.getString(R.string.ok), object: DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface?, p1: Int) {

        }
    })
    builder.setCancelable(false)
    builder.show()
}

fun dateFormat(date: String): String {
    return if (date != "") {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val output = SimpleDateFormat("MMM dd,yyyy")
        val dateTime = simpleDateFormat.parse(date)
        output.format(dateTime)
    } else {
        ""
    }
}

fun showInternetConnectionDialog(context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setIcon(R.drawable.ic_info_outline_black_24dp)
    builder.setTitle(context.getString(R.string.internet_connection))
    builder.setMessage(context.getString(R.string.internet_connection_message))
    builder.setPositiveButton(context.getString(R.string.ok), object: DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface?, p1: Int) {

        }
    })
    builder.setCancelable(false)
    builder.show()
}



fun setUpLoadingDialog(context: Context): Dialog {
    val dialog = Dialog(context)
    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = layoutInflater.inflate(R.layout.dialog_loading, null,false)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(view)
    dialog.setCancelable(false)
    dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.create()
    return dialog
}





