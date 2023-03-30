import ast.*;
import ast.visitor.BaseVisitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class MyContext{
}



public class TypeCheckingVisitor extends BaseVisitor<Symbol, MyContext> {
    private Map<String, String> inheritanceMap;

    public TypeCheckingVisitor() {
        inheritanceMap =  new HashMap<>();
        inheritanceMap.put("Int", "Object");
        inheritanceMap.put("Bool", "Object");
        inheritanceMap.put("String", "Object");
    }

    public boolean isSubtype(String subtype, String supertype) {
        if (subtype.equals(supertype)) {
            return true;
        }
        String parent = inheritanceMap.get(subtype);
        while (parent != null) {
            if (parent.equals(supertype)) {
                return true;
            }
            parent = inheritanceMap.get(parent);
        }
        return false;
    }

    @Override
    public Symbol visit(IsVoidNode node, MyContext data) {
            Symbol typeE1 = visit(node.getE1(), data);
        return TreeConstants.Bool;
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

    public Symbol visit(AssignNode node, MyContext data) {
        Symbol typeE1 = visit(node.getName(), data);
        Symbol typeE2 = visit(node.getExpr(), data);
        typeE2 = node.getType();
        //System.out.println(typeE2.toString());
        //System.out.println(typeE1.toString());
        return  visit((Tree) node.getExpr(), data);
    }

    public Symbol visit(ExpressionNode node, MyContext data) {
        node.setType(node.getType());
        return  visit((Tree) node.getType(), data);
    }

    public Symbol visit(AttributeNode node, MyContext data) {
        Symbol typeE = visit(node.getName(), data);
        Symbol typeT0 = visit(node.getType_decl(), data);
        Symbol typeE1 = visit((Tree) node.getInit(), data);

        //System.out.println(typeT0.toString());
        if(Objects.equals(typeE1, null)){

        }

        return  base(node, data);
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
        node.setType(TreeConstants.Int);
        return TreeConstants.Int;
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


}
