package com.xiaowei.xiaobai.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchCircleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mCenterX = 0f
    private var mCenterY = 0f
    private val colors = arrayOf(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE,
        Color.MAGENTA, Color.GREEN)
    private val mPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = colors.random()
        repeat(3000) {
            // 圆心不断地增加
            canvas?.drawCircle(mCenterX, mCenterY, it.toFloat() / 5, mPaint)
        }
        // OpenGlRenderer:Davey! duration= 700ms  卡顿，ProgressBar是不转动的。
        // 这种场景是不能使用普通的View中去。
        // 此时SurfaceView就应运而生。
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mCenterX = event?.x?:0f
        mCenterY = event?.y?:0f
        invalidate()
        return super.onTouchEvent(event)
    }
}