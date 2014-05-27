package io.github.johardi.travis.language.expression;

public interface IExpression
{
   void accept(IExpressionVisitor visitor);
}
