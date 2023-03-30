import ast.visitor.BaseVisitor;

public class ScopeCheckingVisitor extends BaseVisitor<Object, Object> {

    
    public ScopeCheckingVisitor() {
	    ClassTable IG = new ClassTable(new ArrayList<ClassNode>());
        SymbolTable ST = new SymbolTable();	
    }



//---------------------------------------

    public R visit(ProgramNode node, D data) {
		ArrayList<ClassNode> listOfClasses = new ArrayList<ClassNode>();

		for (ClassNode temp : node.getClasses()){ 
			listOfClasses.add(temp);
		}

		IG.setGraph(listOfClasses);
		if (!IG.checkInheritanceGraph()) return false;
		return true;
        //we have now checked for inheritence 
        
    }

    public boolean visit(ClassNode node, D data) {
        //Do nothing we checked already

        return true;       
    }

    public R visit(FeatureNode node, D data) {
      
        return true;
    }

    public R visit(MethodNode node, D data) {
        //Check table, if its not empty then its not valid 
        if(Table.lookup(node.getName()) != null) {
            return false;
        } 
        else {
        Table.insert(F.attribute.name,F.attribute);
        return base(node, data);
        }
    
    }

    public boolean visit(AttributeNode node, D data) {

	
				 if (Table.lookup(node.Getname())!=null){
					return false;
				}
				if (!(F.attribute.value instanceof AST.no_expr) && !getType(F.attribute.value).equals(F.attribute.typeid)){
					System.out.println("Type of expression doesn't match typeid");
					return false;
				}
				Table.insert(F.attribute.name,F.attribute);
                return true;
		}

    
    

    public boolean visit(FormalNode node, D data) {
        visit(node.getName(), data);
        visit(node.getType_decl(), data);
        return true; //return base(node, data);
    }

    public R visit(BranchNode node, D data) {
        visit(node.getName(), data);
        visit(node.getType_decl(), data);
        return visit((Tree) node.getExpr(), data);
    }
    public R visit(ExpressionNode node, D data) {
        return  visit((Tree) node, data);
    }

    public R visit(AssignNode node, D data) {
        visit(node.getName(), data);
        return  visit((Tree) node.getExpr(), data); 
    }

    public R visit(StaticDispatchNode node, D data) {
        visit((Tree) node.getExpr(), data);
        visit(node.getActuals(), data);
        return base(node, data);
    }

    public R visit(DispatchNode node, D data) {
        visit((Tree) node.getExpr(), data);
        visit(node.getActuals(), data);
        return base(node, data);
    }

    public R visit(CondNode node, D data) {
        visit((Tree) node.getCond(), data);
        visit((Tree) node.getThenExpr(), data);
        visit((Tree) node.getElseExpr(), data);
        return base(node, data);
    }

    public R visit(LoopNode node, D data) {
        visit((Tree) node.getCond(), data);
        visit((Tree) node.getBody(), data);
        return base(node, data);
    }


    public R visit(CaseNode node, D data) {
        visit((Tree)node.getExpr(), data);
        visit(node.getCases(), data);
        return base(node, data);
    }

    public R visit(BlockNode node, D data) {
        return visit(node.getExprs(), data);
    }

    public R visit(LetNode node, D data) {
        //CHeck the name and see if its alredy used, if use throw error

        //If not used we got in scope and just return true
        return base(node, data);
    }

    public R visit(BinopNode node, D data) {
        visit((Tree) node.getE1(), data);
        visit((Tree) node.getE2(), data);
        return base(node, data);
    }

    public R visit(UnopNode node, D data) {
        visit((Tree) node.getE1(), data);
        return base(node, data);
    }

    public boolean visit(IntBinopNode node, D data) {
        return true;
    }

    public boolean visit(BoolBinopNode node, D data) {
        return true;
    }

    public boolean visit(IntUnopNode node, D data) {
        return true;
    }

    public boolean visit(BoolUnopNode node, D data) {
        return true;
    }

    public boolean visit(PlusNode node, D data) {
        return true;
    }

    public boolean visit(SubNode node, D data) {
        return true;
    }

    public boolean visit(MulNode node, D data) {
        return true;
    }

    public boolean visit(DivideNode node, D data)  {
        return true;
    }

    public boolean visit(NegNode node, D data)  {
        return true;
    }

    public boolean visit(LTNode node, D data) {
        //DO STUFF HERE
        return true;
    }

