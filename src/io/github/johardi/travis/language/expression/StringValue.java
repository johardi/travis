package io.github.johardi.travis.language.expression;

public class StringValue extends ValueExpression
{
   @Override
   public void accept(IExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
