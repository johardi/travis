package io.github.johardi.travis.language.model;

import io.github.johardi.travis.language.expression.IExpression;

public class Refine implements IQueryBody
{
   private String mPredicate;
   private IExpression mExpression;

   public void setPredicate(String predicate)
   {
      mPredicate = predicate;
   }

   public String getPredicate()
   {
      return mPredicate;
   }

   public void setExpression(IExpression expression)
   {
      mExpression = expression;
   }

   public IExpression getExpression()
   {
      return mExpression;
   }

   @Override
   public void accept(IQueryBodyVisitor visitor)
   {
      visitor.visit(this);
   }
}
