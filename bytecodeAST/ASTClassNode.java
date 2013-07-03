package bytecodeAST;

import java.util.ArrayList;

public class ASTClassNode extends ASTNode{

	public ASTClassNode(){
		super();
		this.ASTKind="ASTClassNode";
		this.childMethod=new ArrayList<ASTNode>();
		this.interfaces=new ArrayList<String>();
	}
	
	public void setChild(ASTNode child){
		this.childMethod.add(child);
	}
	public void setName(String name){
		this.name=name;
	}
	public void setSuperName(String supername){
		this.supername=supername;
	}
	public void addInterface(String inter){
		this.interfaces.add(inter);
	}
	
	public ArrayList<ASTNode> getChild(){
		return this.childMethod;
	}
	public String getName(){
		return this.name;
	}
	public String getSuperName(){
		return this.supername;
	}
	public ArrayList<String> getInterfaces(){
		return this.interfaces;
	}
	
}
