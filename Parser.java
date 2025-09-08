import java.util.*;

public class Parser {
    private final List<Token> tokens;
    private int position = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() {
        return tokens.get(position);
    }

    private Token consume(TokenType expected) {
        Token t = tokens.get(position);
        if (t.type != expected) {
            throw new RuntimeException("Expected " + expected + " but got " + t.type);
        }
        position++;
        return t;
    }

    private boolean match(TokenType type) {
        if (peek().type == type) {
            position++;
            return true;
        }
        return false;
    }

    public ModuleCall parseModuleCall() {
        consume(TokenType.USE);
        String moduleName = consume(TokenType.IDENTIFIER).text;

        ModuleCall call = new ModuleCall();
        call.moduleName = moduleName;

        consume(TokenType.WITH);

        while (true) {
            String key = consume(TokenType.IDENTIFIER).text;
            consume(TokenType.EQUALS);
            double value = Double.parseDouble(consume(TokenType.NUMBER).text);
            call.inputs.put(key, value);

            if (!match(TokenType.COMMA)) {
                break;
            }
        }

        consume(TokenType.OUT);
        call.outputs.add(consume(TokenType.IDENTIFIER).text);

        return call;
    }

    public OutputCommand parseOutputCommand() {
        consume(TokenType.THEN);
        consume(TokenType.OUTPUT);

        OutputCommand cmd = new OutputCommand();

        cmd.outputs.add(consume(TokenType.IDENTIFIER).text);

        consume(TokenType.VIA);
        cmd.display = consume(TokenType.IDENTIFIER).text;

        consume(TokenType.SEMICOLON);

        return cmd;
    }

}
