package paradigme;

public abstract class Media {
    private String titre;
    private int anneePublication;

    public Media(String titre, int anneePublication) {
        this.titre = titre;
        this.anneePublication = anneePublication;
    }

    public String getTitre() {
        return titre;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return titre + " (" + anneePublication + ")";
    }

    public void afficherDetails() {
        System.out.println(toString() + " - " + getDescription());
    }
}
