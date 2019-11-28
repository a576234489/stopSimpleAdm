package com.webside.parkinglot.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.parkinglot.mapper.ParkingLotMapper;
import com.webside.parkinglot.mapper.ParkingLotServerMapper;
import com.webside.parkinglot.model.ParkingLotServerEntity;
import com.webside.parkinglot.service.ParkingLotServerService;
import com.webside.util.FileUpload;
import com.webside.util.PageData;
import com.webside.util.PageResult;

@Service
public class ParkingLotServerServiceImpl implements ParkingLotServerService{
	@Autowired
	private ParkingLotServerMapper parkingLotServerMapper;
	@Override
	public PageResult<List<ParkingLotServerEntity>> findPage(PageData pd) {
		Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()), Integer.parseInt(pd.get("limit").toString()));
		List<ParkingLotServerEntity> list = parkingLotServerMapper.findPage(pd);
		return new PageResult<List<ParkingLotServerEntity>>("", list, 0, (int) page.getTotal());
	}

	@Override
	public ParkingLotServerEntity findById(Integer id) {
		return parkingLotServerMapper.findById(id);
	}

	@Override
	public void update(ParkingLotServerEntity parkingLotServerEntity,HttpServletRequest request) {
		String[] imgs = parkingLotServerEntity.getImgs();
		String imgUrl = "";
		if(imgs.length > 0) {
			if(imgs[0].startsWith("http")) {
				imgUrl = imgs[0] ;
			}else {
				try {
					String url = FileUpload.base64Upload(request, imgs[0]);
					imgUrl = url ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		parkingLotServerEntity.setImgUrl(imgUrl);
		parkingLotServerEntity.setUpdateTime(new Date());
		parkingLotServerMapper.update(parkingLotServerEntity);
	}

	@Override
	public void add(ParkingLotServerEntity parkingLotServerEntity,HttpServletRequest request) {
		String[] imgs = parkingLotServerEntity.getImgs();
		if(imgs.length > 0) {
			try {
				List<String> Url = FileUpload.base64Uploads(request, imgs);
				parkingLotServerEntity.setImgUrl(Url.get(0));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			parkingLotServerEntity.setImgUrl("");
		}
		parkingLotServerEntity.setCreateTime(new Date());
		parkingLotServerEntity.setUpdateTime(new Date());
		parkingLotServerMapper.insert(parkingLotServerEntity);
	}

	@Override
	public void delete(int[] ids) {
		parkingLotServerMapper.delete(ids);
	}
	
}
