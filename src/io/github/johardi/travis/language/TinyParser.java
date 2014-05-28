/*
 * Travis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Travis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Travis. If not, see http://www.gnu.org/licenses/.
 * 
 * Contributors:
 *     Josef Hardi <josef.hardi@gmail.com> - initial API and implementation
 */
package io.github.johardi.travis.language;

import io.github.johardi.travis.language.expression.Between;
import io.github.johardi.travis.language.expression.EqualsTo;
import io.github.johardi.travis.language.expression.GreaterThan;
import io.github.johardi.travis.language.expression.GreaterThanEquals;
import io.github.johardi.travis.language.expression.LessThan;
import io.github.johardi.travis.language.expression.LessThanEquals;
import io.github.johardi.travis.language.expression.NumericValue;
import io.github.johardi.travis.language.expression.RelationalOperator;
import io.github.johardi.travis.language.expression.StringValue;
import io.github.johardi.travis.language.expression.WithExpression;
import io.github.johardi.travis.language.model.Find;
import io.github.johardi.travis.language.model.Query;
import io.github.johardi.travis.language.model.Refine;
import io.github.johardi.travis.util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class TinyParser implements IParser
{
   private String mInput;
   private String[] mInputLines;

   public TinyParser(String input)
   {
      mInput = input;
      mInputLines = input.split(System.getProperty("line.separator")); //$NON-NLS-1$
   }

   public String getInput()
   {
      return mInput;
   }

   @Override
   public Query getQuery()
   {
      Query query = new Query();
      for (int i = 0; i < mInputLines.length; i++) {
         String stmt = mInputLines[i];
         switch (symbol(stmt)) {
            case K_FIND:
               stmt = StringUtils.truncate(stmt, Keyword.FIND);
               Find find = find(stmt);
               query.setFind(find);
               break;
            case K_REFINE:
               stmt = StringUtils.truncate(stmt, Keyword.REFINE);
               Refine refine = refine(stmt);
               query.addRefine(refine);
               break;
         }
      }
      return query;
   }

   private Find find(String stmt)
   {
      Find find = new Find();
      find.setEntity(entity(stmt));
      return find;
   }

   private String entity(String stmt)
   {
      return stmt;
   }

   private Refine refine(String stmt)
   {
      Refine refine = new Refine();
      
      String op = operator(stmt);
      int opBeginIndex = operatorBeginIndex(stmt, op);
      int opLength = op.length();
      int opEndIndex = opBeginIndex + opLength;
      String predicate = predicate(stmt, opBeginIndex);
      refine.setPredicate(predicate);
      
      if (op.equals(Keyword.WITH)) {
         stmt = StringUtils.substring(stmt, opBeginIndex, stmt.length());
         stmt = StringUtils.truncate(stmt, Keyword.WITH);
         WithExpression with = with(stmt);
         refine.setExpression(with);
      }
      else {
         RelationalOperator operator = opType(op);
         assign(operator, value(stmt, opEndIndex));
         refine.setExpression(operator);
      }
      return refine;
   }

   private WithExpression with(String stmt)
   {
      WithExpression with = new WithExpression();
      
      String op = operator(stmt);
      int opBeginIndex = operatorBeginIndex(stmt, op);
      int opLength = op.length();
      int opEndIndex = opBeginIndex + opLength;
      String predicate = predicate(stmt, opBeginIndex);
      with.setPredicate(predicate);

      RelationalOperator operator = opType(op);
      assign(operator, value(stmt, opEndIndex));
      with.setExpression(operator);
      
      return with;
   }

   private int operatorBeginIndex(String text, String op)
   {
      int opBeginIndex = -1;
      if (op.equals("")) {
         int firstIndexOfQuotes = text.indexOf("\"");
         if (firstIndexOfQuotes != -1) {
            opBeginIndex = firstIndexOfQuotes - 1;
         }
         else {
            opBeginIndex = text.lastIndexOf(" ");
         }
      }
      else {
         opBeginIndex = text.indexOf(op);
      }
      return opBeginIndex;
   }

   private String operator(String stmt)
   {
      for (String op : Keyword.ALL_OPERATORS) {
         if (stmt.contains(op)) {
            return op;
         }
      }
      return ""; //$NON-NLS-1$
   }

   private String predicate(String stmt, int endIndex)
   {
      return StringUtils.substring(stmt, 0, endIndex);
   }

   private String value(String stmt, int beginIndex)
   {
      return StringUtils.substring(stmt, beginIndex, stmt.length());
   }

   private void assign(RelationalOperator op, String value)
   {
      List<String> values = Arrays.asList(value.split("\\s*and\\s*|\\s*,\\s*"));
      for (String v : values) {
         if (StringUtils.enclosedByQuotes(v)) {
            StringValue string = new StringValue();
            string.setValue(v.substring(1, v.length() - 1));
            op.addArgument(string);
         }
         else if (StringUtils.isNumeric(v)) {
            NumericValue numeric = new NumericValue();
            numeric.setValue(v);
            op.addArgument(numeric);
         }
         else {
            StringValue string = new StringValue();
            string.setValue(v);
            op.addArgument(string);
         }
      }
   }

   private RelationalOperator opType(String op)
   {
      switch (symbol(op)) {
         case OP_EQ: return new EqualsTo();
         case OP_LT: return new LessThan();
         case OP_GT: return new GreaterThan();
         case OP_LTE: return new LessThanEquals();
         case OP_GTE: return new GreaterThanEquals();
         case OP_BETWEEN: return new Between();
      }
      throw new RuntimeException("Unknown operator: " + op);
   }

   private int symbol(String input)
   {
      if (input.startsWith(Keyword.FIND)) {
         return K_FIND;
      }
      else if (input.startsWith(Keyword.REFINE)) {
         return K_REFINE;
      }
      else if (input.equals(Keyword.EQ_SYM) || input.equals("")) {
         return OP_EQ;
      }
      else if (input.equals(Keyword.LT_SYM)
            || input.equals(Keyword.LESS_THAN)
            || input.equals(Keyword.SMALLER_THAN)
            || input.equals(Keyword.SLIGHTER_THAN)
            || input.equals(Keyword.SHORTER_THAN)
            || input.equals(Keyword.FEWER_THAN)
            || input.equals(Keyword.UNDER)
            || input.equals(Keyword.BELOW)) {
         return OP_LT;
      }
      else if (input.equals(Keyword.GT_SYM)
            || input.equals(Keyword.GREATER_THAN)
            || input.equals(Keyword.TALLER_THAN)
            || input.equals(Keyword.LARGER_THAN)
            || input.equals(Keyword.BIGGER_THAN)
            || input.equals(Keyword.HIGHER_THAN)
            || input.equals(Keyword.MORE_THAN)
            || input.equals(Keyword.ABOVE)) {
         return OP_GT;
      }
      else if (input.equals(Keyword.LTE_SYM) || input.equals(Keyword.NO_MORE_THAN)) {
         return OP_LTE;
      }
      else if (input.equals(Keyword.GTE_SYM) || input.equals(Keyword.NO_LESS_THAN)) {
         return OP_GTE;
      }
      else if (input.equals(Keyword.BETWEEN)) {
         return OP_BETWEEN;
      }
      return ERR;
   }
}
