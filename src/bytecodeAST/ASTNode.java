package bytecodeAST;

public class ASTNode {
	protected String ASTKind;
	protected ASTNode nextSibling;
	protected ASTNode firstChild;
	
	public ASTNode(String kind){
		this.ASTKind=kind;
		this.nextSibling=null;
		this.firstChild=null;
	}
	
	public boolean addFirstChild(ASTNode child){
		if(this.firstChild==null){
			this.firstChild=child;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addNextSibling(ASTNode sibling){
		if(this.nextSibling==null){
			this.nextSibling=sibling;
			return true;
		}else{
			return false;
		}
	}
	
	public String getASTKind(){
		return this.ASTKind;
	}
	public ASTNode getNext(){
		return this.nextSibling;
	}
	public ASTNode getChild(){
		return this.firstChild;
	}
}
