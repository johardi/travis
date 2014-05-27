package io.github.johardi.travis.language.model;

public class Find implements IQueryBody
{
   private String mEntity;

   public void setEntity(String entity)
   {
      mEntity = entity;
   }

   public String getEntity()
   {
      return mEntity;
   }

   @Override
   public void accept(IQueryBodyVisitor visitor)
   {
      visitor.visit(this);
   }
}
