package bytecodeAST;
import java.util.ArrayList;

public class ASTFieldNode extends ASTNode {
	
	public ASTFieldNode(){
		super();
		this.ASTKind="ASTFieldNode";
		this.fieldValue=new ArrayList<ASTNode>();
	}
	
	public void setName(String name){
		this.name=name;
	}
	public void setOwner(String owner){
		this.owner=owner;
	}
	public void setFieldValue(ASTNode value){
		this.fieldValue.add(value);
	}
	
	public String getName(){
		return this.name;
	}
	public String getOwner(){
		return this.owner;
	}
	public ArrayList<ASTNode> getFieldValue(){
		return this.fieldValue;
	}

}
