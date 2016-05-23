package pattern.observer;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class RSSFeedService extends Observable implements NewsService {
	
	private List<String> feeds;
	
	public RSSFeedService(){
		feeds = new LinkedList<String>();
	}
	
	public List<String> getFeeds(){
		return feeds;
	}
	
	public void addFeed(String feed){
		feeds.add(feed);
		
		 //TODO: notify your observers
		setChanged();
//		notifyObservers();		pull-method
		notifyObservers(feed);	//push-method
	}

}
