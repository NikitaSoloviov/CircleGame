package com.dts.myapplication

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.TimeUnit

val Long.secondsStr: String
    get() = TimeUnit.MILLISECONDS.toSeconds(this).toString()

fun View.snackbar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}
