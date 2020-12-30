package com.numero.material_gallery.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.StyleRes
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.use
import androidx.core.content.withStyledAttributes
import androidx.core.widget.TextViewCompat
import com.numero.material_gallery.R
import com.numero.material_gallery.databinding.ViewTypographyInfoItemBinding

class TypographyInfoItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewTypographyInfoItemBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.TypographyInfoItemView) {
            setTitle(getString(R.styleable.TypographyInfoItemView_title))
            if (hasValue(R.styleable.TypographyInfoItemView_titleTextAppearance)) {
                setTextAppearance(getResourceIdOrThrow(R.styleable.TypographyInfoItemView_titleTextAppearance))
            }
        }
    }

    fun setTitle(title: String?) {
        binding.typographyTextView.text = title
    }

    @SuppressLint("SetTextI18n", "CustomViewStyleable")
    fun setTextAppearance(@StyleRes textAppearance: Int) {
        TextViewCompat.setTextAppearance(binding.typographyTextView, textAppearance)
        context.obtainStyledAttributes(textAppearance, R.styleable.TextAppearance).use {
            val textSize = it.getDimension(R.styleable.TextAppearance_android_textSize, 0f)
            binding.textSizeTextView.text = "${textSize.toSp()} sp"
        }
    }

    private fun Float.toSp(): Int {
        return (this / context.resources.displayMetrics.scaledDensity).toInt()
    }
}