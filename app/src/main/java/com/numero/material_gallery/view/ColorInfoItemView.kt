package com.numero.material_gallery.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.numero.material_gallery.R
import com.numero.material_gallery.databinding.ViewColorInfoItemBinding
import java.util.*

class ColorInfoItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewColorInfoItemBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.ColorInfoItemView) {
            setTitle(getString(R.styleable.ColorInfoItemView_title))
            setItemColor(getColor(R.styleable.ColorInfoItemView_itemColor, Color.WHITE))
        }
    }

    fun setTitle(title: String?) {
        binding.titleTextView.text = title
    }

    fun setItemColor(color: Int) {
        binding.cardView.setCardBackgroundColor(color)
        binding.colorTextView.apply {
            text = Integer.toHexString(color).uppercase(Locale.getDefault())
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