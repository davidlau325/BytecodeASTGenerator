package bytecodeAST;


public class ASTLocalVariableNode extends ASTNode {
	
	public ASTLocalVariableNode(){
		super();
		this.ASTKind="ASTLocalVariableNode";
		this.variableValue=null;
	}
	
	public void setIndex(int index){
		this.variableIndex=index;
	}
	public void setVariableType(String type){
		this.variableType=type;
	}
	public void setVariableValue(ASTNode value){
		this.variableValue=value;
	}
	public int getIndex(){
		return this.variableIndex;
	}
	public String getVariableType(){
		return this.variableType;
	}
	public ASTNode getVariableValue(){
		return this.variableValue;
	}

}
