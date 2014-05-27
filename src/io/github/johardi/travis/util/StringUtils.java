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
package io.github.johardi.travis.util;

import java.util.regex.Pattern;

public class StringUtils
{
   /**
    * System specific line separator character.
    */
   public static final String NEWLINE = System.getProperty("line.separator"); //$NON-NLS-1$

   private static final Pattern containsWhitespacePattern = Pattern.compile(".*\\s.*"); //$NON-NLS-1$
   private static final Pattern isAllWhitespacePattern = Pattern.compile("^\\s*$"); //$NON-NLS-1$

   public static boolean isEmpty(final String text)
   {
      return text == null || text.isEmpty() || isAllWhitespacePattern.matcher(text).matches();
   }

   public static boolean containsWhitespace(final String text)
   {
      if (text == null) {
         return false;
      }
      else {
         return containsWhitespacePattern.matcher(text).matches();
      }
   }

   public static String toLowerCase(final String text)
   {
      return text.toLowerCase();
   }

   public static String toUpperCase(final String text)
   {
      return text.toUpperCase();
   }

   public static String substring(String input, int beginIndex, int endIndex)
   {
      return input.substring(beginIndex, endIndex).trim();
   }

   public static boolean enclosedByQuotes(String input)
   {
      return input.startsWith("\"") && input.endsWith("\""); //$NON-NLS-1$ //$NON-NLS-2$
   }

   public static boolean isNumeric(String input)
   {
      return input.matches("-?\\d+(\\.\\d+)?"); //$NON-NLS-1$
   }
}
