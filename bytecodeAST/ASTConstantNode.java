package bytecodeAST;

public class ASTConstantNode extends ASTNode {
	
	private String constantValue;
	
	public ASTConstantNode(){
		this.ASTKind="ASTConstantNode";
	}
	
	public void setConstantValue(String value){
		this.constantValue=value;
	}
	
	public String getConstantValue(){
		return this.constantValue;
	}

}
