package com.numero.material_gallery.core.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.withStyledAttributes
import com.numero.material_gallery.core.R
import com.numero.material_gallery.core.databinding.ViewThemeColorBinding
import java.util.*

class ThemeColorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewThemeColorBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.ThemeColorView) {
            setTitle(getString(R.styleable.ThemeColorView_title))
            val contentColor = if (hasValue(R.styleable.ThemeColorView_contentColor)) {
                getColorOrThrow(R.styleable.ThemeColorView_contentColor)
            } else {
                null
            }
            setColor(
                backgroundColor = getColor(R.styleable.ThemeColorView_backgroundColor, Color.WHITE),
                contentColor = contentColor
            )
        }
    }

    fun setTitle(title: String?) {
        binding.titleTextView.text = title
    }

    fun setColor(backgroundColor: Int, contentColor: Int? = null) {
        binding.cardView.setCardBackgroundColor(backgroundColor)
        binding.colorTextView.text = Integer.toHexString(backgroundColor).uppercase(Locale.getDefault())
        if (contentColor != null) {
            binding.colorTextView.setTextColor(contentColor)
            binding.titleTextView.setTextColor(contentColor)
            binding.cardView.strokeColor = contentColor
        } else {
            val color = backgroundColor.adjustTextColor()
            binding.colorTextView.setTextColor(color)
            binding.titleTextView.setTextColor(color)
            binding.cardView.strokeColor = color
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