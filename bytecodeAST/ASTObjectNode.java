package bytecodeAST;

public class ASTObjectNode extends ASTNode{
	
	public ASTObjectNode(){
		super();
		this.ASTKind="ASTObjectNode";
	}
	
	public void setObjectType(String type){
		this.ObjectType=type;
	}
	public void setObjectName(String name){
		this.name=name;
	}
	
	public String getObjectType(){
		return this.ObjectType;
	}
	public String getObjectName(){
		return this.name;
	}

}
