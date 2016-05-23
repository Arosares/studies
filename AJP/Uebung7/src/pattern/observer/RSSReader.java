package pattern.observer;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RSSReader implements Observer {
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO: print all feeds to the console
//		if(arg0 instanceof RSSFeedService){						pull-method
//			NewsService feedService = (RSSFeedService) arg0;
//			for (String feed : feedService.getFeeds()) {
//				System.out.println(feed);
//			}
//		}
		System.out.println(arg1);								//push-method
		
	}

}
