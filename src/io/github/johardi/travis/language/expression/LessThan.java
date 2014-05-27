package io.github.johardi.travis.language.expression;

public class LessThan extends RelationalOperator
{
   @Override
   public void accept(IExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
