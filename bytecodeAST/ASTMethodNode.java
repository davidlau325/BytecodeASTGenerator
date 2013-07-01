package bytecodeAST;

import java.util.ArrayList;

public class ASTMethodNode extends ASTNode {
	
	private String name;
	
	public ASTMethodNode(){
		this.ASTKind="ASTMethodNode";
		this.parameter=new ArrayList<ASTNode>();
	}
	
	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}
}
