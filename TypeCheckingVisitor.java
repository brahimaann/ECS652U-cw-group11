import ast.*;
import ast.visitor.BaseVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


class MyContext{
private SymbolTable symbolTable = new SymbolTable<>();
        MyContext() {
    symbolTable.enterScope();
}

}



public class TypeCheckingVisitor extends BaseVisitor<Symbol, MyContext> {
    private Map<String, String> inheritanceMap;
    private SymbolTable symbolTable = new SymbolTable<>();

    public TypeCheckingVisitor() {
        inheritanceMap =  new HashMap<>();
        inheritanceMap.put("Int", "Object");
        inheritanceMap.put("Bool", "Object");
        inheritanceMap.put("String", "Object");
    }

    public boolean isSubtype(Symbol subtype, Symbol supertype) {
        if (subtype.equals(supertype)) {
            return true;
        }
        if (supertype.equals("Object")) {
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

    public Symbol visit(TreeNode node, MyContext data) {
        symbolTable.enterScope();
        return base(node, data);
    }

    public Symbol visit(ClassNode node, MyContext data) {
        symbolTable.enterScope();
        //data.symbolTable.addId(node.getName(), );
        List<FeatureNode> fList = node.getFeatures();
        for(int i= 0; i< fList.size();i++){
            Symbol t0 = visit(fList.get(i), data);
            //System.out.println(fList.get(i));
        }

        return visit(node.getFeatures(), data);
    }

    @Override
    public Symbol visit(IsVoidNode node, MyContext data) {
            Symbol typeE1 = visit(node.getE1(), data);
        return TreeConstants.Bool;
    }

    public Symbol visit(NewNode node, MyContext data) {

       node.setType(node.getType_name());
        return base(node, data);
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

    public Symbol visit(MethodNode node, MyContext data) {
        symbolTable.enterScope();
        visit(node.getName(),data);
        visit(node.getFormals(), data);
        List<FormalNode> fList = node.getFormals();
        for(int i= 0; i< fList.size();i++){
            Symbol name = (fList.get(i).getName());
            symbolTable.addId(name, (fList.get(i).getType_decl()));
            System.out.println(fList.get(i).getName());
        }

        visit(node.getReturn_type(), data);
        visit((Tree) node.getExpr(), data);

        return node.getReturn_type();
    }

    public Symbol visit(BlockNode node, MyContext data) {
        List<ExpressionNode> fList = node.getExprs();
        for(int i= 0; i< fList.size();i++){
            Symbol t0 = visit(fList.get(i), data);
            System.out.println(fList.get(i));
        }
        return visit(node.getExprs(), data);
    }


    public Symbol visit(AssignNode node, MyContext data) {
        Symbol typeE1 = node.getName();
        Symbol typeE2 = visit(node.getExpr(), data);
       // if(!isSubtype(typeE1, ))
       // System.out.println(node.getName());
        Object sym = symbolTable.lookup(typeE1);

        node.getExpr().setType((Symbol) sym);
        //System.out.println(sym.toString());
      // System.out.println(node.getExpr());
        return  visit((Tree) node.getExpr(), data);
    }

    public Symbol visit(ObjectNode node, MyContext data) {
        //System.out.println(node.getName());

        return visit(node.getName(), data);
    }

   /* public Symbol visit(StaticDispatchNode node, MyContext data) {
        visit((Tree) node.getExpr(), data);
        visit(node.getActuals(), data);
        Symbol tN;
        List<ExpressionNode> exprList = node.getActuals();
        for(int i = 0; i<exprList.size(); i++){

        }
        System.out.println(node);
        return base(node, data);
    }
*/

    public Symbol visit(DispatchNode node, MyContext data) {
        Symbol tN0 =visit((Tree) node.getExpr(), data);
        Symbol tN = visit(node.getActuals(), data);
       // Symbol tN1 = visit(node., data);
        //System.out.println(node.getExpr().getType());
        node.setType(node.getExpr().getType());
        return base(node, data);
    }

    public Symbol visit(AttributeNode node, MyContext data) {
        Symbol typeE = visit(node.getName(), data);
        Symbol typeT0 = visit(node.getType_decl(), data);
        Symbol typeE1 = visit((Tree) node.getInit(), data);

        if(Objects.equals(typeE1, null)){
            symbolTable.addId(typeE, node.getType_decl());
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
