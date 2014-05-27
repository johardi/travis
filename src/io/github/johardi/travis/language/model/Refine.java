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
package io.github.johardi.travis.language.model;

import io.github.johardi.travis.language.expression.IExpression;

public class Refine implements IQueryBody
{
   private String mPredicate;
   private IExpression mExpression;

   public void setPredicate(String predicate)
   {
      mPredicate = predicate;
   }

   public String getPredicate()
   {
      return mPredicate;
   }

   public void setExpression(IExpression expression)
   {
      mExpression = expression;
   }

   public IExpression getExpression()
   {
      return mExpression;
   }

   @Override
   public void accept(IQueryBodyVisitor visitor)
   {
      visitor.visit(this);
   }
}
