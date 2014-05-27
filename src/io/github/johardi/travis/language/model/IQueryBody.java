package io.github.johardi.travis.language.model;

public interface IQueryBody
{
   void accept(IQueryBodyVisitor visitor);
}
