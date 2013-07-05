package bytecodeAST;
import java.util.ArrayList;

public class ASTSwitchNode extends ASTNode{
	
	public ASTSwitchNode(){
		super();
		this.ASTKind="ASTSwitchNode";
		this.labels=new ArrayList<ASTLabelNode>();
		this.compareKeys=new ArrayList<Integer>();
	}
	
	public void setMaxValue(int max){
		this.maxValue=max;
	}
	public void setMinValue(int min){
		this.minValue=min;
	}
	public void setDefaultLabel(ASTLabelNode ln){
		this.defaultLabel=ln;
	}
	public void setCheckValue(ASTNode check){
		this.checkValue=check;
	}
	public void addLabel(ASTLabelNode ln){
		this.labels.add(ln);
	}
	public void addKey(Integer key){
		this.compareKeys.add(key);
	}
	
	public int getMaxValue(){
		return this.maxValue;
	}
	public int getMinValue(){
		return this.minValue;
	}
	public ASTLabelNode getDefaultLabel(){
		return this.defaultLabel;
	}
	public ASTNode getCheckValue(){
		return this.checkValue;
	}
	public ArrayList<ASTLabelNode> getLabels(){
		return this.labels;
	}
	public ArrayList<Integer> getKeys(){
		return this.compareKeys;
	}

}
