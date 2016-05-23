package javadoc;

import java.util.List;

public interface JavaDocExample {

	/**
	 * Iterates through given List {@code contentList} searching for the
	 * String {@code toRemove} that shall be removed, starting at
	 * {@code startIndex}. Null values in the list will be ignored.
	 * 
	 * Postcondition:
	 * <ul>	
	 * 	<li>If the String toRemove is fount in the contentList, it is removed.</li>
	 * 	<li>If no occurences are found, no changes will be made.</li>
	 * </ul>
	 * @param contentList
	 *            The String List in which a String shall be removed. Must not
	 *            be null or empty.
	 * @param toRemove
	 *            the String which gets removed from the List. Must not be null.
	 *            If toRemove is not in contentList, do nothing. The
	 *            String-Index needs to be after startIndex.
	 * @param startIndex
	 *            The Index at which the method starts to search for the String
	 *            toRemove in contentList. 
	 *            Must not be null. 
	 *            Must not be negative. 
	 *            Must not be bigger than size.contentList();
	 * @throws IllegalArgumentException
	 * 				if contentList contains null values
	 */
	void removeOccurrences(List<String> contentList, String toRemove,
			int startIndex);

}
