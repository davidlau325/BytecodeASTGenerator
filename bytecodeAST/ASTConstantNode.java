package bytecodeAST;

public class ASTConstantNode extends ASTNode {
	
	public ASTConstantNode(){
		super();
		this.ASTKind="ASTConstantNode";
	}
	
	public void setConstantValue(String value){
		this.constantValue=value;
	}
	public void setConstantType(String type){
		this.constantType=type;
	}
	
	public String getConstantValue(){
		return this.constantValue;
	}
	public String getConstantType(){
		return this.constantType;
	}

}
