
package com.se.pumptesting.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.models.Tbl00MotorMaster;
import com.se.pumptesting.models.Tbl00MotorNoLoadDetails;
import com.se.pumptesting.models.Tbl04TestingMaster;
import com.se.pumptesting.models.Tbl05TestingData;
import com.se.pumptesting.utils.polysolve.PolySolve;

@Service
public class PumpTestingUtil {

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private PolySolve polySolve;

	@Autowired
	private VelotechUtil velotechUtil;

	ResourceUtil resourceUtil = new ResourceUtil();

	public Double velocityHead(Double pipeSize, Double flow) {

		Double velocityHead = 0d;
		try {
			double flowInM3Sec = flow / 3600d;
			double pipeArea = Math.PI / 4 * Math.pow(pipeSize / 1000, 2);
			double velocity = flowInM3Sec / pipeArea;

			velocityHead = Math.pow(velocity, 2) / (2 * 9.81);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return velocityHead;
	}

	public Tbl05TestingData setTestingData(Tbl04TestingMaster tbl04TestingMaster, Tbl00MotorMaster tbl00MotorMaster, Tbl05TestingData testingData) {

		try {

			testingData.setVelocityheadSuction(velocityHead(tbl04TestingMaster.getSuctionPipeSize(), testingData.getFlow()));
			testingData.setVelocityheadDischarge(velocityHead(tbl04TestingMaster.getDischargePipeSize(), testingData.getFlow()));
			testingData.setVelocityheadTotal(testingData.getVelocityheadDischarge() - testingData.getVelocityheadSuction());
			testingData.setTotalHead(testingData.getPressureDischarge() - testingData.getPressureSuction() + testingData.getVelocityheadTotal()
					+ testingData.getDistanceBetwnGauge());
			//double motorLoss = motorLoss(tbl00MotorMaster, tbl04TestingMaster, testingData);
			if(testingData.getMotorEff()==null || testingData.getMotorEff()==0)
				testingData.setMotorEff(tbl00MotorMaster.getEfficiency());
			
			testingData.setPumpInput(testingData.getMotorPower() /testingData.getMotorEff() );
			
			testingData.setFlowDc(Q_rpm_conversion(tbl04TestingMaster.getDpSpeed(), testingData.getSpeed(), testingData.getFlow()));
			testingData.setHeadDc(H_rpm_conversion(tbl04TestingMaster.getDpSpeed(), testingData.getSpeed(), testingData.getTotalHead()));
			testingData.setPowerDc(P_rpm_conversion(tbl04TestingMaster.getDpSpeed(), testingData.getSpeed(), testingData.getPumpInput()));
			testingData.setEfficiencyDc((testingData.getFlowDc() * testingData.getHeadDc()) / (367.2 * testingData.getPowerDc()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return testingData;
	}

	public Double motorLoss(Tbl00MotorMaster tbl00MotorMaster, Tbl04TestingMaster tbl04TestingMaster, Tbl05TestingData testingData) {

		Double totalLoss = 0d;
		double nll = 0d;
		double statorCuLoss = 0d;
		Double motorEffMultiplier = 1d;

		try {
			if (tbl00MotorMaster.getPhase() != 1)
				motorEffMultiplier = Double.parseDouble(resourceUtil.getmotorEffMultiplier());

			double averageResistance = (tbl00MotorMaster.getResistanceR() + tbl00MotorMaster.getResistanceY() + tbl00MotorMaster.getResistanceB())
					/ 3;
			double r75 = averageResistance * (1 + 0.004041 * (75d - tbl00MotorMaster.getResistanceTemprature()));
			double noLoadPower = 0, noLoadCurrent = tbl00MotorMaster.getNoloadCurrent();
			if (tbl00MotorMaster.getPhase() == 1) {
				noLoadPower = tbl00MotorMaster.getNoloadPower();

			} else {
				noLoadPower = getNoLoadData(tbl00MotorMaster.getId(), testingData.getSpeed());

			}
			double noLoadPowertemp = 0d;
			if (tbl00MotorMaster.getConnectionType().equals("STAR"))
				noLoadPowertemp = Math.pow(noLoadCurrent, 2) * r75 * 3 / 1000d;
			else
				noLoadPowertemp = Math.pow(noLoadCurrent, 2) * r75 / 1000d;

			nll = noLoadPower - noLoadPowertemp;
			double frequency = 50d;
			if (tbl00MotorMaster.getPhase() != 1) {
				frequency = testingData.getFrequency();
			}
			if (tbl00MotorMaster.getConnectionType().equals("STAR"))
				statorCuLoss = (Math.pow(testingData.getCurrent_(), 2) * r75) * 3 / 1000d;
			else
				statorCuLoss = (Math.pow(testingData.getCurrent_(), 2) * r75) / 1000d;
			double synchronousSpeed = 120 * frequency / tbl00MotorMaster.getPole();
			double slip = (synchronousSpeed - testingData.getSpeed()) / synchronousSpeed;
			double rotorCuLoss = slip * (testingData.getMotorPower() - nll - statorCuLoss);
			double rototIronLoss = 0.005 * (testingData.getMotorPower() - nll - statorCuLoss - rotorCuLoss);

			totalLoss = nll + statorCuLoss + rotorCuLoss + rototIronLoss;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalLoss * motorEffMultiplier;
	}

	public Double getNoLoadData(Integer motorId, Double reqSpeed) {

		Double ans = 0d;
		try {

			ApplicationResponse applicationResponse = genericDao.getRecordsByParentId(Tbl00MotorNoLoadDetails.class, "tbl00MotorMaster.id", motorId);
			List<Tbl00MotorNoLoadDetails> motorNoLoadDetails = (List<Tbl00MotorNoLoadDetails>) applicationResponse.getData();

			List<Double> speedList = new ArrayList<Double>();
			List<Double> noLoadPowerList = new ArrayList<Double>();
			for (Tbl00MotorNoLoadDetails tbl00MotorNoLoadDetails : motorNoLoadDetails) {
				speedList.add(tbl00MotorNoLoadDetails.getSpeed());
				noLoadPowerList.add(tbl00MotorNoLoadDetails.getNoLoadPower());
			}
			double[] termsNoLoadPower = polySolve.process(velotechUtil.XY(speedList, noLoadPowerList), 1);

			ans = polySolve.plotFunct(reqSpeed, termsNoLoadPower);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	public double Q_rpm_conversion(Double req_speed, Double base_speed, double Q) {

		double ans = 0;
		try {
			if (req_speed == null || req_speed == 0 || base_speed==null || base_speed==0 ) {
				ans = Q;
			} else {
				ans = ((req_speed / base_speed) * Q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	public Double H_rpm_conversion(Double req_speed, Double base_speed, double H) {

		double ans = 0;
		try {
			if (req_speed == null || req_speed == 0 || base_speed==null || base_speed==0) {
				ans = H;
			} else {
				ans = Math.pow((req_speed / base_speed), 2) * H;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	public Double P_rpm_conversion(Double req_speed, Double base_speed, double P) {

		double ans = 0d;
		try {
			if (req_speed == null || req_speed == 0 || base_speed==null || base_speed==0) {
				ans = P;
			} else {
				ans = Math.pow((req_speed / base_speed), 3) * P;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}
}
