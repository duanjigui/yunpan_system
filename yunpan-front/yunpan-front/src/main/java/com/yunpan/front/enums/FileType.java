package com.yunpan.front.enums;
public enum FileType {
	APPS("Apps.png"),
	APK("ApkType.png"),
	EXE("ExeType.png"),
	CADA("CADType.png"),
	DOC("DocType.png"),
	FLODER("FolderType.png"),
	IMAGE("ImgType.png"),
	IPA("IpaType.png"),
	MIXFILE("MixFileType.png"),
	MUSIC("MusicType.png"),
	OTHER("OtherType.png"),
	PDF("PdfType.png"),
	PPT("PptType.png"),
	RAR("RarType.png"),
	TORRENT("TorrentType.png"),
	TXT("TxtType.png"),
	VIDEO("VideoType.png"),
	VSD("VsdType.png"),
	XLS("XlsType.png");
	
	private String name;
	
	private FileType(String name){
		this.name=name;
	}
	
	
	
	public String getName() {
		return name;
	}



	public static void main(String[] args) {
		System.out.println(FileType.APPS.name());
	}
	
}
