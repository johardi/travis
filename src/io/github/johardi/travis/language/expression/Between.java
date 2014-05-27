package io.github.johardi.travis.language.expression;

public class Between extends RelationalOperator
{
   private IExpression mBetweenStart;
   private IExpression mBetweenEnd;

   public void setBetweenStart(IExpression start)
   {
      mBetweenStart = start;
      mArguments.set(0, start);
   }

   public IExpression getBetweenStart()
   {
      return mBetweenStart;
   }

   public void setBetweenEnd(IExpression end)
   {
      mBetweenEnd = end;
      mArguments.set(1, end);
   }

   public IExpression getBetweenEnd()
   {
      return mBetweenEnd;
   }

   @Override
   public void accept(IExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
