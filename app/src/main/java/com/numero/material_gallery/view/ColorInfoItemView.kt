package com.numero.material_gallery.view

import android.content.Context
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
            titleTextView.text = getString(R.styleable.ColorInfoItemView_title)
        }

    }
}