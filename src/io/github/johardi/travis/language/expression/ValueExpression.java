package io.github.johardi.travis.language.expression;

public abstract class ValueExpression implements IExpression
{
   private String mValue;

   public void setValue(String value)
   {
      mValue = value;
   }

   public String getValue()
   {
      return mValue;
   }
}
