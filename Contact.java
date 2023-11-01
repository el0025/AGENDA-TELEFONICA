import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


    public class Contact {
        private String name;
        private String phoneNumber;
        private String email;

        public Contact(String name, String phoneNumber, String email) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email: " + email;
        }
    }

    class ContactDirectory {
        private List<Contact> contacts;
        private String dataFilePath = "contacts.txt";

        public ContactDirectory() {
            contacts = new ArrayList<>();
            loadContactsFromFile();
        }

        private void loadContactsFromFile() {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(dataFilePath));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] contactData = line.split(",");
                    if (contactData.length == 3) {
                        Contact contact = new Contact(contactData[0], contactData[1], contactData[2]);
                        contacts.add(contact);
                    }
                }

                reader.close();
            } catch (IOException e) {
                // En cas d'erreur de lecture du fichier, par exemple s'il n'existe pas encore
            }
        }

        private void saveContactsToFile() {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(dataFilePath));

                for (Contact contact : contacts) {
                    writer.write(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail());
                    writer.newLine();
                }

                writer.close();
            } catch (IOException e) {
                // Gestion des erreurs d'écriture dans le fichier
            }
        }

        public void addContact(Contact contact) {
            contacts.add(contact);
            saveContactsToFile();
        }

        public void updateContact(String oldName, Contact newContact) {
            for (int i = 0; i < contacts.size(); i++) {
                Contact contact = contacts.get(i);
                if (contact.getName().equals(oldName)) {
                    contacts.set(i, newContact);
                    saveContactsToFile();
                    return;
                }
            }
        }

        public void deleteContact(String name) {
            contacts.removeIf(contact -> contact.getName().equals(name));
            saveContactsToFile();
        }

        public Contact findContactByName(String name) {
            for (Contact contact : contacts) {
                if (contact.getName().equals(name)) {
                    return contact;
                }
            }
            return null;
        }

        public Contact findContactByPhoneNumber(String phoneNumber) {
            for (Contact contact : contacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    return contact;
                }
            }
            return null;
        }

        public void displayAllContacts() {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
