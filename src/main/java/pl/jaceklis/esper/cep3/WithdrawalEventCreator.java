package pl.jaceklis.esper.cep3;

import java.math.BigDecimal;
import java.util.Random;

import pl.jaceklis.esper.spec.EventCreator;
import pl.jaceklis.esper.spec.WithdrawalEvent;

import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;

public class WithdrawalEventCreator implements EventCreator<WithdrawalEvent>,
		Runnable {
	
	private EPRuntime runtime;
	
	public WithdrawalEventCreator(EPServiceProvider provider) {
		this.runtime = provider.getEPRuntime();
	}

	Integer accounts[] = { 102, 105, 107, 108, 110, 202, 203, 204, 205, 206 };

	Integer areas[] = { 99, 98, 97, 96, 95, 94, 93, 92, 91, 90 };

	BigDecimal amounts[] = { new BigDecimal(1000000), new BigDecimal(50000),
			BigDecimal.ONE, new BigDecimal(9999), new BigDecimal(99.00), new BigDecimal(65000), new BigDecimal(88000) };

	@Override
	public void run() {
		while (true) {
			WithdrawalEvent withdrawalEvent = createEvent();
			System.out.println("Wys³ano zdarzenie: " + withdrawalEvent);
			runtime.sendEvent(withdrawalEvent);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public WithdrawalEvent createEvent() {
		Random r = new Random();
		int account = r.nextInt(accounts.length);
		int area = r.nextInt(areas.length);
		int amount = r.nextInt(amounts.length);
		int operationId = r.nextInt(1000);
		WithdrawalEvent withdrawalEvent = new WithdrawalEvent(accounts[account], amounts[amount], areas[area], operationId);
		return withdrawalEvent;
	}

}
