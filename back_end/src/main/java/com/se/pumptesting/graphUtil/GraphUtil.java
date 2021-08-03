/**
 * File: GraphUtil.java
 * Project: PumpManagement_V2
 * Developer: Prabal Srivastava
 * Date: Sep 11, 2012
 */

package com.se.pumptesting.graphUtil;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.se.pumptesting.utils.VelotechUtil;

@Component
public class GraphUtil {

	@Autowired
	private VelotechUtil velotechUtil;

	public CombinedDomainXYPlot combineGraph(String xAxisUnit, boolean includeZeroInX, int xGap) {

		NumberAxis xAxis = new NumberAxis(xAxisUnit);
		xAxis.setAutoRangeIncludesZero(includeZeroInX);
		CombinedDomainXYPlot combined_plot = new CombinedDomainXYPlot(xAxis);
		if (xGap > 0)
			combined_plot.setGap(xGap);
		combined_plot.setOrientation(PlotOrientation.VERTICAL);
		return combined_plot;
	}

	private double getTickUnitSize(NumberAxis axis, int number) {

		double interval = 0.0;
		double tempMax = 0.0;
		if (axis.getUpperBound() > 500) {
			interval = 100.0;
		} else if (axis.getUpperBound() > 100) {
			interval = 50.0;
		} else if (axis.getUpperBound() > 10) {
			interval = 10.0;
		} else {
			interval = 1.0;
		}
		tempMax = interval * Math.ceil(axis.getUpperBound() / interval);
		int tick = (int) Math.ceil(tempMax / number);

		// removed to take care of axis value below 10
		/*
		 * while (tick % number != 0) { tick++; }
		 */

		System.out.println("TICK: " + tick);
		return tick;
	}

	public JFreeChart getChart(String chartHeading, String chartSubHeading, CombinedDomainXYPlot combined_plot, boolean minorAxisX) {

		NumberAxis xAxis = (NumberAxis) combined_plot.getDomainAxis();
		// NumberTickUnit rUnit = new NumberTickUnit(getTickUnitSize(xAxis,
		// 10));
		// xAxis.setTickUnit(rUnit);
		 xAxis.setAutoRange(true);

		/*Double maxXRange = velotechUtil.getRoundValue(xAxis.getUpperBound());
		xAxis.setRange(0, maxXRange);
		xAxis.setTickUnit(new NumberTickUnit(maxXRange / 10));*/

		if (minorAxisX) {
			xAxis.setMinorTickCount(5);
			xAxis.setMinorTickMarksVisible(true);
			combined_plot.setDomainMinorGridlinesVisible(true);
			combined_plot.setDomainMinorGridlineStroke(new BasicStroke(0.0f));
			combined_plot.setDomainMinorGridlinePaint(Color.BLACK);
			combined_plot.setDomainGridlinePaint(new Color(128, 128, 128));
			combined_plot.setDomainGridlineStroke(new BasicStroke(1.0f));

		}
		JFreeChart chart = new JFreeChart(chartHeading, JFreeChart.DEFAULT_TITLE_FONT, combined_plot, false);
		chart.setBackgroundPaint(Color.white);
		if (!chartSubHeading.equals("")) {
			chart.addSubtitle(new TextTitle(chartSubHeading));
		}

		return chart;
	}

