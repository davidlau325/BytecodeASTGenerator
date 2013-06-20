package bytecodeAST;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ASTfactory {
	private ASTNode thisAST;
	private HashMap<String,List<VariableOperation>> vop;
	private Stack<String> executionStack;
	
	public ASTfactory(){
		this.thisAST=null;
		this.vop=new HashMap<String,List<VariableOperation>>();
		this.executionStack=new Stack<String>();
	}
	
	public HashMap<String,List<VariableOperation>> getVariableOp(){
		return this.vop;
	}
	public ASTNode getASTNode(){
		return this.thisAST;
	}
	
	private void insertNewVariableOperation(String index,VariableOperation vo){
		if(this.vop.containsKey(index)){
			this.vop.get(index).add(vo);
		}else{
			List<VariableOperation> tempVO=new ArrayList<VariableOperation>();
			tempVO.add(vo);
			this.vop.put(index,tempVO);
		}
	}
	public void generateMethodAST(MethodNode mn,PrintWriter pwOut){
			pwOut.println("----Method----");
			pwOut.println("name: "+mn.name);
			pwOut.println("desc: "+mn.desc);
			pwOut.println("Siganture: "+mn.signature);
			pwOut.println("Local Variable:{");
			List<LocalVariableNode> lvn=mn.localVariables;
			if(lvn!=null){
				for(LocalVariableNode lv:lvn){
					pwOut.println(lv.toString());
				}
			}
			pwOut.println("}");
			pwOut.println("Instruction: {");
			InsnList insn=mn.instructions;
			Iterator itr=insn.iterator();
			while(itr.hasNext()){
				AbstractInsnNode ain=(AbstractInsnNode)itr.next();
				pwOut.println("Opcode: "+ain.getOpcode());
				pwOut.println("Type: "+ain.getType());
				
				switch(ain.getOpcode()){
				// 3
				case Opcodes.ICONST_0:
					switch(ain.getType()){
					case 0:
						this.executionStack.push("C0");
						break;
					default:break;
					}
					break;
				// 4
				case Opcodes.ICONST_1:
					switch(ain.getType()){
						case 0:
							this.executionStack.push("C1");
							break;
						default:break;
					}
					break;
				// 18
				case Opcodes.LDC:
					switch(ain.getType()){
						case 9:
							LdcInsnNode lin=(LdcInsnNode)ain;
							this.executionStack.push(lin.cst.toString());
							break;
						default:break;
					}
					break;
				// 25
				case Opcodes.ALOAD: 
					switch(ain.getType()){
						case 2:
							VarInsnNode vin=(VarInsnNode)ain;
							this.executionStack.push(Integer.toString(vin.var));
							VariableOperation vo=new VariableOperation();
							vo.setIndex(Integer.toString(vin.var));
							vo.setOperation("ALOAD");
							vo.setMethod(mn.name);
							insertNewVariableOperation(Integer.toString(vin.var),vo);
						break;
						default:break;
					}
					break;
				case Opcodes.RETURN:
					switch(ain.getType()){
						case 0:break;
						default:break;
					}
					break;
				// 181
				case Opcodes.PUTFIELD:
					switch(ain.getType()){
						case 4: 
							String variable=this.executionStack.pop();
							if(this.executionStack.isEmpty()){
								return;
							}
							String asObject=this.executionStack.pop();
							FieldInsnNode fin=(FieldInsnNode)ain;
							if(!variable.substring(0,1).equals("C")){
								VariableOperation vo=new VariableOperation();
								vo.setIndex(variable);
								vo.setOperation("PUTFIELD");
								vo.setMethod(mn.name);
								insertNewVariableOperation(variable,vo);
							}
							VariableOperation vo=new VariableOperation();
							vo.setIndex(asObject);
							vo.setOperation("PUTFIELD");
							vo.setAsObject(fin.name);
							insertNewVariableOperation(asObject,vo);
							VariableOperation vo1=new VariableOperation();
							vo1.setIndex(fin.name);
							vo1.setOperation("PUTFIELD");
							vo1.setMethod(asObject);
							vo1.setDesc(fin.desc);
							vo1.setValue(variable);
							vo1.setName(fin.name);
							insertNewVariableOperation(fin.name,vo1);
						break;
						default:break;
					}
					break;
				// 182
				case Opcodes.INVOKEVIRTUAL:
					switch(ain.getType()){
						case 5:
							MethodInsnNode min=(MethodInsnNode)ain;
							pwOut.println(min.desc);
							pwOut.println(min.name);
							break;
						default:break;
					}
					break;
				// 183
				case Opcodes.INVOKESPECIAL:
					switch(ain.getType()){
						case 5:
							MethodInsnNode min=(MethodInsnNode)ain;
							pwOut.println(min.desc);
							// find the variable as object to call function
							String asObject=this.executionStack.pop();
							VariableOperation vo=new VariableOperation();
							vo.setIndex(asObject);
							vo.setOperation("INVOKESPECIAL");
							vo.setAsObject(min.name);
							insertNewVariableOperation(asObject,vo);
						break;
						default:break;	
					}
					break;
				default:break;
				}
			}
			pwOut.println("}");
	}

}
