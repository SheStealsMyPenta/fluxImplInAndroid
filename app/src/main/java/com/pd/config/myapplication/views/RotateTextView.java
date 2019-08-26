package com.pd.config.myapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RotateTextView extends View {
    private Paint paint;
    private float viewLeft; //距离左边的坐标
    private float viewTop; //距离上边的坐标
    private int heightOfTheScreen;
    private int widthOfTheScreen;
    private float marginTop;
    private float orignalY;
    private float startPointX;
    private float startPointY;
    private float orignalX;
    private float marginRight;
    private float marginLeft;

    public RotateTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPainter();
        viewSet();
    }

    public RotateTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPainter();
        viewSet();
    }

    public RotateTextView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        initPainter();
        viewSet();
    }

    public RotateTextView(Context context) {
        super(context);
        initPainter();
        viewSet();
    }
    private void viewSet() {

        viewLeft = getLeft();
        viewTop = getTop();
        orignalY = marginTop;

        marginRight = (widthOfTheScreen / 20.0f) * 1.3f;
        marginLeft = (widthOfTheScreen / 20.0f) * 1.3f;
        marginTop = (heightOfTheScreen / 25.0f) * 4.5f;
        startPointX = widthOfTheScreen/2;
        startPointY = heightOfTheScreen/2;
        orignalX = marginLeft;
    }
    private void initPainter() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0.5f);
        paint.setAntiAlias(true);
        paint.setTextSize(16);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        heightOfTheScreen = heightSize;
        widthOfTheScreen = widthSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.rotate(90);
        canvas.drawText("响应(dB)",startPointX,startPointY,paint);

    }
}
