package bytecodeAST;

public class ASTReturnNode extends ASTNode{
	
	public ASTReturnNode(){
		super();
		this.ASTKind="ASTReturnNode";
	}
	
	public void setReturnType(String type){
		this.returnType=type;
	}
	public void setReturnValue(ASTNode value){
		this.returnValue=value;
	}
	
	public String getReturnType(){
		return this.returnType;
	}
	public ASTNode getReturnValue(){
		return this.returnValue;
	}

}
