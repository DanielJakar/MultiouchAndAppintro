package danandroid.course.multitouchandappintro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

/**
 * Display multiple finger pointers onthe view as circles
 */

public class MultiTouchView extends View {
    //Constructors:
    public MultiTouchView(Context context) {
        this(context, null);
    }
    public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MultiTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;
    private int pointerCircleSize = 60;

    private int[] colors = {
            Color.BLACK, Color.BLUE,
            Color.GREEN, Color.MAGENTA,
            Color.RED, Color.YELLOW,
            Color.CYAN, Color.GRAY,
            Color.DKGRAY, Color.LTGRAY};

    private HashMap<Integer, PointF> mActivePointers;

    //A funnel point for all the constructors:
    private void init() {
        mActivePointers = new HashMap<>(); // TODO: Better performance...
        //InteliJ suggets using a sparseArray instead.
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //x,y & masked action
        int actionMasked = e.getActionMasked();

        //get the pointer index:
        int pointerIndex = e.getActionIndex();

        // get the pointer id:
        int pointerId = e.getPointerId(pointerIndex);


        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                //Construct a point for the current pointerIndex:
                PointF pointF = new PointF(e.getX(pointerIndex), e.getY(pointerIndex));

                //save the pointer to the map of active pointers:
                mActivePointers.put(pointerId, pointF);
                break;

            case MotionEvent.ACTION_MOVE:
                //fori loop over the fingers.
                int size = e.getPointerCount();
                for (int i = 0; i < size; i++) {
                    //where is the pinky
                    //inside the loop we have pointer indices-> and we get the id

                    // get the memory address for the point:
                    PointF p = mActivePointers.get(e.getPointerId(i));
                    //update the current move position for the finger:
                    //p.set(e.getX(i), e.getY(i));
                    p.x = e.getX(i);
                    p.y = e.getY(i);

                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mActivePointers.remove(pointerId);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw all the pointers:
        for (Integer pointerID : mActivePointers.keySet()) {
            mPaint.setColor(colors[pointerID %colors.length]);
            PointF pointF = mActivePointers.get(pointerID);
            canvas.drawCircle(pointF.x, pointF.y, pointerCircleSize, mPaint);
        }
    }
}
