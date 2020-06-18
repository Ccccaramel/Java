package admin.edu.controller.test;

import admin.edu.controller.tool.ResourceUploadAndDownload;

public class testResourceUpload extends ResourceUploadAndDownload {

	public testResourceUpload(String saveImgPath, String saveVideoPath) {
		super(saveImgPath, saveVideoPath);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testResourceUpload test=new testResourceUpload("", "");
		test.newName("123.txt", 222);
	}
}
