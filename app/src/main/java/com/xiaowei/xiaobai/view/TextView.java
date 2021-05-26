package com.xiaowei.xiaobai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.xiaowei.xiaobai.R;

import java.lang.reflect.Type;

public class TextView extends View {
    private String mText;
    private int mTextColor = Color.BLACK;
    private int mTextSize = 15;
    private int mPaddingTopSize = 0;
    private int mPaddingBottomSize = 0;
    private Paint mPaint;

    //new出来的
    public TextView(Context context) {
        this(context,null);
    }

    // xml
    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    // style
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable._TextView);
        mText = typedArray.getString(R.styleable._TextView__text);
        mTextColor = typedArray.getColor(R.styleable._TextView__textColor,mTextColor);

        // 15 px sp?
        mTextSize = typedArray.getDimensionPixelSize(R.styleable._TextView__textSize, sp2Px(mTextSize));
        mPaddingTopSize = (int) typedArray.getDimension(R.styleable._TextView__textPaddingTopSize, dp2Px(mPaddingTopSize));
        mPaddingBottomSize = (int) typedArray.getDimension(R.styleable._TextView__textPaddingBottomSize, dp2Px(mPaddingBottomSize));

        // 回收
        typedArray.recycle();

        initPaint();
    }

    private int dp2Px(int paddingTopSizeDp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,paddingTopSizeDp,getResources().getDisplayMetrics());
    }

    /**
     * sp转换成px
     *
     * @param textSizeSp
     * @return
     */
    private int sp2Px(int textSizeSp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,textSizeSp,getResources().getDisplayMetrics());
    }

    private void initPaint() {
        mPaint = new Paint();
        // 抗锯齿,不模糊，比较清晰些，圆滑一些
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int cusHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽高的模式,有wrap_content,match_parent,固定值等等
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // MeasureSpec.AT_MOST:在布局中指定了有wrap_content
        // MeasureSpec.EXACTLY:在布局中指定了确切的值或者match_parent或者fill_parent
        // MeasureSpec.UNSPECIFIED:尽可能的大,很少能用到,ListView ScollView,在测量子布局的时候会使用UNSPECIFIED
        //ScollView + ListView 会显示不全问题

        // 指定宽高
        // 1、确定的值，给多少就是多少
        int width = MeasureSpec.getSize(widthMeasureSpec);

        // 2、给的wrap_content,需要计算
        if (widthMode == MeasureSpec.AT_MOST) {
            // 计算的宽度与字体的长度、大小有关,用画笔来测量
            Rect bounds = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(),bounds);
            width = bounds.width() + getPaddingLeft() + getPaddingRight();
        }

        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            // 计算的宽度与字体的长度、大小有关,用画笔来测量
            Rect bounds = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(),bounds);
            height = bounds.height() + mPaddingBottomSize + mPaddingTopSize;
        }

        // 设置控件的宽高
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // x:开始的位置点x轴坐标  y：基线baseline
        // baseline:asent(距离内容区上的间距) decent(距离内容区下的间距)  top((距离最上面的间距))  bottom(距离最下面的间距)
        // dy：高度的一半到baseline的距离
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;
        Log.e("weip","baseLine value : " + baseLine + ",mPaddingTopSize "+ mPaddingTopSize +",mPaddingBottomSize :" +mPaddingBottomSize);
        canvas.drawText(mText,getPaddingLeft(),baseLine,mPaint);
        // 如果继承viewgroup,则不会调用onDraw，可使用如下几种解决方案:
        // 最终的目的都是改变mPrivateFlag
        //1、dispatchDraw
        //2、设置透明背景，重新计算flag (构造函数)
        //3、setWillNotDraw(false)
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
