package bytecodeAST;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ASTPrinter {
	
	protected ASTNode beginNode;
	protected String jarName;
	
	public ASTPrinter(ASTNode begin,String jarName){
		this.beginNode=begin;
		this.jarName=jarName;
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
	
	private ASTNode checkReturnKind(PrintWriter pwOut,ASTNode usedReturn){
		ASTNode next=null;
		switch(usedReturn.getASTKind()){
		case "ASTFieldNode":
		{
			ASTFieldNode afn=(ASTFieldNode)usedReturn;
			pwOut.println("ASTFieldNode");
			pwOut.println("Name: "+afn.getName());
			pwOut.println("Owner: "+afn.getOwner());
			pwOut.println("Sign: "+afn.getSignature());
			if(!afn.getUsedBy().isEmpty()){
				next=afn.getUsedBy().get(0);
			}
			if(!afn.getUsedAsObject().isEmpty()){
				next=afn.getUsedAsObject().get(0);
			}
		}
		break;
		
		default:System.out.println(usedReturn.getASTKind());
			break;
		}
		return next;
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
								pwOut.println("Used by: {");
								for(ASTNode usedReturn:amn.getUsedBy()){
									pwOut.println("#1");
									ASTNode first=checkReturnKind(pwOut,usedReturn);
									pwOut.println("#2");
									ASTNode second=null;
									if(first!=null){
										pwOut.println("get to second");
										second=checkReturnKind(pwOut,first);
									}
									pwOut.println("#3");
									if(second!=null){
										checkReturnKind(pwOut,second);
									}
								}
								pwOut.println("}");
								pwOut.println("Used as object:{");
								for(ASTNode usedObject:amn.getUsedAsObject()){
									
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
