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

import static org.junit.Assert.assertEquals;
import io.github.johardi.travis.language.expression.Between;
import io.github.johardi.travis.language.expression.EqualsTo;
import io.github.johardi.travis.language.expression.GreaterThan;
import io.github.johardi.travis.language.expression.GreaterThanEquals;
import io.github.johardi.travis.language.expression.IExpression;
import io.github.johardi.travis.language.expression.IExpressionVisitor;
import io.github.johardi.travis.language.expression.LessThan;
import io.github.johardi.travis.language.expression.LessThanEquals;
import io.github.johardi.travis.language.expression.Matches;
import io.github.johardi.travis.language.expression.NumericValue;
import io.github.johardi.travis.language.expression.StringValue;
import io.github.johardi.travis.language.expression.WithExpression;
import io.github.johardi.travis.language.model.Find;
import io.github.johardi.travis.language.model.IQueryBodyVisitor;
import io.github.johardi.travis.language.model.IQueryVisitor;
import io.github.johardi.travis.language.model.Query;
import io.github.johardi.travis.language.model.Refine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TinyParserTest
{
   private List<String> mQueries = new ArrayList<String>();
   private List<String> mExpectedResults = new ArrayList<String>();

   @Test
   public void testQuery()
   {
      readQueries("res/examples.tiny.txt");
      
      System.out.println("Found " + mQueries.size() + " queries to test");
      startParsing();
   }

   private void startParsing()
   {
      for (int i = 0; i < mQueries.size(); i++) {
         System.out.println("Testing query #" + (i+1));
         String query = mQueries.get(i);
         TinyParser parser = new TinyParser(query);
         String producedResult = printParserTrace(parser.getQuery()).trim();
         String expectedResult = mExpectedResults.get(i);
         assertEquals(expectedResult, producedResult);
      }
   }

   private void readQueries(String filePath)
   {
      try {
         BufferedReader br = new BufferedReader(new FileReader(filePath));
         String line = "";
         boolean needNewline = false;
         StringBuilder sb = new StringBuilder();
         while ((line = br.readLine()) != null) {
            switch (constant(line)) {
               case 0:
                  if (needNewline) {
                     sb.append("\n");
                  }
                  sb.append(line);
                  needNewline = true;
                  break;
               case 1:
                  mQueries.add(sb.toString().trim());
                  sb.setLength(0);
                  needNewline = false;
                  break;
               case 2:
                  mExpectedResults.add(sb.toString().trim());
                  sb.setLength(0);
                  needNewline = false;
                  break;
            }
         }
         br.close();
      }
      catch (IOException e) {
         System.err.println(e.getMessage());
      }
   }

   private int constant(String line)
   {
      if (line.matches("\\-+")) { // separator between query and expected result
         return 1;
      }
      else if (line.matches("\\=+")) { // end of test case
         return 2;
      }
      else {
         return 0;
      }
   }

   private String printParserTrace(Query query)
   {
      QueryPrinter printer = new QueryPrinter();
      query.accept(printer);
      return printer.getTrace();
   }

   /**
    * A utility class to print the parser trace.
    */
   class QueryPrinter implements IQueryVisitor, IQueryBodyVisitor, IExpressionVisitor
   {
      private StringBuilder mTracer = new StringBuilder();

      public String getTrace()
      {
         return mTracer.toString();
      }

      @Override
      public void visit(Query query)
      {
         query.getFind().accept(this);
         if (query.hasRefines()) {
            for (Refine refine : query.getRefines()) {
               refine.accept(this);
            }
         }
      }

      @Override
      public void visit(Find find)
      {
         mTracer.append("FIND ");
         mTracer.append("(").append(find.getEntity()).append(")");
         mTracer.append("/ENTITY");
      }

      @Override
      public void visit(Refine refine)
      {
         mTracer.append("\n");
         mTracer.append("REFINE ");
         mTracer.append("(").append(refine.getPredicate()).append(")");
         mTracer.append("/PREDICATE ");
         refine.getExpression().accept(this);
      }

      @Override
      public void visit(EqualsTo op)
      {
         if (!op.useRegex()) {
            mTracer.append("(=)/OP ");
         }
         else {
            mTracer.append("(=?)/OP ");
         }
         visitArguments(op.getArguments());
      }

      @Override
      public void visit(LessThan op)
      {
         mTracer.append("(<)/OP ");
         visitArguments(op.getArguments());
      }

      @Override
      public void visit(GreaterThan op)
      {
         mTracer.append("(>)/OP ");
         visitArguments(op.getArguments());
      }

      @Override
      public void visit(LessThanEquals op)
      {
         mTracer.append("(<=)/OP ");
         visitArguments(op.getArguments());
      }

      @Override
      public void visit(GreaterThanEquals op)
      {
         mTracer.append("(>=)/OP ");
         visitArguments(op.getArguments());
      }

      @Override
      public void visit(Matches op)
      {
         mTracer.append("(@@)/OP ");
         visitArguments(op.getArguments());
      }

      @Override
      public void visit(Between op)
      {
         mTracer.append("(between)/OP ");
         visitArguments(op.getArguments());
      }

      @Override
      public void visit(StringValue value)
      {
         mTracer.append("(\"").append(value.getValue()).append("\")");
         mTracer.append("/VALUE");
      }

      @Override
      public void visit(NumericValue value)
      {
         mTracer.append("(").append(value.getValue()).append(")");
         mTracer.append("/VALUE");
      }

      @Override
      public void visit(WithExpression withExpression)
      {
         // TODO Auto-generated method stub
      }

      private void visitArguments(List<IExpression> arguments)
      {
         boolean needSpace = false;
         for (IExpression argument : arguments) {
            if (needSpace) {
               mTracer.append(" ");
            }
            argument.accept(this);
            needSpace = true;
         }
      }
   }
}
