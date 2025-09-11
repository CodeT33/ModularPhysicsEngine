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

    public Object parseNextCommand() {
        Token next = peek();
        return switch (next.type) {
            case VAR -> parseVariableCall();
            case CONVERT -> parseUnitCall();
            case USE -> parseModuleCall();
            case THEN -> parseOutputCommand();
            default -> throw new RuntimeException("Unexpected token " + next.type);
        };
    }

    public UnitConvertingCall parseUnitCall() {
        consume(TokenType.CONVERT);
        UnitConvertingCall call = new UnitConvertingCall();

        Token valueToken2 = peek();
        if (valueToken2.type == TokenType.NUMBER) {
            call.value = Double.parseDouble(consume(TokenType.NUMBER).text);
        } else if (valueToken2.type == TokenType.IDENTIFIER) {
            call.valueVarName = consume(TokenType.IDENTIFIER).text;
        } else {
            throw new RuntimeException("Expected NUMBER or IDENTIFIER but got " + valueToken2.type);
        }

        consume(TokenType.SQUAREBRCLEFT);
        call.startUnit = consume(TokenType.IDENTIFIER).text;
        consume(TokenType.SQUAREBRCRIGHT);
        consume(TokenType.INTO);
        consume(TokenType.SQUAREBRCLEFT);
        call.endUnit = consume(TokenType.IDENTIFIER).text;
        consume(TokenType.SQUAREBRCRIGHT);
        consume(TokenType.OUT);
        call.outputVar = consume(TokenType.IDENTIFIER).text;

        return call;
    }

    public VariableCall parseVariableCall () {
        consume(TokenType.VAR);
        VariableCall var = new VariableCall();
        var.varName = consume(TokenType.IDENTIFIER).text;
        consume(TokenType.EQUALS);

        Token valueToken = peek();
        if (valueToken.type == TokenType.NUMBER) {
            var.value = Double.parseDouble(consume(TokenType.NUMBER).text);
        } else if (valueToken.type == TokenType.IDENTIFIER) {
            var.valueVarName = consume(TokenType.IDENTIFIER).text;
        } else {
            throw new RuntimeException("Expected NUMBER or IDENTIFIER but got " + valueToken.type);
        }

        return var;
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

            Token valueToken = peek();
            Object value;
            if (valueToken.type == TokenType.NUMBER) {
                value = Double.parseDouble(consume(TokenType.NUMBER).text);
            } else if (valueToken.type == TokenType.IDENTIFIER) {
                value = consume(TokenType.IDENTIFIER).text;
            } else {
                throw new RuntimeException("Expected NUMBER or IDENTIFIER but got " + valueToken.type);
            }
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

        do {
            cmd.displayVariables.add(consume(TokenType.IDENTIFIER).text);
        } while (match(TokenType.COMMA));

        consume(TokenType.VIA);
        cmd.display = consume(TokenType.IDENTIFIER).text;
        consume(TokenType.SEMICOLON);

        return cmd;
    }

    public List<ModuleCall> parseModuleCalls() {
        List<ModuleCall> calls = new ArrayList<>();
        while (peek().type == TokenType.USE) {
            calls.add(parseModuleCall());
        }
        return calls;
    }

}
