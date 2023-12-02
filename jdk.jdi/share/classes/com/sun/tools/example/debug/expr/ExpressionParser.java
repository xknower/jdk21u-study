package com.sun.tools.example.debug.expr;

import com.sun.jdi.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ExpressionParser implements ExpressionParserConstants {

  Stack<LValue> stack = new Stack<>();
  VirtualMachine vm = null;
  GetFrame frameGetter = null;
  private static GetFrame lastFrameGetter;
  private static LValue lastLValue;

  LValue peek() {
    return stack.peek();
  }

  LValue pop() {
    return stack.pop();
  }

  void push(LValue lval) {
    stack.push(lval);
  }

  public static Value getMassagedValue() throws ParseException {
        return lastLValue.getMassagedValue(lastFrameGetter);
  }

  public interface GetFrame {
        StackFrame get() throws IncompatibleThreadStateException;
  }

  @SuppressWarnings("deprecation")
  public static Value evaluate(String expr, VirtualMachine vm,
                               GetFrame frameGetter) throws ParseException,
                                            InvocationException,
                                            InvalidTypeException,
                                            ClassNotLoadedException,
                                            IncompatibleThreadStateException {
        // TODO StringBufferInputStream is deprecated.
        java.io.InputStream in = new java.io.StringBufferInputStream(expr);
        ExpressionParser parser = new ExpressionParser(in);
        parser.vm = vm;
        parser.frameGetter = frameGetter;
        Value value = null;
        parser.Expression();
        lastFrameGetter = frameGetter;
        lastLValue = parser.pop();
        return lastLValue.getValue();
  }

  public static void main(String args[]) {
    ExpressionParser parser;
    System.out.print("Java Expression Parser:  ");
    if (args.length == 0) {
      System.out.println("Reading from standard input . . .");
      parser = new ExpressionParser(System.in);
    } else if (args.length == 1) {
      System.out.println("Reading from file " + args[0] + " . . .");
      try {
        parser = new ExpressionParser(new java.io.FileInputStream(args[0]));
      } catch (java.io.FileNotFoundException e) {
        System.out.println("Java Parser Version 1.0.2:  File " +
                           args[0] + " not found.");
        return;
      }
    } else {
      System.out.println("Usage is one of:");
      System.out.println("         java ExpressionParser < inputfile");
      System.out.println("OR");
      System.out.println("         java ExpressionParser inputfile");
      return;
    }
    try {
        parser.Expression();
        System.out.print("Java Expression Parser:  ");
        System.out.println("Java program parsed successfully.");
    } catch (ParseException e) {
        System.out.print("Java Expression Parser:  ");
        System.out.println("Encountered errors during parse.");
    }
  }

/*****************************************
 * THE JAVA LANGUAGE GRAMMAR STARTS HERE *
 *****************************************/

/*
 * Type, name and expression syntax follows.
 */
  public final void Type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FLOAT:
    case INT:
    case LONG:
    case SHORT:
      PrimitiveType();
      break;
    case IDENTIFIER:
      Name();
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACKET:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      jj_consume_token(LBRACKET);
      jj_consume_token(RBRACKET);
    }
  }

  public final void PrimitiveType() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
      break;
    case CHAR:
      jj_consume_token(CHAR);
      break;
    case BYTE:
      jj_consume_token(BYTE);
      break;
    case SHORT:
      jj_consume_token(SHORT);
      break;
    case INT:
      jj_consume_token(INT);
      break;
    case LONG:
      jj_consume_token(LONG);
      break;
    case FLOAT:
      jj_consume_token(FLOAT);
      break;
    case DOUBLE:
      jj_consume_token(DOUBLE);
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  public final String Name() throws ParseException {
    StringBuilder sb = new StringBuilder();
    jj_consume_token(IDENTIFIER);
                 sb.append(token);
    label_2:
    while (true) {
      if (jj_2_1(2)) {
        ;
      } else {
        break label_2;
      }
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
                                    sb.append('.'); sb.append(token);
    }
          {if (true) return sb.toString();}
    throw new Error("Missing return statement in function");
  }

  public final void NameList() throws ParseException {
    Name();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      jj_consume_token(COMMA);
      Name();
    }
  }

