package bytecodeAST;

public class ASTMethodNode extends ASTNode {
	
	public ASTMethodNode(){
		super();
		this.ASTKind="ASTMethodNode";
	}
	
	public void setName(String name){
		this.name=name;
	}

	public void setOwner(String owner){
		this.owner=owner;
	}
	
	public String getName(){
		return this.name;
	}
	public String getOwner(){
		return this.owner;
	}
}
