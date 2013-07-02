package bytecodeAST;

import java.util.ArrayList;
import java.util.List;

public class ASTNode {
	
	//main
	protected String ASTKind;
	protected String signature;
	protected ArrayList<ASTNode> callBy;
	protected ArrayList<ASTNode> usedBy;
	protected ArrayList<ASTNode> usedAsObject;
	protected ArrayList<ASTNode> parameter;
	protected ASTNode returnValue;
	
	//Extra
	//Local Variable Node
	protected String variableType;
	protected int variableIndex;
	protected ASTNode variableValue;
	
	protected String ObjectType;
	protected String name;
	protected String constantValue;
	protected String owner;
	protected ASTNode fieldValue;
	protected ArrayList<ASTNode> childMethod;
	protected String desc;
	protected List<String> exception;
	
	//jump node
	protected String compare;
	protected String trueLabel;
	protected ASTNode firstOperand;
	protected ASTNode secondOperand;
	
	//Arithmetic Node
	protected String arithmeticOperator;
	protected String arithmeticType;
	
	public ASTNode(){
		this.ASTKind="ASTNode";
		this.parameter=new ArrayList<ASTNode>();
		this.callBy=new ArrayList<ASTNode>();
		this.usedBy=new ArrayList<ASTNode>();
		this.usedAsObject=new ArrayList<ASTNode>();
	}
	
	//Mutator
	public void setSignature(String sign){
		this.signature=sign;
	}
	public void setCallBy(ASTNode call){
		this.callBy.add(call);
	}
	public void setUsedBy(ASTNode used){
		this.usedBy.add(used);
	}
	public void setReturnValue(ASTNode value){
		this.returnValue=value;
	}
	public void addParameter(ASTNode para){
		this.parameter.add(para);
	}
	public void setUsedAsObject(ASTNode used){
		this.usedAsObject.add(used);
	}
	
	//accessor
	public String getASTKind(){
		return this.ASTKind;
	}
	public String getSignature(){
		return this.signature;
	}
	public ArrayList<ASTNode> getCallBy(){
		return this.callBy;
	}
	public ArrayList<ASTNode> getUsedBy(){
		return this.usedBy;
	}
	public ASTNode getReturnValue(){
		return this.returnValue;
	}
	public ArrayList<ASTNode> getPara(){
		return this.parameter;
	}
	public ArrayList<ASTNode> getUsedAsObject(){
		return this.usedAsObject;
	}
}
