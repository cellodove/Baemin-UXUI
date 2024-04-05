package com.cellodove.baemin_ux_ui.ui.home.swipelayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.swiperefreshlayout.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Private class created to work around issues with AnimationListeners being
 * called before the animation is actually complete and support shadows on older
 * platforms.
 */
public class CustomCircleImageView extends androidx.appcompat.widget.AppCompatTextView {

    private static final int DEFAULT_BACKGROUND_COLOR = 0xFFFAFAFA;
    private static final int FILL_SHADOW_COLOR = 0x3D000000;
    private static final int KEY_SHADOW_COLOR = 0x1E000000;

    // PX
    private static final float X_OFFSET = 0f;
    private static final float Y_OFFSET = 1.75f;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final int SHADOW_ELEVATION = 4;

    private Animation.AnimationListener mListener;
    private int mShadowRadius;
    private int mBackgroundColor;

    CustomCircleImageView(Context context) {
        super(context);

        final float density = getContext().getResources().getDisplayMetrics().density;
        final int shadowYOffset = (int) (density * Y_OFFSET);
        final int shadowXOffset = (int) (density * X_OFFSET);

        mShadowRadius = (int) (density * SHADOW_RADIUS);

        // The style attribute is named SwipeRefreshLayout instead of CircleImageView because
        // CircleImageView is not part of the public api.
        @SuppressLint("CustomViewStyleable")
        TypedArray colorArray = getContext().obtainStyledAttributes(R.styleable.SwipeRefreshLayout);
        mBackgroundColor = colorArray.getColor(
            R.styleable.SwipeRefreshLayout_swipeRefreshLayoutProgressSpinnerBackgroundColor,
            DEFAULT_BACKGROUND_COLOR);
        colorArray.recycle();



        ShapeDrawable circle;

        float[] roundCorners = new float[]{100, 100, 100, 100, 100, 100, 100, 100};

        if (elevationSupported()) {
            circle = new ShapeDrawable(new RoundRectShape(roundCorners,null,null));
            ViewCompat.setElevation(this, SHADOW_ELEVATION * density);
        } else {
            circle = new ShapeDrawable(new OvalShadow(this, mShadowRadius));
            setLayerType(View.LAYER_TYPE_SOFTWARE, circle.getPaint());
            circle.getPaint().setShadowLayer(mShadowRadius, shadowXOffset, shadowYOffset,
                KEY_SHADOW_COLOR);
            final int padding = mShadowRadius;
            setPadding(padding, padding, padding, padding);
        }

        setPadding(50, 50, 50, 50);
        circle.getPaint().setColor(ContextCompat.getColor(getContext(), com.cellodove.baemin_ux_ui.R.color.white));
        ViewCompat.setBackground(this, circle);


    }

    private boolean elevationSupported() {
        return android.os.Build.VERSION.SDK_INT >= 21;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!elevationSupported()) {
            setMeasuredDimension(getMeasuredWidth() + mShadowRadius * 2, getMeasuredHeight()
                    + mShadowRadius * 2);
        }
    }

    public void setAnimationListener(Animation.AnimationListener listener) {
        mListener = listener;
    }

    @Override
    public void onAnimationStart() {
        super.onAnimationStart();
        if (mListener != null) {
            mListener.onAnimationStart(getAnimation());
        }
    }

    @Override
    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (mListener != null) {
            mListener.onAnimationEnd(getAnimation());
        }
    }

    @Override
    public void setBackgroundColor(int color) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(color);
            mBackgroundColor = color;
        }
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    private static class OvalShadow extends OvalShape {
        private Paint mShadowPaint;
        private int mShadowRadius;
        private CustomCircleImageView mCircleImageView;

        OvalShadow(CustomCircleImageView circleImageView, int shadowRadius) {
            super();
            mCircleImageView = circleImageView;
            mShadowPaint = new Paint();
            mShadowRadius = shadowRadius;
            updateRadialGradient((int) rect().width());
        }

        @Override
        protected void onResize(float width, float height) {
            super.onResize(width, height);
            updateRadialGradient((int) width);
        }

        @Override
        public void draw(Canvas canvas, Paint paint) {
            final int x = mCircleImageView.getWidth();
            final int y = mCircleImageView.getHeight();
            canvas.drawCircle(x, y, x, mShadowPaint);
            canvas.drawCircle(x, y, x - mShadowRadius, paint);
        }

        private void updateRadialGradient(int diameter) {
            mShadowPaint.setShader(new RadialGradient(
                    diameter,
                diameter,
                mShadowRadius,
                new int[]{FILL_SHADOW_COLOR, Color.TRANSPARENT},
                null,
                Shader.TileMode.CLAMP));
        }
    }
}
