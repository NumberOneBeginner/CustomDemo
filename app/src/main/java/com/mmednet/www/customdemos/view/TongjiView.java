package com.mmednet.www.customdemos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12.
 * 统计柱形图
 */
public class TongjiView extends View {
    private Context mContext;
    private int mWidth,mHeight;//屏幕的宽高
    private List<String> yLineStr;//画Y轴
    private List<String> xLineStr;//画x轴
    private LinkedHashMap<Integer,Integer> mapData;//需要暂时的坐标位置点
    private LinkedHashMap<Float,Float> mapDataNew;//坐标位置点
    private ClassPaint paints;//各种画笔的初始化
    private List<String> ZStr;//柱子上面的数字

    private List<Float> everyYS;
    private List<Float> everyXS;

    private float mTextWidth;// 字体最大宽度
    private int mYWidthLine=10;//画Y轴时设置的宽度
    private int mZhuZiWidth=20;//设置柱子的宽度
    public TongjiView(Context context) {
        super(context);
        this.mContext=context;
        paints =new ClassPaint();
    }

    public TongjiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        paints =new ClassPaint();
    }

    public TongjiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        paints =new ClassPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h-40;
        Log.i("maokuaile","mWidth=="+mWidth);
        Log.i("maokuaile","mHeight=="+mHeight);





    }
    private int end1;
    private int end;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if((yLineStr==null || yLineStr.size()==0 )||xLineStr==null || xLineStr.size()==0){
            return;


        }

        //画Y轴
        float everyY= mHeight/ yLineStr.size();
        for (int i=0;i<yLineStr.size();i++){
            everyYS.add(everyY+everyY*i);
            canvas.drawText(yLineStr.get(i),mYWidthLine, everyY+everyY*i,paints.paintXY);
        }
        //画X轴
        float everyX= (mWidth-40)/ xLineStr.size();
        for (int i=0;i<xLineStr.size();i++){
            everyXS.add(everyX+everyX*i);
            canvas.drawText(xLineStr.get(i),everyX+everyX*i,everyYS.get(yLineStr.size()-1)+20 ,paints.paintXY);
        }
        yEveryCount=(everyYS.get(yLineStr.size()-1)-everyYS.get(0))/end1;
        xEveryCount = (mWidth-40)/end;
        setLntLan();

        //画矩形
        for (Map.Entry<Float, Float> entry : mapDataNew.entrySet()){
            float key=entry.getKey();
            float value=entry.getValue();
            RectF rect = new RectF(key, value, key+mZhuZiWidth, everyYS.get(yLineStr.size()-1));
            canvas.drawRect(rect, paints.paintXY);
        }
        //画矩形图上面的文字
        int ff=0;
        for (Map.Entry<Float, Float> entry : mapDataNew.entrySet()){
            float key=entry.getKey();
            float value=entry.getValue();
            if(ff>mapDataNew.size()-1){
                ff=0;
            }
            canvas.drawText(ZStr.get(ff)+"",key,value-10,paints.paintXY);
            ff++;
        }
        //最后画xy对应的横竖线
        mTextWidth= getMaxSize(mapData,paints.paintXY)[0];

        canvas.drawLine(mYWidthLine+mTextWidth+2 ,0 , mYWidthLine+mTextWidth+2 , everyYS.get(yLineStr.size()-1),paints.paintXYLine);
        canvas.drawLine(mYWidthLine+mTextWidth+2 ,everyYS.get(yLineStr.size()-1) , mWidth , everyYS.get(yLineStr.size()-1),paints.paintXYLine);



    }

    /**
     * 设置坐标点
     */
    private void setLntLan() {
        mapDataNew=new LinkedHashMap<>();
        ZStr=new ArrayList<>();
        float  ff=everyYS.get(yLineStr.size()-1);
        for (Map.Entry<Integer, Integer> entry : mapData.entrySet()){
            int key=entry.getKey();
            int value=entry.getValue();
            float tipY= ff-yEveryCount*value;
            float tipX= xEveryCount*key;
            mapDataNew.put(tipX,tipY);
            ZStr.add(value+"");
        }


    }
    private int xEveryCount;
    /**
     *  画x轴
     * @param start
     * @param end
     * @param jg
     */
    public void setXLine(int start , int end , int jg){
        xLineStr=new ArrayList<>();
        everyXS=new ArrayList<>();
       this.end=end;
       int number= end/jg;
        int count =end/number;
        for (int i=0;i<number;i++){
            xLineStr.add(count+count*i+"");
        }
    }
    private float yEveryCount;
    /**
     *  画y轴
     * @param start
     * @param end
     * @param jg
     */
    public void setYLine(int start , int end , int jg){
        yLineStr=new ArrayList<>();
        everyYS=new ArrayList<>();
        end1=end;
        int number= end/jg;
        int count =end/number;
        for (int i=number-1;i>=0;i--){
            yLineStr.add(count+count*i+"");
        }
        yLineStr.add(start+"");


    }

    /**
     * 设置需要表示的点的位置
     */
    public void setData(LinkedHashMap<Integer,Integer> mapData){
        this.mapData=mapData;
        postInvalidate();
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




        }




    }

    /** 获取字符串数据的最大宽高 */
    private float[] getMaxSize(LinkedHashMap<Integer,Integer> mapData, Paint paint) {
        float[] size = new float[2];
        if (mapData != null) {
            Rect rect = new Rect();
            for (Map.Entry<Integer, Integer> entry : mapData.entrySet()) {
                float value = entry.getValue();
                String str = (int) value + "";
                paint.getTextBounds(str, 0, str.length(), rect);
                size[0] = Math.max(size[0], rect.width());
                size[1] = Math.max(size[1], rect.height());
            }
        }
        return size;
    }

}
