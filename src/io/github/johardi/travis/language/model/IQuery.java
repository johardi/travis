package io.github.johardi.travis.language.model;

public interface IQuery
{
   void accept(IQueryVisitor visitor);
}
