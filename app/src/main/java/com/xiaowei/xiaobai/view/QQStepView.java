package com.xiaowei.xiaobai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.xiaowei.xiaobai.R;

public class QQStepView extends View {
    private int mOutterColor = Color.RED;
    private int mInnerColor = Color.BLUE;
    private int mBorderWidth = 20; // 代表20px
    private int mStepTextSize = 16;
    private int mStepTextColor = Color.BLACK;
    private Paint mOutPaint;
    private Paint mInnerPaint;
    private Paint mTextPaint;
    private int mStepMax;
    private int mCurrentStep;

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 1、确定自定义属性，编码attrs.xml
        // 2、布局中使用
        // 3、获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mOutterColor = typedArray.getColor(R.styleable.QQStepView_outterColor, mOutterColor);
        mInnerColor = typedArray.getColor(R.styleable.QQStepView_innerColor, mInnerColor);
        mBorderWidth = (int) typedArray.getDimension(R.styleable.QQStepView_borderWidth, mBorderWidth);
        mStepTextSize = typedArray.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, mStepTextSize);
        mStepTextColor = typedArray.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor);
        // 回收
        typedArray.recycle();
        initPaint();
        // 4、测量view大小
        // 5、绘制
        // 6、其它逻辑处理
    }

    private void initPaint() {
        mOutPaint = new Paint();
        mOutPaint.setAntiAlias(true);
        mOutPaint.setColor(mOutterColor);
        mOutPaint.setStrokeWidth(mBorderWidth);
        // 设置为 ROUND
        mOutPaint.setStrokeCap(Paint.Cap.ROUND);
        // 画笔空心
        mOutPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        // 设置为 ROUND
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        // 画笔空心
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 在布局文件中，可能是wrap_content 还有可能宽度和高度不一致
        // 获取模式 AS_MOST  40dp
        // 宽度 高度不一致 取最小值 确保正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画圆弧
        // 描边有宽度
        int center = getWidth() / 2;
        int radius = getWidth() / 2 - mBorderWidth / 2;
        //RectF rectF = new RectF(center - radius,center - radius, center + radius,center + radius);
        RectF rectF = new RectF(mBorderWidth / 2, mBorderWidth / 2,
            getWidth() - mBorderWidth / 2, getWidth() - mBorderWidth / 2);
        canvas.drawArc(rectF, 135, 270, false, mOutPaint);

        if (mStepMax == 0)
            return;
        // 画内圆弧，百分比
        float sweepAngle = (float)mCurrentStep/mStepMax;
        canvas.drawArc(rectF, 135, sweepAngle*270, false, mInnerPaint);

        // 画文字
        String stepText = "PM2.5:" + "\t\n" + mCurrentStep;
        // 测量文字的宽高
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);
        int dx = (getWidth()-textBounds.width())/2;

        // 获取画笔的FontMetrics
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        // 计算文字的基线
        int baseLine = (int) (getHeight() / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
        canvas.drawText(stepText,dx,baseLine,mTextPaint);
    }

    public synchronized void setStepMax(int stepMax) {
        this.mStepMax = stepMax;
    }

    public synchronized void setCurrentStep(int currentStep) {
        this.mCurrentStep = currentStep;
        // 不断绘制,不停地调用onDraw方法
        invalidate();
    }
}
