package bytecodeAST;

public class ASTArrayNode extends ASTNode {
	
	public ASTArrayNode(){
		super();
		this.ASTKind="ASTArrayNode";
	}
	
	public void setArrayType(String type){
		this.arrayType=type;
	}
	public void setArraySize(ASTNode size){
		this.arraySize=size;
	}
	
	public String getArrayType(){
		return this.arrayType;
	}
	public ASTNode getArraySize(){
		return this.arraySize;
	}

}
