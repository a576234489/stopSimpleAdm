package com.webside.parkinglot.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.webside.util.ImageCompress;


@Component
public class AsyncImgCompress {
	// 异步压缩图片
	@Async
	public static void asyncImgCompress(String filePath, String savePath) throws IOException, InterruptedException {
		FileOutputStream fos = new FileOutputStream(savePath);
		ImageCompress.thumbnail_w_h(new File(filePath), 0, 250, fos);
	}

}
