package com.kkobook.level

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {

    private val greenPaint: Paint = Paint()
    private val yellowPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()
    private val paintThick: Paint = Paint()

    private var width: Float = 0f
    private var height: Float = 0f
    private var cX: Float = 0f
    private var cY: Float = 0f
    private var horizontalX: Float = 0f
    private var horizontalY: Float = 0f
    private var verticalX: Float = 0f
    private var verticalY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        greenPaint.color = Color.GREEN
        yellowPaint.color = Color.YELLOW
        blackPaint.style = Paint.Style.STROKE
        blackPaint.strokeWidth = 3F
        paintThick.color = resources.getColor(R.color.borders)
        paintThick.style = Paint.Style.STROKE
        paintThick.strokeWidth = 10F
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        width = w * 1f
        height = h * 1f
        cX = w * 4f / 7f
        cY = h * 4f / 7f
        horizontalX = width / 2
        horizontalY = 150f
        verticalX = 150f
        verticalY = height / 2 + 100f
    }

    override fun onDraw(canvas: Canvas?) {
        // 원과 막대
        canvas?.drawRect(100f, verticalY - 350f, 200f, verticalY + 350f, paintThick)
        canvas?.drawRect(100f, verticalY - 350f, 200f, verticalY + 350f, yellowPaint)

        canvas?.drawRect(horizontalX - 350f, 100f, horizontalX + 350f, 200f, paintThick)
        canvas?.drawRect(horizontalX - 350f, 100f, horizontalX + 350f, 200f, yellowPaint)

        canvas?.drawCircle(cX, cY, 350f, paintThick)
        canvas?.drawCircle(cX, cY, 350f, yellowPaint)

        // 녹색 원들
        canvas?.drawCircle(cX - xCoord, cY + yCoord, 50f, greenPaint)
        canvas?.drawCircle(horizontalX - xCoord, horizontalY, 50f, greenPaint)
        canvas?.drawCircle(verticalX, verticalY + yCoord, 50f, greenPaint)

        // 가운데 십자가
        canvas?.drawLine(cX - 20, cY, cX + 20, cY, blackPaint)
        canvas?.drawLine( cX, cY - 20 , cX, cY + 20, blackPaint)

        // 막대 십자가
        canvas?.drawLine(150f - 20, verticalY, 150f + 20, verticalY, blackPaint)
        canvas?.drawLine( 150f, verticalY - 20 , 150f, verticalY + 20, blackPaint)

        canvas?.drawLine(horizontalX - 20, 150f, horizontalX + 20, 150f, blackPaint)
        canvas?.drawLine(horizontalX, 150f - 20, horizontalX, 150f + 20, blackPaint)
    }

    // MainActivity 에서 호출하여 View에 반영되도록 함수 작성
    // onSensorChanged 함수에서 호출됨
    fun onSensorEvent(event: SensorEvent) {
        xCoord = event.values[0] * 30
        yCoord = event.values[1] * 30
        invalidate()
    }

}