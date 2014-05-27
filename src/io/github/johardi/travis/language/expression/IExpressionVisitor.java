package io.github.johardi.travis.language.expression;

public interface IExpressionVisitor
{
   void visit(EqualsTo op);

   void visit(LessThan op);

   void visit(GreaterThan op);

   void visit(LessThanEquals op);

   void visit(GreaterThanEquals op);

   void visit(Matches op);

   void visit(Between op);

   void visit(StringValue value);

   void visit(NumericValue value);

   void visit(WithExpression withExpression);
}
