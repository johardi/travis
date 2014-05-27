package io.github.johardi.travis.language;

import io.github.johardi.travis.language.model.Query;

public interface IParser
{
   public static final int K_FIND = 1;
   public static final int K_REFINE = 2;
   public static final int K_WITH = 3;

   public static final int OP_EQ = 11;
   public static final int OP_GT = 12;
   public static final int OP_LT = 13;
   public static final int OP_GTE = 14;
   public static final int OP_LTE = 15;
   public static final int OP_BETWEEN = 16;

   public static final int ERR = -1;

   Query getQuery();
}
