import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;

public class CompoundStatementDetector extends CBaseVisitor {
/*    @Override
    public Object visitStatement(CParser.StatementContext ctx) {
            System.out.println(ctx.getChild(0).getText()+" "+ctx.getChild(0).getClass().getSimpleName());
        return super.visitStatement(ctx);
    }*/

    /*    @Override
        public Object visitCompoundStatement(CParser.CompoundStatementContext ctx) {
    //        System.out.println(ctx.getText());
            System.out.println(ctx.getChild(0).getClass().getSimpleName());
            System.out.println(ctx.getChild(0).getText()+"flag = 1 "+ctx.getChild(1).getText()+" "+ctx.getChild(2).getText());
            return super.visitCompoundStatement(ctx);
        }*/
    int mainFunc = 0;

    @Override
    public Object visitFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        if (ctx.getChild(1).getText().equals("main()"))
            mainFunc = 1;
        return super.visitFunctionDefinition(ctx);
    }

    int jump = 0;

    @Override
    public Object visitJumpStatement(CParser.JumpStatementContext ctx) {
        jump = 1;
        return super.visitJumpStatement(ctx);
    }

    int inlineloop = 0;
    int FOR=0;

    @Override
    public Object visitIterationStatement(CParser.IterationStatementContext ctx) {
        if(ctx.getChild(0).getText().equals("for"))FOR=2;
        if (ctx.getChild(4).getText().charAt(0) != '{') {
            inlineloop = 1;
        }
        return super.visitIterationStatement(ctx);
    }

    int structOrUnion = 0;

    @Override
    public Object visitStructOrUnion(CParser.StructOrUnionContext ctx) {
        structOrUnion = 1;
        return super.visitStructOrUnion(ctx);
    }

    int inlineif = 0;
    int inlineelse = 0;
    int switchBlock = 0;

    @Override
    public Object visitSelectionStatement(CParser.SelectionStatementContext ctx) {
        if(ctx.getChildCount()>5&&ctx.getChild(6).getText().charAt(0) != '{')inlineelse=1;
        if (ctx.getChild(4).getText().charAt(0) != '{') {
            inlineif = 1;
        }
        if (ctx.getChild(0).getText().equals("switch")) switchBlock = 1;
//        System.out.println(ctx.getChildCount());
        return super.visitSelectionStatement(ctx);
    }

    int labelStatement = 0;

    @Override
    public Object visitLabeledStatement(CParser.LabeledStatementContext ctx) {
        labelStatement = 1;
        return super.visitLabeledStatement(ctx);
    }


    int blockNumber = 0;

    @Override
    public Object visitTerminal(TerminalNode node) {
        if (node.getText().equals("}") || node.getText().equals(";")) {
            //System.out.println(node.getText());

            Test.outFile.add(node.getText());
            if(FOR>0){
                FOR--;
            }
            else Test.outFile.add("\n");



        } else if (node.getText().equals("{")) {
            if (switchBlock == 1 || structOrUnion == 1) {
                //System.out.println("{");

                Test.outFile.add("{");
                Test.outFile.add("\n");

                switchBlock = 0;
                structOrUnion = 0;
            } else {
                //System.out.println("{\nflag["+ blockNumber++ +"] = 1 ;");

                Test.outFile.add("{flag[" + blockNumber++ + "] = 1;");
                Test.outFile.add("\n");

            }
        } else {
            //System.out.print(node.getText() + " ");

            if (node.getText().equals("return") && mainFunc == 1) {
                Test.outFile.add("for(int i=0;i<" + Test.flagCount + ";i++)if(flag[i])printf(\"%d\\n\",i);\n");
            }
            if (!node.getText().equals("<EOF>"))
                Test.outFile.add(node.getText() + " ");

        }
        if (node.getText().equals("else") && inlineelse == 1) {
            //System.out.print("flag["+ blockNumber++ +"] = 1,");

            Test.outFile.add("flag[" + blockNumber++ + "] = 1,");

            inlineelse = 0;
        }
        if (node.getText().equals(")") && inlineif == 1) {
            //System.out.print("flag["+ blockNumber++ +"] = 1,");

            Test.outFile.add("flag[" + blockNumber++ + "] = 1,");

            inlineif = 0;
        }
        if (node.getText().equals(")") && inlineloop == 1) {
            //System.out.print("flag["+ blockNumber++ +"] = 1,");

            Test.outFile.add("flag[" + blockNumber++ + "] = 1,");

            inlineloop = 0;
        }
        if (node.getText().equals(":") && labelStatement == 1) {
            //System.out.print("flag["+ blockNumber++ +"] = 1;\n");

            Test.outFile.add("flag[" + blockNumber++ + "] = 1;\n");

            labelStatement = 0;
        }
        if (node.getText().equals(";") && jump == 1) {
            //System.out.print("flag["+ blockNumber++ +"] = 1;\n");

            Test.outFile.add("flag[" + blockNumber++ + "] = 1;\n");

            jump = 0;
        }
        return super.visitTerminal(node);
    }
}
