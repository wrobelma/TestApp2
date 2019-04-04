package com.example.testapp2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.order_list_item.view.*

/**
 * Klasa Adapter do obsługi widoków w RecyclerView dla listy zleceń
 *
 * @param orders lista zleceń dla której generowane będą widoki
 * @param mainActivity zapisanie kontekstu aktywności dla której generowane będą widoki
 * @return adapter, referencja do listy utworzonych widoków
 */
class OrderAdapter(val orders: ArrayList<String>, val mainActivity: MainActivity) : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.orderView.text = orders[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.order_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    /***
     * Metoda dodaje nowy wpis do listy zleceń i odświeża widok listy zleceń (docelowo ma wysyłać zlecenie)
     *
     * @param order zlecenie do wysłania (String, docelowo obiekt Order)
     */
    fun addOrder(order: String) {
        Log.d("xxx","add liczba orderow przed: ${orders.size}")
        orders.add(order)
        notifyItemInserted(orders.size)
        Log.d("xxx","add liczba orderow po: ${orders.size}")
    }

    /**
     * Metoda usuwa zlecenie z listy zleceń i odświeża widok listy zleceń
     *
     * @param position indeks ordera na liście zleceń
     */
    fun removeOrder(position: Int) {
        Log.d("xxx","rmv liczba orderow przed: ${orders.size}")
        orders.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, orders.size)
        Log.d("xxx","rmv liczba orderow po: ${orders.size}")
    }
}

/**
 * Klasa definiuje logikę widoku zlecenia na liście zleceń
 *
 * @param view referencja do widoku wygenerowanego z definicji order_list_item
 * @return widok z dopiętym listnerem do obsługi kliknięć
 */
class ViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    val orderView : TextView = view.order_details

    init {
        view.setOnClickListener(this)
    }

    /*fun Context.startActivity1(f: Intent.() -> Unit): Unit =
        Intent().apply(f).run(this::startActivity)

    inline fun <reified T: Activity> Context.start(
        noinline createIntent: Intent.() -> Unit = {}
    ) = startActivity1 {
        component = gotoClass(T::class.java)
        createIntent(this)
    }

    fun Context.gotoClass(targetType: Class<*>) = ComponentName(this, targetType)*/
    /**
     * Metoda do obsługi kliknięć w widok zlecenia na liście zleceń
     */
    override fun onClick(v: View?) {
        Toast.makeText( itemView.context , "taped ${orderView.text}", Toast.LENGTH_SHORT).show()
        Log.d("xxx","test log")
        val intent = Intent(itemView.context, OrderDetails::class.java)
        intent.putExtra(OrderDetails.ORDER_ID, orderView.text)
        startActivity(itemView.context, intent, null)
    }
}

/**
 * Klasa abstrakcyjne określająca zachowanie widoku podczas funkcji swipe
 *
 * Metoda onSwipe definiowana jest podczas tworzenia obiektu,
 *   definiuje akcje jakie będą wykonane po zakończeniu funkcji swipe w określonym kierunku
 */
abstract class SwipeSendDelete(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private val deleteCol = ContextCompat.getColor(context, R.color.swipeLeft)
    private val sendCol = ContextCompat.getColor(context, R.color.swipeRight)
    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_black_24dp)
    private val sendIcon  = ContextCompat.getDrawable(context, R.drawable.ic_mail_outline_black_24dp)
    private val background = ColorDrawable()

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    /**
     * Metoda rysująca widok podczas wykonywania swipe
     *
     * Ustawia odpowiednie tło z ikoną i przeźroczystość widoku w zależności od kierunku swipe'a
     */
    @SuppressLint("ResourceAsColor")
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val item = viewHolder.itemView
        background.setBounds(item.left, item.top, item.right, item.bottom)
        when (dX.toInt()) {
            in -item.width..-1 -> {
                background.color = deleteCol
                item.alpha = dX / item.right + 1
                val itemHeight = item.bottom - item.top
                val iconMargin = (itemHeight - deleteIcon!!.intrinsicHeight) / 2
                val iconTop = item.top + iconMargin
                val iconBottom = iconTop + deleteIcon.intrinsicHeight
                val iconRight = item.right - iconMargin
                val iconLeft = iconRight - deleteIcon.intrinsicWidth
                deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                background.draw(c)
                deleteIcon.draw(c)
            }
            in 1..item.width -> {
                background.color = sendCol
                item.alpha = 1 - dX / item.right
                val itemHeight = item.bottom - item.top
                val iconMargin = (itemHeight - sendIcon!!.intrinsicHeight) / 2
                val iconTop = item.top + iconMargin
                val iconBottom = iconTop + sendIcon.intrinsicHeight
                val iconLeft = item.left + iconMargin
                val iconRight = iconLeft + sendIcon.intrinsicWidth
                sendIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                background.draw(c)
                sendIcon.draw(c)
            }
            else -> {
                deleteIcon!!.setVisible(false, false)
                sendIcon!!.setVisible(false, false)
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

}