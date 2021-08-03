
package com.se.pumptesting.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Timer;

import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;

public class TwinCatDM {

	private AmsAddr amsAddr = new AmsAddr();

	private int port = 851;

	private HashMap<String, BuildVariable> map = new HashMap<String, BuildVariable>();

	// private String input = "MAIN.inputs.input3";

	private JNIByteBuffer symbolBuff;

	private JNIByteBuffer handleBuff;

	private JNIByteBuffer dataBuff;

	private int hdlBuffToInt;

	private Timer timerReadBySymbol;

	private int timer = 1;

	private Object readValue;

	private String symbolPath;

	private long readLength;

	private long writeLength;

	private String dataType;

	private Object writeContent;

	ResourceUtil resourceUtil = new ResourceUtil();

	public long openPort() throws IOException {

		long err = 0;
		err = AdsCallDllFunction.adsPortOpen();
		String id = resourceUtil.getTwinCATAddr();// 5.64.150.186.1.1,
													// 192.168.1.8.1.1
		char[] bytes = id.toCharArray();
		// amsAddr.setNetId(new AmsNetId(bytes));
		amsAddr.setNetIdString(id);
		// AdsCallDllFunction.getLocalAddress(amsAddr);
		amsAddr.setPort(port);

		return err;
	}

	public long getHandleBySymbolName() {

		long err = 0;
		long indexGroup = AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME;
		long indexOffSet = 0xF020;

		err = AdsCallDllFunction.adsSyncReadWriteReq(amsAddr, indexGroup, indexOffSet, handleBuff.getUsedBytesCount(), handleBuff,
				symbolBuff.getUsedBytesCount(), symbolBuff);
		if (err != 0)
			System.out.println("Error: Get handle: 0x" + Long.toHexString(err));
		hdlBuffToInt = Convert.ByteArrToInt(handleBuff.getByteArray());
		return err;
	}

	public long readValueByHandle() {

		long err = 0;

		err = AdsCallDllFunction.adsSyncReadReq(amsAddr, AdsCallDllFunction.ADSIGRP_SYM_VALBYHND, hdlBuffToInt, readLength, dataBuff);
		if (err != 0)
			System.out.println("Error: Read by handle: 0x" + Long.toHexString(err));
		else {
			if (dataType.equalsIgnoreCase("BOOL")) {
				readValue = Convert.ByteArrToBool(dataBuff.getByteArray());
			} else if (dataType.equalsIgnoreCase("WORD")) {
				ByteBuffer bb = ByteBuffer.wrap(new byte[] { dataBuff.getByteArray()[1], dataBuff.getByteArray()[0] });
				readValue = bb.getShort();
			} else if (dataType.equalsIgnoreCase("REAL")) {
				readValue = Convert.ByteArrToFloat(dataBuff.getByteArray());
			} else if (dataType.equalsIgnoreCase("DINT")) {
				readValue = Convert.ByteArrToInt(dataBuff.getByteArray());
			} else if (dataType.equalsIgnoreCase("INT")) {
				readValue = Convert.ByteArrToInt(dataBuff.getByteArray());
			} else {
				ByteBuffer bb = ByteBuffer.wrap(new byte[] { dataBuff.getByteArray()[1], dataBuff.getByteArray()[0] });
				int l = bb.getShort();
				readValue = l;
			}
		}
		return err;
	}

	public long writeValueByHandle() {

		long err = 0;
		err = AdsCallDllFunction.adsSyncWriteReq(amsAddr, AdsCallDllFunction.ADSIGRP_SYM_VALBYHND, hdlBuffToInt, writeLength, dataBuff);
		if (err != 0)
			System.out.println("Error: " + Long.toHexString(err));
		return err;
	}

	public long releaseHandle() {

		long err = 0;
		err = AdsCallDllFunction.adsSyncWriteReq(amsAddr, AdsCallDllFunction.ADSIGRP_SYM_RELEASEHND, 0, handleBuff.getUsedBytesCount(), handleBuff);
		if (err != 0)
			System.out.println("Error: Release Handle: 0x" + Long.toHexString(err));
		return err;
	}

	public long closePort() {

		long err = 0;
		err = AdsCallDllFunction.adsPortClose();
		if (err != 0)
			System.out.println("Error: Close Communication: 0x" + Long.toHexString(err));
		return err;
	}

	public Object readValuefromTwincat(String symbolPath, long readLength, long writeLength, String dataType) throws IOException {

		long err = 0;
		buildVariable(symbolPath, readLength, writeLength, dataType);
		err = openPort();
		err = getHandleBySymbolName();
		err = readValueByHandle();
		return readValue;
	}

