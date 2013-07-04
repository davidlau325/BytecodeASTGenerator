package bytecodeAST;

import java.util.Iterator;
import java.util.Stack;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.Type;

public class ASTfactory {
	private ASTNode thisAST;
	private Stack<ASTNode> executionStack;
	private ASTLabelNode currentLabelNode;
	
	public ASTfactory(){
		this.thisAST=null;
		this.executionStack=new Stack<ASTNode>();
	}
	
	public ASTNode getASTNode(){
		return this.thisAST;
	}
	
	public void generateFunctionAST(MethodNode mn){
			
			ASTFunctionNode afn=new ASTFunctionNode();
			afn.setDesc(mn.desc);
			afn.setName(mn.name);
			afn.setSignature(mn.signature);
			afn.setException(mn.exceptions);
			this.thisAST=afn;
			InsnList insn=mn.instructions;
			Iterator itr=insn.iterator();
			int newLabel=0;
			while(itr.hasNext()){
				AbstractInsnNode ain=(AbstractInsnNode)itr.next();					
				if(newLabel==1){
					newLabel=2;
				}else if(newLabel==2){
					newLabel=0;
				}
				switch(ain.getOpcode()){
				// -1
				case Opcodes.F_NEW:
					switch(ain.getType()){
					case 8:
					LabelNode ln=(LabelNode)ain;
					Label temp=ln.getLabel();
					currentLabelNode=new ASTLabelNode();
					currentLabelNode.setLabel(temp.toString());
					afn.addChild(currentLabelNode);
					newLabel=1;
					break;
					default:break;
					}
					break;
				// 1
				case Opcodes.ACONST_NULL:
					switch(ain.getType()){
						case 0:
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("NULL");
							acn.setConstantType("NULL");
							acn.setCallBy(afn);
							afn.setUsedAsObject(acn);
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 2
				case Opcodes.ICONST_M1:
					switch(ain.getType()){
						case 0:
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("-1");
							acn.setConstantType("Int");
							acn.setCallBy(afn);
							afn.setUsedAsObject(acn);
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 3
				case Opcodes.ICONST_0:
					switch(ain.getType()){
					case 0:
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("0");
						acn.setConstantType("Int");
						acn.setCallBy(afn);
						afn.setUsedAsObject(acn);
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
							acn.setConstantType("Int");
							acn.setCallBy(afn);
							afn.setUsedAsObject(acn);
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 5
				case Opcodes.ICONST_2:
					switch(ain.getType()){
						case 0:
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("2");
							acn.setConstantType("Int");
							acn.setCallBy(afn);
							afn.setUsedAsObject(acn);
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 6
				case Opcodes.ICONST_3:
					switch(ain.getType()){
					case 0:
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("3");
						acn.setConstantType("Int");
						acn.setCallBy(afn);
						afn.setUsedAsObject(acn);
						executionStack.push(acn);
						break;
					default:break;
					}
					break;
				// 7
				case Opcodes.ICONST_4:
					switch(ain.getType()){
					case 0:
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("4");
						acn.setConstantType("Int");
						acn.setCallBy(afn);
						afn.setUsedAsObject(acn);
						executionStack.push(acn);
						break;
					default:break;
					}
					break;
				// 8
				case Opcodes.ICONST_5:
					switch(ain.getType()){
						case 0:
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("5");
							acn.setConstantType("Int");
							acn.setCallBy(afn);
							afn.setUsedAsObject(acn);
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 9
				case Opcodes.LCONST_0:
					switch(ain.getType()){
					case 0:
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("0");
						acn.setConstantType("Long");
						acn.setCallBy(afn);
						afn.setUsedAsObject(acn);
						executionStack.push(acn);
						break;
					default:break;
					}
				break;
				// 10
				case Opcodes.LCONST_1:
					switch(ain.getType()){
						case 0:
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("1");
						acn.setConstantType("Long");
						acn.setCallBy(afn);
						afn.setUsedAsObject(acn);
						executionStack.push(acn);
						break;
					default:break;
					}
					break;
				// 11
				case Opcodes.FCONST_0:
					switch(ain.getType()){
					case 0:
					ASTConstantNode acn=new ASTConstantNode();
					acn.setConstantValue("0");
					acn.setConstantType("Float");
					acn.setCallBy(afn);
					afn.setUsedAsObject(acn);
					executionStack.push(acn);
					break;
				default:break;
				}
				break;
				// 12
				case Opcodes.FCONST_1:
					switch(ain.getType()){
					case 0:
					ASTConstantNode acn=new ASTConstantNode();
					acn.setConstantValue("1");
					acn.setConstantType("Float");
					acn.setCallBy(afn);
					afn.setUsedAsObject(acn);
					executionStack.push(acn);
					break;
				default:break;
				}
				break;
				// 13
				case Opcodes.FCONST_2:
					switch(ain.getType()){
					case 0:
					ASTConstantNode acn=new ASTConstantNode();
					acn.setConstantValue("1");
					acn.setConstantType("Float");
					acn.setCallBy(afn);
					afn.setUsedAsObject(acn);
					executionStack.push(acn);
					break;
				default:break;
				}
				break;
				// 15
				case Opcodes.DCONST_1:
					switch(ain.getType()){
					case 0:
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("1");
						acn.setConstantType("Double");
						acn.setCallBy(afn);
						afn.setUsedAsObject(acn);
						executionStack.push(acn);
						break;
					default:break;
					}
				break;
				// 16
				case Opcodes.BIPUSH:
					switch(ain.getType()){
						case 1:
							IntInsnNode iin=(IntInsnNode)ain;
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue(iin.operand+"");
							acn.setConstantType("Int");
							acn.setCallBy(afn);
							afn.setUsedAsObject(acn);
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 17
				case Opcodes.SIPUSH:
					switch(ain.getType()){
						case 1:
							IntInsnNode iin=(IntInsnNode)ain;
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue(iin.operand+"");
							acn.setConstantType("Int");
							acn.setCallBy(afn);
							afn.setUsedAsObject(acn);
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 18
				case Opcodes.LDC:
					switch(ain.getType()){
						case 9:
							LdcInsnNode lin=(LdcInsnNode)ain;
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue(lin.cst.toString());
							acn.setConstantType("String");
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 21
				case Opcodes.ILOAD:
					switch(ain.getType()){
						case 2:
							VarInsnNode vin=(VarInsnNode)ain;
							ASTLocalVariableNode alvn=new ASTLocalVariableNode();
							alvn.setIndex(vin.var);
							alvn.setVariableType("Int");
							afn.setUsedAsObject(alvn);
							alvn.setCallBy(afn);
							executionStack.push(alvn);
							break;
						default:break;
					}
					break;
				// 22
				case Opcodes.LLOAD:
					switch(ain.getType()){
						case 2:
							VarInsnNode vin=(VarInsnNode)ain;
							ASTLocalVariableNode alvn=new ASTLocalVariableNode();
							alvn.setIndex(vin.var);
							alvn.setVariableType("Long");
							afn.setUsedAsObject(alvn);
							alvn.setCallBy(afn);
							executionStack.push(alvn);
							break;
						default:break;
					}
					break;
				// 23
				case Opcodes.FLOAD:
					switch(ain.getType()){
					case 2:
						VarInsnNode vin=(VarInsnNode)ain;
						ASTLocalVariableNode alvn=new ASTLocalVariableNode();
						alvn.setIndex(vin.var);
						alvn.setVariableType("Float");
						afn.setUsedAsObject(alvn);
						alvn.setCallBy(afn);
						executionStack.push(alvn);
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
							alvn.setVariableType("Object");
							afn.setUsedAsObject(alvn);
							alvn.setCallBy(afn);
							executionStack.push(alvn);
							break;
						default:break;
					}
					break;
				// 46
				case Opcodes.IALOAD:
					switch(ain.getType()){
					case 0:
						ASTNode index;
						ASTNode array;
						if(newLabel==2 & executionStack.isEmpty()){
							index=currentLabelNode;
						}else{
							index=executionStack.pop();
						}
						if(newLabel==2 & executionStack.isEmpty()){
							array=currentLabelNode;
						}else{
							array=executionStack.pop();
						}
						ASTArrayValueNode aavn=new ASTArrayValueNode();
						aavn.setValueIndex(index);
						aavn.setValueType("Int");
						index.setUsedBy(aavn);
						aavn.setUsedBy(array);
						array.setUsedAsObject(aavn);
						executionStack.push(aavn);
						break;
					default:break;
				}
				break;
				// 50
				case Opcodes.AALOAD:
					switch(ain.getType()){
						case 0:
							ASTNode index;
							ASTNode array;
							if(newLabel==2 & executionStack.isEmpty()){
								index=currentLabelNode;
							}else{
								index=executionStack.pop();
							}
							if(newLabel==2 & executionStack.isEmpty()){
								array=currentLabelNode;
							}else{
								array=executionStack.pop();
							}
							ASTArrayValueNode aavn=new ASTArrayValueNode();
							aavn.setValueIndex(index);
							aavn.setValueType("Object");
							index.setUsedBy(aavn);
							aavn.setUsedBy(array);
							array.setUsedAsObject(aavn);
							executionStack.push(aavn);
							break;
						default:break;
					}
					break;
				// 51
				case Opcodes.BALOAD:
					switch(ain.getType()){
						case 0:
							ASTNode index;
							ASTNode array;
							if(newLabel==2 & executionStack.isEmpty()){
								index=currentLabelNode;
							}else{
								index=executionStack.pop();
							}
							if(newLabel==2 & executionStack.isEmpty()){
								array=currentLabelNode;
							}else{
								array=executionStack.pop();
							}
							ASTArrayValueNode aavn=new ASTArrayValueNode();
							aavn.setValueIndex(index);
							aavn.setValueType("Byte");
							index.setUsedBy(aavn);
							aavn.setUsedBy(array);
							array.setUsedAsObject(aavn);
							executionStack.push(aavn);
							break;
						default:break;
					}
					break;
				// 54
				case Opcodes.ISTORE:
					switch(ain.getType()){
						case 2:
							VarInsnNode vin=(VarInsnNode)ain;
							ASTLocalVariableNode alvn=new ASTLocalVariableNode();
							alvn.setIndex(vin.var);
							alvn.setVariableType("Int");
							afn.setUsedAsObject(alvn);
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;	
							}else{
							ast=executionStack.pop();
							}
							alvn.setVariableValue(ast);
							ast.setUsedBy(alvn);
							break;
						default:break;
					}
					break;
				// 55
				case Opcodes.LSTORE:
					switch(ain.getType()){
						case 2:
							VarInsnNode vin=(VarInsnNode)ain;
							ASTLocalVariableNode alvn=new ASTLocalVariableNode();
							alvn.setIndex(vin.var);
							alvn.setVariableType("Long");
							afn.setUsedAsObject(alvn);
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
								ast=currentLabelNode;
							}else{
								ast=executionStack.pop();
							}
							alvn.setVariableValue(ast);
							ast.setUsedBy(alvn);
						break;	
						default:break;
					}
					break;
				// 56
				case Opcodes.FSTORE:
					switch(ain.getType()){
					case 2:
						VarInsnNode vin=(VarInsnNode)ain;
						ASTLocalVariableNode alvn=new ASTLocalVariableNode();
						alvn.setIndex(vin.var);
						alvn.setVariableType("Float");
						afn.setUsedAsObject(alvn);
						ASTNode ast;
						if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;
						}else{
							ast=executionStack.pop();
						}
						alvn.setVariableValue(ast);
						ast.setUsedBy(alvn);
					break;	
					default:break;
					}
					break;
				// 58
				case Opcodes.ASTORE:
					switch(ain.getType()){
						case 2:
							VarInsnNode vin=(VarInsnNode)ain;
							ASTLocalVariableNode alvn=new ASTLocalVariableNode();
							alvn.setIndex(vin.var);
							alvn.setVariableType("Object");
							afn.setUsedAsObject(alvn);
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
								ast=currentLabelNode;
							}else{
								ast=executionStack.pop();
							}
							alvn.setVariableValue(ast);
							ast.setUsedBy(alvn);
						break;	
						default:break;
					}
					break;
				// 79
				case Opcodes.IASTORE:
					switch(ain.getType()){
					case 0:
						ASTNode value;
						ASTNode index;
						ASTNode array;
						
						if(newLabel==2 && executionStack.isEmpty()){
							value=currentLabelNode;
						}else{
							value=executionStack.pop();
						}
						if(newLabel==2 && executionStack.isEmpty()){
							index=currentLabelNode;
						}else{
							index=executionStack.pop();
						}
						if(newLabel==2 && executionStack.isEmpty()){
							array=currentLabelNode;
						}else{
							array=executionStack.pop();
						}
						ASTArrayValueNode aavn=new ASTArrayValueNode();
						aavn.setValueNode(value);
						value.setUsedBy(aavn);
						aavn.setValueIndex(index);
						index.setUsedBy(aavn);
						array.setUsedAsObject(aavn);
						aavn.setUsedBy(array);
						array.setSignature("Int Array");
						break;
					default:break;
					}
					break;
				// 83
				case Opcodes.AASTORE:
					switch(ain.getType()){
					case 0:
						ASTNode value;
						ASTNode index;
						ASTNode array;
						
						if(newLabel==2 && executionStack.isEmpty()){
							value=currentLabelNode;
						}else{
							value=executionStack.pop();
						}
						if(newLabel==2 && executionStack.isEmpty()){
							index=currentLabelNode;
						}else{
							index=executionStack.pop();
						}
						if(newLabel==2 && executionStack.isEmpty()){
							array=currentLabelNode;
						}else{
							array=executionStack.pop();
						}
						ASTArrayValueNode aavn=new ASTArrayValueNode();
						aavn.setValueNode(value);
						value.setUsedBy(aavn);
						aavn.setValueIndex(index);
						index.setUsedBy(aavn);
						array.setUsedAsObject(aavn);
						aavn.setUsedBy(array);
						array.setSignature("Object Array");
						break;
					default:break;
					}
					break;
				// 87
				case Opcodes.POP:
					if(newLabel==2 && executionStack.isEmpty()){
						
					}else{
					executionStack.pop();
					}
					break;
				// 89
				case Opcodes.DUP:
					switch(ain.getType()){
						case 0:
							ASTNode get;
							if(newLabel==2 && executionStack.isEmpty()){
							get=currentLabelNode;
							}else{
							get=executionStack.pop();
							}
							ASTNode dup=get;
							executionStack.push(dup);
							executionStack.push(get);
							break;
						default:break;
					}
					break;
				// 96
				case Opcodes.IADD:
					switch(ain.getType()){
						case 0:
							ASTArithmeticNode aatn=new ASTArithmeticNode();
							ASTNode second;
							if(newLabel==2 && executionStack.isEmpty()){
								second=currentLabelNode;
							}else{
								second=executionStack.pop();
							}
							second.setUsedBy(aatn);
							aatn.setSecondOperant(second);
							ASTNode first;
							if(newLabel==2 && executionStack.isEmpty()){
								first=currentLabelNode;
							}else{
								first=executionStack.pop();
							}
							first.setUsedBy(aatn);
							aatn.setFirstOperand(first);
							aatn.setArithmeticOperator("+");
							aatn.setArithmeticType("Int");
							executionStack.push(aatn);
							break;
						default:break;
					}
					break;
				// 97
				case Opcodes.LADD:
					switch(ain.getType()){
						case 0:
							ASTArithmeticNode aatn=new ASTArithmeticNode();
							ASTNode second;
							if(newLabel==2 && executionStack.isEmpty()){
								second=currentLabelNode;
							}else{
								second=executionStack.pop();
							}
							second.setUsedBy(aatn);
							aatn.setSecondOperant(second);
							ASTNode first;
							if(newLabel==2 && executionStack.isEmpty()){
								first=currentLabelNode;
							}else{
								first=executionStack.pop();
							}
							first.setUsedBy(aatn);
							aatn.setFirstOperand(first);
							aatn.setArithmeticOperator("+");
							aatn.setArithmeticType("Long");
							executionStack.push(aatn);
							break;
						default:break;
					}
					break;
				// 100
				case Opcodes.ISUB:
					switch(ain.getType()){
						case 0:
							ASTNode second;
							if(newLabel==2 && executionStack.isEmpty()){
								second=currentLabelNode;
							}else{
								second=executionStack.pop();
							}
							ASTArithmeticNode aatn=new ASTArithmeticNode();
							second.setUsedBy(aatn);
							aatn.setSecondOperant(second);
							ASTNode first;
							if(newLabel==2 && executionStack.isEmpty()){
								first=currentLabelNode;
							}else{
								first=executionStack.pop();
							}
							aatn.setFirstOperand(first);
							aatn.setArithmeticType("Int");
							first.setUsedBy(aatn);
							aatn.setArithmeticOperator("-");
							
							executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 101
				case Opcodes.LSUB:
					switch(ain.getType()){
						case 0:
							ASTNode second;
							if(newLabel==2 && executionStack.isEmpty()){
								second=currentLabelNode;
							}else{
								second=executionStack.pop();
							}
							ASTArithmeticNode aatn=new ASTArithmeticNode();
							second.setUsedBy(aatn);
							aatn.setSecondOperant(second);
							ASTNode first;
							if(newLabel==2 && executionStack.isEmpty()){
								first=currentLabelNode;
							}else{
								first=executionStack.pop();
							}
							aatn.setFirstOperand(first);
							first.setUsedBy(aatn);
							aatn.setArithmeticOperator("-");
							aatn.setArithmeticType("Long");
							
							executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 104
				case Opcodes.IMUL:
					switch(ain.getType()){
						case 0:
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
							first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("*");
						aatn.setArithmeticType("Int");
						
						executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 105
				case Opcodes.LMUL:
					switch(ain.getType()){
					case 0:
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
						first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("*");
						aatn.setArithmeticType("Long");
						executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 106
				case Opcodes.FMUL:
					switch(ain.getType()){
					case 0:
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
						first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("*");
						aatn.setArithmeticType("Float");
						executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 107
				case Opcodes.DMUL:
					switch(ain.getType()){
					case 0:
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
						first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("*");
						aatn.setArithmeticType("Double");
						executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 108
				case Opcodes.IDIV:
					switch(ain.getType()){
						case 0:
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
							first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("/");
						aatn.setArithmeticType("Int");
						
						executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 109
				case Opcodes.LDIV:
					switch(ain.getType()){
					case 0:
					ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
							first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("/");
						aatn.setArithmeticType("Long");
						executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 111
				case Opcodes.DDIV:
					switch(ain.getType()){
					case 0:
					ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
							first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("/");
						aatn.setArithmeticType("Double");
						executionStack.push(aatn);
						break;
						default:break;
					}
				break;
				// 112
				case Opcodes.IREM:
					switch(ain.getType()){
					case 0:
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
							first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("%");
						aatn.setArithmeticType("Int");
					
						executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 126
				case Opcodes.IAND:
					switch(ain.getType()){
					case 0:
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
							first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("&&");
						aatn.setArithmeticType("Int");
						executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 132
				case Opcodes.IINC:
					//Integer increment, no stack operation, ignore
					break;
				// 133
				case Opcodes.I2L:
					switch(ain.getType()){
						case 0:
						ASTNode cast;
						if(newLabel==2 && executionStack.isEmpty()){
							cast=currentLabelNode;
						}else{
							cast=executionStack.pop();
						}
						ASTCastNode acn=new ASTCastNode();
						acn.setOriginalCast("Int");
						acn.setConvertedCast("Long");
						cast.setUsedBy(acn);
						executionStack.push(acn);
						break;
						default:break;
					}
					break;
				// 134
				case Opcodes.I2F:
					switch(ain.getType()){
					case 0:
					ASTNode cast;
					if(newLabel==2 && executionStack.isEmpty()){
						cast=currentLabelNode;
					}else{
						cast=executionStack.pop();
					}
					ASTCastNode acn=new ASTCastNode();
					acn.setOriginalCast("Int");
					acn.setConvertedCast("Float");
					cast.setUsedBy(acn);
					executionStack.push(acn);
					break;
					default:break;
					}
					break;
				// 135
				case Opcodes.I2D:
					switch(ain.getType()){
					case 0:
						ASTNode cast;
						if(newLabel==2 && executionStack.isEmpty()){
							cast=currentLabelNode;
						}else{
							cast=executionStack.pop();
						}
						ASTCastNode acn=new ASTCastNode();
						acn.setOriginalCast("Int");
						acn.setConvertedCast("Double");
						cast.setUsedBy(acn);
						executionStack.push(acn);
						break;
					default:break;
					}
					break;
				// 136
				case Opcodes.L2I:
					switch(ain.getType()){
						case 0:
							ASTNode cast;
							if(newLabel==2 && executionStack.isEmpty()){
								cast=currentLabelNode;
							}else{
								cast=executionStack.pop();
							}
							ASTCastNode acn=new ASTCastNode();
							acn.setOriginalCast("Long");
							acn.setConvertedCast("Int");
							cast.setUsedBy(acn);
							executionStack.push(acn);
						break;
						default:break;
					}
					break;
				// 139
				case Opcodes.F2I:
					switch(ain.getType()){
					case 0:
						ASTNode cast;
						if(newLabel==2 && executionStack.isEmpty()){
							cast=currentLabelNode;
						}else{
							cast=executionStack.pop();
						}
						ASTCastNode acn=new ASTCastNode();
						acn.setOriginalCast("Float");
						acn.setConvertedCast("Int");
						cast.setUsedBy(acn);
						executionStack.push(acn);
						break;
						default:break;
					}
					break;
				// 142
				case Opcodes.D2I:
					switch(ain.getType()){
					case 0:
						ASTNode cast;
						if(newLabel==2 && executionStack.isEmpty()){
							cast=currentLabelNode;
						}else{
							cast=executionStack.pop();
						}
						ASTCastNode acn=new ASTCastNode();
						acn.setOriginalCast("Double");
						acn.setConvertedCast("Int");
						cast.setUsedBy(acn);
						executionStack.push(acn);
						break;
						default:break;
					}
					break;
				// 148
				case Opcodes.LCMP:
					switch(ain.getType()){
					case 0:
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						ASTArithmeticNode aatn=new ASTArithmeticNode();
						second.setUsedBy(aatn);
						aatn.setSecondOperant(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
							first=executionStack.pop();
						}
						aatn.setFirstOperand(first);
						first.setUsedBy(aatn);
						aatn.setArithmeticOperator("==");
						aatn.setArithmeticType("Long");
						executionStack.push(aatn);
						break;
						default:break;
					}
					break;
				// 153
				case Opcodes.IFEQ:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
								ast=currentLabelNode;
							}else{
								ast=executionStack.pop();
							}
							ast.setUsedBy(ajn);
							ajn.setFirstOperand(ast);
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("0");
							acn.setConstantType("Int");
							acn.setUsedBy(ajn);
							ajn.setSecondOperand(acn);
							ajn.setCompare("==");
							ajn.setTrueLabel(temp.getLabel().toString());
							break;
						default:break;
					}
					break;
				// 154
				case Opcodes.IFNE:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
								ast=currentLabelNode;
							}else{
								ast=executionStack.pop();
							}
							ast.setUsedBy(ajn);
							ajn.setFirstOperand(ast);
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("0");
							acn.setConstantType("Int");
							acn.setUsedBy(ajn);
							ajn.setSecondOperand(acn);
							ajn.setCompare("!=");
							ajn.setTrueLabel(temp.getLabel().toString());
						break;
						default:break;
					}
					break;
				// 155
				case Opcodes.IFLT:
					switch(ain.getType()){
					case 7:
					JumpInsnNode jin=(JumpInsnNode)ain;
					LabelNode temp=(LabelNode)jin.label;
					ASTJumpNode ajn=new ASTJumpNode();
					ASTNode ast;
					if(newLabel==2 && executionStack.isEmpty()){
						ast=currentLabelNode;
					}else{
						ast=executionStack.pop();
					}
					ast.setUsedBy(ajn);
					ajn.setFirstOperand(ast);
					ASTConstantNode acn=new ASTConstantNode();
					acn.setConstantValue("0");
					acn.setConstantType("Int");
					acn.setUsedBy(ajn);
					ajn.setSecondOperand(acn);
					ajn.setCompare("<");
					ajn.setTrueLabel(temp.getLabel().toString());
					break;
					default:break;
					}
					break;
				// 156
				case Opcodes.IFGE:
					switch(ain.getType()){
						case 7:
						JumpInsnNode jin=(JumpInsnNode)ain;
						LabelNode temp=(LabelNode)jin.label;
						ASTJumpNode ajn=new ASTJumpNode();
						ASTNode ast;
						if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;
						}else{
							ast=executionStack.pop();
						}
						ast.setUsedBy(ajn);
						ajn.setFirstOperand(ast);
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("0");
						acn.setConstantType("Int");
						acn.setUsedBy(ajn);
						ajn.setSecondOperand(acn);
						ajn.setCompare(">=");
						ajn.setTrueLabel(temp.getLabel().toString());
						break;
						default:break;
					}
					break;
				// 157
				case Opcodes.IFGT:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
								ast=currentLabelNode;
							}else{
								ast=executionStack.pop();
							}
							ast.setUsedBy(ajn);
							ajn.setFirstOperand(ast);
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("0");
							acn.setConstantType("Int");
							acn.setUsedBy(ajn);
							ajn.setSecondOperand(acn);
							ajn.setCompare(">");
							ajn.setTrueLabel(temp.getLabel().toString());
							break;
						default:break;
					}
					break;
				// 158
				case Opcodes.IFLE:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
								ast=currentLabelNode;
							}else{
								ast=executionStack.pop();
							}
							ast.setUsedBy(ajn);
							ajn.setFirstOperand(ast);
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("0");
							acn.setConstantType("Int");
							acn.setUsedBy(ajn);
							ajn.setSecondOperand(acn);
							ajn.setCompare("<=");
							ajn.setTrueLabel(temp.getLabel().toString());
						break;
						default:break;
					}
					break;
				// 159
				case Opcodes.IF_ICMPEQ:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode second;
							if(newLabel==2 && executionStack.isEmpty()){
								second=currentLabelNode;
							}else{
								second=executionStack.pop();
							}
							second.setUsedBy(ajn);
							ajn.setSecondOperand(second);
							ASTNode first;
							if(newLabel==2 && executionStack.isEmpty()){
								first=currentLabelNode;
							}else{
								first=executionStack.pop();
							}
							first.setUsedBy(ajn);
							ajn.setFirstOperand(first);
							ajn.setCompare("==");
							ajn.setTrueLabel(temp.getLabel().toString());
						break;
						default:break;
					}
					break;
				// 160
				case Opcodes.IF_ICMPNE:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode second;
							if(newLabel==2 && executionStack.isEmpty()){
								second=currentLabelNode;
							}else{
								second=executionStack.pop();
							}
							second.setUsedBy(ajn);
							ajn.setSecondOperand(second);
							ASTNode first;
							if(newLabel==2 && executionStack.isEmpty()){
								first=currentLabelNode;
							}else{
								first=executionStack.pop();
							}
							first.setUsedBy(ajn);
							ajn.setFirstOperand(first);
							ajn.setCompare("!=");
							ajn.setTrueLabel(temp.getLabel().toString());
							
							break;
						default:break;
					}
					break;
				// 161
				case Opcodes.IF_ICMPLT:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode second;
							if(newLabel==2 && executionStack.isEmpty()){
								second=currentLabelNode;
							}else{
								second=executionStack.pop();
							}
							second.setUsedBy(ajn);
							ajn.setSecondOperand(second);
							ASTNode first;
							if(newLabel==2 && executionStack.isEmpty()){
								first=currentLabelNode;
							}else{
								first=executionStack.pop();
							}
							first.setUsedBy(ajn);
							ajn.setFirstOperand(first);
							ajn.setCompare("<");
							ajn.setTrueLabel(temp.getLabel().toString());
							break;
						default:break;
					}
					break;
				// 162
				case Opcodes.IF_ICMPGE:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode second;
							if(newLabel==2 && executionStack.isEmpty()){
								second=currentLabelNode;
							}else{
								second=executionStack.pop();
							}
							second.setUsedBy(ajn);
							ajn.setSecondOperand(second);
							ASTNode first;
							if(newLabel==2 && executionStack.isEmpty()){
								first=currentLabelNode;
							}else{
								first=executionStack.pop();
							}
							first.setUsedBy(ajn);
							ajn.setFirstOperand(first);
							ajn.setCompare(">=");
							ajn.setTrueLabel(temp.getLabel().toString());
						
							break;
						default:break;
					}
					break;
				// 164
				case Opcodes.IF_ICMPLE:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode second;
							if(newLabel==2 && executionStack.isEmpty()){
								second=currentLabelNode;
							}else{
								second=executionStack.pop();
							}
							second.setUsedBy(ajn);
							ajn.setSecondOperand(second);
							ASTNode first;
							if(newLabel==2 && executionStack.isEmpty()){
								first=currentLabelNode;
							}else{
								first=executionStack.pop();
							}
							first.setUsedBy(ajn);
							ajn.setFirstOperand(first);
							ajn.setCompare("<=");
							ajn.setTrueLabel(temp.getLabel().toString());
						
							break;
						default:break;
					}
					break;
				// 165
				case Opcodes.IF_ACMPEQ:
					switch(ain.getType()){
					case 7:
						JumpInsnNode jin=(JumpInsnNode)ain;
						LabelNode temp=(LabelNode)jin.label;
						ASTJumpNode ajn=new ASTJumpNode();
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						second.setUsedBy(ajn);
						ajn.setSecondOperand(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
							first=executionStack.pop();
						}
						first.setUsedBy(ajn);
						ajn.setFirstOperand(first);
						ajn.setCompare("==");
						ajn.setTrueLabel(temp.getLabel().toString());
					
						break;
					default:break;
				}
				break;
				// 166
				case Opcodes.IF_ACMPNE:
					switch(ain.getType()){
					case 7:
						JumpInsnNode jin=(JumpInsnNode)ain;
						LabelNode temp=(LabelNode)jin.label;
						ASTJumpNode ajn=new ASTJumpNode();
						ASTNode second;
						if(newLabel==2 && executionStack.isEmpty()){
							second=currentLabelNode;
						}else{
							second=executionStack.pop();
						}
						second.setUsedBy(ajn);
						ajn.setSecondOperand(second);
						ASTNode first;
						if(newLabel==2 && executionStack.isEmpty()){
							first=currentLabelNode;
						}else{
							first=executionStack.pop();
						}
						first.setUsedBy(ajn);
						ajn.setFirstOperand(first);
						ajn.setCompare("!=");
						ajn.setTrueLabel(temp.getLabel().toString());
					
						break;
					default:break;
				}
				break;
				// 167
				case Opcodes.GOTO:
					// GOTO is not needed for this analysis, ignore
					break;
				// 170
				case Opcodes.TABLESWITCH:
					switch(ain.getType()){
					case 11:
						TableSwitchInsnNode tsin=(TableSwitchInsnNode)ain;
						ASTSwitchNode asn=new ASTSwitchNode();
						asn.setMaxValue(tsin.max);
						asn.setMinValue(tsin.min);
						ASTLabelNode aln=new ASTLabelNode();
						aln.setLabel(tsin.dflt.getLabel().toString());
						aln.setUsedBy(asn);
						asn.setDefaultLabel(aln);
						for(Object ob:tsin.labels){
							LabelNode ln=(LabelNode)ob;
							ASTLabelNode tempALN=new ASTLabelNode();
							tempALN.setLabel(ln.getLabel().toString());
							tempALN.setUsedBy(asn);
							asn.addLabel(tempALN);
						}
						ASTNode check;
						if(newLabel==2 && executionStack.isEmpty()){
							check=currentLabelNode;
						}else{
							check=executionStack.pop();
						}
						asn.setCheckValue(check);
						executionStack.push(asn);
						break;
					default:break;
					}
					break;
				// 172
				case Opcodes.IRETURN:
					switch(ain.getType()){
						case 0:
							ASTReturnNode arn=new ASTReturnNode();
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
								ast=currentLabelNode;
							}else{
								ast=executionStack.pop();
							}
							ast.setUsedBy(arn);
							arn.setReturnType("Int");
							arn.setReturnValue(arn);
							break;
						default:break;
					}
					break;
				// 173
				case Opcodes.LRETURN:
					switch(ain.getType()){
					case 0:
						ASTReturnNode arn=new ASTReturnNode();
						ASTNode ast;
						if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;
						}else{
							ast=executionStack.pop();
						}
						ast.setUsedBy(arn);
						arn.setReturnType("Long");
						arn.setReturnValue(arn);
						break;
						default:break;
					}
					break;
				// 174
				case Opcodes.FRETURN:
					switch(ain.getType()){
					case 0:
						ASTReturnNode arn=new ASTReturnNode();
						ASTNode ast;
						if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;
						}else{
							ast=executionStack.pop();
						}
						ast.setUsedBy(arn);
						arn.setReturnType("Float");
						arn.setReturnValue(arn);
						break;
						default:break;
					}
					break;
				// 176
				case Opcodes.ARETURN:
					switch(ain.getType()){
						case 0:
							ASTReturnNode arn=new ASTReturnNode();
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
								ast=currentLabelNode;
							}else{
								ast=executionStack.pop();
							}
							ast.setUsedBy(arn);
							arn.setReturnType("Object");
							arn.setReturnValue(arn);
							break;
						default:break;
					}
					break;
				// 177
				case Opcodes.RETURN:
					switch(ain.getType()){
					// just return empty to end the function, ignore
						case 0:break;
						default:break;
					}
					break;
				// 178
				case Opcodes.GETSTATIC:
					switch(ain.getType()){
					case 4:
						FieldInsnNode fin=(FieldInsnNode)ain;
						ASTFieldNode fien=new ASTFieldNode();
							
						fien.setCallBy(afn);
						fien.setName(fin.name);
						fien.setOwner(fin.owner);
						fien.setSignature(fin.desc);
						afn.setUsedAsObject(fien);
						
						executionStack.push(fien);		
						break;
					default:break;
					}
					break;
				// 179
				case Opcodes.PUTSTATIC:
					switch(ain.getType()){
					case 4:
						FieldInsnNode fin=(FieldInsnNode)ain;
						ASTFieldNode fien=new ASTFieldNode();
						ASTNode ast;
						if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;
						}else{
							ast=executionStack.pop();
						}
						ast.setUsedBy(fien);
						fien.setCallBy(afn);
						fien.setName(fin.name);
						fien.setOwner(fin.owner);
						fien.setSignature(fin.desc);
						fien.setFieldValue(ast);
						
						afn.addChild(fien);
						break;
					default:break;
					}
					break;
				// 180
				case Opcodes.GETFIELD:
					switch(ain.getType()){
						case 4:
							FieldInsnNode fin=(FieldInsnNode)ain;
							ASTFieldNode fien=new ASTFieldNode();
							ASTNode temp;
							if(newLabel==2 && executionStack.isEmpty()){
								temp=currentLabelNode;
							}else{
								temp=executionStack.pop();		
							}
							fien.setCallBy(temp);
							fien.setName(fin.name);
							fien.setOwner(fin.owner);
							fien.setSignature(fin.desc);
							temp.setUsedAsObject(fien);
							
							executionStack.push(fien);		
							break;
						default:break;
					}
					break;
				// 181
				case Opcodes.PUTFIELD:
					switch(ain.getType()){
						case 4: 
							FieldInsnNode fin=(FieldInsnNode)ain;
							ASTFieldNode fien=new ASTFieldNode();
							ASTNode temp;
							if(newLabel==2 && executionStack.isEmpty()){
								temp=currentLabelNode;
							}else{
								temp=executionStack.pop();
							}
							temp.setUsedBy(fien);
							fien.setFieldValue(temp);
							ASTNode temp2;
							if(newLabel==2 && executionStack.isEmpty()){
								temp2=currentLabelNode;
							}else{
								temp2=executionStack.pop();
							}
							temp2.setUsedAsObject(fien);
							fien.setCallBy(temp2);
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
							MethodInsnNode min=(MethodInsnNode)ain;
							ASTMethodNode amn=new ASTMethodNode();
							amn.setName(min.name);
							amn.setSignature(min.desc);
							amn.setOwner(min.owner);
							Type[] args=Type.getArgumentTypes(min.desc);							
							for(int i=0;i<args.length;i++){
								ASTNode temp;
								if(newLabel==2 && executionStack.isEmpty()){
									temp=currentLabelNode;
								}else{
									temp=executionStack.pop();
								}
								temp.setUsedBy(amn);
								amn.addParameter(temp);
							}
							ASTNode temp;
							if(newLabel==2 && executionStack.isEmpty()){
								temp=currentLabelNode;
							}else{
								temp=executionStack.pop();
							}
							temp.setUsedAsObject(amn);
							amn.setCallBy(temp);
							Type returnType=Type.getReturnType(min.desc);
						
							if(!returnType.toString().equals("V")){
								ASTObjectNode returnObject=new ASTObjectNode();
								returnObject.setObjectType(returnType.toString());
								amn.setReturnValue(returnObject);
								executionStack.push(amn);
							}
							
							afn.addChild(amn);
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
							amn.setOwner(min.owner);
							Type[] args=Type.getArgumentTypes(min.desc);							
							for(int i=0;i<args.length;i++){
								ASTNode temp;
								if(newLabel==2 && executionStack.isEmpty()){
									temp=currentLabelNode;
								}else{
									temp=executionStack.pop();
								}
								temp.setUsedBy(amn);
								amn.addParameter(temp);
							}
							ASTNode temp;
							if(newLabel==2 && executionStack.isEmpty()){
								temp=currentLabelNode;
							}else{
								temp=executionStack.pop();
							}
							temp.setUsedAsObject(amn);
							amn.setCallBy(temp);
							Type returnType=Type.getReturnType(min.desc);
							
							if(!returnType.toString().equals("V")){
							ASTObjectNode returnObject=new ASTObjectNode();
							returnObject.setObjectType(returnType.toString());
							amn.setReturnValue(returnObject);
							executionStack.push(amn);
							}
							
							afn.addChild(amn);
							
							break;
						default:break;	
					}
					break;
				// 184
				case Opcodes.INVOKESTATIC:
					switch(ain.getType()){
						case 5:
							MethodInsnNode min=(MethodInsnNode)ain;
							ASTMethodNode amn=new ASTMethodNode();
							amn.setName(min.name);
							amn.setSignature(min.desc);
							amn.setOwner(min.owner);
							Type[] args=Type.getArgumentTypes(min.desc);							
							for(int i=0;i<args.length;i++){
								ASTNode temp;
								if(newLabel==2 && executionStack.isEmpty()){
									temp=currentLabelNode;
								}else{
									temp=executionStack.pop();
								}
								temp.setUsedBy(amn);
								amn.addParameter(temp);
							}
							amn.setCallBy(afn);
							Type returnType=Type.getReturnType(min.desc);
							
							if(!returnType.toString().equals("V")){
							ASTObjectNode returnObject=new ASTObjectNode();
							returnObject.setObjectType(returnType.toString());
							amn.setReturnValue(returnObject);
							executionStack.push(amn);
							}
							
							afn.addChild(amn);
							break;
						default: break;
					}
					break;
				// 185
				case Opcodes.INVOKEINTERFACE:
					switch(ain.getType()){
						case 5:
							MethodInsnNode min=(MethodInsnNode)ain;
							ASTMethodNode amn=new ASTMethodNode();
							amn.setName(min.name);
							amn.setSignature(min.desc);
							amn.setOwner(min.owner);
							Type[] args=Type.getArgumentTypes(min.desc);							
							for(int i=0;i<args.length;i++){
								ASTNode temp;
								if(newLabel==2 && executionStack.isEmpty()){
									temp=currentLabelNode;
								}else{
									temp=executionStack.pop();
								}
								temp.setUsedBy(amn);
								amn.addParameter(temp);
							}
							ASTNode temp;
							if(newLabel==2 && executionStack.isEmpty()){
								temp=currentLabelNode;
							}else{
								temp=executionStack.pop();
							}
							temp.setUsedAsObject(amn);
							amn.setCallBy(temp);
							Type returnType=Type.getReturnType(min.desc);
						
							if(!returnType.toString().equals("V")){
								ASTObjectNode returnObject=new ASTObjectNode();
								returnObject.setObjectType(returnType.toString());
								amn.setReturnValue(returnObject);
								executionStack.push(amn);
							}
						
							afn.addChild(amn);
						
							break;
						default:break;	
					}
					break;
				// 187
				case Opcodes.NEW:
					switch(ain.getType()){
						case 3:
							TypeInsnNode tin=(TypeInsnNode)ain;
							ASTObjectNode aon=new ASTObjectNode();
							aon.setObjectType(tin.desc);
							aon.setCallBy(afn);
							afn.setUsedAsObject(aon);
							executionStack.push(aon);
							break;
						default:break;
					}
					break;
				// 188
				case Opcodes.NEWARRAY:
					switch(ain.getType()){
					case 1:
						IntInsnNode iin=(IntInsnNode)ain;
						int type=iin.operand;
						ASTArrayNode aan=new ASTArrayNode();
						ASTNode size;
						if(newLabel==2 && executionStack.isEmpty()){
							size=currentLabelNode;
						}else{
							size=executionStack.pop();
						}
						aan.setArraySize(size);
						size.setUsedBy(aan);
						aan.setArrayType(type+"");
						executionStack.push(aan);
						break;
					default:break;
					}
					break;
				// 189
				case Opcodes.ANEWARRAY:
					switch(ain.getType()){
						case 3:
							TypeInsnNode tin=(TypeInsnNode)ain;
							ASTArrayNode aan=new ASTArrayNode();
							aan.setArrayType(tin.desc);
							ASTNode size;
							if(newLabel==2 && executionStack.isEmpty()){
								size=currentLabelNode;
							}else{
								size=executionStack.pop();
							}
							size.setUsedBy(aan);
							aan.setArraySize(size);
							executionStack.push(aan);
							break;
						default:break;
					}
					break;
				// 190
				case Opcodes.ARRAYLENGTH:
					switch(ain.getType()){
					case 0:
						ASTNode array;
						if(newLabel==2 && executionStack.isEmpty()){
							array=currentLabelNode;
						}else{
							array=executionStack.pop();
						}
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("array length");
						array.setUsedAsObject(acn);
						acn.setUsedBy(acn);
						executionStack.push(acn);
						break;
					default:break;
					}
					break;
				// 191
				case Opcodes.ATHROW:
					switch(ain.getType()){
					case 0:
						ASTReturnNode arn=new ASTReturnNode();
						ASTNode ast;
						if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;
						}else{
							ast=executionStack.pop();
						}
						ast.setUsedBy(arn);
						arn.setReturnType("Object Throw");
						arn.setReturnValue(arn);
						break;
						default:break;
					}
					break;
				// 192
				case Opcodes.CHECKCAST:
					switch(ain.getType()){
					case 3:
						TypeInsnNode tin=(TypeInsnNode)ain;
						ASTCastNode acn=new ASTCastNode();
						acn.setOriginalCast("Explicit");
						acn.setConvertedCast(tin.desc);
						ASTNode ast;
						if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;
						}else{
							ast=executionStack.pop();
						}
						ast.setUsedBy(acn);
						executionStack.push(acn);
						break;
						default:break;
					}
					break;
				// 193
				case Opcodes.INSTANCEOF:
					switch(ain.getType()){
						case 3:
							TypeInsnNode tin=(TypeInsnNode)ain;
							ASTConstantNode acn=new ASTConstantNode();
							ASTNode check;
							if(newLabel==2 && executionStack.isEmpty()){
								check=currentLabelNode;
							}else{
								check=executionStack.pop();
							}
							check.setUsedBy(acn);
							acn.setConstantType(tin.desc);
							acn.setConstantValue("InstanceOf");
							acn.setCallBy(check);
							executionStack.push(acn);
							break;
						default:break;
					}
					break;
				// 194
				case Opcodes.MONITORENTER:
					switch(ain.getType()){
					case 0:
						ASTMethodNode amn=new ASTMethodNode();
						amn.setName("Synchronized Lock");
						ASTNode lock;
						if(newLabel==2 && executionStack.isEmpty()){
							lock=currentLabelNode;
						}else{
							lock=executionStack.pop();
						}
						lock.setUsedBy(amn);
						amn.addParameter(lock);
						break;
					default:break;
					}
					break;
				// 195
				case Opcodes.MONITOREXIT:
					switch(ain.getType()){
					case 0:
						ASTMethodNode amn=new ASTMethodNode();
						amn.setName("Synchronized Unlock");
						ASTNode lock;
						if(newLabel==2 && executionStack.isEmpty()){
							lock=currentLabelNode;
						}else{
							lock=executionStack.pop();
						}
						lock.setUsedBy(amn);
						amn.addParameter(lock);
						break;
					default:break;
					}
					break;
				// 198
				case Opcodes.IFNULL:
					switch(ain.getType()){
						case 7:
							JumpInsnNode jin=(JumpInsnNode)ain;
							LabelNode temp=(LabelNode)jin.label;
							ASTJumpNode ajn=new ASTJumpNode();
							ASTNode ast;
							if(newLabel==2 && executionStack.isEmpty()){
								ast=currentLabelNode;
							}else{
								ast=executionStack.pop();
							}
							ast.setUsedBy(ajn);
							ajn.setFirstOperand(ast);
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue("NULL");
							acn.setConstantType("NULL");
							acn.setUsedBy(ajn);
							ajn.setSecondOperand(acn);
							ajn.setCompare("==");
							ajn.setTrueLabel(temp.getLabel().toString());
						default:break;
					}
					break;
				// 199
				case Opcodes.IFNONNULL:
					switch(ain.getType()){
					case 7:
						JumpInsnNode jin=(JumpInsnNode)ain;
						LabelNode temp=(LabelNode)jin.label;
						ASTJumpNode ajn=new ASTJumpNode();
						ASTNode ast;
						if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;
						}else{
							ast=executionStack.pop();
						}
						ast.setUsedBy(ajn);
						ajn.setFirstOperand(ast);
						ASTConstantNode acn=new ASTConstantNode();
						acn.setConstantValue("NULL");
						acn.setConstantType("NULL");
						acn.setUsedBy(ajn);
						ajn.setSecondOperand(acn);
						ajn.setCompare("!=");
						ajn.setTrueLabel(temp.getLabel().toString());
						default:break;
					}
					break;
				default:
					System.out.println(ain.getOpcode());
					System.out.println(ain.getType());
					break;
				}
			}
	}

}
