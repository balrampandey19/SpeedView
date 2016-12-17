package com.github.anastr.speedviewlib.components.Indicators;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.github.anastr.speedviewlib.Speedometer;

/**
 * this Library build By Anas Altair
 * see it on <a href="https://github.com/anastr/SpeedView">GitHub</a>
 */
public abstract class Indicator {

    protected Paint indicatorPaint =  new Paint(Paint.ANTI_ALIAS_FLAG);
    private float density;
    private float indicatorWidth;
    private float viewWidth;
    private float viewHeight;
    private float speedometerWidth;
    private int indicatorColor;
    private int padding;
    private boolean inEditMode;

    protected Indicator (Speedometer speedometer) {
        this.density = speedometer.getContext().getResources().getDisplayMetrics().density;
        this.indicatorWidth = speedometer.getIndicatorWidth();
        this.viewWidth = speedometer.getWidthPa();
        this.viewHeight = speedometer.getHeightPa();
        this.speedometerWidth = speedometer.getSpeedometerWidth();
        this.indicatorColor = speedometer.getIndicatorColor();
        this.padding = speedometer.getPadding();
        this.inEditMode = speedometer.isInEditMode();
        init();
    }

    private void init() {
        indicatorPaint.setColor(indicatorColor);
    }

    public abstract void draw(Canvas canvas, float degree);
    /** called when size change or color, width */
    protected abstract void updateIndicator();
    /** if indicator have effects like BlurMaskFilter */
    protected abstract void setWithEffects(boolean withEffects);

    /**
     * must call in {@code speedometer.onSizeChanged()}
     * @param w new speedometer width without padding.
     * @param h new speedometer height without padding.
     */
    public void onSizeChange(int w, int h) {
        this.viewWidth = w;
        this.viewHeight = h;
        updateIndicator();
    }

    public float dpTOpx(float dp) {
        return dp * density;
    }

    public float getIndicatorWidth() {
        return indicatorWidth;
    }

    public void setIndicatorWidth(float indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
        updateIndicator();
    }

    public float getViewWidth() {
        return viewWidth;
    }

    public float getViewHeight() {
        return viewHeight;
    }

    public int getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        updateIndicator();
    }

    public float getCenterX() {
        return viewWidth /2f;
    }

    public float getCenterY() {
        return viewHeight /2f;
    }

    public int getPadding() {
        return padding;
    }

    public float getSpeedometerWidth() {
        return speedometerWidth;
    }

    public void setSpeedometerWidth(float speedometerWidth) {
        this.speedometerWidth = speedometerWidth;
        updateIndicator();
    }

    public void noticePaddingChange(int newPadding) {
        this.padding = newPadding;
        updateIndicator();
    }

    public void withEffects(boolean withEffects) {
        setWithEffects(withEffects);
        updateIndicator();
    }

    public boolean isInEditMode() {
        return inEditMode;
    }

    public enum Indicators {
        NoIndicator, NormalIndicator, NormalSmallIndicator
        , TriangleIndicator, SpindleIndicator
    }

}
