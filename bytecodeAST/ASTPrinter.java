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

}
