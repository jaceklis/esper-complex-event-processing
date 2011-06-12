package pl.jaceklis.esper.spec;

import java.io.Serializable;
import java.math.BigDecimal;

public class WithdrawalEvent implements Serializable {
	
	private static final long serialVersionUID = -2051020032831282473L;

	private Integer operationId;
	
	private Integer accountId;
		
	private BigDecimal amount;
	
	private Integer areaId;

	public WithdrawalEvent(Integer accountId, BigDecimal amount, Integer areaId, Integer operationId) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.areaId = areaId;
		this.operationId = operationId;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public Integer getAreaId() {
		return areaId;
	}
	
	public Integer getOperationId() {
		return operationId;
	}

	@Override
	public String toString() {
		return "WithdrawalEvent [operationId=" + operationId + ", accountId="
				+ accountId + ", amount=" + amount + ", areaId=" + areaId + "]";
	}

}