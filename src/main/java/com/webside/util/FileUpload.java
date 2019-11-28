package com.webside.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.webside.parkinglot.controller.AsyncImgCompress;

import sun.misc.BASE64Decoder;

@Component
public class FileUpload {
	public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Logger logger = LoggerFactory.getLogger(FileUpload.class);

	// 上传 不生成压缩文件
	public static List<String> fileArrayUpload(HttpServletRequest request, MultipartFile[] mfile) throws IOException {
		List<String> saveNames = new ArrayList<String>();

		if (mfile != null && mfile.length > 0) {
			logger.info("upload start");
			for (int i = 0; i < mfile.length; i++) {
				String saveName = fileSingleUpload(request, mfile[i]);
				saveNames.add(saveName);
			}
			logger.info("upload succ");
		} else {
			logger.info("上传文件为空 上传失败");
		}
		return saveNames;
	}

	public static List<String> fileListUpload(HttpServletRequest request, List<MultipartFile> mfile)
			throws IOException {
		List<String> saveNames = new ArrayList<String>();

		if (mfile != null && mfile.size() > 0) {
			logger.info("upload start");
			for (int i = 0; i < mfile.size(); i++) {
				String saveName = fileSingleUpload(request, mfile.get(i));
				saveNames.add(saveName);
			}
			logger.info("upload succ");
		} else {
			logger.info("上传文件为空 上传失败");
		}
		return saveNames;
	}

	// 文件上传并生成压缩文件
	public static Map<String, Object> fileUploadAndCreatCompress(HttpServletRequest request, List<MultipartFile> mfile)
			throws IOException {
		List<String> saveNames = new ArrayList<String>();
		SimpleDateFormat foramt = new SimpleDateFormat("yyyy-MM-dd");
		String dateForamt = "/" + foramt.format(new Date());
		Map<String, Object> map = new HashMap<>();
		if (mfile != null && mfile.size() > 0) {
			logger.info("upload start");
			// 文件夹路径
			String savePath = request.getSession().getServletContext().getRealPath("/").replace("fire", "fire_upload")
					+ dateForamt + "/";
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			System.out.println("savePath:" + savePath);
			File file = new File(savePath);
			// 路径不存在就新建
			if (!file.exists()) {
				file.mkdirs();
			}
			for (int i = 0; i < mfile.size(); i++) {
				// 如果上传文件不为空
				if (mfile.get(i).getSize() > 0) {
					// 文件名称取当前时间，避免重复
					String now = System.currentTimeMillis() + generateUpperLowerString();
					try {
						String relName = mfile.get(i).getOriginalFilename();
						// 文件后缀
						String fileExt = relName.substring(relName.lastIndexOf("."), relName.length());
						// 文件保存路径
						String filePath = savePath + now + fileExt;
						mfile.get(i).transferTo(new File(filePath));
						saveNames.add(url + "/fire_upload/" + dateForamt + "/" + now + fileExt);
						// 第一张图生成缩略图
						if (i == 0) {
							String fileSmall = filePath.replace(now + "", now + "small");
							// AsyncImgCompress.asyncImgCompress(filePath, fileSmall);
							AsyncImgCompress.asyncImgCompress(filePath, fileSmall);

							map.put("small", url + "/fire_upload/" + dateForamt + "/" + now + "small" + fileExt);
						}
					} catch (Exception e) {
						logger.warn("upload err " + e);
						e.printStackTrace();
					}
				}
			}
			logger.info("upload succ");
		} else {
			logger.info("上传文件为空 上传失败");
		}
		map.put("saveNames", saveNames);
		return map;
	}

	// 上传 单个图片
	public static String fileSingleUpload(HttpServletRequest request, MultipartFile mfile) throws IOException {
		SimpleDateFormat foramt = new SimpleDateFormat("yyyy-MM-dd");
		String dateForamt = "/" + foramt.format(new Date());
		String saveName = "";
		if (mfile != null) {
			logger.info("upload start");
			// 文件夹路径
			String savePath = request.getSession().getServletContext().getRealPath("/").replace("fire", "fire_upload")
					+ dateForamt + "/";
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			System.out.println("savePath:" + savePath);
			File file = new File(savePath);
			// 路径不存在就新建
			if (!file.exists()) {
				file.mkdirs();
			}
			// 文件名称取当前时间，避免重复
			String now = System.currentTimeMillis() + generateUpperLowerString();
			try {
				String relName = mfile.getOriginalFilename();
				// 文件后缀
				String fileExt = relName.substring(relName.lastIndexOf("."), relName.length());
				// 文件保存路径
				String filePath = savePath + now + fileExt;

				mfile.transferTo(new File(filePath));
				saveName = url + "/fire_upload/" + dateForamt + "/" + now + fileExt;

			} catch (Exception e) {
				logger.warn("upload err " + e);
				e.printStackTrace();
			}

			logger.info("upload succ");
		} else {
			logger.info("上传文件为空 上传失败");
		}
		return saveName;
	}

