package telran.numbers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

public class Calculator {
	@SuppressWarnings("serial")
	private static Map<Character, DoubleBinaryOperator> ops = new HashMap<>() {{
		put('*', (e1, e2) -> e1 * e2);
		put('/', (e1, e2) -> e1 / e2);
		put('-', (e1, e2) -> e1 - e2);
		put('+', (e1, e2) -> e1 + e2);
	}};

	static public double calculate(CalcData cd) {
		
		return ops.get(cd.getOperation()).applyAsDouble(cd.getOp1(), cd.getOp2());
	}

}
