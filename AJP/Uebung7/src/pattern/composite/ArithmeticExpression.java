package pattern.composite;

public abstract class ArithmeticExpression {

	abstract public int getResult();

	abstract public String getFormula();
	
	abstract public String getFormulaPrefixNotation();

	public String getFormulaWithResult() {
		return getFormula() + " = " + getResult();
	}

}
