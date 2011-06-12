package pl.jaceklis.esper.spec;

import java.io.Serializable;

public class CallEvent implements Serializable {
	
	private static final long serialVersionUID = -2564853077170579040L;
	
	private Integer customerId;
	
	private Integer orderId;

	public CallEvent(Integer customerId, Integer orderid) {
		super();
		this.customerId = customerId;
		this.orderId = orderid;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "CallEvent [customerId=" + customerId + ", orderId=" + orderId
				+ "]";
	}
	
}
