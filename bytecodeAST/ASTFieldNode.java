package bytecodeAST;

public class ASTFieldNode extends ASTNode {
	
	public ASTFieldNode(){
		super();
		this.ASTKind="ASTFieldNode";
	}
	
	public void setName(String name){
		this.name=name;
	}
	public void setOwner(String owner){
		this.owner=owner;
	}
	public void setFieldValue(ASTNode value){
		this.fieldValue=value;
	}
	
	public String getName(){
		return this.name;
	}
	public String getOwner(){
		return this.owner;
	}
	public ASTNode getFieldValue(){
		return this.fieldValue;
	}

}
