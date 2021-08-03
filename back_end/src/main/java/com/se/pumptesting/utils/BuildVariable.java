
package com.se.pumptesting.utils;

import de.beckhoff.jni.Convert;

public class BuildVariable {

	long indexGroup;

	long indexOffSet;

	long readLength;

	long writeLength;

	int requestBufferSize;

	int responseBufferSize;

	String dataType;

	String symbolPath;

	byte[] writeDataValue;

	Object writeContent;

	public BuildVariable(long indexGroup, long indexOffSet, long readLength, long writeLength, String dataType) {
		this.indexGroup = indexGroup;
		this.indexOffSet = indexOffSet;
		this.readLength = readLength;
		this.writeLength = writeLength;
		this.dataType = dataType;

		check();
	}

	public BuildVariable(String symbolPath, long readLength, long writeLength, String dataType) {
		this.symbolPath = symbolPath;
		this.readLength = readLength;
		this.writeLength = writeLength;
		this.dataType = dataType;

		check();
	}

	public BuildVariable(String symbolPath, long readLength, long writeLength, String dataType, Object writeContent) {
		this.symbolPath = symbolPath;
		this.readLength = readLength;
		this.writeLength = writeLength;
		this.dataType = dataType;
		this.writeContent = writeContent;
		check();
	}

	public void check() {

		if (this.dataType.equals("BOOL")) {
			this.requestBufferSize = 4;
			this.responseBufferSize = 1;
			this.writeDataValue = Convert.BoolToByteArr((Boolean) writeContent);
		} else {
			this.requestBufferSize = 4;
			this.responseBufferSize = 4;
			this.writeDataValue = Convert.IntToByteArr(121);
		}

	}

	public long getIndexGroup() {

		return indexGroup;
	}

	public void setIndexGroup(long indexGroup) {

		this.indexGroup = indexGroup;
	}

	public long getIndexOffSet() {

		return indexOffSet;
	}

	public void setIndexOffSet(long indexOffSet) {

		this.indexOffSet = indexOffSet;
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

	public int getRequestBufferSize() {

		return requestBufferSize;
	}

	public void setRequestBufferSize(int requestBufferSize) {

		this.requestBufferSize = requestBufferSize;
	}

	public int getResponseBufferSize() {

		return responseBufferSize;
	}

	public void setResponseBufferSize(int responseBufferSize) {

		this.responseBufferSize = responseBufferSize;
	}

	public String getDataType() {

		return dataType;
	}

	public void setDataType(String dataType) {

		this.dataType = dataType;
	}

	public byte[] getWriteDataValue() {

		return writeDataValue;
	}

	public void setWriteDataValue(byte[] writeDataValue) {

		this.writeDataValue = writeDataValue;
	}

	public String getSymbolPath() {

		return symbolPath;
	}

	public void setSymbolPath(String symbolPath) {

		this.symbolPath = symbolPath;
	}

	public Object getWriteContent() {

		return writeContent;
	}

	public void setWriteContent(Object writeContent) {

		this.writeContent = writeContent;
	}

}
