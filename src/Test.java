import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.*;

public class Test {
    static public int flagCount = 0;
    static public List<String> outFile=new ArrayList<>();
    public static void main(String[] args) throws Exception {
        String inputFile = "Input C Code/main.c";
        String outputFile = "src/mainTarget.c";

        String regex = "#[ \\t]*include[ \\t]*\"[^\"]+\"|#[ \\t]*include[ \\t]*<[^>]+>";
        outFile.add(Files.lines(new File(inputFile).toPath())
                .map(s->s.trim())
                .filter(s->s.matches(regex)).reduce((s,a)->s+'\n'+a).get());
        outFile.add("\n");

        FileInputStream is = new FileInputStream(inputFile);
        FileOutputStream os = new FileOutputStream(outputFile);

        ANTLRInputStream input = new ANTLRInputStream(is);

        CLexer lexer = new CLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CParser parser = new CParser(tokens);

        ParseTree tree = parser.compilationUnit();

        flagCount = 0;

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

        parseTreeWalker.walk(new CompoundStatementWalker(),tree);

        outFile.add("int flag["+ flagCount +"];\n");
        //System.out.println("int flags["+ flagCount +"];\n");

        CompoundStatementDetector compoundStatementDetector = new CompoundStatementDetector();

        compoundStatementDetector.visit(tree);

        outFile.forEach(s-> {
            try {
                os.write(s.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        is.close();
        os.close();

        File dir = new File(Paths.get("").toAbsolutePath().toString()+"/src");
        Process process = Runtime.getRuntime().exec("cmd.exe /c gcc mainTarget.c -o flags & flags ",null,dir);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<Integer> flags = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            flags.add(Integer.parseInt(line));
        }
        //flags.forEach(System.out::println);
        process.destroy();
        reader.close();

        String flagReg="flag\\[(\\d+)\\] = 1.";
        String flagarrReg="int flag\\[(\\d+)\\];";
        String forFlagsReg =".*if\\(flag\\[i\\]\\).*";
        String lt = "<";
        String gt = ">";
        Pattern p = Pattern.compile(flagReg);
        int f=1,last=1;
        List<String>lines = new ArrayList<>();
        Files.lines(new File(outputFile).toPath())
                .forEach(s->lines.add(s));
        for(int i=0;i<lines.size();i++){
            Matcher m = p.matcher(lines.get(i));
            if(lines.get(i).equals("}")) f=1;
            if(m.find()) {
                if (!flags.contains(Integer.valueOf(m.group(1)))) {
                    f = 0;
                }
                else f = 1;
                lines.set(i,lines.get(i).replaceFirst(flagReg,""));

            }
            if(lines.get(i).matches(flagarrReg))
                lines.set(i,lines.get(i).replaceFirst(flagarrReg,""));
            if(lines.get(i).matches(forFlagsReg))
                lines.set(i,lines.get(i).replaceFirst(forFlagsReg,""));
            lines.set(i,lines.get(i).replace(lt,"&lt;"));
            lines.set(i,lines.get(i).replace(gt,"&gt;"));
            lines.set(i,f==1?"<code style=\"color: green;\">"+lines.get(i)+"</code><br/>":"<code style=\"color: red;\">"+lines.get(i)+"</code><br/>");
        }
        FileOutputStream html = new FileOutputStream("Output HTML/main.html");
        lines.stream().forEach(s-> {
            try {
                html.write(s.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