	public XYPlot individualGraph(List<GraphModel> graphModelList, String yUnit, boolean minorAxisX, boolean minorAxisY) {

		XYPlot subPlot = null;
		try {
			XYSeriesCollection dataset = new XYSeriesCollection();
			XYLineAndShapeRenderer renderer_point = new XYLineAndShapeRenderer();
			for (int i = 0; i < graphModelList.size(); i++) {
				XYSeries series1 = new XYSeries(i);
				for (int j = 0; j < graphModelList.get(i).getxPoint().size(); j++) {
					series1.add(graphModelList.get(i).getxPoint().get(j), graphModelList.get(i).getyPoint().get(j));
				}
				dataset.addSeries(series1);

				Shape shape = new Ellipse2D.Double(-2, -2, 4, 4);

				renderer_point.setSeriesShape(i, shape);
				renderer_point.setSeriesPaint(i, graphModelList.get(i).getPointColor());

				NumberAxis yAxis = new NumberAxis(yUnit);
				// yAxis.setAutoRangeIncludesZero(true);
				/*Double maxYRange = velotechUtil.getRoundValue(DatasetUtilities.findMaximumRangeValue(dataset).doubleValue());
				yAxis.setRange(0, maxYRange);
				yAxis.setTickUnit(new NumberTickUnit(maxYRange / 5));*/
				
				yAxis.setAutoRangeIncludesZero(true);

				if (yUnit.contains("Eff")) {
					// yAxis.setNumberFormatOverride(new DecimalFormat("0%"));
					DecimalFormat pctFormat = new DecimalFormat("##");
					pctFormat.setMultiplier(100);
					yAxis.setNumberFormatOverride(pctFormat);
				}

				if (minorAxisY) {
					yAxis.setMinorTickCount(5);
					yAxis.setMinorTickMarksVisible(true);
				}

				subPlot = new XYPlot(dataset, new NumberAxis(""), yAxis, renderer_point);
				subPlot.setDomainGridlinePaint(Color.BLACK);
				subPlot.setRangeGridlinePaint(Color.BLACK);

				subPlot.setDomainGridlineStroke(new BasicStroke(0.6f));
				subPlot.setRangeGridlineStroke(new BasicStroke(0.6f));

				if (minorAxisY) {
					subPlot.setRangeMinorGridlinesVisible(true);
					subPlot.setRangeMinorGridlineStroke(new BasicStroke(0.15f));
					subPlot.setRangeMinorGridlinePaint(Color.GRAY);
				}
				if (minorAxisX) {
					subPlot.setDomainMinorGridlinesVisible(true);
					subPlot.setDomainMinorGridlineStroke(new BasicStroke(0.15f));
					subPlot.setDomainMinorGridlinePaint(Color.GRAY);
				}
				// yAxis.setUpperBound(DatasetUtilities.findMaximumRangeValue(dataset).doubleValue()
				// * 1.2);
				//double a = subPlot.getRangeAxis().getUpperBound();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subPlot;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saveChartAsImage(String fileName, JFreeChart chart, String typeOfChart, Object datasetObj1, Object datasetObj2, int chartWidth,
			int chartHeight) {

		Color[] colors = new Color[15];
		// Wilo color specifications
		colors[0] = new Color(0, 156, 130);// wiloGreen
		colors[1] = new Color(80, 80, 80);// gunMetal
		colors[2] = new Color(255, 180, 0);// technicYellow
		colors[3] = new Color(120, 120, 120);// coolGrey or gunMetal 50%
		colors[4] = new Color(77, 186, 168);// wiloGreen 70%
		colors[5] = new Color(255, 191, 76);// technicYellow 70%
		colors[6] = new Color(166, 166, 166);// gunMetal 50%
		colors[7] = new Color(128, 206, 193);// wiloGreen 50%
		colors[8] = new Color(255, 209, 128);// technicYellow 50%
		colors[9] = new Color(179, 225, 218);// wiloGreen 30%
		colors[10] = new Color(204, 204, 204);// gunMetal 30%
		colors[11] = new Color(0, 90, 205);// Water Blue
		colors[12] = new Color(170, 200, 0);// natural Green
		colors[13] = new Color(245, 65, 0);// vitalRed
		colors[14] = new Color(255, 255, 255);// White

		try {
			if (typeOfChart.equals("PIE")) {
				PiePlot pieplot = (PiePlot) chart.getPlot();
				DefaultPieDataset dataset = (DefaultPieDataset) datasetObj1;
				List<Comparable> keys = dataset.getKeys();
				for (int i = 0; i < keys.size(); i++) {
					int aInt = i % colors.length;
					pieplot.setSectionPaint(keys.get(i), colors[aInt]);
				}
				pieplot.setLabelFont(new Font("SansSerif", Font.PLAIN, 11));
				pieplot.setLabelBackgroundPaint(colors[14]);
				pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));
				pieplot.setLabelLinkStyle(PieLabelLinkStyle.QUAD_CURVE);
				pieplot.setBackgroundPaint(new Color(255, 255, 255));
				pieplot.setNoDataMessage("No Data Available");
				pieplot.setSimpleLabels(false);
				pieplot.setCircular(true);
				pieplot.setOutlineStroke(null);

			} else if (typeOfChart.equals("STACKEDBAR")) {
				CategoryPlot catplot = (CategoryPlot) chart.getPlot();
				DefaultCategoryDataset dataset = (DefaultCategoryDataset) datasetObj1;

				catplot.setNoDataMessage("No Data Available");
				NumberAxis numberAxis = (NumberAxis) catplot.getRangeAxis();
				numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				StackedBarRenderer renderer = (StackedBarRenderer) catplot.getRenderer();

				List<Comparable> keys = dataset.getColumnKeys();
				for (int i = 0; i < keys.size(); i++) {
					int aInt = i % colors.length;
					renderer.setSeriesPaint(i, colors[aInt]);
				}
				renderer.setMaximumBarWidth(.06);
				renderer.setBarPainter(new StandardBarPainter());
				renderer.setDrawBarOutline(true);
				renderer.setBaseItemLabelsVisible(true);
				renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
				catplot.setOutlineStroke(null);
				catplot.setBackgroundPaint(new Color(255, 255, 255));
				CategoryAxis domainAxis = catplot.getDomainAxis();
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
				renderer.setItemMargin(0.01);

			} else if (typeOfChart.equals("XYPlot")) {
				XYPlot plot = (XYPlot) chart.getPlot();
				plot.setNoDataMessage("No Data Available");
				plot.setBackgroundPaint(new Color(255, 255, 255));
				plot.setDomainZeroBaselineVisible(true);
				plot.setRangeZeroBaselineVisible(true);

				plot.setDomainGridlineStroke(new BasicStroke(0.0f));
				plot.setDomainMinorGridlineStroke(new BasicStroke(0.0f));
				plot.setDomainGridlinePaint(Color.blue);
				plot.setRangeGridlineStroke(new BasicStroke(0.0f));
				plot.setRangeMinorGridlineStroke(new BasicStroke(0.0f));
				plot.setRangeGridlinePaint(Color.blue);
				// plot.setOutlineStroke(null);
				plot.setDomainMinorGridlinesVisible(true);
				plot.setRangeMinorGridlinesVisible(true);

				XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
				renderer.setSeriesOutlinePaint(0, Color.black);
				renderer.setUseOutlinePaint(true);
				NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();

				domainAxis.setAutoRangeIncludesZero(false);
				domainAxis.setTickMarkInsideLength(2.0f);
				domainAxis.setTickMarkOutsideLength(2.0f);
				domainAxis.setMinorTickCount(2);
				domainAxis.setMinorTickMarksVisible(false);

				NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

				rangeAxis.setTickMarkInsideLength(2.0f);
				rangeAxis.setTickMarkOutsideLength(2.0f);
				rangeAxis.setMinorTickCount(2);
				rangeAxis.setMinorTickMarksVisible(false);

			} else if (typeOfChart.equals("BAR")) {
				CategoryPlot catplot = (CategoryPlot) chart.getPlot();
				catplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
				DefaultCategoryDataset dataset = (DefaultCategoryDataset) datasetObj1;

				catplot.setNoDataMessage("No Data Available");
				NumberAxis numberAxis = (NumberAxis) catplot.getRangeAxis();
				numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				BarRenderer renderer = (BarRenderer) catplot.getRenderer();

				List<Comparable> keys = dataset.getColumnKeys();
				for (int i = 0; i < keys.size(); i++) {
					int aInt = i % colors.length;
					renderer.setSeriesPaint(i, colors[aInt]);
				}

				renderer.setMaximumBarWidth(.06);
				renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
				renderer.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 8));
				renderer.setBarPainter(new StandardBarPainter());
				renderer.setDrawBarOutline(true);
				renderer.setBaseItemLabelsVisible(true);
				renderer.setShadowVisible(false);
				renderer.setItemMargin(0.01);
				// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
				catplot.setOutlineStroke(null);
				catplot.setBackgroundPaint(new Color(255, 255, 255));

			} else if (typeOfChart.equals("DUAL_BAR")) {
				CategoryPlot plot = (CategoryPlot) chart.getPlot();
				plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
				plot.setNoDataMessage("No Data Available");
				CategoryDataset dataofferOrder = (CategoryDataset) datasetObj1;
				ValueAxis axis2 = new NumberAxis("Total Offers and Orders");
				axis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				plot.setRangeAxis(2, axis2);
				plot.setDataset(1, dataofferOrder);
				plot.mapDatasetToRangeAxis(1, 2);
				plot.setRangeAxisLocation(2, AxisLocation.BOTTOM_OR_LEFT);

				BarRenderer renderer = (BarRenderer) plot.getRenderer();
				LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
				renderer2.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
				renderer2.setBaseLinesVisible(false);
				plot.setRenderer(1, renderer2);
				plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
				List<Comparable> keys = dataofferOrder.getColumnKeys();
				for (int i = 0; i < keys.size(); i++) {
					int aInt = i % colors.length;
					renderer.setSeriesPaint(i, colors[aInt]);
					renderer2.setSeriesPaint(i, colors[aInt]);
				}
				renderer.setMaximumBarWidth(.03);
				renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
				renderer.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 8));
				renderer2.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
				renderer2.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 8));
				renderer.setBarPainter(new StandardBarPainter());
				renderer.setDrawBarOutline(true);
				renderer.setBaseItemLabelsVisible(true);
				renderer.setShadowVisible(false);
				renderer.setItemMargin(0.01);
				plot.setOutlineStroke(null);
				plot.setBackgroundPaint(new Color(255, 255, 255));

			}
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

			File file1 = new File(velotechUtil.getUserContextPath(), fileName);
			if (!file1.exists())
				file1.createNewFile();
			ChartUtilities.saveChartAsPNG(file1, chart, chartWidth, chartHeight, info);
		} catch (Exception e) {
			fileName = "false";
			e.printStackTrace();
		}
		return fileName;
	}

}
