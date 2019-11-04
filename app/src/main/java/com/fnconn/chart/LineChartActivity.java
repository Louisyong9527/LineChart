package com.fnconn.chart;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fnconn.chartlibrary.bean.CompositeIndexBean;
import com.fnconn.chartlibrary.bean.IncomeBean;
import com.fnconn.chartlibrary.manager.LineChartManager;
import com.fnconn.chartlibrary.utils.LocalJsonAnalyzeUtil;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public class LineChartActivity extends AppCompatActivity {

    private LineChartBean lineChartBean;
    private List<IncomeBean> incomeBeanList;//个人收益
    private List<CompositeIndexBean> shanghai;//沪市指数

    private LineChart lineChart1;

    private LineChartManager lineChartManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        initData();
        initView();
    }

    private void initData() {
        //获取数据
        lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(this, "line_chart.json", LineChartBean.class);
        incomeBeanList = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();
        shanghai = lineChartBean.getGRID0().getResult().getCompositeIndexShanghai();
    }

    private void initView() {
        lineChart1 = findViewById(R.id.lineChart);
        lineChartManager = new LineChartManager(lineChart1);

        //展示图表
        lineChartManager.showLineChart(incomeBeanList, "每月业绩统计", getResources().getColor(R.color.color_4E2010));
        lineChartManager.addLine(shanghai, "累计业绩统计", getResources().getColor(R.color.color_99CCFF));

        //设置曲线填充色 以及 MarkerView
//        Drawable drawable = getResources().getDrawable(R.drawable.fade_blue);
//        lineChartManager1.setChartFillDrawable(drawable);
        lineChartManager.setMarkerView(this);
    }
}
