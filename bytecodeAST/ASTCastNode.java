package bytecodeAST;

public class ASTCastNode extends ASTNode {
	
	public ASTCastNode(){
		super();
		this.ASTKind="ASTCastNode";
	}
	
	public void setOriginalCast(String type){
		this.originalCast=type;
	}
	public void setConvertedCast(String type){
		this.convertedCast=type;
	}
	public String getOriginalCast(){
		return this.originalCast;
	}
	public String getConvertedCast(){
		return this.convertedCast;
	}
}
