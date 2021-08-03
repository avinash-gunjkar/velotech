
package com.se.pumptesting.reports.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.graphUtil.GraphModel;
import com.se.pumptesting.graphUtil.GraphUtil;
import com.se.pumptesting.models.Tbl05TestingData;
import com.se.pumptesting.reports.dto.ReportDto;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.VelotechUtil;

import net.sf.jasperreports.renderers.JCommonDrawableRenderer;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

	static Logger log = Logger.getLogger(ReportServiceImpl.class.getName());

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private VelotechUtil velotechUtil;

	@Autowired
	GraphUtil graphUtil;

	@Override
	public ApplicationResponse getReport(ReportDto dto) {

		ApplicationResponse applicationResponse = null;
		try {
			String path = null;

			Random randomGenerator = new Random();
			Integer var = randomGenerator.nextInt(999999);
			String outputFileName = dto.getReportName().replaceAll(" ", "") + "-" + var + ".pdf";
			String jasperName = null;
			Map<String, Object> parameters = new HashMap<String, Object>();
			// parameters.put("logo1", velotechUtil.getCompanyLogoPath() +
			// tbl02Company.getLogoName());
			// parameters.put("companyName", tbl02Company.getCompanyName());
			// parameters.put("addressLine1", tbl02Company.getAddress1());
			// parameters.put("addressLine2", "");
			// parameters.put("city", tbl02Company.getCity());
			// parameters.put("state", tbl02Company.getState());
			// parameters.put("country", tbl02Company.getCountry());
			// parameters.put("postalCode", tbl02Company.getPostalCode());
			// parameters.put("email", tbl02Company.getEmail());
			// parameters.put("website", tbl02Company.getWebsite());
			// parameters.put("mobileNo", tbl02Company.getMobileNo());
			// parameters.put("contactPhone", tbl02Company.getContactPhone());
			// parameters.put("panNo", tbl02Company.getPanNo());
			// parameters.put("iecCode", tbl02Company.getIecCode());
			// parameters.put("companyShort", tbl02Company.getCompanyShort());
			// parameters.put("corporateIdentityNo",
			// tbl02Company.getCorporateIdentityNumber());
			// parameters.put("gstNo", tbl02Company.getGstNumber());
			// parameters.put("clientId", velotechUtil.getClientId());

			switch (dto.getReportName()) {
			case "Test Report":
				jasperName = "instrumentMaster.jasper";
				parameters.put("instrumentMasterId", dto.getId());
				break;
			case "pumpTest":
				jasperName = "pumpTest.jasper";
				parameters.put("testMasterId", dto.getId());
				parameters.put("chart", new JCommonDrawableRenderer(getPerformanceReport(dto.getId())));
				break;
			default:
				break;
			}

			path = velotechUtil.getReport(jasperName, outputFileName, parameters);

			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, path);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	public JFreeChart getPerformanceReport(Integer testMasterId) {

		JFreeChart chart = null;
		try {

			List<GraphModel> graphModelListHead = new ArrayList();
			List<GraphModel> graphModelListEff = new ArrayList();
			List<GraphModel> graphModelListPower = new ArrayList();
			List<Tbl05TestingData> tbl05TestingDatas = (List<Tbl05TestingData>) genericDao
					.getRecordsByParentId(Tbl05TestingData.class, "tbl04TestingMaster.id", testMasterId).getData();
			List<Double> flowPoint = new ArrayList<>();
			List<Double> headPoint = new ArrayList<>();
			List<Double> powerPoint = new ArrayList<>();
			List<Double> effPoint = new ArrayList<>();
			for (Tbl05TestingData tbl05TestingData : tbl05TestingDatas) {
				flowPoint.add(tbl05TestingData.getFlowDc());
				headPoint.add(tbl05TestingData.getHeadDc());
				powerPoint.add(tbl05TestingData.getPowerDc());
				effPoint.add(tbl05TestingData.getEfficiencyDc());
			}
			graphModelListHead.add(new GraphModel(flowPoint, headPoint, true, "blue", "Solid", 2, "blue", "circles"));
			graphModelListPower.add(new GraphModel(flowPoint, powerPoint, true, "blue", "Solid", 2, "blue", "circles"));
			graphModelListEff.add(new GraphModel(flowPoint, effPoint, true, "blue", "Solid", 2, "blue", "circles"));

			CombinedDomainXYPlot combinedDomainXYPlot = graphUtil.combineGraph("Flow in m3/hr", true, 15);
			XYPlot plotHead = graphUtil.individualGraph(graphModelListHead, "Head(m)", true, true);
			XYPlot plotPower = graphUtil.individualGraph(graphModelListPower, "Power(kW)", true, true);
			XYPlot plotEff = graphUtil.individualGraph(graphModelListEff, "Eff(%)", true, true);

			combinedDomainXYPlot.add(plotHead, 2);
			combinedDomainXYPlot.add(plotPower, 1);
			combinedDomainXYPlot.add(plotEff, 1);

			chart = graphUtil.getChart("", "", combinedDomainXYPlot, true);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return chart;
	}

}
