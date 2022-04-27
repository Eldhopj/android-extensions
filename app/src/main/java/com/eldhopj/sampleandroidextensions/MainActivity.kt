package com.eldhopj.sampleandroidextensions

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.eldhopj.android_extensions.action
import com.eldhopj.android_extensions.bold
import com.eldhopj.android_extensions.setOnSafeClickListener
import com.eldhopj.android_extensions.snackBarAction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val textview = findViewById<TextView>(R.id.textView)
        button.setOnSafeClickListener {
            it?.snackBarAction(R.string.app_name) {
                action("Retry") {

                }
            }
        }
        textview.bold()
    }
}
