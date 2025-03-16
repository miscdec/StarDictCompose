package com.datong.dict.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;

/* loaded from: classes.dex */
public class AnimatorHelper {
    public static final String ALPHA = "Alpha";
    private static AnimatorHelper INSTANCE = null;
    public static final String ROTATION = "Rotation";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String TRANSLATION_X = "TranslationX";
    public static final String TRANSLATION_Y = "TranslationY";
    public static final int TYPE_COLOR = 3;
    public static final int TYPE_OBJ = 1;
    public static final int TYPE_SCALE = 2;
    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() { // from class: com.datong.dict.utils.AnimatorHelper.1
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
            if (AnimatorHelper.this.startCallback != null) {
                AnimatorHelper.this.startCallback.onStart(animation);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            if (AnimatorHelper.this.endCallback != null) {
                AnimatorHelper.this.endCallback.onEnd(animation);
            }
        }
    };
    private long duration;
    private OnEndCallback endCallback;
    private TimeInterpolator interpolator;
    private ObjectAnimator objAnimator;
    private String propertyName;
    private OnStartCallback startCallback;
    private Object target;
    private int type;

    /* loaded from: classes.dex */
    public interface OnEndCallback {
        void onEnd(Animator animation);
    }

    /* loaded from: classes.dex */
    public interface OnStartCallback {
        void onStart(Animator animation);
    }

    public static AnimatorHelper target(Object target) {
        AnimatorHelper animatorHelper = new AnimatorHelper(target);
        INSTANCE = animatorHelper;
        return animatorHelper;
    }

    public AnimatorHelper(Object target) {
        this.target = target;
    }

    public AnimatorHelper type(int type) {
        this.type = type;
        return INSTANCE;
    }

    public AnimatorHelper propertyName(String propertyName) {
        this.propertyName = propertyName;
        return INSTANCE;
    }

    public AnimatorHelper duration(long duration) {
        this.duration = duration;
        return INSTANCE;
    }

    public AnimatorHelper interpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
        return INSTANCE;
    }

    public AnimatorHelper apply(float... values) {
        int i = this.type;
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return INSTANCE;
                }
                return INSTANCE;
            }
            return INSTANCE;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.target, this.propertyName, values);
        this.objAnimator = ofFloat;
        TimeInterpolator timeInterpolator = this.interpolator;
        if (timeInterpolator != null) {
            ofFloat.setInterpolator(timeInterpolator);
        }
        this.objAnimator.setDuration(this.duration);
        this.objAnimator.addListener(this.animatorListener);
        this.objAnimator.start();
        return INSTANCE;
    }

    public AnimatorHelper onStart(OnStartCallback startCallback) {
        this.startCallback = startCallback;
        return INSTANCE;
    }

    public AnimatorHelper onEnd(OnEndCallback endCallback) {
        this.endCallback = endCallback;
        return INSTANCE;
    }
}
