package ru.otus.basicarchitecture.personal

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.ReplacementSpan


class DateFormatterTextWatcher: TextWatcher {
    private val maxLength = 6
    private var internalStopFormatFlag = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {
        if (internalStopFormatFlag) {
            return
        }
        internalStopFormatFlag = true
        formatExpiryDate(s, maxLength)
        internalStopFormatFlag = false
    }

    fun formatExpiryDate(date: Editable, maxLength: Int) {
        var textLength = date.length
        val spans = date.getSpans(0, date.length, DotSpan::class.java)
        for (i in spans.indices) {
            date.removeSpan(spans[i])
        }
        if (maxLength > 0 && textLength > maxLength - 1) {
            date.replace(maxLength, textLength, "")
            --textLength
        }
        for (i in 1..(textLength - 1) / 2) {
            val end = i * 2 + 1
            val start = end - 1
            val marginSPan = DotSpan()
            date.setSpan(marginSPan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    class DotSpan : ReplacementSpan() {
        override fun getSize(
            paint: Paint,
            text: CharSequence,
            start: Int,
            end: Int,
            fm: FontMetricsInt?
        ): Int {
            val widths = FloatArray(end - start)
            val slashWidth = FloatArray(1)
            paint.getTextWidths(text, start, end, widths)
            paint.getTextWidths(".", slashWidth)
            var sum = slashWidth[0].toInt()
            for (i in widths.indices) {
                sum += widths[i].toInt()
            }
            return sum
        }

        override fun draw(
            canvas: Canvas,
            text: CharSequence,
            start: Int,
            end: Int,
            x: Float,
            top: Int,
            y: Int,
            bottom: Int,
            paint: Paint
        ) {
            val xtext = "." + text.subSequence(start, end)
            canvas.drawText(xtext, 0, xtext.length, x, y.toFloat(), paint)
        }
    }
}