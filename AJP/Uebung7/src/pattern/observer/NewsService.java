package pattern.observer;

import java.util.List;

public interface NewsService {
	
	List<String> getFeeds();

	void addFeed(String feed);
	
}
