package bytecodeAST;

import java.util.ArrayList;
import java.util.List;

public class ASTFunctionNode extends ASTNode{

	public ASTFunctionNode(){
		super();
		this.ASTKind="ASTFunctionNode";
		this.childMethod=new ArrayList<ASTNode>();
		this.exception=new ArrayList<String>();
	}
	
	public void addChild(ASTNode child){
		this.childMethod.add(child);
	}
	public void setName(String name){
		this.name=name;
	}
	public void setDesc(String desc){
		this.desc=desc;
	}
	public void setException(String exception){
		this.exception.add(exception);
	}
	public void setParentClass(ASTNode parent){
		this.parentClass=parent;
	}
	
	public ArrayList<ASTNode> getChild(){
		return this.childMethod;
	}
	public String getName(){
		return this.name;
	}
	public String getDesc(){
		return this.desc;
	}
	public List<String> getException(){
		return this.exception;
	}
	public ASTNode getParentClass(){
		return this.parentClass;
	}
}