	// 上传 单个图片
	public static String base64Upload(HttpServletRequest request, String mfile) throws IOException {
		SimpleDateFormat foramt = new SimpleDateFormat("yyyy-MM-dd");
		String dateForamt = "/" + foramt.format(new Date());
		String saveName = "";
		if (mfile != null) {
			logger.info("upload start");
			// 文件夹路径
			String savePath = request.getSession().getServletContext().getRealPath("/").replace("stopSimpleAdm", "stopSimpleAdm_upload")
					+ dateForamt + "/";
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			System.out.println("savePath:" + savePath);
			File file = new File(savePath);
			// 路径不存在就新建
			if (!file.exists()) {
				file.mkdirs();
			}
			// 文件名称取当前时间，避免重复
			String now = System.currentTimeMillis() + generateUpperLowerString();
			try {
				String[] mfiles = mfile.split(";base64");
				// 文件后缀
				String fileExt = mfiles[0].substring(12);
				BASE64Decoder decoder = new BASE64Decoder();
				String baseValue = mfile.replaceAll(" ", "+");
				byte[] b = decoder.decodeBuffer(baseValue.replace(mfiles[0] + ";base64,", ""));
				// Base64解码
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
				// 文件保存路径
				String filePath = savePath + now +"."+ fileExt;
				OutputStream out = new FileOutputStream(filePath);
				out.write(b);
				out.flush();
				out.close();
				saveName = url + "/stopSimpleAdm_upload/" + dateForamt + "/" + now +"." + fileExt;
			} catch (Exception e) {
				logger.warn("upload err " + e);
				e.printStackTrace();
			}

			logger.info("upload succ");
		} else {
			logger.info("上传文件为空 上传失败");
		}
		return saveName;
	}

	public static List<String> base64Uploads(HttpServletRequest request, String[] mfiles) throws IOException {
		List<String> saveNames = new ArrayList<String>();
		SimpleDateFormat foramt = new SimpleDateFormat("yyyy-MM-dd");
		String dateForamt = "/" + foramt.format(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		if (mfiles.length != 0) {
			logger.info("upload start");
			// 文件夹路径
			String savePath = request.getSession().getServletContext().getRealPath("/").replace("stopSimpleAdm", "stopSimpleAdm_upload")
					+ dateForamt + "/";
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			System.out.println("savePath:" + savePath);
			File file = new File(savePath);
			// 路径不存在就新建
			if (!file.exists()) {
				file.mkdirs();
			}
			// 文件名称取当前时间，避免重复
			try {
				BASE64Decoder decoder = new BASE64Decoder();

				for (int i = 0; i < mfiles.length; ++i) {
					String now = System.currentTimeMillis() + generateUpperLowerString();
					
					String[] mfiles2 = mfiles[i].split(";base64");
					// 文件后缀
					String fileExt = mfiles2[0].substring(11);
					String baseValue = mfiles[i].replaceAll(" ", "+");
					byte[] b = decoder.decodeBuffer(baseValue.replace(mfiles2[0] + ";base64,", ""));
					// Base64解码
					for (int j = 0; j < b.length; ++j) {
						if (b[j] < 0) {// 调整异常数据
							b[j] += 256;
						}
					}
					// 文件保存路径
					String filePath = savePath + now + "." + fileExt;
					OutputStream out = new FileOutputStream(filePath);
					out.write(b);
					out.flush();
					out.close();
					saveNames.add(url + "/stopSimpleAdm_upload/" + dateForamt + "/" + now + "." + fileExt);
				}
			} catch (Exception e) {
				logger.warn("upload err " + e);
				e.printStackTrace();
			}

			logger.info("upload succ");
		} else {
			logger.info("上传文件为空 上传失败");
		}
		return saveNames;
	}
	
	public static Map<String, Object> applyBase64Uploads(HttpServletRequest request, String[] mfiles) throws IOException {
		List<String> saveNames = new ArrayList<String>();
		List<String> smalls = new ArrayList<String>();
		SimpleDateFormat foramt = new SimpleDateFormat("yyyy-MM-dd");
		String dateForamt = "/" + foramt.format(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		if (mfiles.length != 0) {
			logger.info("upload start");
			// 文件夹路径
			String savePath = request.getSession().getServletContext().getRealPath("/").replace("fire", "fire_upload")
					+ dateForamt + "/";
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			System.out.println("savePath:" + savePath);
			File file = new File(savePath);
			// 路径不存在就新建
			if (!file.exists()) {
				file.mkdirs();
			}
			// 文件名称取当前时间，避免重复
			try {
				BASE64Decoder decoder = new BASE64Decoder();

				for (int i = 0; i < mfiles.length; ++i) {
					String now = System.currentTimeMillis() + generateUpperLowerString();
					
					String[] mfiles2 = mfiles[i].split(";base64");
					// 文件后缀
					String fileExt = mfiles2[0].substring(11);
					String baseValue = mfiles[i].replaceAll(" ", "+");
					byte[] b = decoder.decodeBuffer(baseValue.replace(mfiles2[0] + ";base64,", ""));
					// Base64解码
					for (int j = 0; j < b.length; ++j) {
						if (b[j] < 0) {// 调整异常数据
							b[j] += 256;
						}
					}
					// 文件保存路径
					String filePath = savePath + now + "." + fileExt;
					OutputStream out = new FileOutputStream(filePath);
					out.write(b);
					out.flush();
					out.close();
					String fileSmall = filePath.replace(now + "", now + "small");
					AsyncImgCompress.asyncImgCompress(filePath, fileSmall);
					smalls.add(url + "/fire_upload/" + dateForamt + "/" + now + "small" + "." +  fileExt);
				}
			} catch (Exception e) {
				logger.warn("upload err " + e);
				e.printStackTrace();
			}

			logger.info("upload succ");
		} else {
			logger.info("上传文件为空 上传失败");
		}
		map.put("saveNames", saveNames);
		map.put("smalls", smalls);
		return map;
	}

	public static boolean isImg(String fileName) {
		boolean flag = false;
		String fileExt = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		if (fileExt.equals(".jpg") || fileExt.equals(".png") || fileExt.equals(".gif") || fileExt.equals(".jpeg")
				|| fileExt.equals(".bmp")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 *
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperLowerString() {
		int length = 5;
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}

}
