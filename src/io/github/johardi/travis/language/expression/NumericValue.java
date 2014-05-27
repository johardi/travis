package io.github.johardi.travis.language.expression;

public class NumericValue extends ValueExpression
{
   @Override
   public void accept(IExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
