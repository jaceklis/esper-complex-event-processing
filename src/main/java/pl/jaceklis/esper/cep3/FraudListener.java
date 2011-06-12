package pl.jaceklis.esper.cep3;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class FraudListener implements UpdateListener {

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		EventBean eventBean = newEvents[0];
		System.err.println("Podejrzana operacje: " + eventBean.get("B.operationId") + " i " + eventBean.get("A.operationId"));

	}

}
