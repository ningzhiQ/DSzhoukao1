package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.w.dszhoukao1.R;


public class MyView extends View {
    public boolean Inited = false;//初始化
    private Bitmap bitmap;
    private Canvas canvas;
    private Path path;
    private float mx;
    private float my;
    private float TOUCH_TOLERANCE;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //初始化刮刮卡
    public void initka(){
        //新建画笔
        Paint paint = new Paint();
        //透明度
        paint.setAlpha(250);
        paint.setDither(true);
        //画笔类型
        paint.setStyle(Paint.Style.STROKE);
        //画笔接洽点类型
        paint.setStrokeJoin(Paint.Join.ROUND);
        //画笔笔刷类型
        paint.setStrokeCap(Paint.Cap.ROUND);
        //宽度
        paint.setStrokeWidth(200);
        path = new Path();
        //创建一个空的画布
        bitmap = Bitmap.createBitmap(getLayoutParams().width, getLayoutParams().height,Bitmap.Config.ARGB_8888);
        //通过bitmap生成一个画布
        canvas = new Canvas(bitmap);
        Paint paint1 = new Paint();
        paint1.setTextSize(50);
        paint1.setColor(Color.BLUE);
        //背景颜色
        canvas.drawColor(Color.RED);
        Bitmap resource = BitmapFactory.decodeResource(getResources(), R.mipmap.w);
        canvas.drawBitmap(resource, 0 , 1 , paint1);

        //设置外层的文字。
        canvas.drawText("刮一下！！", getLayoutParams().width / 4, getLayoutParams().height / 2 + 15, paint1);
        Inited = true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!Inited) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(),event.getY());
                mx = event.getX();
                my = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = Math.abs(event.getX() - mx);
                float y = Math.abs(event.getY() - my);
                if (x >= TOUCH_TOLERANCE || y >= TOUCH_TOLERANCE) {
                   path.quadTo(mx,my,(event.getX()+mx)/2,(event.getY()+my)/2);
                    mx=event.getX();
                    my=event.getY();
                    invalidate();
                }
                break;
        }
        return true;
    }
}
