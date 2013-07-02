package bytecodeAST;

public class ASTConstantNode extends ASTNode {
	
	public ASTConstantNode(){
		super();
		this.ASTKind="ASTConstantNode";
	}
	
	public void setConstantValue(String value){
		this.constantValue=value;
	}
	
	public String getConstantValue(){
		return this.constantValue;
	}

}
