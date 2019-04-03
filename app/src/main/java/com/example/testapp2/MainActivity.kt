package com.example.testapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Aktywność z listą zapisanych zleceń
 *
 * Aktywność do zarządzania listą zleceń zapisanych w lokalnej bazie
 *
 * @property orders lista zleceń (na tą chwilę String, później obiektów Order)
 */
class MainActivity : AppCompatActivity() {

    private val orders: ArrayList<String> = ArrayList()
    /**
     * Inicjalizacja aktywności, wypełnia listę zleceń danymi z lokalnej bazy
     * oraz ustawia widok RecyclerView z obsługą funkcji swipe w lewo i prawo
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getOrders()

        order_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        order_list.layoutManager = LinearLayoutManager(this)
        order_list.adapter = OrderAdapter(orders, this)

        val swipeHandler = object : SwipeSendDelete(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter= order_list.adapter as OrderAdapter

                if (direction == ItemTouchHelper.LEFT) {
                    adapter.removeOrder(viewHolder.adapterPosition)
                    Toast.makeText(viewHolder.itemView.context,"Delete", Toast.LENGTH_SHORT).show()
                }

                if (direction == ItemTouchHelper.RIGHT) {
                    adapter.addOrder("Send order")
                    Toast.makeText(viewHolder.itemView.context,"Sent", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val orderTouchHelper = ItemTouchHelper(swipeHandler)
        orderTouchHelper.attachToRecyclerView(order_list)

    }

    /**
     * Pobiera dane zleceń z lokalnej bazy
     *
     * <Do zrobienia>
     */
    private fun getOrders() {
        orders.add("Order 1 ooooooooooooooooooooooooooooooooooooooooooo")
        orders.add("Order 2")
        orders.add("Order 3")
        orders.add("Order 4")
        orders.add("Order 5")
        orders.add("Order 6")
        orders.add("Order 7")
        orders.add("Order 8")
        orders.add("Order 9")
        orders.add("Order 10")
        orders.add("Order 11")
        orders.add("Order 12")
        orders.add("Order 13")
        orders.add("Order 14")
        orders.add("Order 15")
        orders.add("Order 16")
        orders.add("Order 17")
        orders.add("Order 18")
        orders.add("Order 19")
        orders.add("Order 20")
        orders.add("Order 21")
        orders.add("Order 22")
        orders.add("Order 23")
        orders.add("Order 24")
    }
}
