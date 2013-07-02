package bytecodeAST;

public class ASTArithmeticNode extends ASTNode {
	
	public ASTArithmeticNode(){
		super();
		this.ASTKind="ASTArithmeticNode";
	}
	
	public void setArithmeticOperator(String type){
		this.arithmeticOperator=type;
	}
	public void setArithmeticType(String type){
		this.arithmeticType=type;
	}
	public void setFirstOperand(ASTNode first){
		this.firstOperand=first;
	}
	public void setSecondOperant(ASTNode second){
		this.secondOperand=second;
	}
	
	public String getArithmeticOperator(){
		return this.arithmeticOperator;
	}
	public String getArithmeticType(){
		return this.arithmeticType;
	}
	public ASTNode getFirstOperand(){
		return this.firstOperand;
	}
	public ASTNode getSecondOperand(){
		return this.secondOperand;
	}

}
