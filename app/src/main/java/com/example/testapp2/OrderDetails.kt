package com.example.testapp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_order_details.*

/**
 * Klasa do obsługi formularza szczegółów zlecenia
 *
 * pozwala na podgląd szczegółów zapisanego zlecenia lub dodawanie nowego zlecenia
 *
 * @property order_id numer zlecenia odczytywany z Intent, przekazywany po kliknięciu
 *                    wybranego zlecenia w aktywności z listą zleceń
 */
class OrderDetails : AppCompatActivity() {

    private lateinit var order_id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        setSupportActionBar(toolbar)

        val bundle = intent.extras
        order_id = bundle!!.get(ORDER_ID) as String
        title = order_id

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * Obiekt statyczny na potrzeby komunikacji poprzez Intent
     */
    companion object {
        const val ORDER_ID = "order_id"
    }

}
