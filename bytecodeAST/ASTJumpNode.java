package bytecodeAST;

public class ASTJumpNode extends ASTNode{
	
	public ASTJumpNode(){
		super();
		this.ASTKind="ASTJumpNode";
	}
	
	public void setCompare(String type){
		this.compare=type;
	}
	public void setTrueLabel(String label){
		this.trueLabel=label;
	}
	public void setFirstOperand(ASTNode first){
		this.firstOperand=first;
	}
	public void setSecondOperand(ASTNode second){
		this.secondOperand=second;
	}
	public void setTrueLabelConnect(ASTNode connect){
		this.trueLabelConnect=connect;
	}
	
	public String getCompare(){
		return this.compare;
	}
	public String getTrueLabel(){
		return this.trueLabel;
	}
	public ASTNode getFirstOperand(){
		return this.firstOperand;
	}
	public ASTNode getSecondOperand(){
		return this.secondOperand;
	}
	public ASTNode getTrueLabelConnect(){
		return this.trueLabelConnect;
	}

}
