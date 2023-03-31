import ast.*;

import java.util.LinkedList;
import java.util.List;
import java.util.List;
import java.util.HashMap;


/**
 * This class may be used to contain the semantic information such as
 * the inheritance graph.  You may use it or not as you like: it is only
 * here to provide a container for the supplied methods.
 */
class ClassTable {
        List<ClassNode> listOfClasses;
        HashMap<Symbol,Integer> VisitChecker;

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


	listOfClasses.add(Object_class);
	listOfClasses.add(IO_class);
	listOfClasses.add(Int_class);
	listOfClasses.add(Bool_class);
	listOfClasses.add(Str_class);




    }

    public ClassTable(List<ClassNode> cls) {

       		listOfClasses = cls;
                installBasicClasses();
                VisitChecker  = new HashMap<Symbol, Integer>(); 
   

    }

    public boolean checkInheritanceGraph(){
                
                for(int i=0;i<listOfClasses.size();i++){
                        ClassNode t = listOfClasses.get(i);
                        System.out.println(t.getName());
                }

		for(int i=0;i<listOfClasses.size();i++){
			ClassNode temp = listOfClasses.get(i);
			if (temp.getParent().equals("Object_") || temp.getParent().equals("_no_class") ) {
				continue;	
			}
			boolean cont = false;
			for (int j=0;j<listOfClasses.size();j++){
                                System.out.println(temp.getParent().getName());
				if (temp.getParent().equals(listOfClasses.get(j).getName()) && ((!temp.getParent().equals("Int"))))                                    
                                {
					cont =true;
				}
			}
			if (!cont){
                                //THROW ERROR
			
                                Utilities.semantError().println("No matching valid parentclass found for class ");
				return false;
			}
		}	

		for (ClassNode cn : listOfClasses){
			boolean isCyclic = checkCycles(cn.getName());
			if (isCyclic) {
                                //throw error
				System.out.println("The inheritance graph contains cycles");
				return true;
			}
		}

		return false;

	}



        public ClassNode getClassNode(Symbol x ) {
                ClassNode out;
                for (ClassNode temp : listOfClasses) {
                        if(temp.getName().equals(x)) {
                                return temp;
                        }
                }

                return null;
        }



        //Loop through and find starting node
        public boolean checkCycles(Symbol classNode)
        {       
                
                ClassNode temp;
                Symbol Parent;

                temp = getClassNode(classNode);
                Parent = temp.getParent();

                if(Parent.getName().equals("Object")) {
                return false;
                }
                
                if(VisitChecker.getOrDefault(Parent.getName(), 0) == 1) {
                return true;

                } else {
                        VisitChecker.put(Parent, 1);
                        boolean check = checkCycles(Parent); 
                        VisitChecker.put(Parent, 0);
                        return check;
                }


        }

    
}
