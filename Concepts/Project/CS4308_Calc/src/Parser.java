import java.util.ArrayList;

public class Parser {
    private Token token;
    private LexicalAnalyzer lexicalAnalyzer;
    private Memory memory = new Memory();

    public Parser(LexicalAnalyzer lexicalAnalyzer) {
        this.lexicalAnalyzer = lexicalAnalyzer;
        token = lexicalAnalyzer.getToken();

    }

        public void match(TokenType expected ){
            if (expected != token.getType()) {
                throw new RuntimeException("Expected " + expected + " but got " + token.getType());
            }else{
                token = lexicalAnalyzer.getToken();

            }

        }
        public Expression number(){
            int value = Integer.parseInt(token.getLexeme());
            match(TokenType.INTEGER_LITERAL);
            return new UnaryExpression(value);
        }
        public Expression factor() {
            if (token.getType() == TokenType.LEFT_PARENTHESIS) {
                match(TokenType.LEFT_PARENTHESIS);
                Expression expr = expression();
                match(TokenType.RIGHT_PARENTHESIS);
                return expr;
            } else if (token.getType() == TokenType.SUBTRACTION) {
                match(TokenType.SUBTRACTION);
                Expression expr = expression();
                return new BinaryExpression(new UnaryExpression(0), expr, '-');
            }else if(token.getType() == TokenType.IDENTIFIER) {
                String name = token.getLexeme();
                match(TokenType.IDENTIFIER);
                return new IdNode(name, memory);
            } else {
                return number();
            }
        }
        public Expression term(){
            Expression factor = factor();
            return termPrime(factor);
        }
        public Expression termPrime(Expression left){
            if (token.getType() == TokenType.MULTIPLICATION) {
                match(TokenType.MULTIPLICATION);
                Expression right = factor();
                return termPrime(new BinaryExpression(left, right, '*'));
            }else if (token.getType() == TokenType.DIVISION){
                match(TokenType.DIVISION);
                Expression right = factor();
                return termPrime(new  BinaryExpression(left, right, '/'));
            }else {
                return left;
            }
        }
        public Expression expression(){
            Expression term = term();
            return expressionPrime(term);
        }
        public Expression expressionPrime(Expression left){
            if (token.getType() == TokenType.ADDITION) {
                match(TokenType.ADDITION);
                Expression term = term();
                return expressionPrime(new BinaryExpression(left, term, '+'));
            }else if (token.getType() == TokenType.SUBTRACTION) {
                match(TokenType.SUBTRACTION);
                Expression term = term();
                return expressionPrime(new BinaryExpression(left, term, '-'));
            }else {
                return left;
            }
        }
        public ArrayList<Object> program(){
            return stmtList();
        }
        public ArrayList<Object> stmtList(){
            ArrayList<Object> stmtList = new ArrayList<>();
            while(token.getType() != TokenType.END_OF_INPUT && token.getType() != TokenType.ELSE && token.getType() != TokenType.END){
                stmtList.add(statement());

            }
            return stmtList;
        }
        public Object statement(){
            if(token.getType() == TokenType.PRINT){
                return  printStmt();
            } else if (token.getType() == TokenType.READ) {
                return readStmt();
            } else if (token.getType() == TokenType.IDENTIFIER) {
                return assnStmt();
            }else if (token.getType() == TokenType.IF) {
                return ifStmt();
            } else if (token.getType() == TokenType.DO) {
                if (lexicalAnalyzer.peekToken().getType() == TokenType.WHILE) {
                    return doWhileStmt();
                }else{
                    return doStmt();
                }
            } else {
                throw new RuntimeException("Invalid statement at " + token.getLexeme());
            }
        }
        public PrintNode printStmt(){
            match(TokenType.PRINT);
            String name = token.getLexeme();
            match(TokenType.IDENTIFIER);
            return new PrintNode(name, memory);
        }
        public ReadNode readStmt(){
            match(TokenType.READ);
            String name = token.getLexeme();
            match(TokenType.IDENTIFIER);
            return new ReadNode(name, memory);
        }
        public AssignmentNode assnStmt(){
            String name = token.getLexeme();
            match(TokenType.IDENTIFIER);
            match(TokenType.ASSIGNMENT);
            Expression expr = expression();
            return new AssignmentNode(name, expr, memory);
        }
        public LogicalExpression logicalExpr(){
            Expression left = expression();
            TokenType operator = token.getType();
            match(token.getType());
            Expression right = expression();
            return new LogicalExpression(left, right, operator);
        }
        public IfNode ifStmt(){
            match(TokenType.IF);
            match(TokenType.LEFT_PARENTHESIS);
            LogicalExpression condition = logicalExpr();
            match(TokenType.RIGHT_PARENTHESIS);
            match(TokenType.THEN);
            ArrayList<Object> thenBranch = stmtList();
            match(TokenType.ELSE);
            ArrayList<Object> elseBranch = stmtList();
            match(TokenType.END);
            match(TokenType.IF);
            return new IfNode(condition, thenBranch, elseBranch);
        }
        public DoWhileNode doWhileStmt(){
            match(TokenType.DO);
            match(TokenType.WHILE);
            match(TokenType.LEFT_PARENTHESIS);
            LogicalExpression condition = logicalExpr();
            match(TokenType.RIGHT_PARENTHESIS);
            ArrayList<Object> body = stmtList();
            match(TokenType.END);
            match(TokenType.DO);
            return new DoWhileNode(condition, body);
        }
        public DoNode doStmt(){
            match(TokenType.DO);
            String id = token.getLexeme();
            match(TokenType.IDENTIFIER);
            match(TokenType.ASSIGNMENT);
            Expression from = expression();
            match(TokenType.COMMA);
            Expression to = expression();
            ArrayList<Object> body = stmtList();
            match(TokenType.END);
            match(TokenType.DO);
            return new DoNode(id,from ,to ,body, memory);
        }
    }

