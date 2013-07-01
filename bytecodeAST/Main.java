package bytecodeAST;

import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.*;

public class Main {

	/**
	 * by David Lau
	 * Started Date: 20 June 2013
	 */
	
	//main parameter
	private final static String fileName = "./jar/youdaoDict.jar";
	private final static String logFileName="log.txt";
	private final static String outputFileName="output.txt";
	private static ZipFile f;
	
	public static void main(String[] args) throws Exception{
	    PrintWriter pwLog = new PrintWriter(new FileOutputStream(logFileName));
	    PrintWriter pwOut = new PrintWriter(new FileOutputStream(outputFileName));
	    
/*	    f = new ZipFile(fileName);
	    Enumeration<? extends ZipEntry> en=f.entries();
	    while(en.hasMoreElements()){
	    	ZipEntry e=en.nextElement();
	    	String name=e.getName();
	    	if(name.endsWith(".class")){
	    //for each .class file in jar do analysis
*/	    		
	    	    ClassLoader cl=ClassLoader.getSystemClassLoader();
	    	    InputStream a=cl.getResourceAsStream("./QuickDictActivity.class");
	    	    ClassReader cr=new ClassReader(a);
	    		
//	    ClassReader cr=new ClassReader(f.getInputStream(e));
	    ClassNode classNode=new ClassNode();
        cr.accept(classNode, 0);
        
        List<MethodNode> mnList=classNode.methods;
        for(MethodNode mn:mnList){
        	ASTfactory af=new ASTfactory();
        	af.generateFunctionAST(mn,pwOut);	
        	ASTFunctionNode functionNode=(ASTFunctionNode)af.getASTNode();
        	
        	pwLog.println("---Function Node---");
        	pwLog.println("Name: "+functionNode.getName());
        	pwLog.println("Desc: "+functionNode.getDesc());
        	pwLog.println("Sign: "+functionNode.getSignature());
        	pwLog.println("Exception: {");
        	for(String ex:functionNode.getException()){
        		pwLog.println(ex);
        	}
        	pwLog.println("}");
        	
        	for(ASTNode ob:functionNode.getChild()){
        		if(ob.getASTKind().equals("ASTFieldNode")){
        			ASTFieldNode afn=(ASTFieldNode)ob;
        			pwLog.println("---FieldNode---");
        			pwLog.println("Name: "+afn.getName());
        			pwLog.println("Owner: "+afn.getOwner());
        			pwLog.println("Field Value: {");
        			pwLog.println("Type: "+afn.getFieldValue().getASTKind());
        			pwLog.println("}");
        			pwLog.println("Call By: {");
        			pwLog.println("Type: "+afn.getCallBy().getASTKind());
        			pwLog.println("}");
        			
        		}else if(ob.getASTKind().equals("ASTMethodNode")){
        			ASTMethodNode amn=(ASTMethodNode)ob;
        			pwLog.println("---MethodNode---");
        			pwLog.println("Name: "+amn.getName());
        			pwLog.println("Desc: "+amn.getSignature());
        			pwLog.println("Call By: {");
        			pwLog.println("Type: "+amn.getCallBy().getASTKind());
        			pwLog.println("}");
        			pwLog.println("Parameter: {");
        			for(ASTNode asn:amn.getPara()){
        				pwLog.println("Type: "+asn.getASTKind());
        				pwLog.println("------");
        			}
        			pwLog.println("}");
        			pwLog.println("Return Value: {");
        			if(amn.getReturnValue()==null){
        				pwLog.println("Type: void");
        			}else{
        				pwLog.println("Type: "+amn.getReturnValue().getASTKind());
        				if(amn.getUsedBy()!=null){
        				pwLog.println("To: "+amn.getUsedBy().getASTKind());
        				}else{
        				pwLog.println("To: no used");
        				}
        			}
        			pwLog.println("}");
        		}
        		pwLog.println();
        	}
        }
        
       
//	    	}
//	    }
        pwLog.close();
        pwOut.close();
	}
}
