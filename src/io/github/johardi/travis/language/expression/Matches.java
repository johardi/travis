package io.github.johardi.travis.language.expression;

public class Matches extends RelationalOperator
{
   @Override
   public void accept(IExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
