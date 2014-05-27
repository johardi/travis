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
