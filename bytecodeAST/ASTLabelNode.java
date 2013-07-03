package bytecodeAST;

public class ASTLabelNode extends ASTNode{
	
	public ASTLabelNode(){
		super();
		this.ASTKind="ASTLabelNode";
	}
	
	public void setLabel(String id){
		this.labelID=id;
	}
	public void setLabelConnect(ASTNode connect){
		this.labelConnect=connect;
	}
	
	public String getLabel(){
		return this.labelID;
	}
	
	public ASTNode getLabelConnect(){
		return this.labelConnect;
	}

}
