package com.datong.dict.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;

/* loaded from: classes.dex */
public class AnimatorUtil {
    public static final String ALPHA = "Alpha";
    public static final String ROTATION = "Rotation";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String TRANSLATION_X = "TranslationX";
    public static final String TRANSLATION_Y = "TranslationY";

    /* loaded from: classes.dex */
    public static abstract class Callback implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
        }
    }

    public static void setScaleXYObjectAnimator(Object target, long duration, Callback callBack, float... values) {
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator ofFloat = ObjectAnimator.ofFloat(target, "scaleX", values);
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(target, "scaleY", values);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.setDuration(duration);
        animatorSet.start();
        if (callBack != null) {
            animatorSet.addListener(callBack);
        }
    }

    public static void setObjectAnimator(Object target, String propertyName, long duration, Callback callback, float... values) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(target, propertyName, values);
        ofFloat.setDuration(duration);
        ofFloat.start();
        if (callback != null) {
            ofFloat.addListener(callback);
        }
    }

    public static void setColorAnimator(ValueAnimator.AnimatorUpdateListener listener, long duration, int fromColor, int toColor) {
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(fromColor), Integer.valueOf(toColor));
        ofObject.setDuration(duration);
        ofObject.start();
        if (listener != null) {
            ofObject.addUpdateListener(listener);
        }
    }
}
