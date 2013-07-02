package bytecodeAST;

import java.util.ArrayList;

public class ASTMethodNode extends ASTNode {
	
	public ASTMethodNode(){
		super();
		this.ASTKind="ASTMethodNode";
	}
	
	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}
}
