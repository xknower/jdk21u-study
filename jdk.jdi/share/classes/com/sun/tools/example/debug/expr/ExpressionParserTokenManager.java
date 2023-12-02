package com.sun.tools.example.debug.expr;
import com.sun.jdi.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/** Token Manager. */
public class ExpressionParserTokenManager implements ExpressionParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1)
{
   switch (pos)
   {
      case 0:
         if ((active1 & 0x100200000000L) != 0L)
            return 49;
         if ((active1 & 0x4000L) != 0L)
            return 4;
         if ((active0 & 0x7fffffffffffe00L) != 0L)
         {
            jjmatchedKind = 67;
            return 28;
         }
         return -1;
      case 1:
         if ((active0 & 0x40300000L) != 0L)
            return 28;
         if ((active0 & 0x7ffffffbfcffe00L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 67;
               jjmatchedPos = 1;
            }
            return 28;
         }
         return -1;
      case 2:
         if ((active0 & 0x80004c10000000L) != 0L)
            return 28;
         if ((active0 & 0x77fffb3afeffe00L) != 0L)
         {
            if (jjmatchedPos != 2)
            {
               jjmatchedKind = 67;
               jjmatchedPos = 2;
            }
            return 28;
         }
         return -1;
      case 3:
         if ((active0 & 0x63bff2b8faf4e00L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 3;
            return 28;
         }
         if ((active0 & 0x14400902040b000L) != 0L)
            return 28;
         return -1;
      case 4:
         if ((active0 & 0x418a0000f034800L) != 0L)
            return 28;
         if ((active0 & 0x2235f2b80ac0600L) != 0L)
         {
            if (jjmatchedPos != 4)
            {
               jjmatchedKind = 67;
               jjmatchedPos = 4;
            }
            return 28;
         }
         return -1;
      case 5:
         if ((active0 & 0x11582100200000L) != 0L)
            return 28;
         if ((active0 & 0x222070a848c0600L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 5;
            return 28;
         }
         return -1;
      case 6:
         if ((active0 & 0x222040a80040200L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 6;
            return 28;
         }
         if ((active0 & 0x30004880400L) != 0L)
            return 28;
         return -1;
      case 7:
         if ((active0 & 0x200000000040200L) != 0L)
            return 28;
         if ((active0 & 0x22040a80000000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 7;
            return 28;
         }
         return -1;
      case 8:
         if ((active0 & 0x20040800000000L) != 0L)
            return 28;
         if ((active0 & 0x2000280000000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 8;
            return 28;
         }
         return -1;
      case 9:
         if ((active0 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 9;
            return 28;
         }
         if ((active0 & 0x280000000L) != 0L)
            return 28;
         return -1;
      case 10:
         if ((active0 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 10;
            return 28;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0, long active1)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 82;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x2000000L);
      case 37:
         jjmatchedKind = 101;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x1000000000000L);
      case 38:
         jjmatchedKind = 98;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x200008000000L);
      case 40:
         return jjStopAtPos(0, 70);
      case 41:
         return jjStopAtPos(0, 71);
      case 42:
         jjmatchedKind = 96;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x80000000000L);
      case 43:
         jjmatchedKind = 94;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x20010000000L);
      case 44:
         return jjStopAtPos(0, 77);
      case 45:
         jjmatchedKind = 95;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x40020000000L);
      case 46:
         return jjStartNfaWithStates_0(0, 78, 4);
      case 47:
         jjmatchedKind = 97;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x100000000000L);
      case 58:
         return jjStopAtPos(0, 85);
      case 59:
         return jjStopAtPos(0, 76);
      case 60:
         jjmatchedKind = 81;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x2004000800000L);
      case 61:
         jjmatchedKind = 79;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x400000L);
      case 62:
         jjmatchedKind = 80;
         return jjMoveStringLiteralDfa1_0(0x0L, 0xc018001000000L);
      case 63:
         return jjStopAtPos(0, 84);
      case 91:
         return jjStopAtPos(0, 74);
      case 93:
         return jjStopAtPos(0, 75);
      case 94:
         jjmatchedKind = 100;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x800000000000L);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x200L, 0x0L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x1c00L, 0x0L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x7e000L, 0x0L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x380000L, 0x0L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0xc00000L, 0x0L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x1f000000L, 0x0L);
      case 103:
         return jjMoveStringLiteralDfa1_0(0x20000000L, 0x0L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0xfc0000000L, 0x0L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x1000000000L, 0x0L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0xe000000000L, 0x0L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0xf0000000000L, 0x0L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x100000000000L, 0x0L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x3e00000000000L, 0x0L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0xfc000000000000L, 0x0L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x300000000000000L, 0x0L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x400000000000000L, 0x0L);
      case 123:
         return jjStopAtPos(0, 72);
      case 124:
         jjmatchedKind = 99;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x400004000000L);
      case 125:
         return jjStopAtPos(0, 73);
      case 126:
         return jjStopAtPos(0, 83);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0, long active1)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active1 & 0x8000000L) != 0L)
            return jjStopAtPos(1, 91);
         break;
      case 43:
         if ((active1 & 0x10000000L) != 0L)
            return jjStopAtPos(1, 92);
         break;
      case 45:
         if ((active1 & 0x20000000L) != 0L)
            return jjStopAtPos(1, 93);
         break;
      case 60:
         if ((active1 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 102;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x2000000000000L);
      case 61:
         if ((active1 & 0x400000L) != 0L)
            return jjStopAtPos(1, 86);
         else if ((active1 & 0x800000L) != 0L)
            return jjStopAtPos(1, 87);
         else if ((active1 & 0x1000000L) != 0L)
            return jjStopAtPos(1, 88);
         else if ((active1 & 0x2000000L) != 0L)
            return jjStopAtPos(1, 89);
         else if ((active1 & 0x20000000000L) != 0L)
            return jjStopAtPos(1, 105);
         else if ((active1 & 0x40000000000L) != 0L)
            return jjStopAtPos(1, 106);
         else if ((active1 & 0x80000000000L) != 0L)
            return jjStopAtPos(1, 107);
         else if ((active1 & 0x100000000000L) != 0L)
            return jjStopAtPos(1, 108);
         else if ((active1 & 0x200000000000L) != 0L)
            return jjStopAtPos(1, 109);
         else if ((active1 & 0x400000000000L) != 0L)
            return jjStopAtPos(1, 110);
         else if ((active1 & 0x800000000000L) != 0L)
            return jjStopAtPos(1, 111);
         else if ((active1 & 0x1000000000000L) != 0L)
            return jjStopAtPos(1, 112);
         break;
      case 62:
         if ((active1 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 103;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0xc010000000000L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x12001006000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x104000080000L, active1, 0L);
      case 102:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(1, 30, 28);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x41c200000008000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x6000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x8410000L, active1, 0L);
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0x180000000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0xe00000000L, active1, 0L);
      case 111:
         if ((active0 & 0x100000L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x300001030260400L, active1, 0L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0xe0060000000800L, active1, 0L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000000000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x888000000000L, active1, 0L);
      case 119:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000000000L, active1, 0L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000L, active1, 0L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000001000L, active1, 0L);
      case 124:
         if ((active1 & 0x4000000L) != 0L)
            return jjStopAtPos(1, 90);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(0, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, active1);
      return 2;
   }
   switch(curChar)
   {
      case 61:
         if ((active1 & 0x2000000000000L) != 0L)
            return jjStopAtPos(2, 113);
         else if ((active1 & 0x4000000000000L) != 0L)
            return jjStopAtPos(2, 114);
         break;
      case 62:
         if ((active1 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 104;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0L, active1, 0x8000000000000L);
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x20400000018000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000000L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x800L, active1, 0L);
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x505020000000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x200008001000000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x2001006060000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x240008000400L, active1, 0L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x800180000000L, active1, 0L);
      case 114:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(2, 28, 28);
         return jjMoveStringLiteralDfa3_0(active0, 0x18000000000000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x200402200L, active1, 0L);
      case 116:
         if ((active0 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 34;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0x102820805000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000200000L, active1, 0L);
      case 119:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 38, 28);
         break;
      case 121:
         if ((active0 & 0x80000000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 55, 28);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0, active1);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(1, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, active1);
      return 3;
   }
   switch(curChar)
   {
      case 61:
         if ((active1 & 0x8000000000000L) != 0L)
            return jjStopAtPos(3, 115);
         break;
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000000e080800L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000004000L, active1, 0L);
      case 100:
         if ((active0 & 0x100000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 56, 28);
         break;
      case 101:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(3, 12, 28);
         else if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(3, 13, 28);
         else if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(3, 22, 28);
         else if ((active0 & 0x40000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 54, 28);
         return jjMoveStringLiteralDfa4_0(active0, 0x800800800000L, active1, 0L);
      case 103:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 36, 28);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000000L, active1, 0L);
      case 107:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000000000L, active1, 0L);
      case 108:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 39, 28);
         return jjMoveStringLiteralDfa4_0(active0, 0x400080080000400L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000000000000L, active1, 0L);
      case 111:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(3, 29, 28);
         return jjMoveStringLiteralDfa4_0(active0, 0x18000100000000L, active1, 0L);
      case 114:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(3, 15, 28);
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000000L, active1, 0L);
      case 115:
         if ((active0 & 0x4000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 50, 28);
         return jjMoveStringLiteralDfa4_0(active0, 0x1030000L, active1, 0L);
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x1440200040200L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000000000L, active1, 0L);
      case 118:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, active1);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(2, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, 0L);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x30200000000L);
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000000000000L);
      case 101:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(4, 24, 28);
         else if ((active0 & 0x400000000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 58, 28);
         return jjMoveStringLiteralDfa5_0(active0, 0x40080000400L);
      case 104:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(4, 14, 28);
         return jjMoveStringLiteralDfa5_0(active0, 0x2000000000000L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x480000040000L);
      case 107:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(4, 11, 28);
         break;
      case 108:
         if ((active0 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 25;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x4200000L);
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x800000L);
      case 114:
         if ((active0 & 0x800000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 47, 28);
         return jjMoveStringLiteralDfa5_0(active0, 0x100900000200L);
      case 115:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(4, 16, 28);
         return jjMoveStringLiteralDfa5_0(active0, 0x20000000000000L);
      case 116:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(4, 17, 28);
         else if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(4, 27, 28);
         else if ((active0 & 0x200000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 45, 28);
         return jjMoveStringLiteralDfa5_0(active0, 0x200000000000000L);
      case 117:
         return jjMoveStringLiteralDfa5_0(active0, 0x80000L);
      case 118:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000000000L);
      case 119:
         if ((active0 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 51;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x10000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0, 0L);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0, 0L);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x600L);
      case 99:
         if ((active0 & 0x80000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 43, 28);
         else if ((active0 & 0x400000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 46, 28);
         return jjMoveStringLiteralDfa6_0(active0, 0x40000000000L);
      case 100:
         return jjMoveStringLiteralDfa6_0(active0, 0x800000L);
      case 101:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(5, 21, 28);
         else if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 37, 28);
         break;
      case 102:
         return jjMoveStringLiteralDfa6_0(active0, 0x800000000L);
      case 103:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000000000L);
      case 104:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 48, 28);
         break;
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x220000000000000L);
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x4080000L);
      case 109:
         return jjMoveStringLiteralDfa6_0(active0, 0x80000000L);
      case 110:
         if ((active0 & 0x100000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 44, 28);
         return jjMoveStringLiteralDfa6_0(active0, 0x200040000L);
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000000000000L);
      case 115:
         if ((active0 & 0x10000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 52, 28);
         break;
      case 116:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(5, 32, 28);
         return jjMoveStringLiteralDfa6_0(active0, 0x20000000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0, 0L);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0, 0L);
      return 6;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x800000000L);
      case 99:
         return jjMoveStringLiteralDfa7_0(active0, 0x200000200L);
      case 101:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 40, 28);
         else if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 41, 28);
         return jjMoveStringLiteralDfa7_0(active0, 0x20000080000000L);
      case 108:
         return jjMoveStringLiteralDfa7_0(active0, 0x200000000000000L);
      case 110:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(6, 10, 28);
         break;
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x2000000000000L);
      case 115:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(6, 23, 28);
         break;
      case 116:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(6, 19, 28);
         return jjMoveStringLiteralDfa7_0(active0, 0x40000000000L);
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x40000L);
      case 121:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(6, 26, 28);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0, 0L);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0, 0L);
      return 7;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x800000000L);
      case 101:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(7, 18, 28);
         else if ((active0 & 0x200000000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 57, 28);
         return jjMoveStringLiteralDfa8_0(active0, 0x40200000000L);
      case 110:
         return jjMoveStringLiteralDfa8_0(active0, 0x22000080000000L);
      case 116:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(7, 9, 28);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0, 0L);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0, 0L);
      return 8;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 42, 28);
         break;
      case 101:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(8, 35, 28);
         break;
      case 105:
         return jjMoveStringLiteralDfa9_0(active0, 0x2000000000000L);
      case 111:
         return jjMoveStringLiteralDfa9_0(active0, 0x200000000L);
      case 116:
         if ((active0 & 0x20000000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 53, 28);
         return jjMoveStringLiteralDfa9_0(active0, 0x80000000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0, 0L);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0, 0L);
      return 9;
   }
   switch(curChar)
   {
      case 102:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(9, 33, 28);
         break;
      case 115:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(9, 31, 28);
         break;
      case 122:
         return jjMoveStringLiteralDfa10_0(active0, 0x2000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0, 0L);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0, 0L);
      return 10;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa11_0(active0, 0x2000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0, 0L);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0, 0L);
      return 11;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x2000000000000L) != 0L)
            return jjStartNfaWithStates_0(11, 49, 28);
         break;
      default :
         break;
   }
   return jjStartNfa_0(10, active0, 0L);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec3 = {
   0x1ff00000fffffffeL, 0xffffffffffffc000L, 0xffffffffL, 0x600000000000000L
};
static final long[] jjbitVec4 = {
   0x0L, 0x0L, 0x0L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec5 = {
   0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec6 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffL, 0x0L
};
static final long[] jjbitVec7 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x0L, 0x0L
};
static final long[] jjbitVec8 = {
   0x3fffffffffffL, 0x0L, 0x0L, 0x0L
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 67;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 49:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(62, 63);
                  else if (curChar == 47)
                     jjCheckNAddStates(0, 2);
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 54;
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(3, 9);
                  else if (curChar == 47)
                     jjAddStates(10, 12);
                  else if (curChar == 36)
                  {
                     if (kind > 67)
                        kind = 67;
                     jjCheckNAdd(28);
                  }
                  else if (curChar == 34)
                     jjCheckNAddStates(13, 15);
                  else if (curChar == 39)
                     jjAddStates(16, 17);
                  else if (curChar == 46)
                     jjCheckNAdd(4);
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 59)
                        kind = 59;
                     jjCheckNAddTwoStates(1, 2);
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 59)
                        kind = 59;
                     jjCheckNAddStates(18, 20);
                  }
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddTwoStates(1, 2);
                  break;
               case 3:
                  if (curChar == 46)
                     jjCheckNAdd(4);
                  break;
               case 4:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddStates(21, 23);
                  break;
               case 6:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(7);
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddTwoStates(7, 8);
                  break;
               case 9:
                  if (curChar == 39)
                     jjAddStates(16, 17);
                  break;
               case 10:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 11:
                  if (curChar == 39 && kind > 65)
                     kind = 65;
                  break;
               case 13:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 14:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(15, 11);
                  break;
               case 15:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 16:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 17:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(15);
                  break;
               case 18:
                  if (curChar == 34)
                     jjCheckNAddStates(13, 15);
                  break;
               case 19:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStates(13, 15);
                  break;
               case 21:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(13, 15);
                  break;
               case 22:
                  if (curChar == 34 && kind > 66)
                     kind = 66;
                  break;
               case 23:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(24, 27);
                  break;
               case 24:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(13, 15);
                  break;
               case 25:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 26:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(24);
                  break;
               case 27:
                  if (curChar != 36)
                     break;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(28);
                  break;
               case 28:
                  if ((0x3ff001000000000L & l) == 0L)
                     break;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(28);
                  break;
               case 29:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(3, 9);
                  break;
               case 30:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(30, 31);
                  break;
               case 31:
                  if (curChar != 46)
                     break;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddStates(28, 30);
                  break;
               case 32:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddStates(28, 30);
                  break;
               case 34:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(35);
                  break;
               case 35:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddTwoStates(35, 8);
                  break;
               case 36:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(36, 37);
                  break;
               case 38:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(39);
                  break;
               case 39:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddTwoStates(39, 8);
                  break;
               case 40:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(31, 33);
                  break;
               case 42:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(43);
                  break;
               case 43:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(43, 8);
                  break;
               case 44:
                  if (curChar != 48)
                     break;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddStates(18, 20);
                  break;
               case 46:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddTwoStates(46, 2);
                  break;
               case 47:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddTwoStates(47, 2);
                  break;
               case 48:
                  if (curChar == 47)
                     jjAddStates(10, 12);
                  break;
               case 50:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 51:
                  if ((0x2400L & l) != 0L && kind > 6)
                     kind = 6;
                  break;
               case 52:
                  if (curChar == 10 && kind > 6)
                     kind = 6;
                  break;
               case 53:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 52;
                  break;
               case 54:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(55, 56);
                  break;
               case 55:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(55, 56);
                  break;
               case 56:
                  if (curChar == 42)
                     jjCheckNAddStates(34, 36);
                  break;
               case 57:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(58, 56);
                  break;
               case 58:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(58, 56);
                  break;
               case 59:
                  if (curChar == 47 && kind > 7)
                     kind = 7;
                  break;
               case 60:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 54;
                  break;
               case 61:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(62, 63);
                  break;
               case 62:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(62, 63);
                  break;
               case 63:
                  if (curChar == 42)
                     jjCheckNAddStates(37, 39);
                  break;
               case 64:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(65, 63);
                  break;
               case 65:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(65, 63);
                  break;
               case 66:
                  if (curChar == 47 && kind > 8)
                     kind = 8;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 28:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(28);
                  break;
               case 2:
                  if ((0x100000001000L & l) != 0L && kind > 59)
                     kind = 59;
                  break;
               case 5:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(40, 41);
                  break;
               case 8:
                  if ((0x5000000050L & l) != 0L && kind > 63)
                     kind = 63;
                  break;
               case 10:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 12:
                  if (curChar == 92)
                     jjAddStates(42, 44);
                  break;
               case 13:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 19:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(13, 15);
                  break;
               case 20:
                  if (curChar == 92)
                     jjAddStates(45, 47);
                  break;
               case 21:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(13, 15);
                  break;
               case 33:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(48, 49);
                  break;
               case 37:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(50, 51);
                  break;
               case 41:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(52, 53);
                  break;
               case 45:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(46);
                  break;
               case 46:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddTwoStates(46, 2);
                  break;
               case 50:
                  jjAddStates(0, 2);
                  break;
               case 55:
                  jjCheckNAddTwoStates(55, 56);
                  break;
               case 57:
               case 58:
                  jjCheckNAddTwoStates(58, 56);
                  break;
               case 62:
                  jjCheckNAddTwoStates(62, 63);
                  break;
               case 64:
               case 65:
                  jjCheckNAddTwoStates(65, 63);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 28:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(28);
                  break;
               case 10:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 19:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(13, 15);
                  break;
               case 50:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(0, 2);
                  break;
               case 55:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(55, 56);
                  break;
               case 57:
               case 58:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(58, 56);
                  break;
               case 62:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(62, 63);
                  break;
               case 64:
               case 65:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(65, 63);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 67 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   50, 51, 53, 30, 31, 36, 37, 40, 41, 8, 49, 60, 61, 19, 20, 22,
   10, 12, 45, 47, 2, 4, 5, 8, 19, 20, 24, 22, 32, 33, 8, 40,
   41, 8, 56, 57, 59, 63, 64, 66, 6, 7, 13, 14, 16, 21, 23, 25,
   34, 35, 38, 39, 42, 43,
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 51:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 61:
         return ((jjbitVec8[i2] & l2) != 0L);
      default :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null,
