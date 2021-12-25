package com.xiaowei.xiaobai.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import kotlinx.coroutines.*

class TouchCircleSurfaceView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {
    private var mCenterX = 0f
    private var mCenterY = 0f
    private val colors = arrayOf(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE,
            Color.MAGENTA, Color.GREEN)
    private val mPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        pathEffect = ComposePathEffect(CornerPathEffect(30f), DiscretePathEffect(30f, 20f))

        // 不规则的剧烈程度，使用到离散数学算法
//        pathEffect = DiscretePathEffect(30f, 20f)
    }

    // 气泡数据结构
    private data class Bubble(val x: Float, val y: Float, val color: Int, var radius: Float)

    private val bubblesList = mutableListOf<Bubble>()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val centerX = event?.x?:0f
        val centerY = event?.y?:0f
        val color = colors.random()
        val bubble = Bubble(centerX, centerY, color, 1f)
        bubblesList.add(bubble)
        if (bubblesList.size > 30) bubblesList.removeAt(0)
        return super.onTouchEvent(event)
    }

    /**
     * 绘图
     */
    init {
        /**
         * 无线循环
         */
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                if (holder.surface.isValid) {
                    val canvas = holder.lockCanvas()
                    canvas.drawColor(Color.BLACK)
                    // 绘图
                    bubblesList.toList().filter { it.radius < 3000 }.forEach {
                        mPaint.color = it.color
                        canvas.drawCircle(it.x, it.y, it.radius, mPaint)
                        it.radius += 10f
                    }

                    holder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

 /*   override fun onTouchEvent(event: MotionEvent?): Boolean {
        mCenterX = event?.x?:0f
        mCenterY = event?.y?:0f
//        invalidate()
        val canvas = holder.lockCanvas()
        // 清空上一次绘制的
        canvas.drawColor(Color.BLACK)
        repeat(3000) {
            // 圆心不断地增加
            canvas?.drawCircle(mCenterX, mCenterY, it.toFloat() / 5, mPaint)
        }

        // 绘画结束后释放
        holder.unlockCanvasAndPost(canvas)
        return super.onTouchEvent(event)
    }*/
}