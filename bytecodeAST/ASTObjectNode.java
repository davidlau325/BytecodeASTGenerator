package bytecodeAST;

import java.util.ArrayList;

public class ASTObjectNode extends ASTNode{
	
	private String ObjectType;
	
	public ASTObjectNode(){
		this.ASTKind="ASTObjectNode";
		this.parameter=new ArrayList<ASTNode>();
	}
	
	public void setObjectType(String type){
		this.ObjectType=type;
	}
	
	public String getObjectType(){
		return this.ObjectType;
	}

}
