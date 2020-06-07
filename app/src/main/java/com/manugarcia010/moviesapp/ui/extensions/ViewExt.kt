package com.manugarcia010.moviesapp.ui.extensions

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

val View.parentActivity: AppCompatActivity?
    get() {
        var context = this.context
        while (context is ContextWrapper) {
            if (context is AppCompatActivity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }
