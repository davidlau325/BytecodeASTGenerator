package bytecodeAST;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

public class ASTPrinter {
	
	protected ASTNode beginNode;
	protected String jarName;
	protected TreeMap<String,ArrayList<String>> matrix;
	
	public ASTPrinter(ASTNode begin,String jarName){
		this.beginNode=begin;
		this.jarName=jarName;
		matrix=new TreeMap<String,ArrayList<String>>();
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
		pwOut.println("Call By:{");
		for(ASTNode call:amn.getCallBy()){
			pwOut.println(call.getASTKind());
		}
		pwOut.println("}");
	}
	private void printASTReturnNode(PrintWriter pwOut,ASTReturnNode arn){
		pwOut.println("ASTReturnNode");
		pwOut.println("Type: "+arn.getReturnType());
		ASTFunctionNode aafn=arn.getReturnFunction();
		pwOut.println("From Method: "+aafn.getName());
		pwOut.println("Method Sign: "+aafn.getDesc());
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
	private void checkReturnKind(PrintWriter pwOut,ASTNode usedReturn,int level){
		if(level>2){
			return;
		}
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
		default:
			System.out.println(usedReturn.getASTKind());
			break;
		}
		if(level<2){
		pwOut.println("Used By {");
		for(ASTNode ast:usedReturn.getUsedBy()){
			checkReturnKind(pwOut,ast,(level+1));
		}
		pwOut.println("}");
		pwOut.println("Used As Object {");
		for(ASTNode ast:usedReturn.getUsedAsObject()){
			checkReturnKind(pwOut,ast,(level+1));
		}
		pwOut.println("}");
		}
	}
	private void getRelatedAPI(PrintWriter pwOut,ASTNode current,ArrayList<String> CallingRecord){
		if(current!=null){
			if(current.getASTKind().equals("ASTMethodNode")){
				ASTMethodNode amn=(ASTMethodNode)current;
				String name=amn.getOwner()+" "+amn.getName();
				if(CallingRecord.contains(name)){
					return;
				}else{
					CallingRecord.add(name);
					pwOut.println(name);
				}
			}
			if(current.getASTKind().equals("ASTFieldNode")){
				ASTFieldNode afn=(ASTFieldNode)current;
				String name=afn.getOwner()+" "+afn.getName();
				if(CallingRecord.contains(name)){
					return;
				}else{
					CallingRecord.add(name);
				}
			}
		if(!current.getUsedBy().isEmpty()){
			for(ASTNode now:current.getUsedBy()){
				ASTNode next=getNext(pwOut,now);
				getRelatedAPI(pwOut,next,CallingRecord);
			}
		}
		if(!current.getUsedAsObject().isEmpty()){
			for(ASTNode now:current.getUsedAsObject()){
				if(now.getASTKind().equals("ASTMethodNode")){
					ASTMethodNode amn=(ASTMethodNode)now;
					String name=amn.getOwner()+" "+amn.getName();
					if(!CallingRecord.contains(name)){
					CallingRecord.add(name);
					pwOut.println(name);
					}
				}
			}
		}
		}else{
			return;
		}
	}
	private ASTNode getNext(PrintWriter pwOut,ASTNode check){
		ASTNode next=null;
		switch(check.getASTKind()){
		case "ASTMethodNode":{
			next=check;
			break;	
		}
		case "ASTCastNode":{
			next=check;
			break;
		}
		case "ASTFieldNode":{
			next=check;
			break;
		}
		case "ASTLocalVariableNode":{
			next=check;
			break;
		}
		default:break;
		}
		return next;
	}
	
	public void makeMatrix(boolean andriodOnly) throws Exception{
		ASTClassNode acn=(ASTClassNode)this.beginNode;
		PrintWriter pwOut=new PrintWriter(new FileOutputStream("Matrix/"+jarName+".txt"));
		for(ASTNode tempHead:acn.getChild()){
			ASTClassNode classLevel=(ASTClassNode)tempHead;
			for(ASTNode tempClass:classLevel.getChild()){
				ASTFunctionNode functionLevel=(ASTFunctionNode)tempClass;
				for(ASTNode tempFunction:functionLevel.getChild()){
					if(tempFunction.getASTKind().equals("ASTMethodNode")){
						ASTMethodNode amn=(ASTMethodNode)tempFunction;
						if(andriodOnly==true){
							String check=amn.getOwner();
							check=check.substring(0, 7);
							if(check.equals("android")){
							String rowName=classLevel.getName()+" "+functionLevel.getName()+" "+amn.getOwner()+" "+amn.getName();
							pwOut.println("---"+rowName+"---");
							ArrayList<String> callingRecord=new ArrayList<String>();
							getRelatedAPI(pwOut,amn,callingRecord);
							pwOut.println();
							}
						}else{
							String rowName=classLevel.getName()+" "+functionLevel.getName()+" "+amn.getOwner()+" "+amn.getName();
							pwOut.println(rowName);
						}
					}
				}
			}
		}
		pwOut.close();
	}
	
	public void printAllMethod(boolean andriodOnly) throws Exception{
		ASTClassNode acn=(ASTClassNode)this.beginNode;
		PrintWriter pwOut=new PrintWriter(new FileOutputStream("Result/"+jarName+".txt"));
		int countCall=0;
		int andriodCall=0;
		for(ASTNode tempHead:acn.getChild()){
			ASTClassNode classLevel=(ASTClassNode)tempHead;
			for(ASTNode tempClass:classLevel.getChild()){
				ASTFunctionNode functionLevel=(ASTFunctionNode)tempClass;
				for(ASTNode tempFunction:functionLevel.getChild()){
					if(tempFunction.getASTKind().equals("ASTMethodNode")){
						ASTMethodNode amn=(ASTMethodNode)tempFunction;
						if(andriodOnly==true){
							String check=amn.getOwner();
							check=check.substring(0, 7);
							if(check.equals("android")){
								pwOut.println(amn.getName());
								pwOut.println(amn.getOwner());
								pwOut.println(amn.getSignature());
								pwOut.println("Call By: {");
								for(ASTNode call:amn.getCallBy()){
								pwOut.println(call.getASTKind());	
								}
								pwOut.println("}");
								pwOut.println("argument: {");
								for(ASTNode para:amn.getPara()){
									pwOut.println(para.getASTKind());
								}
								pwOut.println("}");
								pwOut.println("return value:{");
								ASTNode returnValue=amn.getReturnValue();
								if(returnValue!=null){
								ASTObjectNode aon=(ASTObjectNode)returnValue;
								pwOut.println(aon.getObjectType());
								pwOut.println("Used By: {");
								for(ASTNode usedReturn:amn.getUsedBy()){
									checkReturnKind(pwOut,usedReturn,0);
								}
								pwOut.println("}");
								pwOut.println("Used As Object:{");
								for(ASTNode usedObject:amn.getUsedAsObject()){
									checkReturnKind(pwOut,usedObject,0);
								}
								pwOut.println("}");
								}
								pwOut.println("}");
								pwOut.println();
							andriodCall++;
							}
						}else{
						pwOut.println(amn.getName());
						pwOut.println(amn.getOwner());
						pwOut.println(amn.getSignature());
						pwOut.println();
						}
						countCall++;
					}
				}
			}
		}
		System.out.println(countCall);
		System.out.println(andriodCall);
		pwOut.close();
	}

}
