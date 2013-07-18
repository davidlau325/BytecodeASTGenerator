package bytecodeAST;

import java.util.Iterator;
import java.util.Stack;
import java.util.HashMap;
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
	private void loadConstant(String type,String value,int uniqueIdentifier){
		ASTConstantNode acn=new ASTConstantNode();
		acn.setConstantValue(value);
		acn.setConstantType(type);
		acn.setName(uniqueIdentifier+"");
		this.thisAST.setUsedAsObject(acn);
		executionStack.push(acn);
	}
	
	private void jumpIfTwoValue(AbstractInsnNode ain,int newLabel,String compare){
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
		ajn.setCompare(compare);
		ajn.setTrueLabel(temp.getLabel().toString());
	}
	
	private void castValue(int newLabel,String original,String converted){
		ASTNode cast;
		if(newLabel==2 && executionStack.isEmpty()){
			cast=currentLabelNode;
		}else{
			cast=executionStack.pop();
		}
		ASTCastNode acn=new ASTCastNode();
		acn.setOriginalCast(original);
		acn.setConvertedCast(converted);
		cast.setUsedBy(acn);
		executionStack.push(acn);
	}
	
	private void loadArithmetic(int newLabel,String operator,String type,int uniqueIdentifier){
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
		aatn.setArithmeticOperator(operator);
		aatn.setArithmeticType(type);
		aatn.setArithmeticName(uniqueIdentifier+"");
		executionStack.push(aatn);
	}
	private void singleStore(AbstractInsnNode ain,int newLabel,String type,HashMap<Integer,ASTLocalVariableNode> localVariable,int uniqueIdentifier){
		VarInsnNode vin=(VarInsnNode)ain;
		ASTNode ast;
		if(newLabel==2 && executionStack.isEmpty()){
			ast=currentLabelNode;
		}else{
			ast=executionStack.pop();
		}
		if(localVariable.containsKey((Integer)vin.var)){
			ASTLocalVariableNode alvn=localVariable.get((Integer)vin.var);
			alvn.setVariableValue(ast);
			ast.setUsedBy(alvn);
		}else{
		ASTLocalVariableNode alvn=new ASTLocalVariableNode();
		alvn.setIndex(vin.var);
		alvn.setName(uniqueIdentifier+"");
		alvn.setVariableType(type);
		alvn.setVariableValue(ast);
		ast.setUsedBy(alvn);
		localVariable.put((Integer)vin.var, alvn);
		}
	}
	private void arrayStore(int newLabel,String type,int uniqueIdentifier){
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
		aavn.setName(uniqueIdentifier+"");
		uniqueIdentifier++;
		array.setSignature(type);
	}
	private void singleLoad(AbstractInsnNode ain,String type,HashMap<Integer,ASTLocalVariableNode> localVariable,int uniqueIdentifier){
		VarInsnNode vin=(VarInsnNode)ain;
		if(localVariable.containsKey((Integer)vin.var)){
			ASTLocalVariableNode alvn=localVariable.get((Integer)vin.var);
			executionStack.push(alvn);
		}else{
		ASTLocalVariableNode alvn=new ASTLocalVariableNode();
		alvn.setIndex(vin.var);
		alvn.setVariableType(type);
		alvn.setName(uniqueIdentifier+"");
		localVariable.put((Integer)vin.var, alvn);
		executionStack.push(alvn);
		}
	}
	private void arrayLoad(int newLabel,String type,int uniqueIdentifier){
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
		aavn.setValueType(type);
		index.setUsedBy(aavn);
		aavn.setUsedBy(array);
		aavn.setName(uniqueIdentifier+"");
		array.setUsedAsObject(aavn);
		executionStack.push(aavn);
	}
	private void setReturn(int newLabel,String type,ASTFunctionNode afn){
		ASTReturnNode arn=new ASTReturnNode();
		ASTNode ast;
		if(newLabel==2 && executionStack.isEmpty()){
			ast=currentLabelNode;
		}else{
			ast=executionStack.pop();
		}
		ast.setUsedBy(arn);
		arn.setReturnType(type);
		arn.setReturnValue(ast);
		arn.setReturnFunction(afn);
	}
	private void setNeg(int newLabel,String type,int uniqueIdentifier){
		ASTNode get;
		if(newLabel==2 && executionStack.isEmpty()){
			get=currentLabelNode;
		}else{
			get=executionStack.pop();
		}
		ASTArithmeticNode aan=new ASTArithmeticNode();
		aan.setArithmeticOperator("-");
		aan.setSecondOperant(get);
		aan.setArithmeticType(type);
		get.setUsedBy(aan);
		aan.setArithmeticName(uniqueIdentifier+"");
		executionStack.push(aan);
	}
	public void generateFunctionAST(MethodNode mn,HashMap<String,ASTFieldNode> fieldVariable){
			
			int uniqueIdentifier=0;
			HashMap<Integer,ASTLocalVariableNode> localVariable=new HashMap<Integer,ASTLocalVariableNode>();
			ASTFunctionNode afn=new ASTFunctionNode();
			afn.setDesc(mn.desc);
			afn.setName(mn.name);
			afn.setSignature(mn.signature);
			for(Object ob:mn.exceptions){
				afn.setException(ob.toString());
			}
			this.thisAST=afn;
			InsnList insn=mn.instructions;
			Iterator itr=insn.iterator();
			int newLabel=0;
			while(itr.hasNext()){
				AbstractInsnNode ain=(AbstractInsnNode)itr.next();	
				System.out.println(ain.getOpcode()+" "+ain.getType()+" size:"+executionStack.size()+" flag:"+newLabel);
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
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 1
				case Opcodes.ACONST_NULL:
					{ loadConstant("NULL","NULL",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 2
				case Opcodes.ICONST_M1:
					{ loadConstant("Int","-1",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 3
				case Opcodes.ICONST_0:
					{ loadConstant("Int","0",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 4
				case Opcodes.ICONST_1:
					{ loadConstant("Int","1",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 5
				case Opcodes.ICONST_2:
					{ loadConstant("Int","2",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 6
				case Opcodes.ICONST_3:
					{ loadConstant("Int","3",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 7
				case Opcodes.ICONST_4:
					{ loadConstant("Int","4",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 8
				case Opcodes.ICONST_5:
					{ loadConstant("Int","5",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 9
				case Opcodes.LCONST_0:
					{ loadConstant("Long","0",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 10
				case Opcodes.LCONST_1:
					{loadConstant("Long","1",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 11
				case Opcodes.FCONST_0:
					{ loadConstant("Float","0",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 12
				case Opcodes.FCONST_1:
					{ loadConstant("Float","1",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 13
				case Opcodes.FCONST_2:
					{ loadConstant("Float","2",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 14
				case Opcodes.DCONST_0:
					{ loadConstant("Double","0",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 15
				case Opcodes.DCONST_1:
					{ loadConstant("Double","1",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 16
				case Opcodes.BIPUSH:
					switch(ain.getType()){
						case 1:
							IntInsnNode iin=(IntInsnNode)ain;
							ASTConstantNode acn=new ASTConstantNode();
							acn.setConstantValue(iin.operand+"");
							acn.setConstantType("Int");
							acn.setName(uniqueIdentifier+"");
							uniqueIdentifier++;
							afn.setUsedAsObject(acn);
							executionStack.push(acn);
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
							acn.setName(uniqueIdentifier+"");
							uniqueIdentifier++;
							afn.setUsedAsObject(acn);
							executionStack.push(acn);
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
							acn.setName(uniqueIdentifier+"");
							uniqueIdentifier++;
							executionStack.push(acn);
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 21
				case Opcodes.ILOAD:
					{
					singleLoad(ain,"Int",localVariable,uniqueIdentifier);
					uniqueIdentifier++;
					}
					break;
				// 22
				case Opcodes.LLOAD:
					{
					singleLoad(ain,"Long",localVariable,uniqueIdentifier);
					uniqueIdentifier++;
					}
					break;
				// 23
				case Opcodes.FLOAD:
					{
					singleLoad(ain,"Float",localVariable,uniqueIdentifier);
					uniqueIdentifier++;
					}
				break;
				// 24
				case Opcodes.DLOAD:
					{
					singleLoad(ain,"Double",localVariable,uniqueIdentifier);
					uniqueIdentifier++;
					}
				break;
				// 25
				case Opcodes.ALOAD: 
					{
					singleLoad(ain,"Object",localVariable,uniqueIdentifier);
					uniqueIdentifier++;
					}
					break;
				// 46
				case Opcodes.IALOAD:
					{ arrayLoad(newLabel,"Int",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 47
				case Opcodes.LALOAD:
					{ arrayLoad(newLabel,"Long",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 48
				case Opcodes.FALOAD:
					{ arrayLoad(newLabel,"Float",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 49
				case Opcodes.DALOAD:
					{ arrayLoad(newLabel,"Double",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 50
				case Opcodes.AALOAD:
					{ arrayLoad(newLabel,"Object",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 51
				case Opcodes.BALOAD:
					{ arrayLoad(newLabel,"Byte",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 52
				case Opcodes.CALOAD:
					{ arrayLoad(newLabel,"Char",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 53
				case Opcodes.SALOAD:
					{ arrayLoad(newLabel,"String",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 54
				case Opcodes.ISTORE:
					{ singleStore(ain,newLabel,"Int",localVariable,uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 55
				case Opcodes.LSTORE:
					{ singleStore(ain,newLabel,"Long",localVariable,uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 56
				case Opcodes.FSTORE:
					{ singleStore(ain,newLabel,"Float",localVariable,uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 57
				case Opcodes.DSTORE:
					{ singleStore(ain,newLabel,"Double",localVariable,uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 58
				case Opcodes.ASTORE:
					{ singleStore(ain,newLabel,"Object",localVariable,uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 79
				case Opcodes.IASTORE:
					{ arrayStore(newLabel,"Int Array",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 80
				case Opcodes.LASTORE:
					{ arrayStore(newLabel,"Long Array",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 81
				case Opcodes.FASTORE:
					{ arrayStore(newLabel,"Float Array",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 82
				case Opcodes.DASTORE:
					{ arrayStore(newLabel,"Double Array",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 83
				case Opcodes.AASTORE:
					{
					arrayStore(newLabel,"Object Array",uniqueIdentifier);
					uniqueIdentifier++;
					}
					break;
				// 84
				case Opcodes.BASTORE:
					{
					arrayStore(newLabel,"Byte Array",uniqueIdentifier);
					uniqueIdentifier++;
					}
				break;
				// 85
				case Opcodes.CASTORE:
					{ arrayStore(newLabel,"Char Array",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 86
				case Opcodes.SASTORE:
					{ arrayStore(newLabel,"String Array",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 87
				case Opcodes.POP:
					if(newLabel==2 && executionStack.isEmpty()){
						
					}else{
					executionStack.pop();
					}
					break;
				// 88
				case Opcodes.POP2:
					if(newLabel==2 && executionStack.isEmpty()){}else{
						executionStack.pop();
					}
					if(executionStack.isEmpty()){}else{
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 90
				case Opcodes.DUP_X1:
				   {ASTNode get1;
					ASTNode get2;
					if(newLabel==2 && executionStack.isEmpty()){
						get1=currentLabelNode;
					}else{
						get1=executionStack.pop();
					}
					if(newLabel==2 && executionStack.isEmpty()){
						get2=currentLabelNode;
					}else{
						get2=executionStack.pop();
					}
					ASTNode dup=get1;
					executionStack.push(dup);
					executionStack.push(get2);
					executionStack.push(get1);}
					break;
				// 91
				case Opcodes.DUP_X2:
					{ASTNode get1;
					ASTNode get2;
					ASTNode get3;
					if(newLabel==2 && executionStack.isEmpty()){
						get1=currentLabelNode;
					}else{
						get1=executionStack.pop();
					}
					if(newLabel==2 && executionStack.isEmpty()){
						get2=currentLabelNode;
					}else{
						get2=executionStack.pop();
					}
					if(newLabel==2 && executionStack.isEmpty()){
						get3=currentLabelNode;
					}else{
						get3=executionStack.pop();
					}
					ASTNode dup=get1;
					executionStack.push(dup);
					executionStack.push(get3);
					executionStack.push(get2);
					executionStack.push(get1);
					}
					break;
				// 92
				case Opcodes.DUP2:
					{
						ASTNode get1;
						ASTNode get2;
						if(newLabel==2 && executionStack.isEmpty()){
							get1=currentLabelNode;
						}else{
							get1=executionStack.pop();
						}
						if(executionStack.isEmpty()){
							executionStack.push(get1);
							executionStack.push(get1);
						}else{
							get2=executionStack.pop();
							ASTNode dup1=get1;
							ASTNode dup2=get2;
							executionStack.push(get1);
							executionStack.push(get2);
							executionStack.push(dup1);
							executionStack.push(dup2);
						}
					}
					break;
				// 93
				case Opcodes.DUP2_X1:
					{
						ASTNode get1;
						ASTNode get2;
						ASTNode get3;
						if(newLabel==2 && executionStack.isEmpty()){
							get1=currentLabelNode;
						}else{
							get1=executionStack.pop();
						}
						if(newLabel==2 && executionStack.isEmpty()){
							get2=currentLabelNode;
						}else{
							get2=executionStack.pop();
						}
						if(executionStack.isEmpty()){
							ASTNode dup1=get1;
							executionStack.push(dup1);
							executionStack.push(get2);
							executionStack.push(get1);
						}else{
							get3=executionStack.pop();
							ASTNode dup1=get1;
							ASTNode dup2=get2;
							executionStack.push(dup2);
							executionStack.push(dup1);
							executionStack.push(get3);
							executionStack.push(get2);
							executionStack.push(get1);
						}
					}
					break;
				// 94
				case Opcodes.DUP2_X2:
					{
						ASTNode get1;
						ASTNode get2;
						ASTNode get3;
						ASTNode get4;
						if(newLabel==2 && executionStack.isEmpty()){
							get1=currentLabelNode;
						}else{
							get1=executionStack.pop();
						}
						if(newLabel==2 && executionStack.isEmpty()){
							get2=currentLabelNode;
						}else{
							get2=executionStack.pop();
						}
						if(executionStack.isEmpty()){
							executionStack.push(get1);
							executionStack.push(get2);
							executionStack.push(get1);
						}else{
							get3=executionStack.pop();
							if(executionStack.isEmpty()){
							executionStack.push(get1);
							executionStack.push(get2);
							executionStack.push(get3);
							executionStack.push(get2);
							executionStack.push(get1);
							}else{
							get4=executionStack.pop();
							executionStack.push(get2);
							executionStack.push(get1);
							executionStack.push(get4);
							executionStack.push(get3);
							executionStack.push(get2);
							executionStack.push(get1);
							}
						}
					}
					break;
				// 95
				case Opcodes.SWAP:
					{ ASTNode get1;
					  ASTNode get2;
						if(newLabel==2 && executionStack.isEmpty()){
							get1=currentLabelNode;
						}else{
							get1=executionStack.pop();
						}
						if(newLabel==2 && executionStack.isEmpty()){
							get2=currentLabelNode;
						}else{
							get2=executionStack.pop();
						}
						executionStack.push(get1);
						executionStack.push(get2);
					}
					break;
				// 96
				case Opcodes.IADD:
					switch(ain.getType()){
						case 0:
							loadArithmetic(newLabel,"+","Int",uniqueIdentifier);
							uniqueIdentifier++;
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 97
				case Opcodes.LADD:
					switch(ain.getType()){
						case 0:
							loadArithmetic(newLabel,"+","Long",uniqueIdentifier);
							uniqueIdentifier++;
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 98
				case Opcodes.FADD:
					{
						loadArithmetic(newLabel,"+","Float",uniqueIdentifier);
						uniqueIdentifier++;
					}
					break;
				// 99
				case Opcodes.DADD:
					{ loadArithmetic(newLabel,"+","Double",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 100
				case Opcodes.ISUB:
					switch(ain.getType()){
						case 0:
							loadArithmetic(newLabel,"-","Int",uniqueIdentifier);
							uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 101
				case Opcodes.LSUB:
					switch(ain.getType()){
						case 0:
							loadArithmetic(newLabel,"-","Long",uniqueIdentifier);
							uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 102
				case Opcodes.FSUB:
					{
						loadArithmetic(newLabel,"-","Float",uniqueIdentifier);
						uniqueIdentifier++;
					}
					break;
				// 103
				case Opcodes.DSUB:
					{ loadArithmetic(newLabel,"-","Double",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 104
				case Opcodes.IMUL:
					switch(ain.getType()){
						case 0:
							loadArithmetic(newLabel,"*","Int",uniqueIdentifier);
							uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 105
				case Opcodes.LMUL:
					switch(ain.getType()){
					case 0:
						loadArithmetic(newLabel,"*","Long",uniqueIdentifier);
						uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 106
				case Opcodes.FMUL:
					switch(ain.getType()){
					case 0:
						loadArithmetic(newLabel,"*","Float",uniqueIdentifier);
						uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 107
				case Opcodes.DMUL:
					switch(ain.getType()){
					case 0:
						loadArithmetic(newLabel,"*","Double",uniqueIdentifier);
						uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 108
				case Opcodes.IDIV:
					switch(ain.getType()){
						case 0:
						loadArithmetic(newLabel,"/","Int",uniqueIdentifier);
						uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 109
				case Opcodes.LDIV:
					switch(ain.getType()){
					case 0:
						loadArithmetic(newLabel,"/","Long",uniqueIdentifier);
						uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 110
				case Opcodes.FDIV:
					switch(ain.getType()){
					case 0:
						loadArithmetic(newLabel,"/","Float",uniqueIdentifier);
						uniqueIdentifier++;
						break;
					default:break;
					}
					break;
				// 111
				case Opcodes.DDIV:
					switch(ain.getType()){
					case 0:
						loadArithmetic(newLabel,"/","Double",uniqueIdentifier);
						uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
				break;
				// 112
				case Opcodes.IREM:
					switch(ain.getType()){
					case 0:
						loadArithmetic(newLabel,"%","Int",uniqueIdentifier);
						uniqueIdentifier++;
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 113
				case Opcodes.LREM:
					{ loadArithmetic(newLabel,"%","Long",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 114
				case Opcodes.FREM:
					{ loadArithmetic(newLabel,"%","Float",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 115
				case Opcodes.DREM:
					{ loadArithmetic(newLabel,"%","Double",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 116
				case Opcodes.INEG:
					{ setNeg(newLabel,"Int",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 117
				case Opcodes.LNEG:
					{ setNeg(newLabel,"Long",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 118
				case Opcodes.FNEG:
					{ setNeg(newLabel,"Float",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 119
				case Opcodes.DNEG:
					{ setNeg(newLabel,"Double",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 120
				case Opcodes.ISHL:
					{ loadArithmetic(newLabel,"<<","Int",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 121
				case Opcodes.LSHL:
					{ loadArithmetic(newLabel,"<<","Long",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 122
				case Opcodes.ISHR:
					{
					loadArithmetic(newLabel,">>","Int",uniqueIdentifier);
					uniqueIdentifier++;
					}
					break;
				// 123
				case Opcodes.LSHR:
					{ loadArithmetic(newLabel,">>","Long",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 124
				case Opcodes.IUSHR:
					{ loadArithmetic(newLabel,">>>","int",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 125
				case Opcodes.LUSHR:
					{ loadArithmetic(newLabel,">>>","Long",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 126
				case Opcodes.IAND:
					{ loadArithmetic(newLabel,"&&","Int",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 127
				case Opcodes.LAND:
					{ loadArithmetic(newLabel,"&&","Long",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 128
				case Opcodes.IOR:
					{ loadArithmetic(newLabel,"|","Int",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 129
				case Opcodes.LOR:
					{ loadArithmetic(newLabel,"|","Long",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 130
				case Opcodes.IXOR:
					{
					loadArithmetic(newLabel,"^","Int",uniqueIdentifier);
					uniqueIdentifier++;
					}
					break;
				// 131
				case Opcodes.LXOR:
					{ loadArithmetic(newLabel,"^","Long",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 132
				case Opcodes.IINC:
					//Integer increment, no stack operation, ignore
					break;
				// 133
				case Opcodes.I2L:
					{ castValue(newLabel,"Int","Long");}
					break;
				// 134
				case Opcodes.I2F:
					{ castValue(newLabel,"Int","Float");}
					break;
				// 135
				case Opcodes.I2D:
					{ castValue(newLabel,"Int","Double");}
					break;
				// 136
				case Opcodes.L2I:
					{ castValue(newLabel,"Long","Int");}
					break;
				// 137
				case Opcodes.L2F:
					{ castValue(newLabel,"Long","Float");}
					break;
				// 138
				case Opcodes.L2D:
					{ castValue(newLabel,"Long","Double");
					break;}
				// 139
				case Opcodes.F2I:
					{ castValue(newLabel,"Float","Int");}
					break;
				// 140
				case Opcodes.F2L:
					{ castValue(newLabel,"Float","Long");}
					break;
				// 141
				case Opcodes.F2D:
					{ castValue(newLabel,"Float","Double");}
				break;
				// 142
				case Opcodes.D2I:
					{ castValue(newLabel,"Double","Int");}
					break;
				// 143
				case Opcodes.D2L:
					{ castValue(newLabel,"Double","Long");}
					break;
				// 144
				case Opcodes.D2F:
					{ castValue(newLabel,"Double","Float");}
					break;
				// 145
				case Opcodes.I2B:
					{
					castValue(newLabel,"Int","Byte");
					}
					break;
				// 146
				case Opcodes.I2C:
					{
					castValue(newLabel,"Int","Char");
					}
					break;
				// 147
				case Opcodes.I2S:
					{ castValue(newLabel,"Int","String");}
					break;
				// 148
				case Opcodes.LCMP:
					{
					loadArithmetic(newLabel,"==","Long",uniqueIdentifier);
					uniqueIdentifier++;
					}
					break;
				// 149
				case Opcodes.FCMPL:
					{
						loadArithmetic(newLabel,"==","Float",uniqueIdentifier);
						uniqueIdentifier++;
					}
					break;
				// 150
				case Opcodes.FCMPG:
					{
						loadArithmetic(newLabel,"==","Float",uniqueIdentifier);
						uniqueIdentifier++;
					}
					break;
				// 151
				case Opcodes.DCMPL:
					{ loadArithmetic(newLabel,"==","Double",uniqueIdentifier);
					uniqueIdentifier++;}
					break;
				// 152
				case Opcodes.DCMPG:
					{ loadArithmetic(newLabel,"==","Double",uniqueIdentifier);
					uniqueIdentifier++;}
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 159
				case Opcodes.IF_ICMPEQ:
					switch(ain.getType()){
						case 7:
							jumpIfTwoValue(ain,newLabel,"==");
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 160
				case Opcodes.IF_ICMPNE:
					switch(ain.getType()){
						case 7:
							jumpIfTwoValue(ain,newLabel,"!=");
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 161
				case Opcodes.IF_ICMPLT:
					switch(ain.getType()){
						case 7:
							jumpIfTwoValue(ain,newLabel,"<");
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 162
				case Opcodes.IF_ICMPGE:
					switch(ain.getType()){
						case 7:
							jumpIfTwoValue(ain,newLabel,">=");
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 163
				case Opcodes.IF_ICMPGT:
					switch(ain.getType()){
						case 7:
							jumpIfTwoValue(ain,newLabel,">");
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 164
				case Opcodes.IF_ICMPLE:
					switch(ain.getType()){
						case 7:
							jumpIfTwoValue(ain,newLabel,"<=");
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 165
				case Opcodes.IF_ACMPEQ:
					switch(ain.getType()){
					case 7:
						jumpIfTwoValue(ain,newLabel,"==");
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
				}
				break;
				// 166
				case Opcodes.IF_ACMPNE:
					switch(ain.getType()){
					case 7:
						jumpIfTwoValue(ain,newLabel,"!=");
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						check.setUsedBy(asn);
						asn.setCheckValue(check);
						executionStack.push(asn);
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 171
				case Opcodes.LOOKUPSWITCH:
					{
						LookupSwitchInsnNode tsin=(LookupSwitchInsnNode)ain;
						ASTSwitchNode asn=new ASTSwitchNode();
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
						for(Object ob:tsin.keys){
							Integer key=(Integer)ob;
							asn.addKey(key);
						}
						ASTNode check;
						if(newLabel==2 && executionStack.isEmpty()){
							check=currentLabelNode;
						}else{
							check=executionStack.pop();
						}
						check.setUsedBy(asn);
						asn.setCheckValue(check);
						executionStack.push(asn);
					}
					break;
				// 172
				case Opcodes.IRETURN:
					{setReturn(newLabel,"Int",afn);}
					break;
				// 173
				case Opcodes.LRETURN:
					{setReturn(newLabel,"Long",afn);}
					break;
				// 174
				case Opcodes.FRETURN:
					{setReturn(newLabel,"Float",afn);}
					break;
				// 175
				case Opcodes.DRETURN:
					{setReturn(newLabel,"Double",afn);}
					break;
				// 176
				case Opcodes.ARETURN:
					{setReturn(newLabel,"Object",afn);}
					break;
				// 177
				case Opcodes.RETURN:
					switch(ain.getType()){
					// just return empty to end the function, ignore
						case 0:break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 178
				case Opcodes.GETSTATIC:
					switch(ain.getType()){
					case 4:
						FieldInsnNode fin=(FieldInsnNode)ain;
						if(fieldVariable.containsKey(fin.name)){
							executionStack.push(fieldVariable.get(fin.name));
						}else{
						ASTFieldNode fien=new ASTFieldNode();
						fien.setName(fin.name);
						fien.setOwner(fin.owner);
						fien.setSignature(fin.desc);
						afn.setUsedAsObject(fien);
						fieldVariable.put(fin.name, fien);
						executionStack.push(fien);	
						}
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 179
				case Opcodes.PUTSTATIC:
					switch(ain.getType()){
					case 4:
						FieldInsnNode fin=(FieldInsnNode)ain;
						ASTNode ast;
						if(newLabel==2 && executionStack.isEmpty()){
							ast=currentLabelNode;
						}else{
							ast=executionStack.pop();
						}
						if(fieldVariable.containsKey(fin.name)){
							ASTFieldNode fien=fieldVariable.get(fin.name);
							fien.setFieldValue(ast);
							ast.setUsedBy(fien);
						}else{
						ASTFieldNode fien=new ASTFieldNode();
						afn.setUsedAsObject(fien);
						fien.setName(fin.name);
						fien.setOwner(fin.owner);
						fien.setSignature(fin.desc);
						fien.setFieldValue(ast);
						fieldVariable.put(fin.name, fien);
						}
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 180
				case Opcodes.GETFIELD:
					switch(ain.getType()){
						case 4:
							FieldInsnNode fin=(FieldInsnNode)ain;
							ASTNode temp;
							if(newLabel==2 && executionStack.isEmpty()){
								temp=currentLabelNode;
							}else{
								temp=executionStack.pop();		
							}
							if(fieldVariable.containsKey(fin.name)){
								ASTFieldNode fien=fieldVariable.get(fin.name);
								fien.setCallBy(temp);
								temp.setUsedAsObject(fien);
								executionStack.push(fien);
							}else{
							ASTFieldNode fien=new ASTFieldNode();
							fien.setCallBy(temp);
							fien.setName(fin.name);
							fien.setOwner(fin.owner);
							fien.setSignature(fin.desc);
							temp.setUsedAsObject(fien);
							executionStack.push(fien);		
							}
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 181
				case Opcodes.PUTFIELD:
					switch(ain.getType()){
						case 4: 
							FieldInsnNode fin=(FieldInsnNode)ain;
							ASTNode temp;
							if(newLabel==2 && executionStack.isEmpty()){
								temp=currentLabelNode;
							}else{
								temp=executionStack.pop();
							}
							ASTNode temp2;
							if(newLabel==2 && executionStack.isEmpty()){
								temp2=currentLabelNode;
							}else{
								temp2=executionStack.pop();
							}
							if(fieldVariable.containsKey(fin.name)){
								ASTFieldNode fien=fieldVariable.get(fin.name);
								temp.setUsedBy(fien);
								fien.setFieldValue(temp);
								temp2.setUsedAsObject(fien);
								fien.setCallBy(temp2);
							}else{
							ASTFieldNode fien=new ASTFieldNode();
							temp.setUsedBy(fien);
							fien.setFieldValue(temp);
							temp2.setUsedAsObject(fien);
							fien.setCallBy(temp2);
							fien.setName(fin.name);
							fien.setOwner(fin.owner);
							fien.setSignature(fin.desc);
							fieldVariable.put(fin.name, fien);
							}
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;	
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;	
					}
					break;
				// 187
				case Opcodes.NEW:
					switch(ain.getType()){
						case 3:
							TypeInsnNode tin=(TypeInsnNode)ain;
							ASTObjectNode aon=new ASTObjectNode();
							aon.setObjectType(tin.desc);
							afn.setUsedAsObject(aon);
							executionStack.push(aon);
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						aan.setName(uniqueIdentifier+"");
						uniqueIdentifier++;
						executionStack.push(aan);
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
							aan.setName(uniqueIdentifier+"");
							uniqueIdentifier++;
							executionStack.push(aan);
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						acn.setConstantType("Int");
						acn.setUsedBy(acn);
						acn.setName(uniqueIdentifier+"");
						uniqueIdentifier++;
						executionStack.push(acn);
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						arn.setReturnValue(ast);
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
							acn.setName(uniqueIdentifier+"");
							uniqueIdentifier++;
							executionStack.push(acn);
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 194
				case Opcodes.MONITORENTER:
					switch(ain.getType()){
					case 0:
						ASTObjectNode aon=new ASTObjectNode();
						aon.setObjectName("Synchronized Lock");
						ASTNode lock;
						if(newLabel==2 && executionStack.isEmpty()){
							lock=currentLabelNode;
						}else{
							lock=executionStack.pop();
						}
						lock.setUsedBy(aon);
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
					}
					break;
				// 195
				case Opcodes.MONITOREXIT:
					switch(ain.getType()){
					case 0:
						ASTObjectNode aon=new ASTObjectNode();
						aon.setObjectName("Synchronized Unlock");
						ASTNode lock;
						if(newLabel==2 && executionStack.isEmpty()){
							lock=currentLabelNode;
						}else{
							lock=executionStack.pop();
						}
						lock.setUsedBy(aon);
						break;
					default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
							break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
						break;
						default: System.out.println("no handle "+ain.getOpcode()+" "+ain.getType()); break;
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
