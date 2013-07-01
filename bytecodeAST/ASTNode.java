package bytecodeAST;

import java.util.ArrayList;

public class ASTNode {
	protected String ASTKind;
	protected String signature;
	protected ASTNode callBy;
	protected ASTNode usedBy;
	protected ArrayList<ASTNode> parameter;
	protected ASTNode returnValue;
	
	public ASTNode(){
		this.ASTKind="ASTNode";
		this.parameter=new ArrayList<ASTNode>();
	}
	
	//Mutator
	public void setSignature(String sign){
		this.signature=sign;
	}
	public void setCallBy(ASTNode call){
		this.callBy=call;
	}
	public void setUsedBy(ASTNode used){
		this.usedBy=used;
	}
	public void setReturnValue(ASTNode value){
		this.returnValue=value;
	}
	public void addParameter(ASTNode para){
		this.parameter.add(para);
	}
	
	//accessor
	public String getASTKind(){
		return this.ASTKind;
	}
	public String getSignature(){
		return this.signature;
	}
	public ASTNode getCallBy(){
		return this.callBy;
	}
	public ASTNode getUsedBy(){
		return this.usedBy;
	}
	public ASTNode getReturnValue(){
		return this.returnValue;
	}
	public ArrayList<ASTNode> getPara(){
		return this.parameter;
	}
	
}
