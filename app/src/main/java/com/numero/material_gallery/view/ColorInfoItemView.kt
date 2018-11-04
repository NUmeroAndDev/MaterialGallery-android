package com.numero.material_gallery.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.numero.material_gallery.R
import kotlinx.android.synthetic.main.view_color_info_item.view.*

class ColorInfoItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        View.inflate(context, R.layout.view_color_info_item, this)

        context.withStyledAttributes(attrs, R.styleable.ColorInfoItemView) {
            setTitle(getString(R.styleable.ColorInfoItemView_title))
            setItemColor(getColor(R.styleable.ColorInfoItemView_itemColor, Color.WHITE))
        }
    }

    fun setTitle(title: String?) {
        titleTextView.text = title
    }

    fun setItemColor(color: Int) {
        cardView.setCardBackgroundColor(color)
        colorTextView.apply {
            text = Integer.toHexString(color).toUpperCase()
            setTextColor(color.adjustTextColor())
        }
    }

    /**
     * @return Color.BLACK or Color.WHITE
     */
    private fun Int.adjustTextColor(): Int {
        val red = Color.red(this)
        val green = Color.green(this)
        val blue = Color.blue(this)
        return if (red * 0.299 + green * 0.587 + blue * 0.114 > 186) {
            Color.BLACK
        } else {
            Color.WHITE
        }
    }
}