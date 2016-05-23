package pattern.composite;

enum Operator {
	ADD("+"), MULT("*"), DIV("/"), SUB("-");

	private final String symbol;

	private Operator(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

}