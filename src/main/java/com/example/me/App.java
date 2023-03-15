package com.example.me;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import com.example.me.codeanalysis.Evaluator;
import com.example.me.codeanalysis.binding.Binder;
import com.example.me.codeanalysis.binding.BoundExpression;
import com.example.me.codeanalysis.syntax.SyntaxNode;
import com.example.me.codeanalysis.syntax.SyntaxToken;
import com.example.me.codeanalysis.syntax.SyntaxTree;
import com.example.me.utils.ColorBackgrounds;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        boolean showTree = false;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println(ColorBackgrounds.ANSI_BLACK_BACKGROUND);
                System.out.print("> ");
                String line = scanner.nextLine();

                if (line.equalsIgnoreCase("#showTree")) {
                    System.out.println(showTree ? "Not showing the tree" : "Showing the tree");
                    showTree = !showTree;
                    continue;
                }

                if (line.equalsIgnoreCase("#cls")) {
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                SyntaxTree syntaxTree = SyntaxTree.parse(line);
                Binder binder = new Binder();
                BoundExpression boundExpression = binder.bindExpression(syntaxTree.root());

                List<String> diagnostics = Stream.concat(syntaxTree.diagnostics().stream(), binder.diagnostics().stream()).toList();

                if (showTree) {
                    System.out.print(ColorBackgrounds.ANSI_BLACK_BACKGROUND);

                    prettyPrint(syntaxTree.root());

                    System.out.print(ColorBackgrounds.ANSI_BLACK_BACKGROUND);
                }

                if (!diagnostics.isEmpty()) {
                    System.out.print(ColorBackgrounds.ANSI_RED_BACKGROUND);

                    for (String diagnostic : diagnostics) {
                        System.out.println(diagnostic);
                    }

                    System.out.print(ColorBackgrounds.ANSI_BLACK_BACKGROUND);
                } else {
                    Evaluator evaluator = new Evaluator(boundExpression);
                    var result = evaluator.evaluate();
                    System.out.println(result);
                }
            }
        }
    }

    public static void prettyPrint(SyntaxNode node, String indent, boolean isLast) {
        String marker = isLast ? "└──" : "├──";

        System.out.print(indent);
        System.out.print(marker);
        System.out.print(node.kind());

        if (node instanceof SyntaxToken t && t.getValue() != null) {
            System.out.print(" ");
            System.out.print(t.getValue());
        }

        System.out.println();

        indent += isLast ? "   " : "│   ";

        SyntaxNode lastChild = lastOrDefault(node.getChildren());

        for (SyntaxNode child : node.getChildren()) {
            prettyPrint(child, indent, child.equals(lastChild));
        }
    }

    public static void prettyPrint(SyntaxNode node) {
        prettyPrint(node, "", true);
    }

    private static SyntaxNode lastOrDefault(Iterable<SyntaxNode> children) {
        SyntaxNode lastChild = null;
        for (SyntaxNode child : children) {
            lastChild = child;
        }
        return lastChild;
    }

}
