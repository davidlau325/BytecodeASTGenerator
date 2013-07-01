package bytecodeAST;

import java.util.ArrayList;
import java.util.List;

public class ASTFunctionNode extends ASTNode{
	
	private ArrayList<ASTNode> childMethod;
	private String name;
	private String desc;
	private List<String> exception;

	public ASTFunctionNode(){
		this.ASTKind="ASTFunctionNode";
		this.parameter=new ArrayList<ASTNode>();
		this.childMethod=new ArrayList<ASTNode>();
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
	public void setException(List<String> exception){
		this.exception=exception;
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
	
}