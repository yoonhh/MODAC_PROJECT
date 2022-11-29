package com.modac.campReview.model.vo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{
	   
	   @Override
	   public File rename(File originFile) {
	      
	      //원본파일명( "aaa.jpg")
	      String originName=originFile.getName();
	      
	      //수정 파일명 : 파일업로드시간(년월일시분초)+5자리 랜덤값=>최대한 이름이 겹치지 않게
	      //확장자 : 원본파일의 확장자 그대로.
	      
	      //원본명            =>         수정명
	      //aaa.jpg         =>2022121587854845148.jpg
	      
	      //1. 파일업로드된시간(년월시분초) => String currentTime;
	      String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	      
	      //2. 5자리 랜덤값 => int ranNum
	      int ranNum = (int)(Math.random()*90000+10000);
	      
	      //3. 원본파일 확장자 추출.
	      //파일명 중간에 .이들어가는 경우를 생각하고 마지막 .의 위치에서 추출하기.
	      
	      String ext = originName.substring(originName.lastIndexOf("."));
	      
	      String newName = currentTime+ranNum+ext;
	      
	      //원본파일(originFile)을 수정된 파일명으로 적용시켜서 파일객체로 변환.
	      return new File(originFile.getParent(), newName);
	   }
}
