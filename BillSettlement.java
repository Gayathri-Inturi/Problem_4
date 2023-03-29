
import java.util.*;

public class BillSettlement {
    public static void main(String[] args) {
        Map<String, Integer> bal = new HashMap<>();
        List<Bill> bills = new ArrayList<>();

        // Add bills to the list
        bills.add(new Bill("Lunch", 2000, "Balaji", Arrays.asList("Anand", "Balaji", "Chaitanya", "Divya")));
        bills.add(new Bill("Movie ticket", 1000, "Anand", Arrays.asList("Anand", "Balaji", "Chaitanya", "Divya")));
        bills.add(new Bill("Snacks", 500, "Chaitanya", Arrays.asList("Anand", "Balaji", "Chaitanya")));

        // Calculate balances
        for (Bill bill : bills) {
            String paidBy = bill.getPaidBy();
            int totalAmount = bill.getTotalAmount();
            List<String> sharedBy = bill.getSharedBy();
            int sharePerPerson = totalAmount / sharedBy.size();

            // Add amount to paidBy person
            if (!bal.containsKey(paidBy)) {
                bal.put(paidBy, totalAmount);
            } else {
                bal.put(paidBy, bal.get(paidBy) + totalAmount);
            }

            // Subtract amount from sharedBy persons
            for (String person : sharedBy) {
                if (!person.equals(paidBy)) {
                    if (!bal.containsKey(person)) {
                        bal.put(person, -sharePerPerson);
                    } else {
                        bal.put(person, bal.get(person) - sharePerPerson);
                    }
                }
            }
        }

        // Print balances
        for (Map.Entry<String, Integer> entry : bal.entrySet()) {
            String person = entry.getKey();
            int balance = entry.getValue();
            System.out.println(person + " owes " + (-balance) + " to others.");
        }
    }
}

class Bill {
    private String description;
    private int totalAmount;
    private String paidBy;
    private List<String> sharedBy;

    public Bill(String description, int totalAmount, String paidBy, List<String> sharedBy) {
        this.description = description;
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.sharedBy = sharedBy;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public List<String> getSharedBy() {
        return sharedBy;
    }
}
/*
 Output
 Chaitanya owes 250 to others.
Divya owes 750 to others.
Balaji owes -1584 to others.
Anand owes -334 to others.
 */
