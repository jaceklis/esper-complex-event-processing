package pl.jaceklis.esper.spec;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderEvent implements Serializable {
	
	private static final long serialVersionUID = 5783006901821417550L;
	
	private Integer orderId;

	private String itemName;
	
	private BigDecimal itemPrice;
	
	private Integer itemCount;
	
	private Integer customerId;

	public OrderEvent(Integer orderId, String itemName, BigDecimal itemPrice, Integer itemCount, Integer customerId) {
		super();
		this.orderId = orderId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemCount = itemCount;
		this.customerId = customerId;
	}

	public String getItemName() {
		return itemName;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public Integer getItemCount() {
		return itemCount;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "OrderEvent [orderId=" + orderId + ", itemName=" + itemName
				+ ", itemPrice=" + itemPrice + ", itemCount=" + itemCount
				+ ", customerId=" + customerId + "]";
	}

}
