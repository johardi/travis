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
