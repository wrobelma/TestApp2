package com.example.testapp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_order_details.*
import kotlinx.android.synthetic.main.content_order_details.*
import java.util.ArrayList

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

        val adapter = OrderDetailsPager(supportFragmentManager)
        adapter.addFragment(OrderPageOne(),getString(R.string.tab_1))
        adapter.addFragment(OrderPageTwo(),getString(R.string.tab_2))
        adapter.addFragment(OrderPageThree(),getString(R.string.tab_3))
        order_pages.adapter = adapter
        tabs.setupWithViewPager(order_pages)

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

class OrderDetailsPager(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList : MutableList<Fragment> = ArrayList()
    private val titleList : MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

}
