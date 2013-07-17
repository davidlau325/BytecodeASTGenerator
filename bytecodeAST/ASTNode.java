package bytecodeAST;

import java.util.ArrayList;
import java.util.List;

public class ASTNode{
	
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
	protected ArrayList<ASTNode> variableValue;
	
	protected String ObjectType;
	protected String name;
	
	// Constant Node
	protected String constantValue;
	protected String constantType;
	
	// Field Node
	protected String owner;
	protected ArrayList<ASTNode> fieldValue;
	
	// Label Node
	protected String labelID;
	protected ASTNode labelConnect;
	
	// Return Node
	protected String returnType;
	protected ASTFunctionNode returnFunction;
	
	// Function Node
	protected ArrayList<ASTNode> childMethod;
	protected String desc;
	protected List<String> exception;
	protected ASTNode parentClass;
	
	// Class Node
	protected String supername;
	protected ArrayList<String> interfaces;
	
	//jump node
	protected String compare;
	protected String trueLabel;
	protected ASTNode trueLabelConnect;
	protected ASTNode firstOperand;
	protected ASTNode secondOperand;
	
	//Arithmetic Node
	protected String arithmeticOperator;
	protected String arithmeticType;
	
	//Cast Node
	protected String originalCast;
	protected String convertedCast;
	
	//Array Node
	protected String arrayType;
	protected ASTNode arraySize;
	
	//Array Value Node
	protected ASTNode valueIndex;
	protected ASTNode valueNode;
	protected String valueType;
	
	// Switch Node
	protected int maxValue;
	protected int minValue;
	protected ASTLabelNode defaultLabel;
	protected ArrayList<ASTLabelNode> labels;
	protected ASTNode checkValue;
	protected ArrayList<Integer> compareKeys;
	
	public ASTNode(){
		this.ASTKind="ASTNode";
		this.parameter=new ArrayList<ASTNode>();
		this.callBy=new ArrayList<ASTNode>();
		this.usedBy=new ArrayList<ASTNode>();
		this.usedAsObject=new ArrayList<ASTNode>();
	}
	public void setName(String name){
		this.name=name;
	}
	
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
	
	public String getName(){
		return this.name;
	}
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

	@Override
	public boolean equals(Object obj){
		ASTNode other=(ASTNode)obj;
		if((this.ASTKind.equals("ASTMethodNode") && other.ASTKind.equals("ASTMethodNode")) || (this.ASTKind.equals("ASTFieldNode") && other.ASTKind.equals("ASTFieldNode"))){
			if(this.name.equals(other.name) && this.owner.equals(other.owner)){
				return true;
			}else{
				return false;
			}
		}else if(this.ASTKind.equals("ASTArithmeticNode") && other.ASTKind.equals("ASTArithmeticNode")){
			if(this.arithmeticOperator.equals(other.arithmeticOperator) && this.arithmeticType.equals(other.arithmeticType) && this.name.equals(other.name)){
				return true;
			}else{
				return false;
			}
		}else if(this.ASTKind.equals("ASTArrayNode") && other.ASTKind.equals("ASTArrayNode")){
			if(this.name.equals(other.name) && this.arrayType.equals(other.arrayType)){
				return true;
			}else{
				return false;
			}
		}else if(this.ASTKind.equals("ASTArrayValueNode") && other.ASTKind.equals("ASTArrayValueNode")){
			if(this.name.equals(other.name)){
				return true;
			}else{
				return false;
			}
		}else if(this.ASTKind.equals("ASTConstantNode") && other.ASTKind.equals("ASTConstantNode")){
			if(this.name.equals(other.name) && this.constantType.equals(other.constantType)){
				return true;
			}else{
				return false;
			}
		}else if(this.ASTKind.equals("ASTLocalVariableNode") && other.ASTKind.equals("ASTLocalVariableNode")){
			if(this.name.equals(other.name) && (this.variableIndex==other.variableIndex) && this.variableType.equals(other.variableType)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
