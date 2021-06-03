public class CompoundStatementWalker extends CBaseListener{

    @Override
    public void enterCompoundStatement(CParser.CompoundStatementContext ctx) {
        Test.flagCount++;
        super.enterCompoundStatement(ctx);
    }


    @Override
    public void enterLabeledStatement(CParser.LabeledStatementContext ctx) {
        Test.flagCount++;
        super.enterLabeledStatement(ctx);
    }

    @Override
    public void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
        if(ctx.getChildCount()>5&&ctx.getChild(6).getText().charAt(0) != '{')
            Test.flagCount++;
        if (ctx.getChild(4).getText().charAt(0) != '{') {
            Test.flagCount++;
        }
        if(ctx.getChild(0).getText().equals("switch")){
            Test.flagCount--;
        }
        super.enterSelectionStatement(ctx);
    }

    @Override
    public void enterJumpStatement(CParser.JumpStatementContext ctx) {
        Test.flagCount++;
        super.enterJumpStatement(ctx);
    }

    @Override
    public void enterIterationStatement(CParser.IterationStatementContext ctx) {
        if (ctx.getChild(4).getText().charAt(0) != '{') {
            Test.flagCount++;
        }
        super.enterIterationStatement(ctx);
    }
}
