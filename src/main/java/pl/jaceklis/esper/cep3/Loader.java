package pl.jaceklis.esper.cep3;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Loader {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		Configuration configuration = new Configuration();		
		configuration.configure("esper-config.xml");
		
		EPServiceProvider provider = EPServiceProviderManager.getProvider("sample", configuration);
		String expression = "select B.operationId, A.operationId from pattern [" +
				" every A=pl.jaceklis.esper.spec.WithdrawalEvent -> " +
				"B=pl.jaceklis.esper.spec.WithdrawalEvent(B.accountId = A.accountId, B.amount > 60000, abs(B.areaId - A.areaId) > 3) where timer:within(15 sec)]";
		
		EPStatement statement = provider.getEPAdministrator().createEPL(expression);
		statement.addListener(new FraudListener());
		
		new Thread(new WithdrawalEventCreator(provider)).start();
		
		Thread.sleep(Integer.MAX_VALUE);
	}

}
