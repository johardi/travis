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

import io.github.johardi.travis.util.StringUtils;

public class EqualsTo extends RelationalOperator
{
   private boolean mUseRegex = false;

   @Override
   public void addArgument(IExpression argument)
   {
      if (argument instanceof ValueExpression) {
         ValueExpression expression = (ValueExpression) argument;
         String value = expression.getValue();
         if (value.endsWith("?")) {
            useRegex(true);
            expression.setValue(StringUtils.substring(value, 0, value.length() - 1));
         }
      }
      super.addArgument(argument);
   }

   public void useRegex(boolean useRegex)
   {
      mUseRegex = useRegex;
   }

   public boolean useRegex()
   {
      return mUseRegex;
   }

   @Override
   public void accept(IExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