	public long writeValueToTwincat(String symbolPath, long readLength, long writeLength, String dataType, Object writeContent) throws IOException {

		long err = 0;
		buildVariable(symbolPath, readLength, writeLength, dataType, writeContent);
		err = openPort();
		err = getHandleBySymbolName();
		setDataBuffer(dataType, writeContent);
		err = writeValueByHandle();
		return err;
	}

	private void setDataBuffer(String dataType2, Object writeContent2) {

		if (dataType2.equalsIgnoreCase("BOOL"))
			this.dataBuff.setByteArray(Convert.BoolToByteArr((Boolean) writeContent2));
		else if (dataType2.equalsIgnoreCase("REAL"))
			this.dataBuff.setByteArray(Convert.FloatToByteArr((Float) writeContent2));
		else if (dataType2.equalsIgnoreCase("DINT"))
			this.dataBuff.setByteArray(Convert.IntToByteArr((Integer) writeContent2));

	}

	public void buildVariable(String symbolPath, long readLength, long writeLength, String dataType) {

		this.symbolPath = symbolPath;
		this.readLength = readLength;
		this.writeLength = writeLength;
		this.dataType = dataType;
		check();
	}

	public void buildVariable(String symbolPath, long readLength, long writeLength, String dataType, Object writeContent) {

		this.symbolPath = symbolPath;
		this.readLength = readLength;
		this.writeLength = writeLength;
		this.dataType = dataType;
		this.writeContent = writeContent;
		check();
	}

	public void check() {

		if (dataType.equals("BOOL")) {
			handleBuff = new JNIByteBuffer(4);
			dataBuff = new JNIByteBuffer(1);
			symbolBuff = new JNIByteBuffer(Convert.StringToByteArr(symbolPath, true));
			// writeDataValue =
			// Convert.BoolToByteArr((Boolean)writeContent);
		} else if (dataType.equals("DINT")) {
			handleBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
			dataBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
			symbolBuff = new JNIByteBuffer(Convert.StringToByteArr(symbolPath, true));
			// writeDataValue = Convert.IntToByteArr(121);
		} else if (dataType.equals("REAL")) {
			handleBuff = new JNIByteBuffer(Float.SIZE / Byte.SIZE);
			dataBuff = new JNIByteBuffer(Float.SIZE / Byte.SIZE);
			symbolBuff = new JNIByteBuffer(Convert.StringToByteArr(symbolPath, true));
			// writeDataValue = Convert.IntToByteArr(121);
		}

	}

	public AmsAddr getAmsAddr() {

		return amsAddr;
	}

	public void setAmsAddr(AmsAddr amsAddr) {

		this.amsAddr = amsAddr;
	}

	public int getPort() {

		return port;
	}

	public void setPort(int port) {

		this.port = port;
	}

	public HashMap<String, BuildVariable> getMap() {

		return map;
	}

	public void setMap(HashMap<String, BuildVariable> map) {

		this.map = map;
	}

	public JNIByteBuffer getSymbolBuff() {

		return symbolBuff;
	}

	public void setSymbolBuff(JNIByteBuffer symbolBuff) {

		this.symbolBuff = symbolBuff;
	}

	public JNIByteBuffer getHandleBuff() {

		return handleBuff;
	}

	public void setHandleBuff(JNIByteBuffer handleBuff) {

		this.handleBuff = handleBuff;
	}

	public JNIByteBuffer getDataBuff() {

		return dataBuff;
	}

	public void setDataBuff(JNIByteBuffer dataBuff) {

		this.dataBuff = dataBuff;
	}

	public int getHdlBuffToInt() {

		return hdlBuffToInt;
	}

	public void setHdlBuffToInt(int hdlBuffToInt) {

		this.hdlBuffToInt = hdlBuffToInt;
	}

	public Timer getTimerReadBySymbol() {

		return timerReadBySymbol;
	}

	public void setTimerReadBySymbol(Timer timerReadBySymbol) {

		this.timerReadBySymbol = timerReadBySymbol;
	}

	public int getTimer() {

		return timer;
	}

	public void setTimer(int timer) {

		this.timer = timer;
	}

	public Object getReadValue() {

		return readValue;
	}

	public void setReadValue(Object readValue) {

		this.readValue = readValue;
	}

	public String getSymbolPath() {

		return symbolPath;
	}

	public void setSymbolPath(String symbolPath) {

		this.symbolPath = symbolPath;
	}

	public long getReadLength() {

		return readLength;
	}

	public void setReadLength(long readLength) {

		this.readLength = readLength;
	}

	public long getWriteLength() {

		return writeLength;
	}

	public void setWriteLength(long writeLength) {

		this.writeLength = writeLength;
	}

	public String getDataType() {

		return dataType;
	}

	public void setDataType(String dataType) {

		this.dataType = dataType;
	}

	public Object getWriteContent() {

		return writeContent;
	}

	public void setWriteContent(Object writeContent) {

		this.writeContent = writeContent;
	}

}
