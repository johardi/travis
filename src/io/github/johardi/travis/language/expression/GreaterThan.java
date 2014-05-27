package io.github.johardi.travis.language.expression;

public class GreaterThan extends RelationalOperator
{
   @Override
   public void accept(IExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