/*
 * Expression syntax follows.
 */
  public final void Expression() throws ParseException {
    if (jj_2_2(2147483647)) {
      Assignment();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FALSE:
      case NEW:
      case NULL:
      case SUPER:
      case THIS:
      case TRUE:
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case BANG:
      case TILDE:
      case INCR:
      case DECR:
      case PLUS:
      case MINUS:
        ConditionalExpression();
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  public final void Assignment() throws ParseException {
    PrimaryExpression();
    AssignmentOperator();
    Expression();
          LValue exprVal = pop(); pop().setValue(exprVal); push(exprVal);
  }

  public final void AssignmentOperator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASSIGN:
      jj_consume_token(ASSIGN);
      break;
    case STARASSIGN:
      jj_consume_token(STARASSIGN);
      break;
    case SLASHASSIGN:
      jj_consume_token(SLASHASSIGN);
      break;
    case REMASSIGN:
      jj_consume_token(REMASSIGN);
      break;
    case PLUSASSIGN:
      jj_consume_token(PLUSASSIGN);
      break;
    case MINUSASSIGN:
      jj_consume_token(MINUSASSIGN);
      break;
    case LSHIFTASSIGN:
      jj_consume_token(LSHIFTASSIGN);
      break;
    case RSIGNEDSHIFTASSIGN:
      jj_consume_token(RSIGNEDSHIFTASSIGN);
      break;
    case RUNSIGNEDSHIFTASSIGN:
      jj_consume_token(RUNSIGNEDSHIFTASSIGN);
      break;
    case ANDASSIGN:
      jj_consume_token(ANDASSIGN);
      break;
    case XORASSIGN:
      jj_consume_token(XORASSIGN);
      break;
    case ORASSIGN:
      jj_consume_token(ORASSIGN);
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  public final void ConditionalExpression() throws ParseException {
    ConditionalOrExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HOOK:
      jj_consume_token(HOOK);
      Expression();
      jj_consume_token(COLON);
      ConditionalExpression();
                  LValue falseBranch = pop(); LValue trueBranch = pop();
                  Value cond = pop().interiorGetValue();
                  if (cond instanceof BooleanValue) {
                        push(((BooleanValue)cond).booleanValue()?
                                        trueBranch : falseBranch);
                  } else {
                        {if (true) throw new ParseException("Condition must be boolean");}
                  }
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
  }

  public final void ConditionalOrExpression() throws ParseException {
    ConditionalAndExpression();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SC_OR:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      jj_consume_token(SC_OR);
      ConditionalAndExpression();
                          {if (true) throw new ParseException("operation not yet supported");}
    }
  }

  public final void ConditionalAndExpression() throws ParseException {
    InclusiveOrExpression();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SC_AND:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_5;
      }
      jj_consume_token(SC_AND);
      InclusiveOrExpression();
                          {if (true) throw new ParseException("operation not yet supported");}
    }
  }

  public final void InclusiveOrExpression() throws ParseException {
    ExclusiveOrExpression();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BIT_OR:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_6;
      }
      jj_consume_token(BIT_OR);
      ExclusiveOrExpression();
                          {if (true) throw new ParseException("operation not yet supported");}
    }
  }

  public final void ExclusiveOrExpression() throws ParseException {
    AndExpression();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case XOR:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_7;
      }
      jj_consume_token(XOR);
      AndExpression();
                          {if (true) throw new ParseException("operation not yet supported");}
    }
  }

  public final void AndExpression() throws ParseException {
    EqualityExpression();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BIT_AND:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_8;
      }
      jj_consume_token(BIT_AND);
      EqualityExpression();
                          {if (true) throw new ParseException("operation not yet supported");}
    }
  }

  public final void EqualityExpression() throws ParseException {
 Token tok;
    InstanceOfExpression();
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQ:
      case NE:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_9;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQ:
        tok = jj_consume_token(EQ);
        break;
      case NE:
        tok = jj_consume_token(NE);
        break;
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      InstanceOfExpression();
                  LValue left = pop();
                  push( LValue.booleanOperation(vm, tok, pop(), left) );
    }
  }

  public final void InstanceOfExpression() throws ParseException {
    RelationalExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INSTANCEOF:
      jj_consume_token(INSTANCEOF);
      Type();
                          {if (true) throw new ParseException("operation not yet supported");}
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
  }

  public final void RelationalExpression() throws ParseException {
 Token tok;
    ShiftExpression();
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case GT:
      case LT:
      case LE:
      case GE:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_10;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
        tok = jj_consume_token(LT);
        break;
      case GT:
        tok = jj_consume_token(GT);
        break;
      case LE:
        tok = jj_consume_token(LE);
        break;
      case GE:
        tok = jj_consume_token(GE);
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      ShiftExpression();
                  LValue left = pop();
                  push( LValue.booleanOperation(vm, tok, pop(), left) );
    }
  }

  public final void ShiftExpression() throws ParseException {
    AdditiveExpression();
    label_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LSHIFT:
      case RSIGNEDSHIFT:
      case RUNSIGNEDSHIFT:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_11;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LSHIFT:
        jj_consume_token(LSHIFT);
        break;
      case RSIGNEDSHIFT:
        jj_consume_token(RSIGNEDSHIFT);
        break;
      case RUNSIGNEDSHIFT:
        jj_consume_token(RUNSIGNEDSHIFT);
        break;
      default:
        jj_la1[18] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      AdditiveExpression();
                          {if (true) throw new ParseException("operation not yet supported");}
    }
  }

  public final void AdditiveExpression() throws ParseException {
 Token tok;
    MultiplicativeExpression();
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_12;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        tok = jj_consume_token(PLUS);
        break;
      case MINUS:
        tok = jj_consume_token(MINUS);
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      MultiplicativeExpression();
                  LValue left = pop();
                  push( LValue.operation(vm, tok, pop(), left, frameGetter) );
    }
  }

  public final void MultiplicativeExpression() throws ParseException {
 Token tok;
    UnaryExpression();
    label_13:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STAR:
      case SLASH:
      case REM:
        ;
        break;
      default:
        jj_la1[21] = jj_gen;
        break label_13;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STAR:
        tok = jj_consume_token(STAR);
        break;
      case SLASH:
        tok = jj_consume_token(SLASH);
        break;
      case REM:
        tok = jj_consume_token(REM);
        break;
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      UnaryExpression();
                  LValue left = pop();
                  push( LValue.operation(vm, tok, pop(), left, frameGetter) );
    }
  }

  public final void UnaryExpression() throws ParseException {
 Token tok;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        tok = jj_consume_token(PLUS);
        break;
      case MINUS:
        tok = jj_consume_token(MINUS);
        break;
      default:
        jj_la1[23] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      UnaryExpression();
                  push( LValue.operation(vm, tok, pop(), frameGetter) );
      break;
    case INCR:
      PreIncrementExpression();
      break;
    case DECR:
      PreDecrementExpression();
      break;
    case FALSE:
    case NEW:
    case NULL:
    case SUPER:
    case THIS:
    case TRUE:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case BANG:
    case TILDE:
      UnaryExpressionNotPlusMinus();
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  public final void PreIncrementExpression() throws ParseException {
    jj_consume_token(INCR);
    PrimaryExpression();
                          {if (true) throw new ParseException("operation not yet supported");}
  }

  public final void PreDecrementExpression() throws ParseException {
    jj_consume_token(DECR);
    PrimaryExpression();
                          {if (true) throw new ParseException("operation not yet supported");}
  }

  public final void UnaryExpressionNotPlusMinus() throws ParseException {
 Token tok;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BANG:
    case TILDE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TILDE:
        tok = jj_consume_token(TILDE);
        break;
      case BANG:
        tok = jj_consume_token(BANG);
        break;
      default:
        jj_la1[25] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      UnaryExpression();
                  push( LValue.operation(vm, tok, pop(), frameGetter) );
      break;
    default:
      jj_la1[26] = jj_gen;
      if (jj_2_3(2147483647)) {
        CastExpression();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case FALSE:
        case NEW:
        case NULL:
        case SUPER:
        case THIS:
        case TRUE:
        case INTEGER_LITERAL:
        case FLOATING_POINT_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
          PostfixExpression();
          break;
        default:
          jj_la1[27] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

// This production is to determine lookahead only.  The LOOKAHEAD specifications
// below are not used, but they are there just to indicate that we know about
// this.
  public final void CastLookahead() throws ParseException {
    if (jj_2_4(2)) {
      jj_consume_token(LPAREN);
      PrimitiveType();
    } else if (jj_2_5(2147483647)) {
      jj_consume_token(LPAREN);
      Name();
      jj_consume_token(LBRACKET);
      jj_consume_token(RBRACKET);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAREN:
        jj_consume_token(LPAREN);
        Name();
        jj_consume_token(RPAREN);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TILDE:
          jj_consume_token(TILDE);
          break;
        case BANG:
          jj_consume_token(BANG);
          break;
        case LPAREN:
          jj_consume_token(LPAREN);
          break;
        case IDENTIFIER:
          jj_consume_token(IDENTIFIER);
          break;
        case THIS:
          jj_consume_token(THIS);
          break;
        case SUPER:
          jj_consume_token(SUPER);
          break;
        case NEW:
          jj_consume_token(NEW);
          break;
        case FALSE:
        case NULL:
        case TRUE:
        case INTEGER_LITERAL:
        case FLOATING_POINT_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
          Literal();
          break;
        default:
          jj_la1[28] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[29] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  public final void PostfixExpression() throws ParseException {
    PrimaryExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INCR:
    case DECR:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INCR:
        jj_consume_token(INCR);
        break;
      case DECR:
        jj_consume_token(DECR);
                          {if (true) throw new ParseException("operation not yet supported");}
        break;
      default:
        jj_la1[30] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[31] = jj_gen;
      ;
    }
  }

  public final void CastExpression() throws ParseException {
    if (jj_2_6(2)) {
      jj_consume_token(LPAREN);
      PrimitiveType();
      label_14:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LBRACKET:
          ;
          break;
        default:
          jj_la1[32] = jj_gen;
          break label_14;
        }
        jj_consume_token(LBRACKET);
        jj_consume_token(RBRACKET);
      }
      jj_consume_token(RPAREN);
      UnaryExpression();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAREN:
        jj_consume_token(LPAREN);
        Name();
        label_15:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case LBRACKET:
            ;
            break;
          default:
            jj_la1[33] = jj_gen;
            break label_15;
          }
          jj_consume_token(LBRACKET);
          jj_consume_token(RBRACKET);
        }
        jj_consume_token(RPAREN);
        UnaryExpressionNotPlusMinus();
        break;
      default:
        jj_la1[34] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  public final void PrimaryExpression() throws ParseException {
    PrimaryPrefix();
    label_16:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAREN:
      case LBRACKET:
      case DOT:
        ;
        break;
      default:
        jj_la1[35] = jj_gen;
        break label_16;
      }
      PrimarySuffix();
    }
  }

  public final void PrimaryPrefix() throws ParseException {
 String name;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FALSE:
    case NULL:
    case TRUE:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
      Literal();
      break;
    case IDENTIFIER:
      name = Name();
                          push(LValue.makeName(vm, frameGetter, name));
      break;
    case THIS:
      jj_consume_token(THIS);
                          push(LValue.makeThisObject(vm, frameGetter, token));
      break;
    case SUPER:
      jj_consume_token(SUPER);
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
                          {if (true) throw new ParseException("operation not yet supported");}
      break;
    case LPAREN:
      jj_consume_token(LPAREN);
      Expression();
      jj_consume_token(RPAREN);
      break;
    case NEW:
      AllocationExpression();
      break;
    default:
      jj_la1[36] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  public final void PrimarySuffix() throws ParseException {
 List<Value> argList;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LBRACKET:
      jj_consume_token(LBRACKET);
      Expression();
      jj_consume_token(RBRACKET);
                          LValue index = pop();
                          push(pop().arrayElementLValue(index));
      break;
    case DOT:
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
                          push(pop().memberLValue(frameGetter, token.image));
      break;
    case LPAREN:
      argList = Arguments();
                          peek().invokeWith(argList);
      break;
    default:
      jj_la1[37] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  public final void Literal() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      jj_consume_token(INTEGER_LITERAL);
                          push(LValue.makeInteger(vm, token));
      break;
    case FLOATING_POINT_LITERAL:
      jj_consume_token(FLOATING_POINT_LITERAL);
                          push(LValue.makeFloat(vm, token));
      break;
    case CHARACTER_LITERAL:
      jj_consume_token(CHARACTER_LITERAL);
                          push(LValue.makeCharacter(vm, token));
      break;
    case STRING_LITERAL:
      jj_consume_token(STRING_LITERAL);
                          push(LValue.makeString(vm, token));
      break;
    case FALSE:
    case TRUE:
      BooleanLiteral();
                          push(LValue.makeBoolean(vm, token));
      break;
    case NULL:
      NullLiteral();
                          push(LValue.makeNull(vm, token));
      break;
    default:
      jj_la1[38] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  public final void BooleanLiteral() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
      jj_consume_token(TRUE);
      break;
    case FALSE:
      jj_consume_token(FALSE);
      break;
    default:
      jj_la1[39] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  public final void NullLiteral() throws ParseException {
    jj_consume_token(NULL);
  }

  public final List<Value> Arguments() throws ParseException {
 List<Value> argList = new ArrayList<>();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FALSE:
    case NEW:
    case NULL:
    case SUPER:
    case THIS:
    case TRUE:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case BANG:
    case TILDE:
    case INCR:
    case DECR:
    case PLUS:
    case MINUS:
      ArgumentList(argList);
      break;
    default:
      jj_la1[40] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    {if (true) return argList;}
    throw new Error("Missing return statement in function");
  }

  public final void ArgumentList(List<Value> argList) throws ParseException {
    Expression();
                argList.add(pop().interiorGetValue());
    label_17:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[41] = jj_gen;
        break label_17;
      }
      jj_consume_token(COMMA);
      Expression();
                      argList.add(pop().interiorGetValue());
    }
  }

  public final void AllocationExpression() throws ParseException {
 List<Value> argList; String className;
    if (jj_2_7(2)) {
      jj_consume_token(NEW);
      PrimitiveType();
      ArrayDimensions();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NEW:
        jj_consume_token(NEW);
        className = Name();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LPAREN:
          argList = Arguments();
                          push(LValue.makeNewObject(vm, frameGetter, className, argList));
          break;
        case LBRACKET:
          ArrayDimensions();
                          {if (true) throw new ParseException("operation not yet supported");}
          break;
        default:
          jj_la1[42] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[43] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

/*
 * The second LOOKAHEAD specification below is to parse to PrimarySuffix
 * if there is an expression between the "[...]".
 */
  public final void ArrayDimensions() throws ParseException {
    label_18:
    while (true) {
      jj_consume_token(LBRACKET);
      Expression();
      jj_consume_token(RBRACKET);
      if (jj_2_8(2)) {
        ;
      } else {
        break label_18;
      }
    }
    label_19:
    while (true) {
      if (jj_2_9(2)) {
        ;
      } else {
        break label_19;
      }
      jj_consume_token(LBRACKET);
      jj_consume_token(RBRACKET);
    }
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  private boolean jj_3R_94() {
    if (jj_scan_token(DECR)) return true;
    if (jj_3R_20()) return true;
    return false;
  }

  private boolean jj_3R_86() {
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3R_93() {
    if (jj_scan_token(INCR)) return true;
    if (jj_3R_20()) return true;
    return false;
  }

  private boolean jj_3R_91() {
    if (jj_3R_95()) return true;
    return false;
  }

  private boolean jj_3R_23() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(10)) {
    jj_scanpos = xsp;
    if (jj_scan_token(15)) {
    jj_scanpos = xsp;
    if (jj_scan_token(12)) {
    jj_scanpos = xsp;
    if (jj_scan_token(45)) {
    jj_scanpos = xsp;
    if (jj_scan_token(34)) {
    jj_scanpos = xsp;
    if (jj_scan_token(36)) {
    jj_scanpos = xsp;
    if (jj_scan_token(27)) {
    jj_scanpos = xsp;
    if (jj_scan_token(21)) return true;
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_90() {
    if (jj_3R_94()) return true;
    return false;
  }

  private boolean jj_3R_89() {
    if (jj_3R_93()) return true;
    return false;
  }

  private boolean jj_3R_85() {
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3R_60() {
    if (jj_3R_58()) return true;
    return false;
  }

  private boolean jj_3R_88() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(94)) {
    jj_scanpos = xsp;
    if (jj_scan_token(95)) return true;
    }
    if (jj_3R_83()) return true;
    return false;
  }

  private boolean jj_3R_83() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_88()) {
    jj_scanpos = xsp;
    if (jj_3R_89()) {
    jj_scanpos = xsp;
    if (jj_3R_90()) {
    jj_scanpos = xsp;
    if (jj_3R_91()) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3R_82() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_85()) {
    jj_scanpos = xsp;
    if (jj_3R_86()) return true;
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_87()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_59() {
    if (jj_3R_55()) return true;
    return false;
  }

  private boolean jj_3R_96() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(96)) {
    jj_scanpos = xsp;
    if (jj_scan_token(97)) {
    jj_scanpos = xsp;
    if (jj_scan_token(101)) return true;
    }
    }
    if (jj_3R_83()) return true;
    return false;
  }

  private boolean jj_3R_80() {
    if (jj_3R_83()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_96()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_92() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(94)) {
    jj_scanpos = xsp;
    if (jj_scan_token(95)) return true;
    }
    if (jj_3R_80()) return true;
    return false;
  }

  private boolean jj_3_8() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_3R_25()) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_58() {
    Token xsp;
    if (jj_3_8()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_8()) { jj_scanpos = xsp; break; }
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_9()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_84() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(102)) {
    jj_scanpos = xsp;
    if (jj_scan_token(103)) {
    jj_scanpos = xsp;
    if (jj_scan_token(104)) return true;
    }
    }
    if (jj_3R_78()) return true;
    return false;
  }

  private boolean jj_3R_78() {
    if (jj_3R_80()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_92()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_54() {
    if (jj_scan_token(NEW)) return true;
    if (jj_3R_24()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_59()) {
    jj_scanpos = xsp;
    if (jj_3R_60()) return true;
    }
    return false;
  }

  private boolean jj_3R_76() {
    if (jj_3R_78()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_84()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_81() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(81)) {
    jj_scanpos = xsp;
    if (jj_scan_token(80)) {
    jj_scanpos = xsp;
    if (jj_scan_token(87)) {
    jj_scanpos = xsp;
    if (jj_scan_token(88)) return true;
    }
    }
    }
    if (jj_3R_76()) return true;
    return false;
  }

  private boolean jj_3R_43() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_7()) {
    jj_scanpos = xsp;
    if (jj_3R_54()) return true;
    }
    return false;
  }

  private boolean jj_3_7() {
    if (jj_scan_token(NEW)) return true;
    if (jj_3R_23()) return true;
    if (jj_3R_58()) return true;
    return false;
  }

  private boolean jj_3R_67() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_25()) return true;
    return false;
  }

  private boolean jj_3R_64() {
    if (jj_3R_25()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_67()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_61() {
    if (jj_3R_64()) return true;
    return false;
  }

  private boolean jj_3R_79() {
    if (jj_scan_token(INSTANCEOF)) return true;
    if (jj_3R_82()) return true;
    return false;
  }

  private boolean jj_3R_74() {
    if (jj_3R_76()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_81()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_55() {
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_61()) jj_scanpos = xsp;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  private boolean jj_3R_72() {
    if (jj_3R_74()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_79()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_77() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(86)) {
    jj_scanpos = xsp;
    if (jj_scan_token(89)) return true;
    }
    if (jj_3R_72()) return true;
    return false;
  }

  private boolean jj_3R_75() {
    if (jj_scan_token(BIT_AND)) return true;
    if (jj_3R_70()) return true;
    return false;
  }

  private boolean jj_3R_70() {
    if (jj_3R_72()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_77()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_57() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(54)) {
    jj_scanpos = xsp;
    if (jj_scan_token(24)) return true;
    }
    return false;
  }

  private boolean jj_3R_53() {
    if (jj_scan_token(39)) return true;
    return false;
  }

  private boolean jj_3R_39() {
    if (jj_3R_42()) return true;
    return false;
  }

  private boolean jj_3R_68() {
    if (jj_3R_70()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_75()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_73() {
    if (jj_scan_token(XOR)) return true;
    if (jj_3R_68()) return true;
    return false;
  }

  private boolean jj_3R_52() {
    if (jj_3R_57()) return true;
    return false;
  }

  private boolean jj_3R_51() {
    if (jj_scan_token(STRING_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_50() {
    if (jj_scan_token(CHARACTER_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_65() {
    if (jj_3R_68()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_73()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_71() {
    if (jj_scan_token(BIT_OR)) return true;
    if (jj_3R_65()) return true;
    return false;
  }

  private boolean jj_3R_49() {
    if (jj_scan_token(FLOATING_POINT_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_42() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_48()) {
    jj_scanpos = xsp;
    if (jj_3R_49()) {
    jj_scanpos = xsp;
    if (jj_3R_50()) {
    jj_scanpos = xsp;
    if (jj_3R_51()) {
    jj_scanpos = xsp;
    if (jj_3R_52()) {
    jj_scanpos = xsp;
    if (jj_3R_53()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_48() {
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_62() {
    if (jj_3R_65()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_71()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_69() {
    if (jj_scan_token(SC_AND)) return true;
    if (jj_3R_62()) return true;
    return false;
  }

  private boolean jj_3R_46() {
    if (jj_3R_55()) return true;
    return false;
  }

  private boolean jj_3R_45() {
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_56() {
    if (jj_3R_62()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_69()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_66() {
    if (jj_scan_token(SC_OR)) return true;
    if (jj_3R_56()) return true;
    return false;
  }

  private boolean jj_3R_44() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_3R_25()) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_38() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_44()) {
    jj_scanpos = xsp;
    if (jj_3R_45()) {
    jj_scanpos = xsp;
    if (jj_3R_46()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_37() {
    if (jj_3R_43()) return true;
    return false;
  }

  private boolean jj_3R_27() {
    if (jj_3R_38()) return true;
    return false;
  }

  private boolean jj_3R_36() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_25()) return true;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  private boolean jj_3R_47() {
    if (jj_3R_56()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_66()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_104() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_35() {
    if (jj_scan_token(SUPER)) return true;
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_34() {
    if (jj_scan_token(THIS)) return true;
    return false;
  }

  private boolean jj_3R_63() {
    if (jj_scan_token(HOOK)) return true;
    if (jj_3R_25()) return true;
    if (jj_scan_token(COLON)) return true;
    if (jj_3R_41()) return true;
    return false;
  }

  private boolean jj_3R_33() {
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3R_105() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_32() {
    if (jj_3R_42()) return true;
    return false;
  }

  private boolean jj_3R_26() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) {
    jj_scanpos = xsp;
    if (jj_3R_35()) {
    jj_scanpos = xsp;
    if (jj_3R_36()) {
    jj_scanpos = xsp;
    if (jj_3R_37()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_20() {
    if (jj_3R_26()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_27()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_41() {
    if (jj_3R_47()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_63()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_106() {
    if (jj_scan_token(DECR)) return true;
    return false;
  }

  private boolean jj_3R_102() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_24()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_105()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_95()) return true;
    return false;
  }

  private boolean jj_3R_21() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(79)) {
    jj_scanpos = xsp;
    if (jj_scan_token(107)) {
    jj_scanpos = xsp;
    if (jj_scan_token(108)) {
    jj_scanpos = xsp;
    if (jj_scan_token(112)) {
    jj_scanpos = xsp;
    if (jj_scan_token(105)) {
    jj_scanpos = xsp;
    if (jj_scan_token(106)) {
    jj_scanpos = xsp;
    if (jj_scan_token(113)) {
    jj_scanpos = xsp;
    if (jj_scan_token(114)) {
    jj_scanpos = xsp;
    if (jj_scan_token(115)) {
    jj_scanpos = xsp;
    if (jj_scan_token(109)) {
    jj_scanpos = xsp;
    if (jj_scan_token(111)) {
    jj_scanpos = xsp;
    if (jj_scan_token(110)) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_103() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(92)) {
    jj_scanpos = xsp;
    if (jj_3R_106()) return true;
    }
    return false;
  }

  private boolean jj_3R_100() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_6()) {
    jj_scanpos = xsp;
    if (jj_3R_102()) return true;
    }
    return false;
  }

  private boolean jj_3_6() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_23()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_104()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_83()) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_3R_20()) return true;
    if (jj_3R_21()) return true;
    return false;
  }

  private boolean jj_3R_40() {
    if (jj_3R_20()) return true;
    if (jj_3R_21()) return true;
    if (jj_3R_25()) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_101() {
    if (jj_3R_20()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_103()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_31() {
    if (jj_3R_41()) return true;
    return false;
  }

  private boolean jj_3R_30() {
    if (jj_3R_40()) return true;
    return false;
  }

  private boolean jj_3R_25() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_30()) {
    jj_scanpos = xsp;
    if (jj_3R_31()) return true;
    }
    return false;
  }

  private boolean jj_3R_29() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(RPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(83)) {
    jj_scanpos = xsp;
    if (jj_scan_token(82)) {
    jj_scanpos = xsp;
    if (jj_scan_token(70)) {
    jj_scanpos = xsp;
    if (jj_scan_token(67)) {
    jj_scanpos = xsp;
    if (jj_scan_token(50)) {
    jj_scanpos = xsp;
    if (jj_scan_token(47)) {
    jj_scanpos = xsp;
    if (jj_scan_token(38)) {
    jj_scanpos = xsp;
    if (jj_3R_39()) return true;
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_28() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3R_22() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_3() {
    if (jj_3R_22()) return true;
    return false;
  }

  private boolean jj_3R_99() {
    if (jj_3R_101()) return true;
    return false;
  }

  private boolean jj_3R_87() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_98() {
    if (jj_3R_100()) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3_9() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_97() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(83)) {
    jj_scanpos = xsp;
    if (jj_scan_token(82)) return true;
    }
    if (jj_3R_83()) return true;
    return false;
  }

  private boolean jj_3R_95() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_97()) {
    jj_scanpos = xsp;
    if (jj_3R_98()) {
    jj_scanpos = xsp;
    if (jj_3R_99()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_24() {
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_1()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /** Generated Token Manager. */
  public ExpressionParserTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  private final int[] jj_la1 = new int[44];
  private static int[] jj_la1_0;
  private static int[] jj_la1_1;
  private static int[] jj_la1_2;
  private static int[] jj_la1_3;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
      jj_la1_init_2();
      jj_la1_init_3();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x8209400,0x0,0x8209400,0x0,0x1000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1000000,0x0,0x0,0x1000000,0x1000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1000000,0x0,0x1000000,0x1000000,0x1000000,0x0,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x2014,0x0,0x2014,0x0,0x884480c0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x884480c0,0x0,0x0,0x884480c0,0x884480c0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x884480c0,0x0,0x88400080,0x400000,0x884480c0,0x0,0x0,0x40,};
   }
   private static void jj_la1_init_2() {
      jj_la1_2 = new int[] {0x8,0x400,0x0,0x2000,0xf00c004e,0x8000,0x100000,0x4000000,0x8000000,0x0,0x0,0x0,0x2400000,0x2400000,0x0,0x1830000,0x1830000,0x0,0x0,0xc0000000,0xc0000000,0x0,0x0,0xc0000000,0xf00c004e,0xc0000,0xc0000,0x4e,0xc004e,0x40,0x30000000,0x30000000,0x400,0x400,0x40,0x4440,0x4e,0x4440,0x6,0x0,0xf00c004e,0x2000,0x440,0x0,};
   }
   private static void jj_la1_init_3() {
      jj_la1_3 = new int[] {0x0,0x0,0x0,0x0,0x0,0xffe00,0x0,0x0,0x0,0x8,0x10,0x4,0x0,0x0,0x0,0x0,0x0,0x1c0,0x1c0,0x0,0x0,0x23,0x23,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
  private final JJCalls[] jj_2_rtns = new JJCalls[9];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public ExpressionParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public ExpressionParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ExpressionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public ExpressionParser(java.io.Reader stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new ExpressionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public ExpressionParser(ExpressionParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ExpressionParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial") // JDK implementation class
  private static final class LookaheadSuccess extends java.lang.Error { }
  private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  public final Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  public final Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[116];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 44; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
          if ((jj_la1_3[i] & (1<<j)) != 0) {
            la1tokens[96+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 116; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  public final void enable_tracing() {
  }

  /** Disable tracing. */
  public final void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 9; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
