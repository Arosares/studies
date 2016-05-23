package pattern.observer;

public class Main {

	public static void main(String[] args) {
		RSSFeedService subject = new RSSFeedService();
		RSSReader observer = new RSSReader();
		
		//TODO: wire up subject and observer
		subject.addObserver(observer);
		
		
		subject.addFeed("This is the first feed");
		subject.addFeed("Yet another feed");
		subject.addFeed("Woa, I finally understand the observer pattern!");
		
		
	}
}
