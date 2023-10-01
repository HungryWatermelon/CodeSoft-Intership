import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookSystem {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private static final String FILE_NAME = "contacts.txt";

    public static void main(String[] args) {
        AddressBookSystem system = new AddressBookSystem();
        Scanner scanner = new Scanner(System.in);

        // Load contact data from the file, if available
        system.loadFromFile(FILE_NAME);

        while (true) {
            System.out.println("\nAddress Book System Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save Data to File");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Contact
                    Contact newContact = system.createContact(scanner);
                    if (newContact != null) {
                        system.addContact(newContact);
                    }
                    break;
                case 2:
                    // Remove Contact
                    System.out.print("Enter the Name to remove a contact: ");
                    String nameToRemove = scanner.nextLine().trim();
                    system.removeContact(nameToRemove);
                    break;
                case 3:
                    // Search Contact
                    System.out.print("Enter the Name to search for a contact: ");
                    String nameToSearch = scanner.nextLine().trim();
                    system.searchContact(nameToSearch);
                    break;
                case 4:
                    // Display All Contacts
                    system.displayAllContacts();
                    break;
                case 5:
                    // Save Data to File
                    system.saveToFile(FILE_NAME);
                    break;
                case 6:
                    // Exit the program
                    System.out.println("Exiting...");
                    system.saveToFile(FILE_NAME); // Save data before exiting
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private Contact createContact(Scanner scanner) {
        System.out.print("Enter Contact Name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return null;
        }

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine().trim();

        if (!phoneNumber.matches("\\d{10}")) {
            System.out.println("Invalid phone number format. Please enter 10 digits.");
            return null;
        }

        System.out.print("Enter Email Address: ");
        String emailAddress = scanner.nextLine().trim();

        if (!emailAddress.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
            System.out.println("Invalid email address format.");
            return null;
        }

        return new Contact(name, phoneNumber, emailAddress);
    }

    private void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully.");
    }

    private void removeContact(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                contacts.remove(i);
                System.out.println("Contact with Name '" + name + "' removed.");
                return;
            }
        }
        System.out.println("Contact with Name '" + name + "' not found.");
    }

    private void searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println("Contact found: " + contact);
                return;
            }
        }
        System.out.println("Contact with Name '" + name + "' not found.");
    }

    private void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
        } else {
            System.out.println("All Contacts:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    private void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmailAddress());
            }
            System.out.println("Contact data saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving contact data: " + e.getMessage());
        }
    }

    private void loadFromFile(String fileName) {
        contacts.clear(); // Clear existing data
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String phoneNumber = parts[1];
                    String emailAddress = parts[2];
                    contacts.add(new Contact(name, phoneNumber, emailAddress));
                }
            }
            System.out.println("Contact data loaded from " + fileName);
        } catch (IOException e) {
            System.err.println("Error loading contact data: " + e.getMessage());
        }
    }
}

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email: " + emailAddress;
    }
}

