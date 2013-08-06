package bytecodeAST;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ASTPrinter {
	
	protected ASTNode beginNode;
	protected String jarName;
	protected ASTMethodNode currentChecking;
	protected String currentKey;
	protected HashMap<String,Boolean> androidAPI;
	protected HashMap<String,Boolean> androidAllAPI;
	protected HashMap<String,Integer> recordCount;
 // protected PrintWriter json;
	
	public ASTPrinter(ASTNode begin,String jarName,HashMap<String,Boolean> androidAPI,HashMap<String,Boolean> androidAllAPI){
		this.beginNode=begin;
		this.jarName=jarName;
		this.androidAPI=androidAPI;
		this.androidAllAPI=androidAllAPI;
		this.currentKey="";
		this.recordCount=new HashMap<String,Integer>();
	}
	
	
	
	public void printClassInfo() throws Exception{
    
    ASTClassNode acn=(ASTClassNode)this.beginNode;
    int cutName=acn.getName().lastIndexOf("/");
    String fileName=acn.getName().substring((cutName+1));
    PrintWriter pwOut = new PrintWriter(new FileOutputStream("Result/"+jarName+"/"+fileName+".txt"));
    pwOut.println("---Class Node---");
    pwOut.println("Name: "+acn.getName());
    pwOut.println("SuperName: "+acn.getSuperName());
    pwOut.println("Interfaces: {");
    for(Object ob:acn.getInterfaces()){
    	pwOut.println(ob.toString());
    }
    pwOut.println("}");
    pwOut.println("Child Method: {");
    for(ASTNode ast:acn.getChild()){
    	ASTFunctionNode afn=(ASTFunctionNode)ast;
    	pwOut.println("Name: "+afn.getName());
    	pwOut.println("Sign: "+afn.getDesc());
    }
    pwOut.println("}"); 
    pwOut.println();
    pwOut.close();
	}
	private void printASTMethodNode(PrintWriter pwOut,ASTMethodNode amn){
		pwOut.println("ASTMethodNode");
		pwOut.println("Name: "+amn.getName());
		pwOut.println("Owner: "+amn.getOwner());
		pwOut.println("Sign: "+amn.getSignature());
	}
	private void printASTReturnNode(PrintWriter pwOut,ASTReturnNode arn){
		pwOut.println("ASTReturnNode");
		pwOut.println("Type: "+arn.getReturnType());
	}
	private void printASTFieldNode(PrintWriter pwOut,ASTFieldNode afn){
		pwOut.println("ASTFieldNode");
		pwOut.println("Name: "+afn.getName());
		pwOut.println("Owner: "+afn.getOwner());
		pwOut.println("Sign: "+afn.getSignature());
	}
	private void printASTLocalVariableNode(PrintWriter pwOut,ASTLocalVariableNode alvn){
		pwOut.println("ASTLocalVariableNode");
		pwOut.println("Type: "+alvn.getVariableType());
	}
	private void printASTCastNode(PrintWriter pwOut,ASTCastNode acn){
		pwOut.println("ASTCastNode");
		pwOut.println("Original: "+acn.getOriginalCast());
		pwOut.println("Casted: "+acn.getConvertedCast());
	}
	private void printASTJumpNode(PrintWriter pwOut,ASTJumpNode ajn){
		pwOut.println("ASTJumpNode");
		pwOut.println("Operator: "+ajn.getCompare());
		pwOut.println("Operand1: "+ajn.getFirstOperand().getASTKind());
		pwOut.println("Operand2: "+ajn.getSecondOperand().getASTKind());
		pwOut.println("True: "+ajn.getTrueLabel());
	}
	private void printASTArithmeticNode(PrintWriter pwOut,ASTArithmeticNode aan){
		pwOut.println("ASTArithmeticNode");
		pwOut.println("Operator: "+aan.getArithmeticOperator());
		pwOut.println("Type: "+aan.getArithmeticType());
		if(aan.getFirstOperand()!=null){
		pwOut.println("Operand1: "+aan.getFirstOperand().getASTKind());
		}
		pwOut.println("Operand2: "+aan.getSecondOperand().getASTKind());
	}
	private void printASTArrayValueNode(PrintWriter pwOut,ASTArrayValueNode aavn){
		pwOut.println("ASTArrayValueNode");
		pwOut.println("Index: "+aavn.getValueIndex());
		pwOut.println("Type: "+aavn.getValueType());
		if(aavn.getValueNode()!=null){
		pwOut.println("Value: "+aavn.getValueNode().getASTKind());
		}
	}
	private void printASTConstantNode(PrintWriter pwOut,ASTConstantNode acn){
		pwOut.println("ASTConstantNode");
		pwOut.println("Value: "+acn.getConstantValue());
		pwOut.println("Type: "+acn.getConstantType());
	}
	private void printASTArrayNode(PrintWriter pwOut,ASTArrayNode aan){
		pwOut.println("ASTArrayNode");
		pwOut.println("Type: "+aan.getArrayType());
		pwOut.println("Size: "+aan.getArraySize().getASTKind());
	}
	private void printASTObjectNode(PrintWriter pwOut,ASTObjectNode aon){
		pwOut.println("ASTObjectNode");
		pwOut.println("Type: "+aon.getObjectType());
		pwOut.println("Name: "+aon.getObjectName());
	}
	private void checkReturnKind(PrintWriter pwOut,ASTNode usedReturn){
		switch(usedReturn.getASTKind()){
		case "ASTArrayNode":
			printASTArrayNode(pwOut,(ASTArrayNode)usedReturn);
			break;
		case "ASTArrayValueNode":
			printASTArrayValueNode(pwOut,(ASTArrayValueNode)usedReturn);
			break;
		case "ASTArithmeticNode":
			printASTArithmeticNode(pwOut,(ASTArithmeticNode)usedReturn);
			break;
		case "ASTCastNode":
			printASTCastNode(pwOut,(ASTCastNode)usedReturn);
			break;
		case "ASTConstantNode":
			printASTConstantNode(pwOut,(ASTConstantNode)usedReturn);
			break;
		case "ASTFieldNode":
			printASTFieldNode(pwOut,(ASTFieldNode)usedReturn);
			break;
		case "ASTJumpNode":
			printASTJumpNode(pwOut,(ASTJumpNode)usedReturn);
			break;
		case "ASTLocalVariableNode":
			printASTLocalVariableNode(pwOut,(ASTLocalVariableNode)usedReturn);
			break;
		case "ASTMethodNode":
			printASTMethodNode(pwOut,(ASTMethodNode)usedReturn);
			break;
		case "ASTReturnNode":
			printASTReturnNode(pwOut,(ASTReturnNode)usedReturn);
			break;
		case "ASTObjectNode":
			printASTObjectNode(pwOut,(ASTObjectNode)usedReturn);
			break;
		default:
			break;
		}
	}
	private void getRelatedAPI(PrintWriter pwOut,ASTNode current,ArrayList<ASTNode> CallingRecord){
		if(current!=null){
		//	checkReturnKind(this.json,current);
			
			if(CallingRecord.contains(current)){
				return;
			}else{
				CallingRecord.add(current);
			if(current.getASTKind().equals("ASTMethodNode")){
				ASTMethodNode amn=(ASTMethodNode)current;
				String name=amn.getOwner()+" "+amn.getName();
					if(this.androidAllAPI.get(name)!=null){
						pwOut.println(this.currentKey+","+name);
					}
			}	
			
		if(!current.getUsedBy().isEmpty()){
		//	this.json.println("UsedBy:{");
			for(ASTNode now:current.getUsedBy()){
				ASTNode next=getNext(pwOut,now);
				getRelatedAPI(pwOut,next,CallingRecord);
			}
		//	this.json.println("}");
		}
		if(!current.getUsedAsObject().isEmpty()){
		//	this.json.println("UsedAsObject:{");
			for(ASTNode now:current.getUsedAsObject()){
				ASTNode next=getNext(pwOut,now);
				getRelatedAPI(pwOut,next,CallingRecord);
			}
		//	this.json.println("}");
		}
		if(!current.getCallBy().isEmpty()){
		//	this.json.println("CallBy:{");
			for(ASTNode call:current.getCallBy()){
				ASTNode next=getNext(pwOut,call);
				getRelatedAPI(pwOut,next,CallingRecord);
			}
		//	this.json.println("}");
		}
		}
		}else{
			return;
		}
	}
	private ASTNode getNext(PrintWriter pwOut,ASTNode check){
		if(check.getASTKind().equals("ASTJumpNode") || check.getASTKind().equals("ASTLabelNode") || check.getASTKind().equals("ASTSwitchNode")){
			return null;
		}else{
			return check;
		}
	}
	
	private void getArgumentAPI(PrintWriter pwOut,ASTNode check,ArrayList<ASTNode> CallingRecord){
		if(check==null){
			return;
		}else{
			if(CallingRecord.contains(check)){
				return;
			}else{
				CallingRecord.add(check);
				if(check.getASTKind().equals("ASTMethodNode")){
					ASTMethodNode amn=(ASTMethodNode)check;
					String name=amn.getOwner()+" "+amn.getName();
					if(this.androidAllAPI.get(name)!=null && !currentChecking.equals(amn)){
						pwOut.println(this.currentKey+","+name);
					}
					for(Object obj:amn.getPara()){
						ASTNode ast=(ASTNode)obj;
						getArgumentAPI(pwOut,ast,CallingRecord);
					}
				}else if(check.getASTKind().equals("ASTFieldNode")){
					ASTFieldNode afn=(ASTFieldNode)check;
					for(Object obj:afn.getFieldValue()){
						ASTNode ast=(ASTNode)obj;
						getArgumentAPI(pwOut,ast,CallingRecord);
					}
				}else if(check.getASTKind().equals("ASTLocalVariableNode")){
					ASTLocalVariableNode alvn=(ASTLocalVariableNode)check;
					for(Object obj:alvn.getVariableValue()){
						ASTNode ast=(ASTNode)obj;
						getArgumentAPI(pwOut,ast,CallingRecord);
					}
				}
			}
		}
	}
	public void makeMatrix(boolean andriodOnly) throws Exception{
		ASTClassNode acn=(ASTClassNode)this.beginNode;
		PrintWriter pwOut=new PrintWriter(new FileOutputStream(jarName+".txt"));
	//	this.json=new PrintWriter(new FileOutputStream(this.jarName+"_json.txt"));
		for(ASTNode tempHead:acn.getChild()){
			ASTClassNode classLevel=(ASTClassNode)tempHead;
			for(ASTNode tempClass:classLevel.getChild()){
				ASTFunctionNode functionLevel=(ASTFunctionNode)tempClass;
				for(ASTNode tempFunction:functionLevel.getChild()){
					if(tempFunction.getASTKind().equals("ASTMethodNode")){
						ASTMethodNode amn=(ASTMethodNode)tempFunction;
						if(andriodOnly==true){
							String rowName=amn.getOwner()+" "+amn.getName();
							if(rowName.length()>7){
							if(this.androidAPI.get(rowName)!=null){
								if(this.recordCount.get(rowName)!=null){
									int methodCount=this.recordCount.get(rowName);
									this.currentKey=this.jarName+"-"+rowName+"-"+methodCount;
									this.recordCount.put(rowName,methodCount+1);
								}else{
									this.currentKey=this.jarName+"-"+rowName+"-0";
									this.recordCount.put(rowName, 1);
								}
							ArrayList<ASTNode> callingRecord=new ArrayList<ASTNode>();
					//		this.json.println(this.currentKey+"{");
					//		this.json.println("return:{");
							getRelatedAPI(pwOut,amn,callingRecord);
					//		this.json.println("}");
							callingRecord.remove(amn);
							currentChecking=amn;
					//		this.json.println("argument:{");
							getArgumentAPI(pwOut,amn,callingRecord);
					//		this.json.println("}");
					//		this.json.println("}");
							}
							}
						}else{
							String rowName=classLevel.getName()+" "+functionLevel.getName()+" "+amn.getOwner()+" "+amn.getName();
							pwOut.println(rowName);
						}
					}
				}
			}
		}
	//	this.json.close();
		pwOut.close();
	}
}