"\141\142\163\164\162\141\143\164", "\142\157\157\154\145\141\156", "\142\162\145\141\153", "\142\171\164\145",
"\143\141\163\145", "\143\141\164\143\150", "\143\150\141\162", "\143\154\141\163\163",
"\143\157\156\163\164", "\143\157\156\164\151\156\165\145", "\144\145\146\141\165\154\164",
"\144\157", "\144\157\165\142\154\145", "\145\154\163\145",
"\145\170\164\145\156\144\163", "\146\141\154\163\145", "\146\151\156\141\154",
"\146\151\156\141\154\154\171", "\146\154\157\141\164", "\146\157\162", "\147\157\164\157", "\151\146",
"\151\155\160\154\145\155\145\156\164\163", "\151\155\160\157\162\164", "\151\156\163\164\141\156\143\145\157\146",
"\151\156\164", "\151\156\164\145\162\146\141\143\145", "\154\157\156\147",
"\156\141\164\151\166\145", "\156\145\167", "\156\165\154\154", "\160\141\143\153\141\147\145",
"\160\162\151\166\141\164\145", "\160\162\157\164\145\143\164\145\144", "\160\165\142\154\151\143",
"\162\145\164\165\162\156", "\163\150\157\162\164", "\163\164\141\164\151\143", "\163\165\160\145\162",
"\163\167\151\164\143\150", "\163\171\156\143\150\162\157\156\151\172\145\144", "\164\150\151\163",
"\164\150\162\157\167", "\164\150\162\157\167\163", "\164\162\141\156\163\151\145\156\164",
"\164\162\165\145", "\164\162\171", "\166\157\151\144", "\166\157\154\141\164\151\154\145",
"\167\150\151\154\145", null, null, null, null, null, null, null, null, null, null, null, "\50",
"\51", "\173", "\175", "\133", "\135", "\73", "\54", "\56", "\75", "\76", "\74",
"\41", "\176", "\77", "\72", "\75\75", "\74\75", "\76\75", "\41\75", "\174\174",
"\46\46", "\53\53", "\55\55", "\53", "\55", "\52", "\57", "\46", "\174", "\136", "\45",
"\74\74", "\76\76", "\76\76\76", "\53\75", "\55\75", "\52\75", "\57\75", "\46\75",
"\174\75", "\136\75", "\45\75", "\74\74\75", "\76\76\75", "\76\76\76\75", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x8ffffffffffffe01L, 0xfffffffffffceL,
};
static final long[] jjtoSkip = {
   0x1feL, 0x0L,
};
static final long[] jjtoSpecial = {
   0x1c0L, 0x0L,
};
protected JavaCharStream input_stream;
private final int[] jjrounds = new int[67];
private final int[] jjstateSet = new int[134];
protected char curChar;
/** Constructor. */
public ExpressionParserTokenManager(JavaCharStream stream){
   if (JavaCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public ExpressionParserTokenManager(JavaCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 67; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken()
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         matchedToken.specialToken = specialToken;
         return matchedToken;
      }
      else
      {
         if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
         {
            matchedToken = jjFillToken();
            if (specialToken == null)
               specialToken = matchedToken;
            else
            {
               matchedToken.specialToken = specialToken;
               specialToken = (specialToken.next = matchedToken);
            }
         }
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
