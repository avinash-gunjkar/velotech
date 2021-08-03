
package com.se.pumptesting.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Service
public class VelotechUtil {

	static Logger log = Logger.getLogger(VelotechUtil.class.getName());

	public int getUserMasterId() {

		int userMasterId = 0;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes.getRequest().getSession().getAttribute("userMasterId") != null)
			userMasterId = (int) requestAttributes.getRequest().getSession().getAttribute("userMasterId");
		return userMasterId;
	}

	public String getClientId() {

		String clientId = null;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes.getRequest().getSession().getAttribute("clientId") != null)
			clientId = requestAttributes.getRequest().getSession().getAttribute("clientId").toString();
		return clientId;
	}

	public int getCompanyId() {

		Integer companyId = null;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes.getRequest().getSession().getAttribute("companyId") != null)
			companyId = (Integer) requestAttributes.getRequest().getSession().getAttribute("companyId");
		return companyId;
	}

	public Integer getCompanyPlantId() {

		Integer companyPlantId = null;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes.getRequest().getSession().getAttribute("companyPlantId") != null)
			companyPlantId = (Integer) requestAttributes.getRequest().getSession().getAttribute("companyPlantId");
		return companyPlantId;
	}

	public int getCompanyPlanUnitId() {

		Integer companyPlantUnitId = null;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes.getRequest().getSession().getAttribute("companyPlantUnitId") != null)
			companyPlantUnitId = (Integer) requestAttributes.getRequest().getSession().getAttribute("companyPlantUnitId");
		return companyPlantUnitId;
	}

	public String getYearCode() {

		String yearCode = null;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes.getRequest().getSession().getAttribute("yearCode") != null)
			yearCode = (String) requestAttributes.getRequest().getSession().getAttribute("yearCode");
		return yearCode;
	}

	public String getUserRealPath() {

		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		return requestAttributes.getRequest().getSession().getAttribute("userRealPath").toString() + System.getProperty("file.separator");
	}

	public String getUserContextPath() {

		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		return requestAttributes.getRequest().getSession().getAttribute("userContextPath").toString() + System.getProperty("file.separator");
	}

	public String getProjectRealPath() {

		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		return requestAttributes.getRequest().getSession().getAttribute("projectRealPath").toString();
	}

	public String getReportPath() {

		VelotechUtil velotechUtil = new VelotechUtil();
		String path = velotechUtil.getProjectRealPath();
		String reportPath = path + "iReports" + System.getProperty("file.separator");
		return reportPath;
	}

	public String getReport(String jasperName, String outputFileName, Map<String, Object> parameters) {

		String path = "";
		try {

			Connection con = JdbcConnection.getConnection();

			JasperPrint jasperPrint = JasperFillManager.fillReport(getReportPath() + jasperName, parameters, con);

			ArrayList<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			jasperPrintList.add(jasperPrint);
			if (outputFileName.equals("")) {
				outputFileName = "Output";
			}
			File file1 = new File(getUserRealPath(), outputFileName);

			if (!file1.exists()) {
				file1.createNewFile();
			}
			OutputStream output = new FileOutputStream(file1);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
			exporter.exportReport();
			output.flush();
			output.close();

			path = getUserContextPath() + outputFileName;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return path;
	}

	public String XY(List<Double> X, List<Double> Y) {

		String ans = "";

		for (int i = 0; i < X.size(); i++) {
			if (Y.get(i) != null) {
				ans += X.get(i) + " ";
				ans += Y.get(i) + " ";
			}
		}
		return ans;
	}

	public Double getRoundValue(double b) {

		Double ans = 0d;
		try {

			Double multiple = 100d;
			if (b < 1)
				multiple = 0.1;
			else if (b < 20)
				multiple = 1d;
			else if (b < 50)
				multiple = 2d;
			else if (b < 100)
				multiple = 10d;
			else if (b < 500)
				multiple = 20d;
			else if (b < 1000)
				multiple = 50d;

			ans = Math.ceil(b / multiple) * multiple;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

}
