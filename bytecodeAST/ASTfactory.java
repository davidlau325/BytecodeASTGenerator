package bytecodeAST;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.Type;

public class ASTfactory {
	private ASTNode thisAST;
	private Stack<ASTNode> executionStack;
	
	public ASTfactory(){
		this.thisAST=null;
		this.executionStack=new Stack<ASTNode>();
	}
	
	public ASTNode getASTNode(){
		return this.thisAST;
	}
	
	public void generateFunctionAST(MethodNode mn,PrintWriter pwOut){
			pwOut.println("----Method----");
			pwOut.println("name: "+mn.name);
			pwOut.println("desc: "+mn.desc);
			pwOut.println("Siganture: "+mn.signature);
			
			ASTFunctionNode afn=new ASTFunctionNode();
			afn.setDesc(mn.desc);
			afn.setName(mn.name);
			afn.setSignature(mn.signature);
			afn.setException(mn.exceptions);
			this.thisAST=afn;
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
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("0");
						executionStack.push(acn);
						break;
					default:break;
					}
					break;
				// 4
				case Opcodes.ICONST_1:
					switch(ain.getType()){
						case 0:
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("1");
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 18
				case Opcodes.LDC:
					switch(ain.getType()){
						case 9:
							
							break;
						default:break;
					}
					break;
				// 25
				case Opcodes.ALOAD: 
					switch(ain.getType()){
						case 2:
							VarInsnNode vin=(VarInsnNode)ain;
							ASTLocalVariableNode alvn=new ASTLocalVariableNode();
							alvn.setIndex(vin.var);
							alvn.setCallBy(this.thisAST);
							executionStack.push(alvn);
							break;
						default:break;
					}
					break;
				// 177
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
							FieldInsnNode fin=(FieldInsnNode)ain;
							ASTFieldNode fien=new ASTFieldNode();
							if(executionStack.isEmpty()){
								pwOut.close();
								System.out.println("No stack");
								return;
							}
							fien.setFieldValue(executionStack.pop());
							if(executionStack.isEmpty()){
								pwOut.close();
								System.out.println("No stack");
								return;
							}
							fien.setCallBy(executionStack.pop());
							fien.setName(fin.name);
							fien.setOwner(fin.owner);
							fien.setSignature(fin.desc);
							
							afn.addChild(fien);
							break;
						default:break;
					}
					break;
				// 182
				case Opcodes.INVOKEVIRTUAL:
					switch(ain.getType()){
						case 5:
							
							break;
						default:break;
					}
					break;
				// 183
				case Opcodes.INVOKESPECIAL:
					switch(ain.getType()){
						case 5:
							MethodInsnNode min=(MethodInsnNode)ain;
							ASTMethodNode amn=new ASTMethodNode();
							amn.setName(min.name);
							amn.setSignature(min.desc);
							Type[] args=Type.getArgumentTypes(min.desc);							
							for(int i=0;i<args.length;i++){
								amn.addParameter(executionStack.pop());
							}
							amn.setCallBy(executionStack.pop());
							Type returnType=Type.getReturnType(min.desc);
							
							if(!returnType.toString().equals("V")){
							ASTObjectNode returnObject=new ASTObjectNode();
							returnObject.setObjectType(returnType.getInternalName());
							amn.setReturnValue(returnObject);
							executionStack.push(amn);
							}else{
								amn.setReturnValue(null);
							}
							afn.addChild(amn);
							
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
