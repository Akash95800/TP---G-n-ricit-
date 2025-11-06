package paradigme;

import java.util.*;
import java.util.function.Predicate;

public class TestBibliotheque {
    public static void main(String[] args) {

        // Médias
        Livre l1 = new Livre("1984", 1949, "George Orwell", 350);
        Livre l2 = new Livre("Clean Code", 2008, "Robert C. Martin", 464);
        CD c1 = new CD("Thriller", 1982, "Michael Jackson", 42);
        CD c2 = new CD("Random Access Memories", 2013, "Daft Punk", 74);

        List<Media> medias = new ArrayList<>();
        medias.add(l1);
        medias.add(l2);
        medias.add(c1);
        medias.add(c2);

        // Membres
        Membre m1 = new Membre("Akash", 1);
        Membre m2 = new Membre("Antoine", 2);
        Set<Membre> membres = new HashSet<>();
        membres.add(m1);
        membres.add(m2);

        // Emprunts
        m1.emprunterMedia(l2);
        m1.emprunterMedia(c2);
        m2.emprunterMedia(l1);

        Map<Membre, List<Media>> emprunts = new HashMap<>();
        emprunts.put(m1, m1.getMediasEmpruntes());
        emprunts.put(m2, m2.getMediasEmpruntes());

        // Méthode générique afficherListe
        System.out.println("=== Liste des médias ===");
        afficherListe(medias);

        // Filtrage : médias après 2010
        List<Media> mediasRecents = filtrer(medias, m -> m.getAnneePublication() > 2010);
        System.out.println("\n=== Médias après 2010 ===");
        afficherListe(mediasRecents);

        // Filtrage : membres dont le nom commence par "A"
        List<Membre> membresA = filtrer(new ArrayList<>(membres), m -> m.getNom().startsWith("A"));
        System.out.println("\n=== Membres dont le nom commence par A ===");
        afficherListe(membresA);

        // Tri par année décroissante, puis titre
        medias.sort(Comparator
                .comparing(Media::getAnneePublication).reversed()
                .thenComparing(Media::getTitre));
        System.out.println("\n=== Tri des médias ===");
        afficherListe(medias);

        // Copie générique
        List<Media> copie = new ArrayList<>();
        copierCollection(medias, copie);
        System.out.println("\n=== Copie de la collection ===");
        afficherListe(copie);

        // Médias empruntés sans doublons
        Set<Media> mediasEmpruntes = new HashSet<>();
        for (List<Media> liste : emprunts.values()) {
            mediasEmpruntes.addAll(liste);
        }
        System.out.println("\n=== Médias empruntés sans doublons ===");
        afficherListe(new ArrayList<>(mediasEmpruntes));

        // Filtrage polymorphe : uniquement les livres
        List<Media> livresSeulement = filtrer(medias, m -> m instanceof Livre);
        System.out.println("\n=== Filtrage : uniquement les livres ===");
        afficherListe(livresSeulement);
    }

    // Méthodes génériques
    public static <T> void afficherListe(List<T> liste) {
        liste.forEach(System.out::println);
    }

    public static <T> List<T> filtrer(List<T> liste, Predicate<T> critere) {
        List<T> resultat = new ArrayList<>();
        for (T element : liste) {
            if (critere.test(element)) {
                resultat.add(element);
            }
        }
        return resultat;
    }

    public static <T> void copierCollection(Collection<T> source, Collection<T> destination) {
        destination.addAll(source);
    }
}
