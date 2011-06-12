package pl.jaceklis.esper.cep1;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Loader {

	public static void main(String[] args) {
		
		EPServiceProvider provider = EPServiceProviderManager.getDefaultProvider();
		String expression = "select itemName, avg(itemPrice*itemCount) as avgPrice from pl.jaceklis.esper.spec.OrderEvent.win:time(10 sec) group by itemName";
		
		EPStatement statement = provider.getEPAdministrator().createEPL(expression);
		statement.addListener(new OrderEventListener());
		
		new Thread(new OrderEventCreator(provider)).start();

	}

}
