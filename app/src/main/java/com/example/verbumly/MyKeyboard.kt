package com.example.verbumly

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout

class MyKeyboard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    private var q_key: Button? = null
    private var w_key: Button? = null
    private var e_key: Button? = null
    private var r_key: Button? = null
    private var t_key: Button? = null
    private var y_key: Button? = null
    private var u_key: Button? = null
    private var i_key: Button? = null
    private var o_key: Button? = null
    private var p_key: Button? = null
    private var a_key: Button? = null
    private var s_key: Button? = null
    private var d_key: Button? = null
    private var f_key: Button? = null
    private var g_key: Button? = null
    private var h_key: Button? = null
    private var j_key: Button? = null
    private var k_key: Button? = null
    private var l_key: Button? = null
    private var enter_key: Button? = null
    private var z_key: Button? = null
    private var x_key: Button? = null
    private var c_key: Button? = null
    private var v_key: Button? = null
    private var b_key: Button? = null
    private var n_key: Button? = null
    private var m_key: Button? = null
    private var backspace_key: Button? = null
    private val keyValues = SparseArray<String>()
    private var inputConnection: InputConnection? = null

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true)
        q_key = findViewById<View>(R.id.q_key) as Button
        q_key!!.setOnClickListener(this)
        w_key = findViewById<View>(R.id.w_key) as Button
        w_key!!.setOnClickListener(this)
        e_key = findViewById<View>(R.id.e_key) as Button
        e_key!!.setOnClickListener(this)
        r_key = findViewById<View>(R.id.r_key) as Button
        r_key!!.setOnClickListener(this)
        t_key = findViewById<View>(R.id.t_key) as Button
        t_key!!.setOnClickListener(this)
        y_key = findViewById<View>(R.id.y_key) as Button
        y_key!!.setOnClickListener(this)
        u_key = findViewById<View>(R.id.u_key) as Button
        u_key!!.setOnClickListener(this)
        i_key = findViewById<View>(R.id.i_key) as Button
        i_key!!.setOnClickListener(this)
        o_key = findViewById<View>(R.id.o_key) as Button
        o_key!!.setOnClickListener(this)
        p_key = findViewById<View>(R.id.p_key) as Button
        p_key!!.setOnClickListener(this)
        a_key = findViewById<View>(R.id.a_key) as Button
        a_key!!.setOnClickListener(this)
        s_key = findViewById<View>(R.id.s_key) as Button
        s_key!!.setOnClickListener(this)
        d_key = findViewById<View>(R.id.d_key) as Button
        d_key!!.setOnClickListener(this)
        f_key = findViewById<View>(R.id.f_key) as Button
        f_key!!.setOnClickListener(this)
        g_key = findViewById<View>(R.id.g_key) as Button
        g_key!!.setOnClickListener(this)
        h_key = findViewById<View>(R.id.h_key) as Button
        h_key!!.setOnClickListener(this)
        j_key = findViewById<View>(R.id.j_key) as Button
        j_key!!.setOnClickListener(this)
        k_key = findViewById<View>(R.id.k_key) as Button
        k_key!!.setOnClickListener(this)
        l_key = findViewById<View>(R.id.l_key) as Button
        l_key!!.setOnClickListener(this)
        enter_key = findViewById<View>(R.id.enter_key) as Button
        enter_key!!.setOnClickListener(this)
        z_key = findViewById<View>(R.id.z_key) as Button
        z_key!!.setOnClickListener(this)
        x_key = findViewById<View>(R.id.x_key) as Button
        x_key!!.setOnClickListener(this)
        c_key = findViewById<View>(R.id.c_key) as Button
        c_key!!.setOnClickListener(this)
        v_key = findViewById<View>(R.id.v_key) as Button
        v_key!!.setOnClickListener(this)
        b_key = findViewById<View>(R.id.b_key) as Button
        b_key!!.setOnClickListener(this)
        n_key = findViewById<View>(R.id.n_key) as Button
        n_key!!.setOnClickListener(this)
        m_key = findViewById<View>(R.id.m_key) as Button
        m_key!!.setOnClickListener(this)
        backspace_key = findViewById<View>(R.id.backspace_key) as Button
        backspace_key!!.setOnClickListener(this)
        keyValues.put(R.id.q_key, "Q")
        keyValues.put(R.id.w_key, "W")
        keyValues.put(R.id.e_key, "E")
        keyValues.put(R.id.r_key, "R")
        keyValues.put(R.id.t_key, "T")
        keyValues.put(R.id.y_key, "Y")
        keyValues.put(R.id.u_key, "U")
        keyValues.put(R.id.o_key, "I")
        keyValues.put(R.id.o_key, "O")
        keyValues.put(R.id.p_key, "P")
        keyValues.put(R.id.a_key, "A")
        keyValues.put(R.id.s_key, "S")
        keyValues.put(R.id.d_key, "D")
        keyValues.put(R.id.f_key, "F")
        keyValues.put(R.id.g_key, "G")
        keyValues.put(R.id.h_key, "H")
        keyValues.put(R.id.j_key, "J")
        keyValues.put(R.id.k_key, "K")
        keyValues.put(R.id.l_key, "L")
        keyValues.put(R.id.enter_key, "\n")
        keyValues.put(R.id.z_key, "Z")
        keyValues.put(R.id.x_key, "X")
        keyValues.put(R.id.c_key, "C")
        keyValues.put(R.id.v_key, "V")
        keyValues.put(R.id.b_key, "B")
        keyValues.put(R.id.n_key, "N")
        keyValues.put(R.id.m_key, "M")
    }

    override fun onClick(view: View) {
        if (inputConnection == null) {
            return
        }
        if (view.id == R.id.backspace_key) {
            val selectedText = inputConnection!!.getSelectedText(0)
            if (TextUtils.isEmpty(selectedText)) {
                inputConnection!!.deleteSurroundingText(1, 0)
            } else {
                inputConnection!!.commitText("", 1)
            }
        } else {
            val value = keyValues[view.id]
            inputConnection!!.commitText(value, 1)
        }
    }

    fun setInputConnection(ic: InputConnection?) {
        inputConnection = ic
    }
}