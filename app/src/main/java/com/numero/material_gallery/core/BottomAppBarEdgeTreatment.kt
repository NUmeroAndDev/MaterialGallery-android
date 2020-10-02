package com.numero.material_gallery.core

import android.annotation.SuppressLint
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapePath

fun BottomAppBar.applyFloatingActionButtonEdgeTreatment(
        fab: FloatingActionButton
) {
    val fabShapeAppearanceModel = fab.shapeAppearanceModel
    val cutCornersFab = fabShapeAppearanceModel.bottomLeftCorner is CutCornerTreatment
            && fabShapeAppearanceModel.bottomRightCorner is CutCornerTreatment

    val topEdge = if (cutCornersFab) {
        BottomAppBarCutCornersTopEdge(
                fabCradleMargin,
                fabCradleRoundedCornerRadius,
                cradleVerticalOffset
        )
    } else {
        BottomAppBarTopEdgeTreatment(
                fabCradleMargin,
                fabCradleRoundedCornerRadius,
                cradleVerticalOffset
        )
    }

    val babBackground = background as? MaterialShapeDrawable ?: return
    babBackground.shapeAppearanceModel = babBackground.shapeAppearanceModel.toBuilder()
            .setTopEdge(topEdge)
            .build()
}

class BottomAppBarCutCornersTopEdge(
        private val fabMargin: Float,
        roundedCornerRadius: Float,
        private val cradleVerticalOffset: Float
) : BottomAppBarTopEdgeTreatment(fabMargin, roundedCornerRadius, cradleVerticalOffset) {

    @SuppressLint("RestrictedApi")
    override fun getEdgePath(length: Float, center: Float, interpolation: Float, shapePath: ShapePath) {
        val fabDiameter = fabDiameter
        if (fabDiameter == 0f) {
            shapePath.lineTo(length, 0f)
            return
        }
        val diamondSize = fabDiameter / 2f
        val middle = center + horizontalOffset
        val verticalOffsetRatio = cradleVerticalOffset / diamondSize
        if (verticalOffsetRatio >= 1.0f) {
            shapePath.lineTo(length, 0f)
            return
        }
        shapePath.lineTo(middle - (fabMargin + diamondSize - cradleVerticalOffset), 0f)
        shapePath.lineTo(middle, (diamondSize - cradleVerticalOffset + fabMargin) * interpolation)
        shapePath.lineTo(middle + (fabMargin + diamondSize - cradleVerticalOffset), 0f)
        shapePath.lineTo(length, 0f)
    }
}