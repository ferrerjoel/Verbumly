package com.example.verbumly;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.epicuntitledmobilegame.R;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    private Button q_key, w_key, e_key, r_key, t_key, y_key, u_key, i_key, o_key, p_key,
                a_key, s_key, d_key, f_key, g_key, h_key, j_key, k_key, l_key,
                enter_key, z_key, x_key, c_key, v_key, b_key, n_key, m_key, backspace_key;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public MyKeyboard(Context context){
        this(context, null, 0);
    }
    public MyKeyboard(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }
    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        q_key = (Button) findViewById(R.id.q_key);
        q_key.setOnClickListener(this);
        w_key = (Button) findViewById(R.id.w_key);
        w_key.setOnClickListener(this);
        e_key = (Button) findViewById(R.id.e_key);
        e_key.setOnClickListener(this);
        r_key = (Button) findViewById(R.id.r_key);
        r_key.setOnClickListener(this);
        t_key = (Button) findViewById(R.id.t_key);
        t_key.setOnClickListener(this);
        y_key = (Button) findViewById(R.id.y_key);
        y_key.setOnClickListener(this);
        u_key = (Button) findViewById(R.id.u_key);
        u_key.setOnClickListener(this);
        i_key = (Button) findViewById(R.id.i_key);
        i_key.setOnClickListener(this);
        o_key = (Button) findViewById(R.id.o_key);
        o_key.setOnClickListener(this);
        p_key = (Button) findViewById(R.id.p_key);
        p_key.setOnClickListener(this);
        a_key = (Button) findViewById(R.id.a_key);
        a_key.setOnClickListener(this);
        s_key = (Button) findViewById(R.id.d_key);
        s_key.setOnClickListener(this);
        d_key = (Button) findViewById(R.id.d_key);
        d_key.setOnClickListener(this);
        f_key = (Button) findViewById(R.id.f_key);
        f_key.setOnClickListener(this);
        g_key = (Button) findViewById(R.id.g_key);
        g_key.setOnClickListener(this);
        h_key = (Button) findViewById(R.id.h_key);
        h_key.setOnClickListener(this);
        j_key = (Button) findViewById(R.id.j_key);
        j_key.setOnClickListener(this);
        k_key = (Button) findViewById(R.id.k_key);
        k_key.setOnClickListener(this);
        l_key = (Button) findViewById(R.id.l_key);
        l_key.setOnClickListener(this);
        enter_key = (Button) findViewById(R.id.enter_key);
        enter_key.setOnClickListener(this);
        z_key = (Button) findViewById(R.id.z_key);
        z_key.setOnClickListener(this);
        x_key = (Button) findViewById(R.id.x_key);
        x_key.setOnClickListener(this);
        c_key = (Button) findViewById(R.id.c_key);
        c_key.setOnClickListener(this);
        v_key = (Button) findViewById(R.id.v_key);
        v_key.setOnClickListener(this);
        b_key = (Button) findViewById(R.id.b_key);
        b_key.setOnClickListener(this);
        n_key = (Button) findViewById(R.id.n_key);
        n_key.setOnClickListener(this);
        m_key = (Button) findViewById(R.id.m_key);
        m_key.setOnClickListener(this);
        backspace_key = (Button) findViewById(R.id.backspace_key);
        backspace_key.setOnClickListener(this);

        keyValues.put(R.id.q_key, "Q");
        keyValues.put(R.id.w_key, "W");
        keyValues.put(R.id.e_key, "E");
        keyValues.put(R.id.r_key, "R");
        keyValues.put(R.id.t_key, "T");
        keyValues.put(R.id.y_key, "Y");
        keyValues.put(R.id.u_key, "U");
        keyValues.put(R.id.o_key, "I");
        keyValues.put(R.id.o_key, "O");
        keyValues.put(R.id.p_key, "P");
        keyValues.put(R.id.a_key, "A");
        keyValues.put(R.id.s_key, "S");
        keyValues.put(R.id.d_key, "D");
        keyValues.put(R.id.f_key, "F");
        keyValues.put(R.id.g_key, "G");
        keyValues.put(R.id.h_key, "H");
        keyValues.put(R.id.j_key, "J");
        keyValues.put(R.id.k_key, "K");
        keyValues.put(R.id.l_key, "L");
        keyValues.put(R.id.enter_key, "\n");
        keyValues.put(R.id.z_key, "Z");
        keyValues.put(R.id.x_key, "X");
        keyValues.put(R.id.c_key, "C");
        keyValues.put(R.id.v_key, "V");
        keyValues.put(R.id.b_key, "B");
        keyValues.put(R.id.n_key, "N");
        keyValues.put(R.id.m_key, "M");
        keyValues.put(R.id.backspace_key, "U+0008");

    }
    @Override
    public void onClick(View view){
        if(inputConnection == null){
            return;
        }
    }
}
