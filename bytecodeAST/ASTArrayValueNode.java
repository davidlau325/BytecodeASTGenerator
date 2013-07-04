package bytecodeAST;

public class ASTArrayValueNode extends ASTNode {
	
	public ASTArrayValueNode(){
		super();
		this.ASTKind="ASTArrayValueNode";
	}
	
	public void setValueIndex(ASTNode index){
		this.valueIndex=index;
	}
	public void setValueNode(ASTNode node){
		this.valueNode=node;
	}
	public void setValueType(String type){
		this.valueType=type;
	}
	
	public ASTNode getValueIndex(){
		return this.valueIndex;
	}
	public ASTNode getValueNode(){
		return this.valueNode;
	}
	public String getValueType(){
		return this.valueType;
	}
}
