package pattern.composite;

public class NumericOperand extends ArithmeticExpression {

	private int value;

	public NumericOperand(int value) {
		super();
		this.value = value;
	}

	@Override
	public int getResult() {
		return value;
	}

	public String getFormula() {
		return Integer.toString(value);
	}
	public String getFormulaPrefixNotation(){
		return Integer.toString(value);
	}

}
