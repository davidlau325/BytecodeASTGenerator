package bytecodeAST;

public class ASTFieldNode extends ASTNode {
	
	private String name;
	private String owner;
	private ASTNode fieldValue;
	
	public ASTFieldNode(){
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
