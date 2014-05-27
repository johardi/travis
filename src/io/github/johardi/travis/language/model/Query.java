package io.github.johardi.travis.language.model;

import java.util.ArrayList;
import java.util.List;

public class Query implements IQuery
{
   private Find mFind;
   private List<Refine> mRefineList;

   public void setFind(Find find)
   {
      mFind = find;
   }

   public Find getFind()
   {
      return mFind;
   }

   public void addRefine(Refine refine)
   {
      if (mRefineList == null) {
         mRefineList = new ArrayList<Refine>();
      }
      mRefineList.add(refine);
   }

   public List<Refine> getRefines()
   {
      return mRefineList;
   }

   public boolean hasRefines()
   {
      if (mRefineList == null) {
         return false;
      }
      return (mRefineList.isEmpty()) ? false : true;
   }

   @Override
   public void accept(IQueryVisitor visitor)
   {
      visitor.visit(this);
   }
}
