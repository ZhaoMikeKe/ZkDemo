package com.example.gesture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button mButton;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureDetector = new GestureDetector(this, new MyOnGestureListener());

        mButton = (Button) findViewById(R.id.btn_textgesture);
        mButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(getClass().getName(), "onTouch-----" + getActionName(event.getAction()));
                mGestureDetector.onTouchEvent(event);
                // 一定要返回true，不然获取不到完整的事件
                return true;
            }
        });
    }

    private String getActionName(int action) {
        String name = "";
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                name = "ACTION_DOWN";
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                name = "ACTION_MOVE";
                break;
            }
            case MotionEvent.ACTION_UP: {
                name = "ACTION_UP";
                break;
            }
            default:
                break;
        }
        return name;
    }

    class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(getClass().getName(), "onSingleTapUp-----" + getActionName(e.getAction()));
            Toast.makeText(getApplicationContext(), "onSingleTapUp-----" + getActionName(e.getAction()), Toast.LENGTH_LONG).show();
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(getClass().getName(), "onLongPress-----" + getActionName(e.getAction()));
            Toast.makeText(getApplicationContext(), "onLongPress-----" + getActionName(e.getAction()), Toast.LENGTH_LONG).show();

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(getClass().getName(),
                    "onScroll-----" + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                            + e2.getX() + "," + e2.getY() + ")");
            Toast.makeText(getApplicationContext(), "onScroll-----" + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                    + e2.getX() + "," + e2.getY() + ")", Toast.LENGTH_LONG).show();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mButton.getLayoutParams();
            layoutParams.leftMargin = (int) (mButton.getLeft() + (e2.getX() - e1.getX()));
            layoutParams.topMargin = (int) (mButton.getTop() + (e2.getY() - e1.getY()));

            mButton.setLayoutParams(layoutParams);
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(getClass().getName(),
                    "onFling-----" + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                            + e2.getX() + "," + e2.getY() + ")");
            Toast.makeText(getApplicationContext(), "onFling-----" + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                    + e2.getX() + "," + e2.getY() + ")", Toast.LENGTH_LONG).show();

            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(getClass().getName(), "onShowPress-----" + getActionName(e.getAction()));
            Toast.makeText(getApplicationContext(), "onShowPress-----" + getActionName(e.getAction()), Toast.LENGTH_LONG).show();

        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(getClass().getName(), "onDown-----" + getActionName(e.getAction()));
            Toast.makeText(getApplicationContext(), "onDown-----" + getActionName(e.getAction()), Toast.LENGTH_LONG).show();

            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(getClass().getName(), "onDoubleTap-----" + getActionName(e.getAction()));
            Toast.makeText(getApplicationContext(), "onDoubleTap-----" + getActionName(e.getAction()), Toast.LENGTH_LONG).show();

            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i(getClass().getName(), "onDoubleTapEvent-----" + getActionName(e.getAction()));
            Toast.makeText(getApplicationContext(), "onDoubleTapEvent-----" + getActionName(e.getAction()), Toast.LENGTH_LONG).show();

            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i(getClass().getName(), "onSingleTapConfirmed-----" + getActionName(e.getAction()));
            Toast.makeText(getApplicationContext(), "onSingleTapConfirmed-----" + getActionName(e.getAction()), Toast.LENGTH_LONG).show();

            return false;
        }
    }
}
