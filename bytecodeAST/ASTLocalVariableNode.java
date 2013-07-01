package bytecodeAST;

import java.util.ArrayList;

public class ASTLocalVariableNode extends ASTNode {
	
	private int variableIndex;
	
	public ASTLocalVariableNode(){
		this.ASTKind="ASTLocalVariableNode";
		this.parameter=new ArrayList<ASTNode>();
	}
	
	public void setIndex(int index){
		this.variableIndex=index;
	}
	public int getIndex(){
		return this.variableIndex;
	}

}
