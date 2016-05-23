package pattern.composite;

public class CompositeOperand extends ArithmeticExpression {

	private ArithmeticExpression leftExpression;
	private ArithmeticExpression rightExpression;
	private Operator operator;

	public CompositeOperand(ArithmeticExpression leftExpression,
			ArithmeticExpression rightExpression, Operator operator) {
		super();
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
		this.operator = operator;
	}

	@Override
	public int getResult() {
		if (Operator.ADD.equals(operator)) {
			return leftExpression.getResult() + rightExpression.getResult();
		} else if (Operator.MULT.equals(operator)) {
			return leftExpression.getResult() * rightExpression.getResult();
		} else if (Operator.DIV.equals(operator)) {
			return leftExpression.getResult() / rightExpression.getResult();
		} else if (Operator.SUB.equals(operator)) {
			return leftExpression.getResult() - rightExpression.getResult();
		} else {
			throw new IllegalStateException("no operator given");
		}
	}

	public String getFormula() {
		return "(" + leftExpression.getFormula() + " " + operator.getSymbol()
				+ " " + rightExpression.getFormula() + ")";
	}
	public String getFormulaPrefixNotation(){
		return "(" + operator.getSymbol() + " " + leftExpression.getFormulaPrefixNotation() + " " + rightExpression.getFormulaPrefixNotation() + ")";
	}

}
