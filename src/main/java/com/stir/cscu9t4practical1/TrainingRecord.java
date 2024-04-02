// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;




import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public String addEntry(Entry e){
        Entry comparison = lookupEntry(e.getDay(), e.getMonth(), e.getYear(), e.getName());
        if (comparison == null) {
            tr.add(e);
            return "Record added to table.";
        }
        return "Record was not added due to duplicate value already existing.";
   } // addClass
   
   // look up the entry of a given day and month
   public String lookupEntry (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "No entries found";
       StringBuilder str = new StringBuilder();

       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) {
              str.append(current.getEntry());
          }
       }
       if (str.toString().isEmpty()) {
           return result;
       } else {
           return str.toString();
       }
   } // lookupEntry

    public String deleteEntry(int d, int m, int y, String n) {
        String result = "No entry found with provided details.";

        Entry e = lookupEntry(d,m,y,n);

        if (e != null) {
            tr.remove(e);
            result = "Found and deleted entry with provided details.";
        }
        return result;
    }


    public Entry lookupEntry (int d, int m, int y, String n) {

        for (Entry current : tr) {
            if (current.getDay() == d && current.getMonth() == m && current.getYear() == y && n.equals(current.getName())) {
                return current;
            }
        }
        return null;
    } // lookupEntry

    public String[] displayAllElements () {
        ListIterator<Entry> iter = tr.listIterator();
        String[] results = new String[tr.size()];

        if (tr.isEmpty()) {
            return null;
        }

        for (int i = 0; i < tr.size(); i++) {
            Entry current = iter.next();
            results[i] = current.getEntry();
        }

        return results;
    } // lookupEntry
   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord