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
        	af.generateMethodAST(mn,pwOut);
        	
        	pwLog.println("--Variable Operation--");
        	pwLog.println("Method Name: "+mn.name);
        	HashMap<String,List<VariableOperation>> vop=af.getVariableOp();
        	
        	for(String key:vop.keySet()){
        		List<VariableOperation> lvo=vop.get(key);
        		for(VariableOperation vo:lvo){
        			pwLog.println(vo.getIndex()+" "+vo.getOperation()+" "+vo.getMethod() + " " +vo.getAsObject() +" "+vo.getDesc() );
        		}
        		pwLog.println();
        	}
        	pwLog.println();
        }
        
       
//	    	}
//	    }
        pwLog.close();
        pwOut.close();
	}
}
