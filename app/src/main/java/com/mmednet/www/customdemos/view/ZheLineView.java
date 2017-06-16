package com.mmednet.www.customdemos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15.
 * 折线图
 */
public class ZheLineView extends View{
    private int mWidth,mHeight;//宽高
    private ClassPaint Paints;//画笔初始化
    private LinkedHashMap<Integer,Integer> data;

    private List<String> intervalsY;//y轴上要显示的字符
    private List<String> intervalsX;//x轴上要显示的字符
    private float mTextWidth;// 字体最大宽度
    private List<Float> everyYS;//y轴的坐标点
    private List<Float> everyXS;//X轴的坐标点
    private int intervalsXY=40;//间隔
    private LinkedHashMap<Float,Float> circleXY;
    private float circleDot=8;
    private float textHeightB;
    private int endDot;
    private List<Paiot> paiots;
    private List<Paiot> paiotss;

    public ZheLineView(Context context) {
        super(context);
        Paints=new ClassPaint();
    }

    public ZheLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Paints=new ClassPaint();
    }

    public ZheLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Paints=new ClassPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w-getPaddingRight()-getPaddingLeft();
        mHeight=h-getPaddingTop()-getPaddingBottom()-intervalsXY;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mTextWidth = getMaxSize(intervalsY,Paints.paintXY)[0]+20;
        //画Y轴 0-100
        float everyY= mHeight/ intervalsY.size();
        for (int i=0;i<intervalsY.size();i++){
            float textWidth= getTextWidth(intervalsY.get(i) ,Paints.paintXY);
            everyYS.add(everyY+everyY*i);
            canvas.drawText(intervalsY.get(i),mTextWidth/2-textWidth/2, everyY+everyY*i ,Paints.paintXY);
        }
        //画Y轴线
        float textHeight=getTextHeight(intervalsY.get(0) ,Paints.paintXY);
        textHeightB=textHeight/2;
        for (int i=0;i<intervalsY.size();i++){
            canvas.drawLine(mTextWidth,everyYS.get(0)-textHeight,mTextWidth, everyYS.get(intervalsY.size()-1),Paints.paintXYLine);
        }

        //画X轴 0-10
        float everyX= (mWidth-mTextWidth-intervalsXY)/ intervalsX.size();
        for (int i=0;i<intervalsX.size();i++){
            everyXS.add(everyX+everyX*i+mTextWidth);
            canvas.drawText(intervalsX.get(i),everyX+everyX*i+mTextWidth, everyYS.get(intervalsY.size()-1)+intervalsXY/2 ,Paints.paintXY);
        }
        //画线 0-10
        for (int i=0;i<intervalsX.size();i++){
            canvas.drawLine(mTextWidth,everyYS.get(intervalsY.size()-1),mWidth-intervalsXY/2,everyYS.get(intervalsY.size()-1),Paints.paintXYLine);
        }

        //设置数据
        setDateCircle(everyX);
        //画圆点
        for (Map.Entry<Float, Float> entry : circleXY.entrySet()){
            canvas.drawCircle(entry.getKey(),entry.getValue(), circleDot,Paints.paintCircle);
        }
        //连线
        for (int i=0;i<paiots.size()-1;i++){
            canvas.drawLine(paiots.get(i).x , paiots.get(i).y,paiots.get(i+1).x,paiots.get(i+1).y,Paints.paintCircle);
        }
        for (int i=0;i<paiotss.size();i++){
            canvas.drawText(paiotss.get(i).text,paiotss.get(i).x,paiotss.get(i).y,Paints.paintCircle);
        }


    }

    /**
     * 初始化圆点的位置点
     */
    private void setDateCircle(float everyX) {
        paiots=new ArrayList<>();
        paiotss=new ArrayList<>();
        if(data!=null && data.size()>0){
            float everyXY= (everyYS.get(intervalsY.size()-1)-everyYS.get(0))/endDot;

            for (Map.Entry<Integer, Integer> entry : data.entrySet()){
                int key= entry.getKey();
                int value=entry.getValue();
                float everyNewY=everyYS.get(intervalsY.size()-1)-(everyXY*value+circleDot/2);
                float everyNewX=everyX*key+mTextWidth+circleDot/2;
                circleXY.put(everyNewX,everyNewY);
                paiots.add(new Paiot(everyNewX,everyNewY,value+""));
                paiotss.add(new Paiot(everyNewX-10,everyNewY-16,value+""));
            }
        }

    }
    private class Paiot{
        public float x;
        public float y;
        public String text;
        public Paiot(float x,float y,String text){
            this.x=x;
            this.y=y;
            this.text=text;
        }

    }
    /**
     * 设置Y轴
     * @param i
     * @param i1
     * @param i2
     */
    public void setYLine(int i, int i1, int i2) {
        intervalsY=new ArrayList<>();
        everyYS=new ArrayList<>();
        endDot=i1;
        int number= i1/i2;
        for (int y=number;y>=0;y--){
            intervalsY.add(y*i2+"");
        }
    }

    /**
     * 设置X轴
     * @param i
     * @param i1
     * @param i2
     */
    public void setXLine(int i, int i1, int i2) {
        everyXS=new ArrayList<>();
        intervalsX=new ArrayList<>();
        int number= i1/i2;
        for (int y=1;y<=number;y++){
            intervalsX.add(y*i2+"");
        }

    }

    /**
     *
     * @param data
     */
    public void setData(LinkedHashMap<Integer,Integer> data) {
        circleXY=new LinkedHashMap<>();
        this.data = data;
        postInvalidate();
    }

    /** 获取字符串数据的最大宽高 */
    private float[] getMaxSize(List<String> arr, Paint paint) {
        float[] size = new float[2];
        if (arr != null) {
            Rect rect = new Rect();
            for (String str : arr) {
                paint.getTextBounds(str, 0, str.length(), rect);
                size[0] = Math.max(size[0], rect.width());
                size[1] = Math.max(size[1], rect.height());
            }
        }
        return size;
    }

    /**
     * 获取文字的宽度
     * @return
     */
    private float getTextWidth(String text ,Paint paint){
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();
    }
    /**
     * 获取文字的宽度
     * @return
     */
    private float getTextHeight(String text ,Paint paint){
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

    /**
     * 各种画笔的初始化
     */
    public class ClassPaint{
        /**
         * //XY轴画笔
         */
        private Paint paintXY;
        private Paint paintXYLine;
        private Paint paintCircle;//圆点
        public ClassPaint(){
            init();
        }

        public void init(){
            paintXY=new Paint();
            paintXY.setColor(Color.parseColor("#07C5C0"));
            paintXY.setStrokeWidth(4);//画笔画出线的宽度
            paintXY.setTextSize(20);
            paintXY.setAntiAlias(true);//抗锯齿 表框的模糊去掉

            paintXYLine=new Paint();
            paintXYLine.setColor(Color.parseColor("#000000"));
            paintXYLine.setStrokeWidth(1);//画笔画出线的宽度
            paintXYLine.setAntiAlias(true);//抗锯齿 表框的模糊去掉

            paintCircle=new Paint();
            paintCircle.setColor(Color.parseColor("#291D64"));
            paintCircle.setAntiAlias(true);//抗锯齿
            paintCircle.setStrokeWidth(2);

        }



    }
}
