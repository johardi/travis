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
package io.github.johardi.travis.language.expression;

public class Between extends RelationalOperator
{
   private IExpression mBetweenStart;
   private IExpression mBetweenEnd;

   public void setBetweenStart(IExpression start)
   {
      mBetweenStart = start;
      mArguments.set(0, start);
   }

   public IExpression getBetweenStart()
   {
      return mBetweenStart;
   }

   public void setBetweenEnd(IExpression end)
   {
      mBetweenEnd = end;
      mArguments.set(1, end);
   }

   public IExpression getBetweenEnd()
   {
      return mBetweenEnd;
   }

   @Override
   public void accept(IExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
