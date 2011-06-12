package pl.jaceklis.esper.cep2;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Loader {

	public static void main(String[] args) throws Exception {
		
		EPServiceProvider provider = EPServiceProviderManager.getDefaultProvider();
		String expression = "select A.customerId, A.orderId from pattern [" +
				" every A=pl.jaceklis.esper.spec.OrderEvent -> (timer:interval(1 min) and not " +
							"B=pl.jaceklis.esper.spec.CallEvent(B.customerId = A.customerId, B.orderId = A.orderId))].win:time(1 hour) ";
		
		EPStatement statement = provider.getEPAdministrator().createEPL(expression);
		statement.addListener(new MissingCallListener());
		
		new Thread(new OrderEventCreator(provider)).start();
		
		Thread.sleep(Integer.MAX_VALUE);
	}

}
