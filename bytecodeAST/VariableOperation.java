package bytecodeAST;

public class VariableOperation {
	private String index;
	private String name;
	private String operation;
	private String byMethod;
	private String asObject;
	private String value;
	private String desc;
	
	public VariableOperation(){
		this.index=null;
		this.name=null;
		this.operation=null;
		this.byMethod=null;
		this.asObject=null;
		this.value=null;
		this.desc=null;
	}
	
	//Mutators
	public void setName(String na){
		this.name=na;
	}
	public void setIndex(String index){
		this.index=index;
	}
	public void setOperation(String op){
		this.operation=op;
	}
	public void setMethod(String me){
		this.byMethod=me;
	}
	public void setAsObject(String ao){
		this.asObject=ao;
	}
	public void setValue(String v){
		this.value=v;
	}
	public void setDesc(String t){
		this.desc=t;
	}
	
	//Accessor
	public String getIndex(){
		return this.index;
	}
	public String getName(){
		return this.name;
	}
	public String getOperation(){
		return this.operation;
	}
	public String getMethod(){
		return this.byMethod;
	}
	public String getAsObject(){
		return this.asObject;
	}
	public String getValue(){
		return this.value;
	}
	public String getDesc(){
		return this.desc;
	}
}
