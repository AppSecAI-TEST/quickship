package dev_t.cs161.quickship;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;

public class quickShipViewGridBorder extends View {

    private Point screen = new Point();
    private Float screenWidth;
    private Float boardGridFrameStartX;
    private Float boardGridFrameStartY;
    private Float boardGridFrameEndX;
    private Float boardGridFrameEndY;
    private Paint boardGridFrameBorderPaint;
    private Float boardGridFrameBorderStrokeWidth;
    private Float boardGridFrameMargin;
    private Paint titlePaint;
    private Float mTitleHeight;
    private quickShipActivityMain mContext;
    private int mFrameColor;

    public quickShipViewGridBorder(quickShipActivityMain context, int frameColor) {
        super(context);
        mContext = context;
        mFrameColor = frameColor;
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getSize(screen);
        calculateBoardGUIPositions();
    }

    public void calculateBoardGUIPositions() {
        boardGridFrameBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        boardGridFrameBorderPaint.setStyle(Paint.Style.STROKE);
        int dpSize =  2;
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics() ;
        boardGridFrameBorderStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dm);
        boardGridFrameBorderPaint.setStrokeWidth(boardGridFrameBorderStrokeWidth);
        boardGridFrameBorderPaint.setColor(mFrameColor);


        titlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        titlePaint.setColor(Color.BLACK);
        titlePaint.setTextSize(16 * getResources().getDisplayMetrics().density);

        screenWidth = (float) screen.x;

        boardGridFrameMargin = (screenWidth - (screenWidth * (float) 0.9)) / 2;

        mTitleHeight = titlePaint.getTextSize();

        boardGridFrameStartX = boardGridFrameMargin;
        boardGridFrameStartY = boardGridFrameMargin + mTitleHeight;
        boardGridFrameEndX = boardGridFrameMargin + (screenWidth * (float) 0.9);
        boardGridFrameEndY = boardGridFrameMargin + (screenWidth * (float) 0.9);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(boardGridFrameStartX, boardGridFrameStartY, boardGridFrameEndX, boardGridFrameEndY, boardGridFrameBorderPaint);
    }

}