package com.evangers.stockfield.ui.util

import android.widget.TextView
import com.evangers.stockfield.R
import java.text.NumberFormat

fun TextView.applyDifference(
    differenceNumber: Int?
) {
    if (differenceNumber == null) {
        this.setTextColor(resources.getColor(R.color.stable, null))
        this.text = "-"
    } else if (differenceNumber > 0) {
        this.setTextColor(resources.getColor(R.color.increasing, null))
        val text = resources.getString(R.string.numberWithPlus, differenceNumber.toString())
        this.text = text
    } else if (differenceNumber < 0) {
        this.setTextColor(resources.getColor(R.color.decreasing, null))
        this.text = NumberFormat.getNumberInstance().format(differenceNumber)
    }
}

fun TextView.applyDifference(
    differenceNumber: Float?
) {
    if (differenceNumber == null) {
        this.setTextColor(resources.getColor(R.color.stable, null))
        this.text = "-"
    } else if (differenceNumber > 0) {
        this.setTextColor(resources.getColor(R.color.increasing, null))
        val numberFormatted = NumberFormat.getNumberInstance().format(differenceNumber)
        val text = resources.getString(R.string.numberWithPlus, numberFormatted)
        this.text = text
    } else if (differenceNumber < 0) {
        this.setTextColor(resources.getColor(R.color.decreasing, null))
        this.text = NumberFormat.getNumberInstance().format(differenceNumber)
    }
}

fun TextView.applyDifference(
    differenceNumber: Double?
) {
    if (differenceNumber == null) {
        this.setTextColor(resources.getColor(R.color.stable, null))
        this.text = "-"
    } else if (differenceNumber > 0) {
        this.setTextColor(resources.getColor(R.color.increasing, null))
        val numberFormatted = NumberFormat.getNumberInstance().format(differenceNumber)
        val text = resources.getString(R.string.numberWithPlus, numberFormatted)
        this.text = text
    } else if (differenceNumber < 0) {
        this.setTextColor(resources.getColor(R.color.decreasing, null))
        this.text = NumberFormat.getNumberInstance().format(differenceNumber)
    }
}


fun TextView.applyDifference(
    differenceNumber: Float?,
    differencePercent: Float?
) {
    if (differenceNumber == null || differencePercent == null) {
        this.setTextColor(resources.getColor(R.color.stable, null))
        this.text = "-"
    } else if (differenceNumber > 0) {
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
    } else if (differenceNumber < 0) {
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