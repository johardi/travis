package io.github.johardi.travis.language.model;

public interface IQueryBodyVisitor
{
   void visit(Find find);

   void visit(Refine refine);
}
