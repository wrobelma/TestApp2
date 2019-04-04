package com.example.testapp2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_order_page_one.*
import kotlinx.android.synthetic.main.fragment_order_page_one.view.*
import java.util.*

/**
 * [Fragment] dla pierwszej strony zlecenia (szczegóły zlecenia)
 *
 */
class OrderPageOne : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_page_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        order_page_one.order_date.text = "${getString(R.string.order_date)}: ${Date()}"
        super.onViewCreated(view, savedInstanceState)
    }

}
