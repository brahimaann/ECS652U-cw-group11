import ast.*;

import java.util.LinkedList;
import java.util.List;


/**
 * This class may be used to contain the semantic information such as
 * the inheritance graph.  You may use it or not as you like: it is only
 * here to provide a container for the supplied methods.
 */
class ClassTable {

    private int semantErrors;
    private PrintStream errorStream;
    public Graph inheritanceGraph;
    public Map<String, class_c> classNameMapper;
    public SymbolTable objectEnv;
    public SymbolTable methodEnv;


    /**
     * Creates data structures representing basic Cool classes (Object,
     * IO, Int, Bool, String).  Please note: as is this method does not
     * do anything useful; you will need to edit it to make if do what
     * you want.
     */
    private void installBasicClasses() {
        Symbol filename
                = StringTable.stringtable.addString("<basic class>");

        LinkedList<FormalNode> formals;

        // The following demonstrates how to create dummy parse trees to
        // refer to basic Cool classes.  There's no need for method
        // bodies -- these are already built into the runtime system.

        // IMPORTANT: The results of the following expressions are
        // stored in local variables.  You will want to do something
        // with those variables at the end of this method to make this
        // code meaningful.

        // The Object class has no parent class. Its methods are
        //        cool_abort() : Object    aborts the program
        //        type_name() : Str        returns a string representation
        //                                 of class name
        //        copy() : SELF_TYPE       returns a copy of the object

        ClassNode Object_class =
                new ClassNode(0,
                        TreeConstants.Object_,
                        TreeConstants.No_class,
                        filename);

        Object_class.add(new MethodNode(0,
                TreeConstants.cool_abort,
                new LinkedList<FormalNode>(),
                TreeConstants.Object_,
                new NoExpressionNode(0)));

        Object_class.add(new MethodNode(0,
                TreeConstants.type_name,
                new LinkedList<FormalNode>(),
                TreeConstants.Str,
                new NoExpressionNode(0)));

        Object_class.add(new MethodNode(0,
                TreeConstants.copy,
                new LinkedList<FormalNode>(),
                TreeConstants.SELF_TYPE,
                new NoExpressionNode(0)));

        // The IO class inherits from Object. Its methods are
        //        out_string(Str) : SELF_TYPE  writes a string to the output
        //        out_int(Int) : SELF_TYPE      "    an int    "  "     "
        //        in_string() : Str            reads a string from the input
        //        in_int() : Int                "   an int     "  "     "

        ClassNode IO_class =
                new ClassNode(0,
                        TreeConstants.IO,
                        TreeConstants.Object_,
                        filename);

        formals = new LinkedList<FormalNode>();
        formals.add(
                new FormalNode(0,
                        TreeConstants.arg,
                        TreeConstants.Str));

        IO_class.add(new MethodNode(0,
                TreeConstants.out_string,
                formals,
                TreeConstants.SELF_TYPE,
                new NoExpressionNode(0)));


        formals = new LinkedList<FormalNode>();
        formals.add(
                new FormalNode(0,
                        TreeConstants.arg,
                        TreeConstants.Int));
        IO_class.add(new MethodNode(0,
                TreeConstants.out_int,
                formals,
                TreeConstants.SELF_TYPE,
                new NoExpressionNode(0)));

        IO_class.add(new MethodNode(0,
                TreeConstants.in_string,
                new LinkedList<FormalNode>(),
                TreeConstants.Str,
                new NoExpressionNode(0)));

        IO_class.add(new MethodNode(0,
                TreeConstants.in_int,
                new LinkedList<FormalNode>(),
                TreeConstants.Int,
                new NoExpressionNode(0)));

        // The Int class has no methods and only a single attribute, the
        // "val" for the integer.

        ClassNode Int_class =
                new ClassNode(0,
                        TreeConstants.Int,
                        TreeConstants.Object_,
                        filename);

        Int_class.add(new AttributeNode(0,
                TreeConstants.val,
                TreeConstants.prim_slot,
                new NoExpressionNode(0)));

        // Bool also has only the "val" slot.
        ClassNode Bool_class =
                new ClassNode(0,
                        TreeConstants.Bool,
                        TreeConstants.Object_,
                        filename);

        Bool_class.add(new AttributeNode(0,
                TreeConstants.val,
                TreeConstants.prim_slot,
                new NoExpressionNode(0)));

        // The class Str has a number of slots and operations:
        //       val                              the length of the string
        //       str_field                        the string itself
        //       length() : Int                   returns length of the string
        //       concat(arg: Str) : Str           performs string concatenation
        //       substr(arg: Int, arg2: Int): Str substring selection

        ClassNode Str_class =
                new ClassNode(0,
                        TreeConstants.Str,
                        TreeConstants.Object_,
                        filename);
        Str_class.add(new AttributeNode(0,
                TreeConstants.val,
                TreeConstants.Int,
                new NoExpressionNode(0)));

        Str_class.add(new AttributeNode(0,
                TreeConstants.str_field,
                TreeConstants.prim_slot,
                new NoExpressionNode(0)));
        Str_class.add(new MethodNode(0,
                TreeConstants.length,
                new LinkedList<FormalNode>(),
                TreeConstants.Int,
                new NoExpressionNode(0)));

        formals = new LinkedList<FormalNode>();
        formals.add(new FormalNode(0,
                TreeConstants.arg,
                TreeConstants.Str));
        Str_class.add(new MethodNode(0,
                TreeConstants.concat,
                formals,
                TreeConstants.Str,
                new NoExpressionNode(0)));

        formals = new LinkedList<FormalNode>();
        formals.add(new FormalNode(0,
                TreeConstants.arg,
                TreeConstants.Int));
        formals.add(new FormalNode(0,
                TreeConstants.arg2,
                TreeConstants.Int));

        Str_class.add(new MethodNode(0,
                TreeConstants.substr,
                formals,
                TreeConstants.Str,
                new NoExpressionNode(0)));

	/* Do somethind with Object_class, IO_class, Int_class,
           Bool_class, and Str_class here */


	classNameMapper.put(TreeConstants.Int.toString(), new ArrayList<ClassNode>.add(Int_class) );
	classNameMapper.put(TreeConstants.Object_.toString(), new ArrayList<ClassNode>.add(Object_class));
	classNameMapper.put(TreeConstants.IO.toString(), IO_class);
	classNameMapper.put(TreeConstants.Str.toString(), Str_class);
	classNameMapper.put(TreeConstants.Bool.toString(), Bool_class);








        for(String className : classNameMapper.keySet()) {
    		class_c c1 = classNameMapper.get(className);
		
	    Map<AbstractSymbol, List<AbstractSymbol>> methodArgMap = new HashMap<AbstractSymbol, List<AbstractSymbol>>();
	    Map<AbstractSymbol, AbstractSymbol> attrTypeMap = new HashMap<AbstractSymbol, AbstractSymbol>(); 
	    for(Enumeration e2 = c1.getFeatures().getElements(); e2.hasMoreElements();){
		Feature f = (Feature) e2.nextElement();
		if(f instanceof attr){
			attr a = (attr) f;
			attrTypeMap.put(a.name, a.type_decl);	
		
		} else if (f instanceof method){
			method m = (method) f;
			List<AbstractSymbol> typeList = new ArrayList<AbstractSymbol>();
			for(Enumeration e3 = m.formals.getElements(); e3.hasMoreElements();){
				formalc fo = (formalc) e3.nextElement();
				typeList.add(fo.type_decl);
			}
			typeList.add(m.return_type);
			methodArgMap.put(m.name, typeList);
			
		} else {
			System.out.println("Error should never reach here!");
		}
	    }
	    methodEnv.addId(c1.name, methodArgMap);
	    objectEnv.addId(c1.name, attrTypeMap);
    		
	}


    }

    public ClassTable(List<ClassNode> cls) {
        




        semantErrors = 0;
	errorStream = System.err;
	inheritanceGraph = new Graph();
	classNameMapper = new HashMap<String, class_c>();

	inheritanceGraph.addEdge(TreeConstants.Object_.toString(), TreeConstants.Int.toString(), 1);
	inheritanceGraph.addEdge(TreeConstants.Object_.toString(), TreeConstants.Str.toString(), 1);
	inheritanceGraph.addEdge(TreeConstants.Object_.toString(), TreeConstants.Bool.toString(), 1);
	inheritanceGraph.addEdge(TreeConstants.Object_.toString(), TreeConstants.IO.toString(), 1);
	objectEnv = new SymbolTable();
	methodEnv = new SymbolTable();
	objectEnv.enterScope();
	methodEnv.enterScope();
	addBasicClassesToClassNameMapperAndAddToEnvironments();

    }

    
}
