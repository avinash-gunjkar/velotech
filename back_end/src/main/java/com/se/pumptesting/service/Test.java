
package com.se.pumptesting.service;

import com.se.pumptesting.utils.TwinCatDM;

public class Test {

	public static void main(String[] args) {

		try {
			TwinCatDM twinCatDM = new TwinCatDM();
			System.out.println("nTachometerArray : " + twinCatDM.readValuefromTwincat("GVL.fAnalyser_Power", 4, 2, "REAL"));
			System.out.println("nTachometerArray : " + twinCatDM.readValuefromTwincat("GVL.fAnalyser_Voltage", 4, 2, "REAL"));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
