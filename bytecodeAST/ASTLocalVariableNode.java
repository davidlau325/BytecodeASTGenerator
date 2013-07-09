package bytecodeAST;
import java.util.ArrayList;

public class ASTLocalVariableNode extends ASTNode {
	
	public ASTLocalVariableNode(){
		super();
		this.ASTKind="ASTLocalVariableNode";
		this.variableValue=new ArrayList<ASTNode>();
	}
	
	public void setIndex(int index){
		this.variableIndex=index;
	}
	public void setVariableType(String type){
		this.variableType=type;
	}
	public void setVariableValue(ASTNode value){
		this.variableValue.add(value);
	}
	public int getIndex(){
		return this.variableIndex;
	}
	public String getVariableType(){
		return this.variableType;
	}
	public ArrayList<ASTNode> getVariableValue(){
		return this.variableValue;
	}

}
