package bytecodeAST;

import java.util.List;

import org.objectweb.asm.tree.InnerClassNode;

public class ASTClassNode extends ASTNode {
	private String name;
	private String outerClass;
	private String outerMethod;
	private String outerMethodDesc;
	private String superName;
	private List<String> interfaces;
	
	public ASTClassNode(String kind){
		super(kind);
		this.name=null;
		this.outerClass=null;
		this.outerMethod=null;
		this.outerMethodDesc=null;
		this.superName=null;
		this.interfaces=null;
	}

	//Mutator
	public void setName(String name){
		this.name=name;
	}
	public void setOuterClass(String outerC){
		this.outerClass=outerC;
	}
	public void setOuterMethod(String outerM){
		this.outerMethod=outerM;
	}
	public void setOuterMethodDesc(String outerMD){
		this.outerMethodDesc=outerMD;
	}
	public void setSuperName(String sname){
		this.superName=sname;
	}
	public void setInterfaces(List<String> interfaces){
		this.interfaces=interfaces;
	}
	
	//Accessor
	public String getName(){
		return this.name;
	}
	public String getOuterClass(){
		return this.outerClass;
	}
	public String getOuterMethod(){
		return this.outerMethod;
	} 
	public String getOuterMethodDesc(){
		return this.outerMethodDesc;
	}
	public String getSuperName(){
		return this.superName;
	}
	public List<String> getInterfaces(){
		return this.interfaces;
	}
}
