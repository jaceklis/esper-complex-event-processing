package pl.jaceklis.esper.cep1;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class OrderEventListener implements UpdateListener {

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		EventBean event = newEvents[0];
        System.err.println(event.get("itemName") + ", average price: " + event.get("avgPrice"));
	}

}
