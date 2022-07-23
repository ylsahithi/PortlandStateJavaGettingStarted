package edu.pdx.cs410J.lakshmiy;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages
{
    public static String missingRequiredParameter( String parameterName )
    {
        return String.format("The required parameter \"%s\" is missing check", parameterName);
    }

    public static String InvalidinputToPost()
    {
        return String.format("Invalid inputs to add contents to Phone bill");
    }

    public static String SuccessfulPost()
    {
        return String.format("Sucessfully added contents to Phone bill");
    }

    public static String InvalidSearchResult()
    {
        return String.format("Searched for Phone call that doesnot exist");
    }

    public static String definedWordAs(String word, String definition )
    {
        return String.format( "Defined %s as %s", word, definition );
    }

    public static String SearchNocalls()
    {
        return String.format( "There are no calls in searched time period" );
    }
    public static String allDictionaryEntriesDeleted() {
        return "All dictionary entries have been deleted";
    }

}
