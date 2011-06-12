package pl.jaceklis.esper.cep2;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class MissingCallListener implements UpdateListener {

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		EventBean event = newEvents[0];
		System.err.println("Nie zadzwoni� klient: " + event.get("A.customerId") + ", zam�wienie: " + event.get("A.orderId"));
	}

}
