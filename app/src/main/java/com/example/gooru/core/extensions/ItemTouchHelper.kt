package com.example.gooru.core.extensions

import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

const val RECOVER_ANIMATION_DURATION = 400L
const val ANIMATION_PROPERTY = "scrollX"

class ItemTouchHelperCallback(
    private val limitScrollX: Int
) : ItemTouchHelper.Callback() {
    private var currentScrollX = 0
    private var currentScrollXWhenInActive = 0
    private var initXWhenInActive = 0f
    private var firstInActive = false
    private var leftSwipeChecker = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = true

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // nothing
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder) =
        Integer.MAX_VALUE.toFloat()

    override fun getSwipeEscapeVelocity(defaultValue: Float) = Integer.MAX_VALUE.toFloat()

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            recoverInactiveItem(recyclerView, viewHolder, dX)
            markStartGestureItemState(viewHolder, dX)
            if (isCurrentlyActive) {
                scrollItemByGesture(viewHolder, dX)
            } else {
                markFinishGestureItemState(viewHolder, dX)
                scrollItemByUserScrollDirectionAutomatically(viewHolder, dX)
            }
        }
    }

    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) {
        super.clearView(recyclerView, viewHolder)
        with(viewHolder.itemView) {
            when {
                scrollX > limitScrollX -> scrollTo(limitScrollX, 0)
                scrollX < 0 -> scrollTo(0, 0)
            }
        }
    }

    private fun markStartGestureItemState(
        viewHolder: RecyclerView.ViewHolder,
        dX: Float
    ) {
        if (dX == 0f) {
            currentScrollX = viewHolder.itemView.scrollX
            firstInActive = true
        }
    }

    private fun markFinishGestureItemState(
        viewHolder: RecyclerView.ViewHolder,
        dX: Float
    ) {
        if (firstInActive) {
            firstInActive = false
            currentScrollXWhenInActive = viewHolder.itemView.scrollX
            initXWhenInActive = dX
        }
    }

    private fun scrollItemByGesture(
        viewHolder: RecyclerView.ViewHolder,
        dX: Float
    ) {
        val scrollOffset =
            (currentScrollX + (-dX).toInt()).coerceIn(minimumValue = 0, maximumValue = limitScrollX)
        viewHolder.itemView.scrollTo(scrollOffset, 0)
    }

    private fun scrollItemByUserScrollDirectionAutomatically(
        viewHolder: RecyclerView.ViewHolder,
        dX: Float
    ) {
        when {
            dX > 0 -> viewHolder.itemView.scrollTo(
                (currentScrollXWhenInActive * dX / initXWhenInActive).toInt(),
                0
            )
            dX < 0 -> viewHolder.itemView.scrollTo(
                limitScrollX - ((limitScrollX - currentScrollXWhenInActive) * dX / initXWhenInActive).toInt(),
                0
            )
        }
    }

    private fun recoverInactiveItem(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float
    ) {
        if (viewHolder.itemView.scrollX == 0) {
            leftSwipeChecker = true
        }
        leftSwipeChecker = leftSwipeChecker && dX < 0
        if (leftSwipeChecker) {
            recoverSwipedItem(viewHolder, recyclerView)
            if (viewHolder.itemView.scrollX != 0) {
                leftSwipeChecker = false
            }
        }
    }

    private fun recoverSwipedItem(
        viewHolder: RecyclerView.ViewHolder,
        recyclerView: RecyclerView
    ) {
        if (recyclerView.adapter != null) {
            for (i in recyclerView.adapter!!.itemCount downTo 0) {
                val itemView = recyclerView.findViewHolderForAdapterPosition(i)?.itemView
                if (i != viewHolder.bindingAdapterPosition) {
                    itemView?.let {
                        if (it.scrollX > 0) {
                            recoverItem(itemView)
                        }
                    }
                }
            }
        }
    }

    private fun recoverItem(itemView: View?) {
        ObjectAnimator.ofInt(
            itemView,
            ANIMATION_PROPERTY,
            0
        ).apply {
            duration = RECOVER_ANIMATION_DURATION
            start()
        }
    }
}


fun RecyclerView.setItemTouchHelper(limitScrollX: Int) {


    ItemTouchHelper(ItemTouchHelperCallback(limitScrollX)).attachToRecyclerView(this)
    ItemTouchHelper(ItemTouchHelperCallback(limitScrollX))

}
