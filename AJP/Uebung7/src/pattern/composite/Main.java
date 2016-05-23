package pattern.composite;

public class Main {

	public static void main(String[] args) {
		// TODO: build this expression (((2 + (5 * 3)) + 3) / 5) and print the
		// result
		ArithmeticExpression numOperand1 = new NumericOperand(5);
		ArithmeticExpression numOperand2 = new NumericOperand(3);
		ArithmeticExpression numOperand3 = new NumericOperand(2);
		

		CompositeOperand operand1 = new CompositeOperand(numOperand1, numOperand2, Operator.MULT);
		CompositeOperand operand2 = new CompositeOperand(operand1, numOperand3, Operator.ADD);
		CompositeOperand operand3 = new CompositeOperand(operand2, new NumericOperand(3), Operator.ADD);
		CompositeOperand operand4 = new CompositeOperand(operand3, new NumericOperand(5), Operator.DIV);
		
		System.out.println(operand4.getFormulaWithResult());
		System.out.println(operand4.getFormulaPrefixNotation());
		
	}
}
