package com.evangers.stockfield.ui.util

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.evangers.stockfield.R
import java.text.NumberFormat
import kotlin.math.abs

fun ViewGroup.createView(layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)

fun ImageView.showUrl(
    url: String?,
    applyActions: RequestBuilder<Drawable>.() -> RequestBuilder<Drawable> = { this }
) {
    Glide.with(this)
        .load(url)
        .applyActions()
        .into(this)
}

fun ImageView.showDrawable(
    @DrawableRes drawableId: Int,
    applyActions: RequestBuilder<Drawable>.() -> RequestBuilder<Drawable> = { this }
) {
    Glide.with(this)
        .load(drawableId)
        .applyActions()
        .into(this)
}

fun TextView.applyDifference(
    differenceNumber: Int?
) {
    val diff = differenceNumber ?: 0
    if (diff == 0) {
        this.setTextColor(resources.getColor(R.color.stable, null))
        this.text = "-"
    } else if (diff > 0) {
        this.setTextColor(resources.getColor(R.color.increasing, null))
        val text = resources.getString(R.string.numberWithPlus, differenceNumber.toString())
        this.text = text
    } else if (diff < 0) {
        this.setTextColor(resources.getColor(R.color.decreasing, null))
        this.text = NumberFormat.getNumberInstance().format(differenceNumber)
    }
}


/**
 * rank가 + 됐다는 건, 사실상 순위가 떨어졌다는 것을 의미
 * 순위값의 양수, 음수에 따른 표현 방식을 따로 처리함
 */
fun TextView.applyRankDifference(
    differenceNumber: Int?
) {
    if(differenceNumber == null) {
        this.setTextColor(resources.getColor(R.color.brand_new, null))
        this.text = resources.getString(R.string.brand_new)
    }else {
        if (differenceNumber == 0) {
            this.setTextColor(resources.getColor(R.color.stable, null))
            this.text = "-"
        } else if (differenceNumber < 0) {
            this.setTextColor(resources.getColor(R.color.increasing, null))
            val text = abs(differenceNumber).toString()
            this.text = text
        } else if (differenceNumber > 0) {
            this.setTextColor(resources.getColor(R.color.decreasing, null))
            this.text = abs(differenceNumber).toString()
        }
    }

}

fun ImageView.applyRankDifference(
    differenceNumber: Int?
) {
    val diff = differenceNumber ?: 0

    if (diff == 0) {
        this.isVisible = false
    } else if (diff < 0) {
        this.isVisible = true
        this.setImageResource(android.R.drawable.arrow_up_float)
        this.imageTintMode = PorterDuff.Mode.SRC_ATOP
        this.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.increasing, null))
    } else if (diff > 0) {
        this.isVisible = true
        this.setImageResource(android.R.drawable.arrow_down_float)
        this.imageTintMode = PorterDuff.Mode.SRC_ATOP
        this.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.decreasing, null))
    }
}

fun TextView.applyDifference(
    differenceNumber: Float?
) {
    val diff = differenceNumber?.compareTo(0) ?: 0
    if (diff == 0) {
        this.setTextColor(resources.getColor(R.color.stable, null))
        this.text = "-"
    } else if (diff > 0) {
        this.setTextColor(resources.getColor(R.color.increasing, null))
        val numberFormatted = NumberFormat.getNumberInstance().format(differenceNumber)
        val text = resources.getString(R.string.numberWithPlus, numberFormatted)
        this.text = text
    } else if (diff < 0) {
        this.setTextColor(resources.getColor(R.color.decreasing, null))
        this.text = NumberFormat.getNumberInstance().format(differenceNumber)
    }
}

fun TextView.applyDifference(
    differenceNumber: Double?
) {
    val diff = differenceNumber?.compareTo(0) ?: 0
    if (diff == 0) {
        this.setTextColor(resources.getColor(R.color.stable, null))
        this.text = "-"
    } else if (diff > 0) {
        this.setTextColor(resources.getColor(R.color.increasing, null))
        val numberFormatted = NumberFormat.getNumberInstance().format(differenceNumber)
        val text = resources.getString(R.string.numberWithPlus, numberFormatted)
        this.text = text
    } else if (diff < 0) {
        this.setTextColor(resources.getColor(R.color.decreasing, null))
        this.text = NumberFormat.getNumberInstance().format(differenceNumber)
    }
}


fun TextView.applyDifference(
    differenceNumber: Float?,
    differencePercent: Float?
) {
    val diff: Float = differenceNumber ?: 0f
    if (diff.compareTo(0f) == 0) {
        this.setTextColor(resources.getColor(R.color.stable, null))
        this.text = "-"
    } else if (diff.compareTo(0f) > 0) {
        this.setTextColor(resources.getColor(R.color.increasing, null))
        val differenceNumberFormatted = NumberFormat.getNumberInstance().format(differenceNumber)
        val differencePercentFormatted =
            NumberFormat.getNumberInstance().format(differencePercent).toString()

        val text = resources.getString(
            R.string.closingPriceIncreaseFormat,
            differenceNumberFormatted,
            differencePercentFormatted
        )
        this.text = text
    } else if (diff.compareTo(0f) < 0) {
        this.setTextColor(resources.getColor(R.color.decreasing, null))
        val differenceNumberFormatted = NumberFormat.getNumberInstance().format(differenceNumber)
        val differencePercentFormatted =
            NumberFormat.getNumberInstance().format(differencePercent).toString()
        val text = resources.getString(
            R.string.closingPriceDecreaseFormat,
            differenceNumberFormatted,
            differencePercentFormatted
        )
        this.text = text
    }
}