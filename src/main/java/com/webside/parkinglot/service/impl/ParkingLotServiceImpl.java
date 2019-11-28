package com.webside.parkinglot.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.parkinglot.mapper.ParkingLotMapper;
import com.webside.parkinglot.model.ParkingLotEntity;
import com.webside.parkinglot.model.ParkingLotPayment;
import com.webside.parkinglot.model.ParkingLotType;
import com.webside.parkinglot.model.ParkingTypeEntity;
import com.webside.parkinglot.model.PaymentWayEntity;
import com.webside.parkinglot.model.SelectFormResult;
import com.webside.parkinglot.service.ParkingLotService;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.util.FileUpload;
import com.webside.util.PageData;
import com.webside.util.PageResult;
@Service
public class ParkingLotServiceImpl implements ParkingLotService{
	@Autowired
	private ParkingLotMapper parkingLotMapper;
	@Override
	public void add(ParkingLotEntity parkingLotEntity,HttpServletRequest request) {
		String parkingLotNumber = getNumber();
		parkingLotEntity.setParkingLotNumber(parkingLotNumber);
		parkingLotEntity.setCreateTime(new Date());
		parkingLotEntity.setUpdateTime(new Date());
		String[] imgs = parkingLotEntity.getImgs();
		String imageUrls = "";
		if(imgs.length > 0) {
			try {
				 List<String> imageUrl= FileUpload.base64Uploads(request, imgs);
				 imageUrls = splicing(imageUrl);
				 parkingLotEntity.setImageUrls(imageUrls);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parkingLotEntity.setImageUrls(imageUrls);
		}else {
			parkingLotEntity.setImageUrls(imageUrls);
		}
		if(parkingLotEntity.getIsChargingPile()==1) {
			parkingLotEntity.setSurplusChargingPileNumber(parkingLotEntity.getTotalChargingPileNumber());
		}
		parkingLotEntity.setSurplusParkNumber(parkingLotEntity.getTotalParkNumber());
		parkingLotMapper.insert(parkingLotEntity);
		String paymentWay = parkingLotEntity.getPaymentWay();
		String[] paymentWays = paymentWay.split(",");
		for (String payment : paymentWays) {
			ParkingLotPayment lotPayment = new ParkingLotPayment();
			lotPayment.setParkingLotId(parkingLotEntity.getId());
			lotPayment.setPaymentTypeId(Integer.parseInt(payment));
			parkingLotMapper.insertParkingLotPayment(lotPayment);
		}
		String parkingLotTypeId = parkingLotEntity.getParkingLotTypeId();
		String[] parkingLotTypeIds = parkingLotTypeId.split(",");
		for (String parkingLotType : parkingLotTypeIds) {
			ParkingTypeEntity parkingTypeEntity = new ParkingTypeEntity();
			parkingTypeEntity.setParkingLotId(parkingLotEntity.getId());
			parkingTypeEntity.setParkingLotTypeId(Integer.parseInt(parkingLotType));
			parkingLotMapper.insertParkingType(parkingTypeEntity);
		}
	}
	
	@Override
	public ParkingLotEntity findByName(String name) {
		return parkingLotMapper.findByName(name);
	}
	
	public String getNumber() {
		Random random = new Random();
	    DecimalFormat df = new DecimalFormat("00");
	    String no = new SimpleDateFormat("yyyyMMddHHmmss")
	                .format(new Date()) + df.format(random.nextInt(100));
	    return no;
	}
	public String splicing(List<String> files) {
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> iterator = files.iterator(); iterator.hasNext();) {
			String fileUrls = (String) iterator.next();
			System.out.println("fileUrls:" + fileUrls);
			sb.append(fileUrls + ",");
		}
		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	@Override
	public PageResult<List<ParkingLotEntity>> findPage(PageData pd) {
		List<ParkingLotEntity> subset = parkingLotMapper.findPageSubset(pd);
		Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()), Integer.parseInt(pd.get("limit").toString()));
		List<ParkingLotEntity> list = parkingLotMapper.findPage(pd);
		for (ParkingLotEntity parkingLotEntity : list) {
			for (ParkingLotEntity parkingLotEntity2 : subset) {
				if(parkingLotEntity.getId() == parkingLotEntity2.getId()) {
					parkingLotEntity.setPaymentWayEntitys(parkingLotEntity2.getPaymentWayEntitys());
					parkingLotEntity.setParkingLotTypes(parkingLotEntity2.getParkingLotTypes());
					break;
				}
			}		
		}
		return new PageResult<List<ParkingLotEntity>>("", list, 0, (int) page.getTotal());
	}

	@Override
	public ParkingLotEntity findById(Integer id) {
		return parkingLotMapper.findById(id);
	}

	@Override
	public void update(ParkingLotEntity parkingLotEntity,HttpServletRequest request) {
		parkingLotMapper.deleteParkingLotPayment(parkingLotEntity.getId());
		parkingLotMapper.deleteParkingType(parkingLotEntity.getId());
		String paymentWay = parkingLotEntity.getPaymentWay();
		String[] paymentWays = paymentWay.split(",");
		for (String payment : paymentWays) {
			ParkingLotPayment lotPayment = new ParkingLotPayment();
			lotPayment.setParkingLotId(parkingLotEntity.getId());
			lotPayment.setPaymentTypeId(Integer.parseInt(payment));
			parkingLotMapper.insertParkingLotPayment(lotPayment);
		}
		String parkingLotTypeId = parkingLotEntity.getParkingLotTypeId();
		String[] parkingLotTypeIds = parkingLotTypeId.split(",");
		for (String parkingLotType : parkingLotTypeIds) {
			ParkingTypeEntity parkingTypeEntity = new ParkingTypeEntity();
			parkingTypeEntity.setParkingLotId(parkingLotEntity.getId());
			parkingTypeEntity.setParkingLotTypeId(Integer.parseInt(parkingLotType));
			parkingLotMapper.insertParkingType(parkingTypeEntity);
		}
		String[] imgs = parkingLotEntity.getImgs();
		String imageUrls = "";
		if(imgs.length > 0) {
			for (String img : imgs) {
				if(img.startsWith("http")) {
					imageUrls += img + ",";
				}else {
					try {
						String url = FileUpload.base64Upload(request, img);
						imageUrls += url + ",";
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			imageUrls = imageUrls.substring(0, imageUrls.length()-1);
		}
		parkingLotEntity.setImageUrls(imageUrls);
		parkingLotEntity.setUpdateTime(new Date());
		if(parkingLotEntity.getIsChargingPile()==2) {
			parkingLotEntity.setTotalChargingPileNumber(0);
			parkingLotEntity.setSurplusChargingPileNumber(0);
		}
		parkingLotMapper.update(parkingLotEntity);
	}

	@Override
	public void delete(int[] ids) {
		parkingLotMapper.deleteParkingLotPaymentBatch(ids);
		parkingLotMapper.deleteParkingTypeBatch(ids);
		parkingLotMapper.deleteParking(ids);
	}

	@Override
	public List<ParkingLotEntity> findAll() {
		return parkingLotMapper.findPage(new PageData());
	}

	@Override
	public List<PaymentWayEntity> findAllPaymentWay() {
		return parkingLotMapper.findAllPaymentWay();
	}

	@Override
	public List<ParkingLotType> findAllParkingLotType() {
		return parkingLotMapper.findAllParkingLotType();
	}

	@Override
	public List<SelectFormResult> findHavingPaymentWay(Integer id) {
		List<PaymentWayEntity> listAll = parkingLotMapper.findAllPaymentWay();
		ParkingLotEntity entity = parkingLotMapper.findById(id);
		List<PaymentWayEntity> list = entity.getPaymentWayEntitys();
		ArrayList<SelectFormResult> selectFormResultS = new ArrayList<SelectFormResult>();
		for (PaymentWayEntity paymentWayEntity : listAll) {
			SelectFormResult formResult = new SelectFormResult();
			formResult.setName(paymentWayEntity.getPaymentName());
			formResult.setDisabled("");
			formResult.setValue(paymentWayEntity.getId());
			boolean isChoice = false;
			for (PaymentWayEntity selectFormResult : list) {
				if(paymentWayEntity.getId() == selectFormResult.getId()) {
					isChoice = true;
				}
			}
			if(isChoice) {
				formResult.setSelected("selected");
			}else {
				formResult.setSelected("");
			}
			selectFormResultS.add(formResult);
		}
		return selectFormResultS;
	}

	@Override
	public List<SelectFormResult> findHavingParkingLotType(Integer id) {
		List<ParkingLotType> listAll = parkingLotMapper.findAllParkingLotType();
		ParkingLotEntity entity = parkingLotMapper.findById(id);
		List<ParkingLotType> list = entity.getParkingLotTypes();
		ArrayList<SelectFormResult> selectFormResultS = new ArrayList<SelectFormResult>();
		for (ParkingLotType parkingLotType : listAll) {
			SelectFormResult formResult = new SelectFormResult();
			formResult.setName(parkingLotType.getType());
			formResult.setDisabled("");
			formResult.setValue(parkingLotType.getId());
			boolean isChoice = false;
			for (ParkingLotType parkingLotType2 : list) {
				if(parkingLotType.getId() == parkingLotType2.getId()) {
					isChoice = true;
				}
			}
			if(isChoice) {
				formResult.setSelected("selected");
			}else {
				formResult.setSelected("");
			}
			selectFormResultS.add(formResult);
		}
		return selectFormResultS;
	}

}
