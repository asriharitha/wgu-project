import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintDocument {
	
	String documentName;
	long priority;
	
	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public static List<PrintDocument> merge(List<PrintDocument> d1, List<PrintDocument> d2) {
		
		//Combine both the lists to a single list to merge according to the given merge criteria
		List<PrintDocument> docsList = new ArrayList<>();
		docsList.addAll(d1);
		docsList.addAll(d2);
		
		/*             
		 * 
		 *  			1. Creating a custom comparator to sort the list based on the priority of each document.
		 *              2. Apply Group By Collection on the result set of sorting and convert to a Map.
		 *              3. Converting the HashMap to a list.
		 * 
		 * Step 1:
		 * Collections.sort(docsList, new Comparator<PrintDocument>() {
			public int compare(PrintDocument p1, PrintDocument p2) {
					return p1.getPriority() > p2.getPriority() ? 1: p1.getPriority() < p2.getPriority() ? -1 : 0;
			}
		});
		
		Step 2:
		//Used LinkedHashMap to maintain the order after grouping.
		Map<String, List<PrintDocument>> groupList = docsList.stream().collect(Collectors.groupingBy(
																PrintDocument::getDocumentName, () -> new LinkedHashMap<>(), Collectors.toList()));
		
		Step 3:
		List<PrintDocument> resultList = groupList.values().stream().flatMap(Collection::stream).collect(Collectors.toList());*/
		
		//The above 3 steps are combined into a single lambda expression
		List<PrintDocument> resultList = docsList.stream()
												 .sorted((p1,p2)->Long.compare(p1.getPriority(),p2.getPriority()))
												 .collect(Collectors.groupingBy(PrintDocument::getDocumentName, LinkedHashMap::new, Collectors.toList()))
												 .values().stream()
												 .flatMap(Collection::stream)
												 .collect(Collectors.toList());
		
		return resultList;
	}	 
}