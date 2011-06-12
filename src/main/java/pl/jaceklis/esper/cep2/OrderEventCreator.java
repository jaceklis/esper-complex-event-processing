package pl.jaceklis.esper.cep2;

import java.math.BigDecimal;
import java.util.Random;

import pl.jaceklis.esper.spec.CallEvent;
import pl.jaceklis.esper.spec.EventCreator;
import pl.jaceklis.esper.spec.OrderEvent;

import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;

public class OrderEventCreator implements EventCreator<OrderEvent>, Runnable {
	
	private EPRuntime runtime;
	
	public OrderEventCreator(EPServiceProvider provider) {
		runtime = provider.getEPRuntime();
	}

	String items[] = {"BOOK", "CAR", "BIKE"};
	BigDecimal prices[] = {new BigDecimal(50), new BigDecimal(10000), new BigDecimal(1000)};
	Integer[] customers = { 5, 4, 3};
	Integer[] counts = {2, 1, 5};

	@Override
	public OrderEvent createEvent() {
		Random r = new Random();
		int id = r.nextInt(100);
		int item = r.nextInt(items.length);
		int price = r.nextInt(prices.length);
		int customer = r.nextInt(customers.length);
		int count = r.nextInt(counts.length);
		OrderEvent orderEvent = new OrderEvent(id, items[item], prices[price], counts[count], customers[customer]);
		return orderEvent;
	}

	@Override
	public void run() {
		int i = 0;
		while (i++ <= 9) {
			OrderEvent orderEvent = createEvent();
			System.out.println("Wys³ano zdarzenie: " + orderEvent);
			runtime.sendEvent(orderEvent);
			createCallEvent(orderEvent);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void createCallEvent(OrderEvent orderEvent) {
		if (shouldSendCallEvent(orderEvent)) {
			CallEvent callEvent = new CallEvent(orderEvent.getCustomerId(), orderEvent.getOrderId());
			System.out.println("Wys³ano zdarzenie: " + callEvent);
			runtime.sendEvent(callEvent);
		}
	}

	private boolean shouldSendCallEvent(OrderEvent orderEvent) {
		return orderEvent.getItemCount() % 2 == 0;
	}

}
