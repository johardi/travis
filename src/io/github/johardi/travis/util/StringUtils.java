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
