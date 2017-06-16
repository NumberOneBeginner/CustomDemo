package com.mmednet.www.customdemos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.mmednet.www.customdemos.R;

/**
 * Created by Administrator on 2017/5/15.
 * 波纹图
 */
public class BoWenView extends View{
    private int mWidth,mHeight;
    private ClassPaint Paints;
    private int circleR=80;
    private int b;
    private int nervrR=0;
    private int nervrR1=0;
    private int nervrR2=0;
    private int vv=100;
    private int tip;
    private int alphas;
    private int alphas1;
    private int alphas2;
    public BoWenView(Context context) {
        super(context);
        Paints=new ClassPaint();
    }

    public BoWenView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoWenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w-getPaddingLeft()-getPaddingRight();
        mHeight=h-getPaddingTop()-getPaddingBottom();
        setBackgroundResource(R.color.blank);
        vv=mWidth/3;
        tip=vv/3;
        alphas=alphas1=alphas2=tip;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mWidth / 2, mHeight / 2, circleR, Paints.paintCircle);

            RectF rects = new RectF(mWidth / 2 - circleR, mHeight / 2 - circleR,
                    mWidth / 2 + circleR, mHeight / 2 + circleR);
            canvas.drawArc(rects, //弧线所使用的矩形区域大小
                    270,  //开始角度
                    b, //扫过的角度
                    false, //是否使用中心
                    Paints.paintQ);
        Paints.paintCircleB.setAlpha(alphas);
        Paints.paintCircleC.setAlpha(alphas1);
        Paints.paintCircleD.setAlpha(alphas2);
        canvas.drawCircle(mWidth / 2, mHeight / 2, circleR+nervrR, Paints.paintCircleB);
        canvas.drawCircle(mWidth / 2, mHeight / 2, circleR+nervrR1, Paints.paintCircleC);
        canvas.drawCircle(mWidth / 2, mHeight / 2, circleR+nervrR2, Paints.paintCircleD);
        String str="SPEEK";
        Rect rect=new Rect();
        Paints.paintText.getTextBounds(str, 0, str.length(), rect);

        float mW= rect.width();
        float mH= rect.height();


        canvas.drawText(str, mWidth/2 - mW/2 , mHeight/2 +mH/2 ,Paints.paintText);

    }
    public void moveView(){
        new Thread(moveThread).start();
    }

    private boolean is1,is2;

    private final Runnable moveThread = new Runnable() {

        @Override
        public void run() {
            while (true) {

                if(b==360){
                    b=0;
                }
                if(nervrR==vv){
                    nervrR=0;
                    alphas=tip;
                }
                if(nervrR1==vv){
                    nervrR1=0;
                    alphas1=tip;
                    is1=false;
                }
                if(nervrR2==vv){
                    nervrR2=0;
                    alphas2=tip;
                    is2=false;
                }
                if(nervrR==tip){
                    is1=true;
                }else if(nervrR1==tip){
                    is2=true;
                }

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postInvalidate();
                nervrR++;
                if(nervrR%3==0){
                    alphas--;
                }
                if(is1){
                    nervrR1++;
                    if(nervrR1%3==0){
                        alphas1--;
                    }
                }
                if(is2){
                    nervrR2++;
                    if(nervrR2%3==0) {
                        alphas2--;
                    }
                }
                b++;
            }
        }};

    /**
     * 各种画笔的初始化
     */
    public class ClassPaint{
        /**
         * //XY轴画笔
         */
        private Paint paintCircle;
        private Paint paintCircleB;
        private Paint paintCircleC;
        private Paint paintCircleD;
        private Paint paintXYLine;
        private Paint paintText;
        private Paint paintQ;
        public ClassPaint(){
            init();
        }

        public void init(){
            paintCircle=new Paint();
//            paintCircle.setColor(Color.parseColor("#1A0B42"));
            paintCircle.setColor(Color.parseColor("#FF7600"));
            paintCircle.setStrokeWidth(2);//画笔画出线的宽度
            paintCircle.setAntiAlias(true);//抗锯齿 表框的模糊去掉

            paintCircleB=new Paint();
            paintCircleB.setColor(Color.parseColor("#FF7600"));
            paintCircleB.setStrokeWidth(2);//画笔画出线的宽度
            paintCircleB.setAntiAlias(true);//抗锯齿 表框的模糊去掉


            paintCircleC=new Paint();
            paintCircleC.setColor(Color.parseColor("#FF7600"));
            paintCircleC.setStrokeWidth(2);//画笔画出线的宽度
            paintCircleC.setAntiAlias(true);//抗锯齿 表框的模糊去掉

            paintCircleD=new Paint();
            paintCircleD.setColor(Color.parseColor("#FF7600"));
            paintCircleD.setStrokeWidth(2);//画笔画出线的宽度
            paintCircleD.setAntiAlias(true);//抗锯齿 表框的模糊去掉


            paintXYLine=new Paint();
            paintXYLine.setColor(Color.parseColor("#000000"));
            paintXYLine.setStrokeWidth(1);//画笔画出线的宽度
            paintXYLine.setAntiAlias(true);//抗锯齿 表框的模糊去掉


            paintQ=new Paint();
            paintQ.setColor(Color.parseColor("#000000"));
            paintQ.setStrokeWidth(4);//画笔画出线的宽度
            paintQ.setStyle(Paint.Style.STROKE);
            paintQ.setAntiAlias(true);//抗锯齿 表框的模糊去掉


            paintText=new Paint();
            paintText.setColor(Color.parseColor("#FFFFFF"));
            paintText.setStrokeWidth(4);//画笔画出线的宽度
            paintText.setTextSize(30);
        }

    }

}
