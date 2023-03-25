import ast.*;
import ast.visitor.BaseVisitor;

import java.util.Objects;

class MyContext{
}

public class TypeCheckingVisitor extends BaseVisitor<Symbol, MyContext> {

    @Override
    public Symbol visit(IsVoidNode node, MyContext data) {

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
        return visit((BoolBinopNode) node, data);
    }

    @Override
    public Symbol visit(EqNode node, MyContext data) {

        Symbol typeE1 = visit(node.getE1(),data);

        Symbol typeE2 = visit(node.getE2(),data);
        if(!Objects.equals(typeE2, typeE1)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

        node.setType(TreeConstants.Bool);
        return visit((BoolBinopNode) node, data);
    }

    @Override
    public Symbol visit(NegNode node, MyContext data)  {
        Symbol typeE1 = visit(node.getE1(), data);
        if(!Objects.equals(typeE1, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        return visit((IntUnopNode) node, data);
    }


    @Override
    public Symbol visit(PlusNode node, MyContext data) {

        Symbol typeE1 = visit(node.getE1(),data);
        if(!Objects.equals(typeE1, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }
        Symbol typeE2 = visit(node.getE2(),data);
        if(!Objects.equals(typeE2, TreeConstants.Int)){
            Utilities.semantError().println("error at line "+node.getLineNumber());
        }

            node.setType(TreeConstants.Int);
        return visit((IntBinopNode) node, data);
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
        return visit((IntBinopNode) node, data);
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
        return visit((IntBinopNode) node, data);
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
        return visit((IntBinopNode) node, data);
    }

    @Override
    public Symbol visit(ConstNode node, MyContext data)  {
        return base(node, data);
    }

    @Override
    public Symbol visit(IntConstNode node, MyContext data)  {
        node.setType(TreeConstants.Int);
        return visit((ConstNode) node, data);
    }

    @Override
    public Symbol visit(StringConstNode node, MyContext data)  {
        node.setType(TreeConstants.Str);
        return visit((ConstNode) node, data);
    }

    @Override
    public Symbol visit(BoolConstNode node, MyContext data)  {
        node.setType(TreeConstants.Bool);
        return visit((ConstNode) node, data);
    }


}
