package com.yunpan.front.util;

import com.yunpan.front.enums.FileType;

public class FileInfoConvertUtil {
	
	private final static int BYTE=0;
	private final static int KB=1;
	private final static int MB=2;
	private final static int GB=3;
	private final static int TB=4;
	private final static int PB=5;
	
	public static String convert(int size){
		int type=BYTE;
		String covertValue=null;
		while ((size=size>>10)>1024) {
			type++;
		}
		type++;
		switch (type) {
		case BYTE:
			covertValue=size+" bit";	
			break;
		case KB:
			covertValue=size+" KB";
			break;
		case MB:
			covertValue=size+" MB";		
			break;
		case GB:
			covertValue=size+" GB";
			break;
		case TB:
			covertValue=size+" TB";
			break;
		case PB:
			covertValue=size+" PB";
			break;	
		
		}
		return covertValue;
	}
	
	public static FileType convertFileType(String filename,String fileType){
		if (fileType.contains("image")) {
			return FileType.IMAGE;
		}else if (fileType.contains("pdf")) {
			return FileType.PDF;
		}else if (fileType.contains("msword")) {
			return FileType.DOC;
		}else if (fileType.contains("vnd.ms-powerpoint")) {
			return FileType.PPT;
		}else if (fileType.contains("pdf")) {
			return FileType.PDF;
		}else if (fileType.contains("octet-stream")&&filename.contains(".rar")) {
			return FileType.RAR;
		}else if (fileType.contains("text/plain")) {
			return FileType.TXT;
		}else if (fileType.contains("vnd.ms-excel")) {
			return FileType.XLS;
		}else if (fileType.contains("video")||filename.contains(".flv")||filename.contains(".3gp")) {
			return FileType.VIDEO;
		}else if (fileType.contains("octet-stream")&&filename.contains(".exe")) {
			return FileType.EXE;
		}else if (fileType.contains("audio")) {
			return FileType.MUSIC;
		}else if (filename.contains(".apk")) {
			return FileType.APK;
		}else if (filename.contains(".app")) {
			return FileType.APPS;
		}else if (filename.contains(".cda")) {
			return FileType.CADA;
		}else if (filename.contains(".torrent")) {
			return FileType.TORRENT;
		}else if (filename.contains(".ipa")) {
			return FileType.IPA;
		}else if (filename.contains(".mix")) {
			return FileType.MIXFILE;
		}else {
			return FileType.OTHER;
		}
		
	}
	
	
}
