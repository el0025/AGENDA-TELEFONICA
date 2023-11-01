import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ContactDirectory directory = new ContactDirectory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Mettre à jour un contact");
            System.out.println("3. Supprimer un contact");
            System.out.println("4. Rechercher par nom");
            System.out.println("5. Rechercher par numéro de téléphone");
            System.out.println("6. Afficher tous les contacts");
            System.out.println("7. Quitter");
            System.out.print("Choisissez une option : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choice) {
                case 1:
                    System.out.print("Nom du contact : ");
                    String name = scanner.nextLine();
                    System.out.print("Numéro de téléphone : ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Adresse e-mail : ");
                    String email = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, email);
                    directory.addContact(newContact);
                    System.out.println("Contact ajouté.");
                    break;
                case 2:
                    System.out.print("Nom du contact à mettre à jour : ");
                    String oldName = scanner.nextLine();
                    Contact existingContact = directory.findContactByName(oldName);
                    if (existingContact != null) {
                        System.out.print("Nouveau nom : ");
                        String newName = scanner.nextLine();
                        System.out.print("Nouveau numéro de téléphone : ");
                        String newPhoneNumber = scanner.nextLine();
                        System.out.print("Nouvelle adresse e-mail : ");
                        String newEmail = scanner.nextLine();
                        Contact updatedContact = new Contact(newName, newPhoneNumber, newEmail);
                        directory.updateContact(oldName, updatedContact);
                        System.out.println("Contact mis à jour.");
                    } else {
                        System.out.println("Contact introuvable.");
                    }
                    break;
                case 3:
                    System.out.print("Nom du contact à supprimer : ");
                    String contactToDelete = scanner.nextLine();
                    directory.deleteContact(contactToDelete);
                    System.out.println("Contact supprimé.");
                    break;
                case 4:
                    System.out.print("Nom du contact à rechercher : ");
                    String searchName = scanner.nextLine();
                    Contact foundContact = directory.findContactByName(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact trouvé : " + foundContact);
                    } else {
                        System.out.println("Contact non trouvé.");
                    }
                    break;
                case 5:
                    System.out.print("Numéro de téléphone à rechercher : ");
                    String searchPhoneNumber = scanner.nextLine();
                    Contact foundByPhone = directory.findContactByPhoneNumber(searchPhoneNumber);
                    if (foundByPhone != null) {
                        System.out.println("Contact trouvé : " + foundByPhone);
                    } else {
                        System.out.println("Contact non trouvé.");
                    }
                    break;
                case 6:
                    System.out.println("Liste des contacts :");
                    directory.displayAllContacts();
                    break;
                case 7:
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
}