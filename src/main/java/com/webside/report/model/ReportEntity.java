package com.webside.report.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("reportEntity")
@ApiModel(value="报表查询模型")
public class ReportEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="预约id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="停车账单总数" ,required=false)
	private int totalBill;
	@ApiModelProperty(value="总应收" ,required=false)
	private double totalReceivables;
	@ApiModelProperty(value="总优惠" ,required=false)
	private double totalfavourable;
	@ApiModelProperty(value="总实收" ,required=false)
	private double totalAmount;
	@ApiModelProperty(value="总抵扣" ,required=false)
	private double totalDeductionAmount;
	@ApiModelProperty(value="总商家减免" ,required=false)
	private double totalBusinessAmount;
	@ApiModelProperty(value="停车场名字" ,required=false)
	private String name;
	@ApiModelProperty(value="停车场编号" ,required=false)
	private String parkingLotNumber;
	@ApiModelProperty(value="查询时间" ,required=false)
	private String time;
	@ApiModelProperty(value="现金总额" ,required=false)
	private double totalCashAmount;
	@ApiModelProperty(value="非现金总额" ,required=false)
	private double totalUnCashAmount;
	@ApiModelProperty(value="长租卡数量" ,required=false)
	private int cardQantity;
	@ApiModelProperty(value="长租卡总额" ,required=false)
	private double tatolCardMoney;
	@ApiModelProperty(value="是否只有办卡记录" ,required=false)
	private boolean onlyCard;
	@ApiModelProperty(value="临停账单汇总" ,required=false)
	private double totalBillAll;
	@ApiModelProperty(value="总实收汇总" ,required=false)
	private double totalAmountAll;
	@ApiModelProperty(value="总抵扣汇总" ,required=false)
	private double totalDeductionAmountAll;
	@ApiModelProperty(value="总商家优惠汇总" ,required=false)
	private double totalBusinessAmountAll;
	@ApiModelProperty(value="总现金收款汇总" ,required=false)
	private double totalCashAmountAll;
	@ApiModelProperty(value="总办卡记录汇总" ,required=false)
	private double cardQantityAll;
	@ApiModelProperty(value="总办卡收费汇总" ,required=false)
	private double tatolCardMoneyAll;
	@ApiModelProperty(value="总应收汇总" ,required=false)
	private double totalReceivablesAll;
	@ApiModelProperty(value="总优惠汇总" ,required=false)
	private double totalfavourableAll;
	@ApiModelProperty(value="非现金总额汇总" ,required=false)
	private double totalUnCashAmountAll;
	
	public double getTotalReceivablesAll() {
		return totalReceivablesAll;
	}
	public void setTotalReceivablesAll(double totalReceivablesAll) {
		this.totalReceivablesAll = totalReceivablesAll;
	}
	public double getTotalfavourableAll() {
		return totalfavourableAll;
	}
	public void setTotalfavourableAll(double totalfavourableAll) {
		this.totalfavourableAll = totalfavourableAll;
	}
	public double getTotalUnCashAmountAll() {
		return totalUnCashAmountAll;
	}
	public void setTotalUnCashAmountAll(double totalUnCashAmountAll) {
		this.totalUnCashAmountAll = totalUnCashAmountAll;
	}
	public double getTotalBillAll() {
		return totalBillAll;
	}
	public void setTotalBillAll(double totalBillAll) {
		this.totalBillAll = totalBillAll;
	}
	public double getTotalAmountAll() {
		return totalAmountAll;
	}
	public void setTotalAmountAll(double totalAmountAll) {
		this.totalAmountAll = totalAmountAll;
	}
	public double getTotalDeductionAmountAll() {
		return totalDeductionAmountAll;
	}
	public void setTotalDeductionAmountAll(double totalDeductionAmountAll) {
		this.totalDeductionAmountAll = totalDeductionAmountAll;
	}
	public double getTotalBusinessAmountAll() {
		return totalBusinessAmountAll;
	}
	public void setTotalBusinessAmountAll(double totalBusinessAmountAll) {
		this.totalBusinessAmountAll = totalBusinessAmountAll;
	}
	public double getTotalCashAmountAll() {
		return totalCashAmountAll;
	}
	public void setTotalCashAmountAll(double totalCashAmountAll) {
		this.totalCashAmountAll = totalCashAmountAll;
	}
	public double getCardQantityAll() {
		return cardQantityAll;
	}
	public void setCardQantityAll(double cardQantityAll) {
		this.cardQantityAll = cardQantityAll;
	}
	public double getTatolCardMoneyAll() {
		return tatolCardMoneyAll;
	}
	public void setTatolCardMoneyAll(double tatolCardMoneyAll) {
		this.tatolCardMoneyAll = tatolCardMoneyAll;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(int totalBill) {
		this.totalBill = totalBill;
	}
	public double getTotalReceivables() {
		return totalReceivables;
	}
	public void setTotalReceivables(double totalReceivables) {
		this.totalReceivables = totalReceivables;
	}
	public double getTotalfavourable() {
		return totalfavourable;
	}
	public void setTotalfavourable(double totalfavourable) {
		this.totalfavourable = totalfavourable;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTotalDeductionAmount() {
		return totalDeductionAmount;
	}
	public void setTotalDeductionAmount(double totalDeductionAmount) {
		this.totalDeductionAmount = totalDeductionAmount;
	}
	public double getTotalBusinessAmount() {
		return totalBusinessAmount;
	}
	public void setTotalBusinessAmount(double totalBusinessAmount) {
		this.totalBusinessAmount = totalBusinessAmount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParkingLotNumber() {
		return parkingLotNumber;
	}
	public void setParkingLotNumber(String parkingLotNumber) {
		this.parkingLotNumber = parkingLotNumber;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getTotalCashAmount() {
		return totalCashAmount;
	}
	public void setTotalCashAmount(double totalCashAmount) {
		this.totalCashAmount = totalCashAmount;
	}
	public double getTotalUnCashAmount() {
		return totalUnCashAmount;
	}
	public void setTotalUnCashAmount(double totalUnCashAmount) {
		this.totalUnCashAmount = totalUnCashAmount;
	}
	public int getCardQantity() {
		return cardQantity;
	}
	public void setCardQantity(int cardQantity) {
		this.cardQantity = cardQantity;
	}
	public double getTatolCardMoney() {
		return tatolCardMoney;
	}
	public void setTatolCardMoney(double tatolCardMoney) {
		this.tatolCardMoney = tatolCardMoney;
	}
	public boolean isOnlyCard() {
		return onlyCard;
	}
	public void setOnlyCard(boolean onlyCard) {
		this.onlyCard = onlyCard;
	}
	
	
	
	
}
