package com.mmednet.www.customdemos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/5/11.
 */
public class QuanQuanView extends View{

    private int mWidth,mHeight;
    private int CircleWidth=190;
    private int circleLine=18;
    private String[] arrText={"过低","低","正常","高","过高"};
    private int b=0;
    private int textV=0;//设置过低到过高
    private String BFtext="0%";//设置百分号
    private int JGtext=270/arrText.length;
    private boolean isTrue=true;
    public QuanQuanView(Context context) {
        super(context);
    }

    public QuanQuanView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuanQuanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth=w;
        mHeight=h;


        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paintCircle=new Paint();
        paintCircle.setColor(Color.parseColor("#291D64"));
        paintCircle.setAntiAlias(true);//抗锯齿
        paintCircle.setStrokeWidth(6);
        paintCircle.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth/2,mHeight/2,CircleWidth,paintCircle);


        Paint paintText=new Paint();
        paintText.setTextSize(40);
        paintText.setColor(Color.parseColor("#07C5C0"));

        Rect rectText = new Rect();
        paintText.getTextBounds(arrText[textV], 0, arrText[textV].length(), rectText);
        float width = rectText.width();//文本的宽度
        float height= rectText.height();


        canvas.drawText(arrText[textV],mWidth/2-width/2,mHeight/2+height/2,paintText);


        Rect rectTextBF = new Rect();
        paintText.getTextBounds(BFtext, 0, BFtext.length(), rectTextBF);
        float width1 = rectTextBF.width();//文本的宽度
        float height1= rectTextBF.height();
        canvas.drawText(BFtext,mWidth/2-width1/2,mHeight/2+height1/2+60,paintText);




        Paint paintRect=new Paint();
        paintRect.setAntiAlias(true);//抗锯齿
        paintRect.setColor(Color.parseColor("#07C5C0"));
        paintRect.setStrokeWidth(6);
        paintRect.setStyle(Paint.Style.STROKE);
        //绘制弧线区域
        RectF rect = new RectF(mWidth/2-CircleWidth, mHeight/2-CircleWidth, mWidth/2+CircleWidth, mHeight/2+CircleWidth);
        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                135,  //开始角度
                270 , //扫过的角度
                false, //是否使用中心
                paintRect);
        Paint paintRects=new Paint();
        paintRects.setAntiAlias(true);//抗锯齿
        paintRects.setColor(Color.parseColor("#3D3792"));
        paintRects.setStrokeWidth(30);
        paintRects.setStyle(Paint.Style.STROKE);
        RectF rects = new RectF(mWidth/2-CircleWidth+circleLine, mHeight/2-CircleWidth+circleLine,
                mWidth/2+CircleWidth-circleLine, mHeight/2+CircleWidth-circleLine);
            canvas.drawArc(rects, //弧线所使用的矩形区域大小
                    135,  //开始角度
                    b, //扫过的角度
                    false, //是否使用中心
                    paintRects);

        int x1   = (int) (mWidth/2   +   CircleWidth   *   Math.cos(45   *   3.14 /180 ));
        int y1   = (int) (mHeight/2   +   CircleWidth   *  Math. sin(45   *   3.14 /180));
        Paint paintLine=new Paint();
        paintLine.setColor(Color.parseColor("#07C5C0"));
        paintLine.setStrokeWidth(6);
        canvas.drawLine(x1,y1,x1+30,y1+30,paintLine);
        int x2   = (int) (mWidth/2   +   CircleWidth   *   Math.cos(135   *   3.14 /180 ));
        int y2   = (int) (mHeight/2   +   CircleWidth   *  Math. sin(135   *   3.14 /180));
        canvas.drawLine(x2,y2,x2-30,y2+30,paintLine);

        Paint paintTextV=new Paint();
        paintTextV.setColor(Color.parseColor("#07C5C0"));
        paintTextV.setTextSize(30);
        canvas.drawText("过高",x1+30,y1+60,paintTextV);
        canvas.drawText("过低",x2-40,y2+60,paintTextV);


        Paint paintLineSm=new Paint();
        paintLineSm.setColor(Color.parseColor("#07C5C0"));
        paintLineSm.setStrokeWidth(6);
        for (int i=1 ;i<9;i++){
            int x3  = (int) (mWidth/2   +   CircleWidth   *   Math.cos((i*30+135 )  *   3.14 /180 ));
            int y3   = (int) (mHeight/2   +   CircleWidth   *  Math. sin((i*30+135 )   *   3.14 /180));
            if(i==1) {
                canvas.drawLine(x3, y3, x3 - 20, y3+5 , paintLineSm);
            }else if(i==2) {
                canvas.drawLine(x3, y3, x3 - 15, y3-5, paintLineSm);
            }else if(i==3) {
                canvas.drawLine(x3, y3, x3 - 10, y3-10, paintLineSm);
            }else if(i==4) {
                canvas.drawLine(x3, y3, x3-5 , y3 -15, paintLineSm);
            }else if(i==5) {
                canvas.drawLine(x3, y3, x3+5 , y3 -20, paintLineSm);
            }else if(i==6) {
                canvas.drawLine(x3, y3, x3 +15, y3 -15, paintLineSm);
            }else if(i==7) {
                canvas.drawLine(x3, y3, x3 +20, y3-10 , paintLineSm);
            }else if(i==8) {
                canvas.drawLine(x3, y3, x3 +20, y3+5, paintLineSm);
            }


//            double cos = Math.cos((i * 30 + 135) * Math.PI / 180);
//            double sin = Math.sin((i * 30 + 135) * Math.PI / 180);
//            int diff = i % 2 == 0 ? 10 : 20;
//
//            int startX = (int) (mWidth / 2 + CircleWidth * cos);
//            int startY = (int) (mHeight / 2 + CircleWidth * sin);
//            int endX = (int) (mWidth / 2 + (CircleWidth + diff) * cos);
//            int endY = (int) (mHeight / 2 + (CircleWidth + diff) * sin);
//
//            canvas.drawLine(startX, startY, endX, endY, paintLineSm);


        }

    }
    public void moveView(){
        new Thread(moveThread).start();
    }

    private final Runnable moveThread = new Runnable() {

        @Override
        public void run() {
            while (isTrue){
                if(b<270){
                    b++;

                }else if(b==270){
                    postInvalidate();
                    isTrue=false;
                    return;
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if(b==67){
                    textV=1;
                }else if(b==67*2){
                    textV=2;
                }else if(b==67*3){
                    textV=3;
                }else if(b==67*4){
                    textV=4;
                }
                DecimalFormat df   = new DecimalFormat("######0.00");
                double f= 40.00 / 270.00;
                double ff=b*f;
                if(b==270){
                    BFtext="40.00%";
                }else{
                    String fff= df.format(ff);
                    BFtext=fff+"%";
                }

                postInvalidate();
            }
        }


    };
}
