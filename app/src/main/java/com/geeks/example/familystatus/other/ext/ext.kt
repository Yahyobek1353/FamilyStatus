package com.geeks.example.familystatus.other.ext

import android.app.Activity
import android.content.Context
import android.content.Intent

fun Context.mainIntent(classA : Activity){
    startActivity(Intent(this , classA::class.java  ))
}


