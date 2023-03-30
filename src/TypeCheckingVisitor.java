import ast.*;
import ast.visitor.BaseVisitor;

import java.util.Objects;

class MyContext{
    SymbolTable map = new SymbolTable<Integer>();

}

public class TypeCheckingVisitor extends BaseVisitor<Symbol, MyContext> {

    @Override
    public Symbol visit(IsVoidNode node, MyContext data) {
        Symbol typeE1 = visit(node.getE1(),data);
        if(!Objects.equals(typeE1, null)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

        node.setType(TreeConstants.No_class);
        return visit(node.getE1(),data);
    }

    @Override
    public Symbol visit(LTNode node, MyContext data) {

        Symbol typeE1 = visit(node.getE1(),data);
        if(!Objects.equals(typeE1, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        Symbol typeE2 = visit(node.getE2(),data);
        if(!Objects.equals(typeE2, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

        node.setType(TreeConstants.Bool);
        return TreeConstants.Bool;
    }

    @Override
    public Symbol visit(EqNode node, MyContext data) {

        Symbol typeE1 = visit(node.getE1(),data);

        Symbol typeE2 = visit(node.getE2(),data);
        if(!Objects.equals(typeE2, typeE1)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

        node.setType(TreeConstants.Bool);
        return TreeConstants.Bool;
    }

    @Override
    public Symbol visit(NegNode node, MyContext data)  {
        Symbol typeE1 = visit(node.getE1(), data);
        if(!Objects.equals(typeE1, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        return TreeConstants.Int;
    }

    @Override
    public Symbol visit(PlusNode node, MyContext data) {
6
        Symbol typeE1 = visit(node.getE1(),data);
        if(!Objects.equals(typeE1, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        //5 + 2
        Symbol typeE2 = visit(node.getE2(),data);
        if(!Objects.equals(typeE2, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

            node.setType(TreeConstants.Int);
        return TreeConstants.Int;
    }

    @Override
    public Symbol visit(SubNode node, MyContext data) {

        Symbol typeE1 = visit(node.getE1(),data);
        if(!Objects.equals(typeE1, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        Symbol typeE2 = visit(node.getE2(),data);
        if(!Objects.equals(typeE2, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

        node.setType(TreeConstants.Int);
        return TreeConstants.Int;
    }

    @Override
    public Symbol visit(MulNode node, MyContext data) {

        Symbol typeE1 = visit(node.getE1(),data);
        if(!Objects.equals(typeE1, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        Symbol typeE2 = visit(node.getE2(),data);
        if(!Objects.equals(typeE2, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

        node.setType(TreeConstants.Int);
        return TreeConstants.Int;
    }

    @Override
    public Symbol visit(DivideNode node, MyContext data) {

        Symbol typeE1 = visit(node.getE1(),data);
        if(!Objects.equals(typeE1, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        Symbol typeE2 = visit(node.getE2(),data);
        if(!Objects.equals(typeE2, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

        node.setType(TreeConstants.Int);
        return TreeConstants.Int;
    }

    @Override
    public Symbol visit(ConstNode node, MyContext data)  {
        return base(node, data);
    }

    @Override
    public Symbol visit(IntConstNode node, MyContext data)  {
        node.setType(TreeConstants.Int);
        return TreeConstants.Int;
    }

    @Override
    public Symbol visit(StringConstNode node, MyContext data)  {
        node.setType(TreeConstants.Str);
        return TreeConstants.Str;
    }

    @Override
    public Symbol visit(BoolConstNode node, MyContext data)  {
        node.setType(TreeConstants.Bool);
        return TreeConstants.Bool;
    }

    @Override
    public Symbol visit(LetNode node, MyContext data) {
        node.setType(TreeConstants.Let);
        return visit(node, data);
    }

}
