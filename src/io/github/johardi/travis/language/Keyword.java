/*
 * Travis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Travis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Travis. If not, see http://www.gnu.org/licenses/.
 * 
 * Contributors:
 *     Josef Hardi <josef.hardi@gmail.com> - initial API and implementation
 */
package io.github.johardi.travis.language;

import java.util.ArrayList;
import java.util.List;

public final class Keyword
{
   /* Reserved words */

   public static final String FIND = "find";
   public static final String REFINE = "refine";

   /* Expression operators */

   public static final String EQ_SYM = "=";

   public static final String LT_SYM = "<";
   public static final String LESS_THAN = "less than";
   public static final String SMALLER_THAN = "smaller than";
   public static final String SLIGHTER_THAN = "slighter than";
   public static final String SHORTER_THAN = "shorter than";
   public static final String FEWER_THAN = "fewer than";
   public static final String UNDER = "under";
   public static final String BELOW = "below";

   public static final String GT_SYM = ">";
   public static final String GREATER_THAN = "greater than";
   public static final String TALLER_THAN = "taller than";
   public static final String LARGER_THAN = "larger than";
   public static final String BIGGER_THAN = "bigger than";
   public static final String HIGHER_THAN = "higher than";
   public static final String MORE_THAN = "more than";
   public static final String ABOVE = "above";

   public static final String LTE_SYM = "<=";
   public static final String NO_MORE_THAN = "no more than";

   public static final String GTE_SYM = ">=";
   public static final String NO_LESS_THAN = "no less than";

   public static final String BETWEEN = "between";

   public static final String WITH = "with"; // compound operator

   /* Keyword lists */

   public static final List<String> ALL_COMMANDS = new ArrayList<String>();
   static {
      ALL_COMMANDS.add(FIND);
      ALL_COMMANDS.add(REFINE);
   }

   public static final List<String> ALL_OPERATORS = new ArrayList<String>();
   static {
      /*
       * Compound operators
       */
      ALL_OPERATORS.add(WITH);
      
      /*
       * Symbolic operators are on the top of the list
       */
      ALL_OPERATORS.add(LTE_SYM); // composite symbols first, i.e., <=, >=
      ALL_OPERATORS.add(GTE_SYM);
      ALL_OPERATORS.add(EQ_SYM);
      ALL_OPERATORS.add(LT_SYM);
      ALL_OPERATORS.add(GT_SYM);
      
      /*
       * Textual operators are ordered based on frequent usage 
       */
      ALL_OPERATORS.add(NO_MORE_THAN); // composite texts first, i.e., no more than, no less than
      ALL_OPERATORS.add(NO_LESS_THAN);
      ALL_OPERATORS.add(MORE_THAN);
      ALL_OPERATORS.add(LESS_THAN);
      ALL_OPERATORS.add(BETWEEN);
      ALL_OPERATORS.add(UNDER);
      ALL_OPERATORS.add(BELOW);
      ALL_OPERATORS.add(ABOVE);
      ALL_OPERATORS.add(SMALLER_THAN);
      ALL_OPERATORS.add(SLIGHTER_THAN);
      ALL_OPERATORS.add(SHORTER_THAN);
      ALL_OPERATORS.add(FEWER_THAN);
      ALL_OPERATORS.add(GREATER_THAN);
      ALL_OPERATORS.add(TALLER_THAN);
      ALL_OPERATORS.add(LARGER_THAN);
      ALL_OPERATORS.add(BIGGER_THAN);
      ALL_OPERATORS.add(HIGHER_THAN);
   }

   public static final List<String> ALL = new ArrayList<String>();
   static {
      ALL.addAll(ALL_COMMANDS);
      ALL.addAll(ALL_OPERATORS);
   }
}
