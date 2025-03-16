package com.datong.dict.widget.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.datong.dict.R
import kotlin.jvm.internal.Intrinsics

class FlatButton(context: Context, attributeSet: AttributeSet) : CardView(context, attributeSet) {

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_flat_button, this)
    }

    fun setText(text: String) {
        val btnText = findViewById<TextView>(R.id.btnText)
        btnText.text = text
    }

    fun setBtnColor(i: Int) {
        setCardBackgroundColor(i)
    }

    fun setTextColor(i: Int) {
        findViewById<TextView>(R.id.btnText).setTextColor(i)
    }
}
