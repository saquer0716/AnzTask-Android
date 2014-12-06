/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 *
 * This flip button flips horizontally when being clicked and button text changed, too.
 * We could just flip 180 degree in one animation but the text is flipped as well which
 * is not what we want.
 *
 * The secret is flip from 0 to 90 degree first then flip from -90 to 0 and the button text can
 * ben changed at the end of first flip animation.
 */

package com.anz.mobile.task.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.anz.mobile.task.R;
import com.anz.mobile.task.util.AnzLog;

public class CircleFlipButton extends ImageView {

	private static final int TEXT_HEIGHT = 48;
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_FLIP_COLOR = Color.GRAY;

	private int mCenterY;
	private int mCenterX;
	private int mRadius;

    private int mTextWidth;
    private int mTextHeight;

	private Paint mCirclePaint;
    private Paint mTextPaint;

    private int mDispayCircleColor;

    private String mDisplayText;
    private String mFrontText;
    private String mBackText;

	private float animationProgress;
    private boolean isShowingFront = true;

	private ObjectAnimator mFlipAnimatorFirstHalf;
	private ObjectAnimator mFlipAnimatorSecondHalf;

    /**
     * Constructor for CircleFlipButton
     *
     * @param  context The Context the view is running in,
     *                   through which it can access the current theme, resources, etc.
     * @return      the CircleFlipButton instance
     */
	public CircleFlipButton(Context context) {
		super(context);
		init(context, null);
	}

    /**
     * Constructor for CircleFlipButton
     *
     * @param  context The Context the view is running in,
     *                   through which it can access the current theme, resources, etc.
     * @param  attrs The attributes comes from layout file
     * @return      the CircleFlipButton instance
     */
	public CircleFlipButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

    /**
     * Constructor for CircleFlipButton
     *
     * @param  context The Context the view is running in,
     *                   through which it can access the current theme, resources, etc.
     * @param  attrs The attributes comes from layout file
     * @param  defStyle The default style for the view
     * @return      the CircleFlipButton instance
     */
	public CircleFlipButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	@Override
	public void setPressed(boolean pressed) {
		super.setPressed(pressed);

		if (pressed) {
            mFlipAnimatorFirstHalf.start();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(mCenterX, mCenterY, mRadius, mCirclePaint);
        //text is drawn at the
        canvas.drawText(mDisplayText, this.getWidth() / 2  - mTextWidth / 2, this.getHeight() / 2  + mTextHeight / 2, mTextPaint);
		super.onDraw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
        mRadius = Math.min(w, h) / 2;
	}

    /**
     * Set the color of the flip button
     *
     * @param  color The flip button's color
     */
	private void setColor(int color) {
        mCirclePaint.setColor(color);
        mTextPaint.setColor(DEFAULT_TEXT_COLOR);
	}

    /**
     * Set the front text and back text
     *
     * @param  showingFront what side of button is shown
     * @param  front front side text
     * @param  back back side text
     */
    public void setDisplayText(boolean showingFront, String front, String back) {
        mFrontText = front;
        mBackText = back;
        isShowingFront = showingFront;
        mDisplayText = showingFront ? mFrontText : mBackText;

        setColor(isShowingFront ? mDispayCircleColor : DEFAULT_FLIP_COLOR);
        updateTextSize();
    }

    /**
     * Need to update text size so the text can be centered in the circle
     */
    private void updateTextSize() {
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(mDisplayText, 0, mDisplayText.length(), textBounds);
        mTextWidth = textBounds.width();
        mTextHeight = textBounds.height();
        this.invalidate();
    }

    /**
     * Initialise member variables, most important thing is the Object Animator.
     */
	private void init(Context context, AttributeSet attrs) {
		this.setFocusable(true);
		this.setScaleType(ScaleType.CENTER_INSIDE);
		setClickable(true);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);

		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(TEXT_HEIGHT);

        mDispayCircleColor = getResources().getColor(R.color.magnitude_color);
        setColor(mDispayCircleColor);

		final int pressedAnimationTime = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        mFlipAnimatorFirstHalf = ObjectAnimator.ofFloat(this, "rotationY", 0f, 90f);
        mFlipAnimatorFirstHalf.setDuration(pressedAnimationTime);
        mFlipAnimatorFirstHalf.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mFlipAnimatorSecondHalf.start();

                if (isShowingFront) {
                    mDisplayText = mBackText;
                    setColor(DEFAULT_FLIP_COLOR);
                    AnzLog.d("flipped to back side");
                }else {
                    mDisplayText = mFrontText;
                    setColor(mDispayCircleColor);
                    AnzLog.d("flipped to front side");
                }
                updateTextSize();
                isShowingFront = !isShowingFront;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mFlipAnimatorSecondHalf = ObjectAnimator.ofFloat(this, "rotationY", -90f, 0f);
        mFlipAnimatorSecondHalf.setDuration(pressedAnimationTime);
	}
}
