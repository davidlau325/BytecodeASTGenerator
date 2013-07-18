package bytecodeAST;

import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
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
	private final static String jarName="4646_d8ee5eeb-034d-43dc-a3bd-ab694f4523c4-dex2jar";
	private final static String fileName = "./jar/"+jarName+".jar";
	private static ZipFile f;
	
	public static void main(String[] args) throws Exception{
	    
//	    String fileName=args[0];
//	    int jarPost=fileName.indexOf(".jar");
//	    String jarName=fileName.substring(0,jarPost);
		f = new ZipFile(fileName);
	    Enumeration<? extends ZipEntry> en=f.entries();
	    ArrayList<String> allClasses=new ArrayList<String>();
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
        allClasses.add(classNode.name);
        astClassNode.setSuperName(classNode.superName);
        head.setChild(astClassNode);
        for(Object inter:classNode.interfaces){
        	astClassNode.addInterface(inter.toString());
        }
        
        List<MethodNode> mnList=classNode.methods;
        		for(MethodNode mn:mnList){
        			ASTfactory af=new ASTfactory();
        			af.generateFunctionAST(mn,fieldVariable);	
        			ASTFunctionNode functionNode=(ASTFunctionNode)af.getASTNode();
        			functionNode.setParentClass(astClassNode);
        			astClassNode.setChild(functionNode);
        		}
	    	}
	    }
	    
	    ASTPrinter print=new ASTPrinter(head,jarName,allClasses);
        print.makeMatrix(true);
	}
}