    public boolean visit(EqNode node, D data) {
        return true;
    }

    public boolean visit(LEqNode node, D data) {
        return true;
    }

    public boolean visit(CompNode node, D data) {
        return true;
    }

    public boolean visit(ConstNode node, D data) {
        return true;
    }

    public boolean visit(IntConstNode node, D data) {
        return true;
    }

    public boolean visit(BoolConstNode node, D data) {
        return true;
    }

    public boolean visit(StringConstNode node, D data) {

        // WE CAN IGNORE ALL THIS FOR THE MOST PART
        return true;
    }

    public boolean visit(NewNode node, D data) {
        return true;
    }

    public boolean visit(IsVoidNode node, D data) {
        return true;
    }

    public boolean visit(ObjectNode node, D data) {
        //somethign here??
        return true;
    }
    public R visit(NoExpressionNode node, D data) { return base(node, data); }

}

    

















































    // @Override
    // public boolean visit(ExpressionNode node, D data) {
    //     E.visitEcount++;
		
	// 	if (Node.getType() instanceof ast.BlockNode){
    //         ST.enterScope();

    //         return true;
	// 	}
	// 	else if (Node.getType instanceof ast.AssignNode){

	// 		ast.AssignNode ag = (ast.AssignNode) node.getType();
	// 		ast.AssignNode node;
	// 		node = ST.lookup(ag.name);

	// 			if (node == null || node instanceof ast.method){
    //                 //THROW ERROR HERE
	// 				System.out.println("assign operation to an invalid Identifier with name "+ag.name);
	// 				E.flag = true;
	// 				return false;

	// 			}
	// 			else{
    //                 //VALID AND ADD IT TO OUR TABLE
	// 				// AST.attr at = (AST.attr) node;
	// 				// if (getType(E.expr).equals(at.typeid)){
	// 				// 	Table.insert(ag.name,E.expr);
	// 				// }else {
	// 				// 	System.out.println("The type of assign expression doesn't match");
	// 				// }
	// 			}


	// 		}

	// 	}

	// 	else if (E.expr instanceof AST.dispatch){
	// 		System.out.println("dispatch expression");
			
	// 		AST.dispatch dp = (AST.dispatch) E.expr;
	// 		AST.ASTNode node;
	// 		if (!E.flag && E.visitEcount<2){
	// 			node =Table.lookUpClassSpace(dp.name);
	// 			if (node ==null || node instanceof AST.attr){
	// 				System.out.println("Flagged");
	// 				E.flag=true;
	// 				return false;
	// 			}

	// 			else {
	// 				AST.method m = (AST.method) node;
	// 				if (m.formals.size() != dp.actuals.size()){
	// 					System.out.println("The number of parameters passes doesn't match");
	// 					return false;
	// 				}
	// 				for (int i=0;i<m.formals.size();i++){
	// 					if (!m.formals.get(i).typeid.equals(getType(dp.actuals.get(i)))){
	// 						System.out.println("The parameters type don't match with the signature");
	// 						return false;
	// 					}
	// 				}
	// 				// Check condition of caller

					
	// 			}

	// 		}
	// 		else if (E.flag){
	// 			// Check the inheritance graph;
	// 		}
			
			
	// 	}

	// 	else if (E.expr instanceof AST.static_dispatch){
	// 		System.out.println("static d expression");

	// 	}

	// 	else if (E.expr instanceof AST.cond){
	// 		System.out.println("if expression");
	// 		getType(E.expr);
	// 		return true;
	// 	}
	// 	else if (E.expr instanceof AST.loop){
	// 		System.out.println("loop expression");
	// 		getType(E.expr);
	// 		return true;
	// 	}
	// 	else if (E.expr instanceof AST.let){
	// 		System.out.println("let expression");

	// 	}

	// 	else if (E.expr instanceof AST.typcase){
	// 		System.out.println("case expression");

	// 	}
	// 	else if (E.expr instanceof AST.new_){
	// 		System.out.println("New expression");
	// 		AST.new_ newExp = (AST.new_) E.expr;
	// 		if (!E.flag && E.visitEcount<2){
			
	// 			if ((newExp.typeid.equals("Int") || newExp.typeid.equals("Bool") || newExp.typeid.equals("String"))){
	// 				return true;

	// 			}
	// 			else{
	// 				System.out.println("Flagged");
	// 				E.flag=true;
	// 				return false;
	// 			}

	// 		}
	// 	}

	// 	return true;

