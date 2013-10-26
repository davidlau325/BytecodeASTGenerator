package bytecodeAST;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.*;

public class Main {

	/**
	 * by David Lau
	 * Started Date: 20 June 2013
	 */
	
	//main parameter
//	private final static String jarName="ciku";
//	private final static String fileName = "./jar/"+jarName+".jar";
	private static ZipFile f;
	
	public static void main(String[] args) throws Exception{
	    
	    String fileName=args[0];
	    int jarPost=fileName.indexOf(".jar");
	    String jarName=fileName.substring(0,jarPost);
	//    String jarName="ciku";
		f = new ZipFile(fileName);
	    Enumeration<? extends ZipEntry> en=f.entries();
	    Scanner checkAllAPI=new Scanner(new FileInputStream("android_java_api"));
	    HashMap<String,Boolean> androidAllAPI=new HashMap<String,Boolean>();
	    while(checkAllAPI.hasNextLine()){
	    	androidAllAPI.put(checkAllAPI.nextLine(), true);
	    }
	    checkAllAPI.close();
	    
	    Scanner checkAPI=new Scanner(new FileInputStream("android_api"));
	    HashMap<String,Boolean> androidAPI=new HashMap<String,Boolean>();
	    while(checkAPI.hasNextLine()){
	    	androidAPI.put(checkAPI.nextLine(), true);
	    }
	    checkAPI.close();
	    
	    ASTClassNode head=new ASTClassNode();
	    head.setName(jarName);
	    
	    while(en.hasMoreElements()){
	    	ZipEntry e=en.nextElement();
	    	String name=e.getName();
	    	if(name.endsWith(".class")){
	    //for each .class file in jar do analysis
    		
//	    	    ClassLoader cl=ClassLoader.getSystemClassLoader();
//	    	    InputStream a=cl.getResourceAsStream("./QuickDictActivity.class");
//	    	    ClassReader cr=new ClassReader(a);
	    		
	    ClassReader cr=new ClassReader(f.getInputStream(e));
	    ClassNode classNode=new ClassNode();
        cr.accept(classNode, 0);
        
        HashMap<String,ASTFieldNode> fieldVariable=new HashMap<String,ASTFieldNode>();
        
        ASTClassNode astClassNode=new ASTClassNode();
        astClassNode.setName(classNode.name);
        astClassNode.setSuperName(classNode.superName);
        head.setChild(astClassNode);
        for(Object inter:classNode.interfaces){
        	astClassNode.addInterface(inter.toString());
        }
        @SuppressWarnings("unchecked")
        List<MethodNode> mnList=(List<MethodNode>)classNode.methods;
        		try{
        		for(MethodNode mn:mnList){
        			ASTfactory af=new ASTfactory();
        			af.generateFunctionAST(mn,fieldVariable);	
        			ASTFunctionNode functionNode=(ASTFunctionNode)af.getASTNode();
        			functionNode.setParentClass(astClassNode);
        			astClassNode.setChild(functionNode);
        		}
        		}catch(Exception ex){
        			PrintWriter pwErr=new PrintWriter(new FileOutputStream(jarName+"_err.txt"));
        			pwErr.println(ex.getMessage());
        			pwErr.close();
        			System.out.println("Failed: "+jarName);
        		}
	    	}
	    }
	    
	    ASTPrinter print=new ASTPrinter(head,jarName,androidAPI,androidAllAPI);
        print.makeMatrix(true);
        System.out.println("Succeed: "+jarName);
	}
}
