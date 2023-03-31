import ast.visitor.BaseVisitor;
import ast.*;
import ast.visitor.BaseVisitor;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;


public class ScopeCheckingVisitor extends BaseVisitor<Symbol, MyContext> {
    	ClassTable IG;
        SymbolTable ST;	
        boolean flag = false;
    
    public ScopeCheckingVisitor() {
	    IG = new ClassTable(new ArrayList<ClassNode>());
        ST = new SymbolTable();	
    }



//     attribute definitions; --
// • formal parameters of methods; -- 
// • let expressions;
// • branches of case statements.


    @Override
    public Symbol visit(ProgramNode node, MyContext data) {
		ArrayList<ClassNode> listOfClasses = new ArrayList<ClassNode>();
		for (ClassNode temp : node.getClasses()){ 
			listOfClasses.add(temp);
		}
        IG = new ClassTable(listOfClasses);
		if (IG.checkInheritanceGraph()) {
        Utilities.semantError().println("error at line "+node.getLineNumber());
        } 


	    return visit(node.getClasses(), data);

    }

   @Override
    public Symbol visit(ClassNode node, MyContext data) {
        //Do nothing we checked already

        // return node.getFeatures();   
        return null;  
    }
    @Override
    public Symbol visit(FeatureNode node, MyContext data) {
      
          return TreeConstants.Bool;
    }
    @Override
    public Symbol visit(MethodNode node, MyContext data) {
        ST.enterScope();
        if(ST.lookup(node.getName()) != null) {
        Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        ST.addId(node.getName(), node);
        visit(node.getName(),data);
        visit(node.getFormals(), data);

        ST.exitScope();
        return base(node, data);
    
    }
    @Override
    public Symbol visit(AttributeNode node, MyContext data) {

        //SC.enterScope();
        if(ST.lookup(node.getName()) != null) {
        Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        ST.addId(node.getName(), node);

       return base(node, data);
		}

    
    
    @Override
    public Symbol visit(FormalNode node, MyContext data) {
        if(ST.lookup(node.getName()) != null) {
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        return base(node, data);
    }

    @Override
    public Symbol visit(BranchNode node, MyContext data) {
        if(ST.lookup(node.getName()) != null) {
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

        return null;
    }
    public Symbol visit(ExpressionNode node, MyContext data) {
        return  null;
    }
    @Override
    public Symbol visit(AssignNode node, MyContext data) {
        return  null; 
    }

    @Override
    public Symbol visit(StaticDispatchNode node, MyContext data) {

        return null;
    }

    @Override
    public Symbol visit(DispatchNode node, MyContext data) {

        return null;
    }

    @Override
    public Symbol visit(CondNode node, MyContext data) {

        return null;
    }

    @Override
    public Symbol visit(LoopNode node, MyContext data) {

        return null;
    }

    @Override
    public Symbol visit(CaseNode node, MyContext data) {

        // if(ST.lookup(node.getName()) != null) {
        // Utilities.semantError().println("error at line "+node.getLineNumber());
        // }
        // ST.lookup(node.getName(), node);
        return null;
    }
    @Override
    public Symbol visit(BlockNode node, MyContext data) {
        return null;
    }

    @Override
    public Symbol visit(LetNode node, MyContext data) {


        ST.enterScope();
        if(ST.lookup(node.getIdentifier()) != null) {
        Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        ST.addId(node.getIdentifier(), node);
        return null;
    }

    // @Override
    // public Symbol visit(BinopNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(UnopNode node, MyContext data) {

    //     return null;
    // }

    // @Override
    // public Symbol visit(IntBinopNode node, MyContext data) {
    //     return null;
    // }

    // public Symbol visit(BoolBinopNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(IntUnopNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(BoolUnopNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(PlusNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(SubNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(MulNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(DivideNode node, MyContext data)  {
    //     return null;
    // }

    // @Override
    // public Symbol visit(NegNode node, MyContext data)  {
    //     return null;
    // }

    // @Override
    // public Symbol visit(LTNode node, MyContext data) {
    //     //DO STUFF HERE
    //     return null;
    // }

    // @Override
    // public Symbol visit(EqNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(LEqNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(CompNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(ConstNode node, MyContext data) {
    //     return null;
    // }

    // @Override
    // public Symbol visit(IntConstNode node, MyContext data) {
    //     return null;
    // }
    // @Override
    // public Symbol visit(BoolConstNode node, MyContext data) {
    //     return null;
    // }
    // @Override
    // public Symbol visit(StringConstNode node, MyContext data) {

    //     // WE CAN IGNORE ALL THIS FOR THE MOST PART
    //     return null;
    // }
    // @Override
    // public Symbol visit(NewNode node, MyContext data) {
    //     return null;
    // }
    // @Override
    // public Symbol visit(IsVoidNode node, MyContext data) {
    //     return null;
    // }
    // @Override
    // public Symbol visit(ObjectNode node, MyContext data) {
    //     //somethign here??
    //     return null;
    // }
    // // public Symbol visit(NoExpressionNode node, MyContext data) { return base(node, data); }

}   