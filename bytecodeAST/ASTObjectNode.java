package bytecodeAST;

public class ASTObjectNode extends ASTNode{
	
	public ASTObjectNode(){
		super();
		this.ASTKind="ASTObjectNode";
	}
	
	public void setObjectType(String type){
		this.ObjectType=type;
	}
	
	public String getObjectType(){
		return this.ObjectType;
	}

}
