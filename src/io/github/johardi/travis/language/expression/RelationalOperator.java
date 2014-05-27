package io.github.johardi.travis.language.expression;

import java.util.ArrayList;
import java.util.List;

public abstract class RelationalOperator implements IExpression
{
   protected List<IExpression> mArguments;

   public void addArgument(IExpression argument)
   {
      if (mArguments == null) {
         mArguments = new ArrayList<IExpression>();
      }
      mArguments.add(argument);
   }

   public List<IExpression> getArguments()
   {
      return mArguments;
   }
}
