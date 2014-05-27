package io.github.johardi.travis.language.expression;

public class WithExpression implements IExpression
{
   @Override
   public void accept(IExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
