import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}



class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        int result = checkBrackets(text);

        if(result != 0) {
            System.out.println(result);
        } else {
            System.out.println("Success");
        }
    }

    public static int checkBrackets(String text) {
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();

        for (int position = 1; position <= text.length(); ++position) {
            char next = text.charAt(position - 1);

            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position));
            }

            else if (next == ')' || next == ']' || next == '}') {
                if(!opening_brackets_stack.isEmpty() && opening_brackets_stack.peek().Match(next)) {
                    opening_brackets_stack.pop();
                } else {
                    return position;
                }
            }
        }

        if(opening_brackets_stack.isEmpty()) {
            return 0;
        } else {
            return opening_brackets_stack.peek().position;
        }
    }
}
