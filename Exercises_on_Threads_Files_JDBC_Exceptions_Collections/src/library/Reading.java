package library;

import java.util.ArrayList;

public abstract class Reading {

	
	protected String name;
	protected ArrayList<LogEntry> history;
	protected volatile boolean isLent;
	
	
	
	protected abstract ArrayList<LogEntry> getHistory();
	protected abstract boolean isLent();
	protected abstract void view();
	protected abstract String info();
}
